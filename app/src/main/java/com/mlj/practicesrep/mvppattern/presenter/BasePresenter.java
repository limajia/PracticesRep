package com.mlj.practicesrep.mvppattern.presenter;

import android.content.Context;

import com.mlj.practicesrep.mvppattern.view.BaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

//public abstract class BasePresenter<V> {
//替换为
//public abstract class BasePresenter<V extends BaseView> 这只是一个声明方式 不是给对象赋值的
//假如赋值了 可以直接使用V继承自BaseView的方法

// 类型擦除
//BasePresenter<V extends BaseView>  外界不声明的话 T擦除为BaseView
//BasePresenter<V>                   外界不声明的话 T擦除为Object
//BasePresenter<V>                   外界声明的话 T就是外界声明的类型
public abstract class BasePresenter<V extends BaseView> {

    private Context context;

    public BasePresenter(Context context) {
        this.context = context;
    }

    /**
     * View 接口弱引用 防止内存泄漏
     */
    protected Reference<V> mViewReference;

    public void attachView(V view) {
        mViewReference = new WeakReference<>(view);
    }

    /**
     * 返回view接口对象
     *
     * @return
     */
    protected V getView() {
        return mViewReference.get();
    }

    public boolean isViewAttached() {
        return mViewReference != null && mViewReference.get() != null;
    }

    public void detachView() {
        if (mViewReference != null) {
            mViewReference.clear(); // 解除关联 防止内存泄漏
            mViewReference = null;
        }
    }
}
