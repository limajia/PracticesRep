package com.mlj.practicesrep.hooktest.hook;

import android.widget.Toast;

import com.mlj.practicesrep.App;
import com.mlj.practicesrep.hooktest.error.HookObject;

//extends HookObject 不改变原有的逻辑 装饰模式 相当于一个接口（好理解）
public class HookObjectProxy extends HookObject {

    HookObject hookObject; //原来的错误对象

    public HookObjectProxy(HookObject hookObject) {
        this.hookObject = hookObject;
    }

    //只修复错误的方法  重写 或者 逻辑判断修复 //父类中是default，只能同包（自己.访问）及子类访问(直接访问)
    public void fun() {
        Toast.makeText(App.getApp(), "正确的方法", Toast.LENGTH_SHORT).show();
    }

    public void funExt() {
        Toast.makeText(App.getApp(), "正确的方法", Toast.LENGTH_SHORT).show();
    }

    // 注意
   /* public void 正确方法(){
        hookObject.正确方法();
    }*/
}
