package com.docker.gradlepluginandasm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author docker
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int result = tesStackMethod(1, 11);
    }

    /**
     * 通过字节码插桩技术为该方法编译时期改变.class文件中的此方法添加log日志
     */
    @TestAnnotation(age = 10, name = "dockerOuter")
    public int tesStackMethod(int a, int b) {
        int addResult = a + b;
        return addResult;
    }
    // 这个项目是 gradle插件 + asm实现字节码插桩
    // 没有涉及到 注解处理器 这里只是用到了运行时注解 去插桩
}