package com.mlj.practicesrep.hooktest.error;


// hook对象的持有者
public class HookObjectWrapper {

    HookObject hookObject; // 内部方法有错误，需要替换这个对象

    public HookObjectWrapper(HookObject hookObject) {
        this.hookObject = hookObject;
    }

    public void doSome() {
        hookObject.funExt();
    }
}
