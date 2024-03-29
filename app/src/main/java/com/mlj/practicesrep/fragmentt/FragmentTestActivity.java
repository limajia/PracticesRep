package com.mlj.practicesrep.fragmentt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mlj.practicesrep.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentTestActivity extends AppCompatActivity {

    private static final String TAG = "FragmentTwo11";

    List<BaseFragment> fragmentList = new ArrayList<BaseFragment>() {
        {
            add(FragmentOne.newInstance("", ""));
            add(FragmentTwo.newInstance("", ""));
        }
    };
    private Button mChangefrag;
    private String mtag = "tagtwo";

    private Button mChangefrag1;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test2);
        // 跳转ViewPager
        View goToViewPager = findViewById(R.id.gotoViewPager);
        goToViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentTestActivity.this, ViewpagerFragmentActivity.class);
                startActivity(intent);
            }
        });
        //
        mChangefrag = findViewById(R.id.changefrag);
        //
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_view, fragmentList.get(1), "tagtwo");
        fragmentTransaction.addToBackStack("two");
        fragmentTransaction.commit();
        //
        mChangefrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.equals(mtag, "tagtwo")) {
                    mtag = "tagone";
                    FragmentManager supportFragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    Fragment tagone = supportFragmentManager.findFragmentByTag(mtag);
                    if (tagone == null) {
                        fragmentTransaction.add(R.id.content_view, fragmentList.get(0), "tagone");
                    } else {
                        fragmentTransaction.show(tagone);
                    }
                    Fragment tagtwo = supportFragmentManager.findFragmentByTag("tagtwo");
                    if (tagtwo != null) {
                        fragmentTransaction.hide(tagtwo);
                    }
                    fragmentTransaction.addToBackStack("one");
                    fragmentTransaction.commit();
                } else {
                    mtag = "tagtwo";
                    FragmentManager supportFragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    Fragment tagtwo = supportFragmentManager.findFragmentByTag(mtag);
                    if (tagtwo == null) {
                        fragmentTransaction.add(R.id.content_view, fragmentList.get(1), "tagtwo").addToBackStack("");
                    } else {
                        fragmentTransaction.show(tagtwo);
                    }
                    Fragment tagone = supportFragmentManager.findFragmentByTag("tagone");
                    if (tagone != null) {
                        fragmentTransaction.hide(tagone);
                    }
                    fragmentTransaction.addToBackStack("null");
                    fragmentTransaction.commit();
                }
            }
        });
        mChangefrag1 = findViewById(R.id.changefrag1);
        mChangefrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_view, fragmentList.get((index++) % 2));
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commitAllowingStateLoss();
            }
        });
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}

//add(add会show展示) show hide 生命周期为
/*
D/FragmentOne: onAttach:
D/FragmentOne: onCreate:
D/FragmentOne: onCreateView:
D/FragmentOne: onViewCreated:
D/FragmentOne: onActivityCreated:
D/FragmentOne: onViewStateRestored:
D/FragmentOne: onStart:
D/FragmentOne: onResume:
D/FragmentTwo: onAttach:
D/FragmentTwo: onCreate:
D/FragmentTwo: onCreateView:
D/FragmentTwo: onViewCreated:
D/FragmentTwo: onActivityCreated:
D/FragmentTwo: onViewStateRestored:
D/FragmentTwo: onStart:
D/FragmentOne: onHiddenChanged() hidden = [true]
D/FragmentTwo: onResume:
D/FragmentOne: onHiddenChanged() hidden = [false]
D/FragmentTwo: onHiddenChanged() hidden = [true]
D/FragmentTwo: onHiddenChanged() hidden = [false]
D/FragmentOne: onHiddenChanged() hidden = [true]
D/FragmentOne: onHiddenChanged() hidden = [false]
D/FragmentTwo: onHiddenChanged() hidden = [true]
*/

//setUserVisibleHint 是和viewpager一起使用的一个hint标识


