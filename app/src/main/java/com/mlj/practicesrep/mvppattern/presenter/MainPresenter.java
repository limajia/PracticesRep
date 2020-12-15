package com.mlj.practicesrep.mvppattern.presenter;

import android.content.Context;

import com.mlj.practicesrep.mvppattern.module.IMainModule;
import com.mlj.practicesrep.mvppattern.module.MainModuleImpl;
import com.mlj.practicesrep.mvppattern.view.MainView;

/**
 * 这里应该是抽取出View操作的一个接口
 */
//public class MainPresenter<V extends BaseView> extends BasePresenter<V> {
// 上面这个 不需要对外扩展一层子类 来确定V这个类型了 应该直接在MainPresenter这一层确定 修改一下泛型

public class MainPresenter extends BasePresenter<MainView> {

    IMainModule mainModule;

    public MainPresenter(Context context) {
        super(context);
        mainModule = new MainModuleImpl();
    }

    // BasePresenter 抽取除了 所有公共的绑定及判断View的方法
    // MainPresenter 则只需要书写一下自己的特定的业务方法

    // 具体业务 [操作model等数据]
    public String addCount() {
        //设置到View 由Presenter决定 怎么去绘制界面
        getView().baseViewFunction("add count = " + mainModule.add());
        return "addCount";
    }

    public String minusCount() {
        getView().mainViewFunction("minusCount =" + mainModule.minus());
        return "minusCount";
    }
}
