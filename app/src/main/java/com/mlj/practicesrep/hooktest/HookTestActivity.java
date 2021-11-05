package com.mlj.practicesrep.hooktest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;
import com.mlj.practicesrep.hooktest.error.HookObject;
import com.mlj.practicesrep.hooktest.error.HookObjectWrapper;
import com.mlj.practicesrep.hooktest.hook.HookObjectProxy;

import java.lang.reflect.Field;

// https://www.jianshu.com/p/74c12164ffca hook 参考博客
public class HookTestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mFixedBefore;
    private Button mFixedAfter;
    private HookObject hookObject;
    private HookObjectWrapper hookObjectWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook_test);
        initView();
        hookObject = new HookObject();
        hookObjectWrapper = new HookObjectWrapper(hookObject);
    }

    private void initView() {
        mFixedBefore = findViewById(R.id.fixed_before);
        mFixedBefore.setOnClickListener(this);
        mFixedAfter = findViewById(R.id.doHook);
        mFixedAfter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fixed_before:
                hookObjectWrapper.doSome();
                break;
            case R.id.doHook:
                doHook();
                break;
        }
    }

    private void doHook() {
        // class 字节码文件是一个唯一的 就是一个结构 类似于类
        // 反射 通过结构 +实例 拿到对象或方法
        try {
            Class<?> aClass = Class.forName("com.mlj.practicesrep.hooktest.error.HookObjectWrapper");
            Field hookObjectFiled = aClass.getDeclaredField("hookObject");
            hookObjectFiled.setAccessible(true); // 拿到结构
            HookObject hookObjectBefore = (HookObject) hookObjectFiled.get(hookObjectWrapper);//传入实例

            HookObjectProxy hookObjectProxy = new HookObjectProxy(hookObjectBefore);
            hookObjectFiled.set(hookObjectWrapper, hookObjectProxy);//使用结构 传入实例 完成替换
            // 第一个参数为 wrapper对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}