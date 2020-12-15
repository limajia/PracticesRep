package com.mlj.practicesrep.mvppattern.module;

public class MainModuleImpl implements IMainModule {
    int count = 100;

    @Override
    public int add() {
        return ++count;
    }

    @Override
    public int minus() {
        return --count;
    }
}