// replace 生命周期
/*
D/FragmentTwo: onPause:
D/FragmentTwo: onStop:
D/FragmentOne: onAttach:
D/FragmentOne: onCreate:
D/FragmentOne: onCreateView:
D/FragmentOne: onViewCreated:
D/FragmentOne: onActivityCreated:
D/FragmentOne: onViewStateRestored:
D/FragmentOne: onStart:
D/FragmentTwo: onDestroyView:
D/FragmentTwo: onDestroy:
D/FragmentTwo: onDetach:
D/FragmentOne: onResume:
D/FragmentOne: onPause:
D/FragmentOne: onStop:
D/FragmentTwo: onAttach:
D/FragmentTwo: onCreate:
D/FragmentTwo: onCreateView:
D/FragmentTwo: onViewCreated:
D/FragmentTwo: onActivityCreated:
D/FragmentTwo: onViewStateRestored:
D/FragmentTwo: onStart:
D/FragmentOne: onDestroyView:
D/FragmentOne: onDestroy:
D/FragmentOne: onDetach:
D/FragmentTwo: onResume: */


//（注意：加入回退栈的话，不会走onDetach和onAttach方法，但是视图已经分离了）
//addBackToStack 就是记住步骤 然后进行反向操作 https://www.cnblogs.com/jiani/p/11796662.html
//首先fragment事物的提交方式有四种：now的提交，不允许加入到回退栈。
//
///**
//* 摘出其中的关键点
//* it will be scheduled as work on the main thread
//* to be done the next time that thread is ready.
//* 不会立即执行 而是通过调度器有序的在主线程被执行
//* 显然是可以被加入back stack
//**/
// public abstract int commit();
//
///**
//* 该方法是 commit的加强版 支持在activity非活跃状态下提交事物
//**/
//public abstract int commitAllowingStateLoss();
//
///**
//* Transactions committed in this way may not be added to the
//* FragmentManager's back stack
//* 了解到 这个方法 提交的fragment是不会被添加到 FragmentManager's back stack
//**/
//public abstract void commitNow();
//
///**
//* 1类似commitNow();类似到什么程度呢？就是不会把fragment 加入fragment back stack栈。 看来是个加强版
//* 2 the commit can be lost if the activity needs to later be restored from its state,
//* 这个方法可以 在activity状态改变之后使用在activity非active状态的情况下是可以提交 并不会报错的
//* 了解到 这个方法 提交的fragment是不会被添加到 FragmentManager's back stack
//**/
//public abstract void commitNowAllowingStateLoss();/


// activity的不同生命周期函数中添加Fragment 生命周期执行
//在Create中添加fragment
/*      D/FragmentTestActivity: onCreate:
        D/FragmentTwo: onAttach:
        D/FragmentTwo: onCreate:
        D/FragmentTwo: onCreateView:
        D/FragmentTwo: onViewCreated:
        D/FragmentTwo: onActivityCreated:
        D/FragmentTwo: onViewStateRestored:
        D/FragmentTwo: onStart:
        D/FragmentTestActivity: onStart:
        D/FragmentTestActivity: onResume:
        D/FragmentTwo: onResume:
        D/FragmentTwo: onPause:
        D/FragmentTestActivity: onPause:
        D/FragmentTwo: onStop:
        D/FragmentTestActivity: onStop:
        D/FragmentTwo: onDestroyView:
        D/FragmentTwo: onDestroy:
        D/FragmentTwo: onDetach:
        D/FragmentTestActivity: onDestroy:*/

//在Resume中添加fragment
       /* D/FragmentTestActivity: onCreate:
        D/FragmentTestActivity: onStart:
        D/FragmentTestActivity: onResume:
        D/FragmentTwo: onAttach:
        D/FragmentTwo: onCreate:
        D/FragmentTwo: onCreateView: //只是自己的一个一个顺序执行的方法
        D/FragmentTwo: onViewCreated:
        D/FragmentTwo: onActivityCreated:
        D/FragmentTwo: onViewStateRestored:
        D/FragmentTwo: onStart:
        D/FragmentTwo: onResume:
        D/FragmentTwo: onPause:
        D/FragmentTestActivity: onPause:
        D/FragmentTwo: onStop:
        D/FragmentTestActivity: onStop:
        D/FragmentTwo: onDestroyView:
        D/FragmentTwo: onDestroy:
        D/FragmentTwo: onDetach:
        D/FragmentTestActivity: onDestroy:
*/
//创建完成后后面的周期方法就会绑定一起顺序执行
// Home键的时候 APP会走 onPause 和 onStop 生命周期 别让了onStop
