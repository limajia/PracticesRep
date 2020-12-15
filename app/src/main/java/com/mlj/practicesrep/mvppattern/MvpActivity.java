package com.mlj.practicesrep.mvppattern;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.mlj.practicesrep.R;
import com.mlj.practicesrep.mvppattern.presenter.MainPresenter;
import com.mlj.practicesrep.mvppattern.view.MainView;

/**
 * @author docker
 */
// TODO: 2020/12/15 声明的泛型 表示的是要对外进行扩展【还有子类需要继承】 接收新的子类 或者 object   类后面的这一堆米有用
public class MvpActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @Override
    protected MainPresenter createPresenter(Context context) {
        return new MainPresenter(context);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        Button add = findViewById(R.id.addButton);
        add.setOnClickListener(v ->
        {// 界面去操作业务
            if (mPresenter.isViewAttached()) {
                mPresenter.addCount();
            }
        });
        Button minus = findViewById(R.id.minusButton);
        minus.setOnClickListener(v ->
        {// 界面去操作业务
            if (mPresenter.isViewAttached()) {
                mPresenter.minusCount();
            }
        });
    }

    @Override
    public void baseViewFunction(String baseString) {
        Toast.makeText(MvpActivity.this, baseString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mainViewFunction(String mainViewString) {
        Toast.makeText(MvpActivity.this, mainViewString, Toast.LENGTH_SHORT).show();
    }
}