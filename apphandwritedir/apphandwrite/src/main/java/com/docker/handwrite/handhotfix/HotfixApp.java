package com.docker.handwrite.handhotfix;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

// 插件话 是在某一个特定的点去插功能 不可能做到app中所有的方法位置，都通过反射，替换的方式进行修复。
// 热修复 不能使用新的classloader 修改已经有的loader
// 看到的.class文件能看到代码 是编辑器已经反编译出来的
// 打印找到loader 、pathClassLoader-->BaseDexClassLoader--->ClassLoader 1.[需要看源码 androidxref.com] 2.下载源码 放在sdk目录中
// 可以自定义classLoader
// DexPathList(pathList) ---> Elements(dexElements)
public class HotfixApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        File apk = new File(getCacheDir() + "/hotfix.dex");

        if (apk.exists()) {
            try {
                // 这里例子的原理是：新建classLoader 替换 originalLoader中的Elements 这里展示的方案 必须杀死 参考热修复方案 robust
                ClassLoader originalLoader = getClassLoader();
                // 第一个参数可以传递多目录 "path1:path2:path3"
                DexClassLoader classLoader = new DexClassLoader(apk.getPath(), getCacheDir().getPath(), null, null);
                Class loaderClass = BaseDexClassLoader.class;
                Field pathListField = loaderClass.getDeclaredField("pathList");
                pathListField.setAccessible(true);
                Object pathListObject = pathListField.get(classLoader);

                Class pathListClass = pathListObject.getClass();
                Field dexElementsField = pathListClass.getDeclaredField("dexElements");
                dexElementsField.setAccessible(true);
                Object dexElementsObject = dexElementsField.get(pathListObject);

                Object originalPathListObject = pathListField.get(originalLoader);
                Object originalDexElementsObject = dexElementsField.get(originalPathListObject);

                int oldLength = Array.getLength(originalDexElementsObject);
                int newLength = Array.getLength(dexElementsObject);
                Object concatDexElementsObject = Array.newInstance(dexElementsObject.getClass().getComponentType(), oldLength + newLength);
                for (int i = 0; i < newLength; i++) {
                    Array.set(concatDexElementsObject, i, Array.get(dexElementsObject, i));
                }
                for (int i = 0; i < oldLength; i++) {
                    Array.set(concatDexElementsObject, newLength + i, Array.get(originalDexElementsObject, i));
                }
                // 拼接进去
                dexElementsField.set(originalPathListObject, concatDexElementsObject);

                // originalLoader.pathList.dexElements = classLoader.pathList.dexElements; // 替换Element之后，因为有缓存class 不会生效，重启先加载挂载插件 才可以生效，需要application自动加载
                // originalLoader.pathList.dexElements += classLoader.pathList.dexElements;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
