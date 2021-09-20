package com.docker.lib.testrefector;

import java.lang.reflect.InvocationTargetException;

public class TestRefector {

    public static void main(String[] args) {
        Man man = new Man();
        try {
            // 同一个包中直接.class  修改构造函数访问权限的方法  safety security
//          Constructor<?> declaredConstructor = Human.class.getDeclaredConstructors()[0];
//          declaredConstructor.setAccessible(true);

            // 同项目 不同模块 可以使用反射加载对象
            // 不同项目 可以使用classloader。 java虚拟机 bootclassloader和根证书一样，是一个无条件相信的
            // jvm在不同平台是差异的，字节指定不一样，解释出不同平台的机器码
            // 如：实现插件化

            // dex{虚拟机执行文件}文件  odex也是优化后的dex文件  oat：optimized Android file type
            // aot: ahead of time compilecaiton

            //简单的插件化(反射+classloader) 基本雏形 跨应用 跨模块
            //assets:目录中的文件不能直接用？？？ 可以getAsset获取流，然后拷贝， 需要拷贝到缓存
            //DexClassLoader classLoader = new DexClassLoader("可以apk目录，因为包括dex文件"，""，""，"");
            //第二个参数optimizedPath，就是系统自己优化后的要生成odex文件目录
            //classLoader.loadClass("插件中的全路径类文件");

            //问题1：宿主无法打开插件activity，没有清单文件中注册。只是可以简单反射，插件中的类。
            //动态[实时]加载清单文件方案是不可以的，严格控制activity的行为，是一个安全限制。
            //可以在打包过程中修改打包过程合并，但是不是插件化的实施过程。
            //可以让插件为新的Fragment,或者宿主中添加一个壳子activity，
            //startActivity会调用activity中的instrumentation清单中execstartActivity(),可以反射这里的清单，但是反射9.0后更加危险了。
            //问题2：插件中的资源文件无法加载
            // 查看 手写热更新练习

            //插件化=动态（运行时）的添加东西   热更新=动态（运行时）的替换东西

            //aab = Android App Bundles 小包分开下载，不是动态发布部署，而是已经上传好，用户拆分性的下载。

            // 插件话，可以解决 1.一个dex 65535方法问题，多个dex方案。
            // 2.动态部署 lazyLoad [可能加快启动速度]
            // 3.减少初始化安装包的大小。
            // 4.解决组件化 模块化 耦合度过高的问题。
            // 5.热修复 即[在线更新，在线部署]

            Human human = Human.class.getDeclaredConstructor().newInstance();
            System.out.println(human.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //java.lang.NoSuchMethodException: com.docker.lib.testrefector.Human.<init>()
        //	at java.lang.Class.getConstructor0(Class.java:3082)
        //	at java.lang.Class.getDeclaredConstructor(Class.java:2178)
        //	at com.docker.lib.testrefector.TestRefector.main(TestRefector.java:10)
    }
}


class Man implements Human {
    String name = "docker";
}

interface Human {
    int height = 180;
}
