package com.mlj.practicesrep.customviewexttest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// 监听自定义控件xml中的子view的几种方式
public class ParentView extends FrameLayout {
    public ParentView(@NonNull Context context) {
        super(context);
    }

    public ParentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //方式一.若该控件上指定了laout可以通过AttributeSet遍历属性的方式，找到layoutid然后自己latoutInflater
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        System.out.println("docker: ParentView.addView(View child) id = "+child.getId());
    }

    @Override
    public void addView(View child, int index) {
        super.addView(child, index);
        System.out.println("docker: ParentView.addView(View child, int index) id = "+child.getId());
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        ////方式二：进行View类型判断，因为LayoutInflater 打布局的时候，也是root后add子view，会调用这个方法
        System.out.println("docker: ParentView.addView(View child, ViewGroup.LayoutParams params)");
    }

    @Override
    public void addView(View child, int width, int height) {
        super.addView(child, width, height);
        System.out.println("docker: ParentView.addView(View child, int width, int height) ");
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        //方式二：进行View类型判断，因为LayoutInflater 打布局的时候，也是root后add子view，会调用这个方法
        System.out.println("docker: ParentView.addView(View child, int index, ViewGroup.LayoutParams params)");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        System.out.println("docker: ParentView.onFinishInflate  childSize= " + getChildCount());
        //方式三：所有view填充完毕之后，会调用这个方法
    }
    //当兄弟层级里面有孩子节点的话，这就无法监听了
}
