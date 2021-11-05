package com.mlj.practicesrep.hooktest.error;

import android.widget.Toast;

import com.mlj.practicesrep.App;

// hook（偷梁换柱、替换）对象
// 一提到hook 就是找到要替换的对象（不能是方法），所以要找到持有这个对象的外层类，反射外层的类，替换这个对象
public class HookObject {
    void fun() { // 替换成功 重写 也要注意权限
        Toast.makeText(App.getApp(), "错误的方法", Toast.LENGTH_SHORT).show();
    }

    public void funExt() {
        Toast.makeText(App.getApp(), "错误的方法", Toast.LENGTH_SHORT).show();
    }
}
