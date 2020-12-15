package com.mlj.practicesrep.mvppattern;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.mvppattern.presenter.BasePresenter;
import com.mlj.practicesrep.mvppattern.view.BaseView;

// 泛型类型边界 子类前传 如：父类已经限定 v extends BaseView，子类则也必须限定值。 否则没有限定边界 都是V
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity
        implements BaseView {

    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter(getBaseContext());
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
            // 这里几类可以不实现接口，
            // 但是子类必须实现接口，最好实现BaseView防止类型擦除时候的问题。
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            if (mPresenter.isViewAttached()) {
                mPresenter.detachView();
            }
        }
    }

    protected abstract P createPresenter(Context context);
}
