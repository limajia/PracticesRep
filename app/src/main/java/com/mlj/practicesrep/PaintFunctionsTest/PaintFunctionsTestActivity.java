package com.mlj.practicesrep.PaintFunctionsTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mlj.practicesrep.R;

public class PaintFunctionsTestActivity extends AppCompatActivity {

    String maskfunction = "setMaskFilter(MaskFilter maskfilter)： 设置MaskFilter，可以用不同的MaskFilter实现滤镜的效果，如滤化，立体等！ 而我们一般不会直接去用这个MaskFilter，而是使用它的两个子类：\n" +
            "\n" +
            "BlurMaskFilter：指定了一个模糊的样式和半径来处理Paint的边缘。\n" +
            "\n" +
            "EmbossMaskFilter：指定了光源的方向和环境光强度来添加浮雕效果。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_functions_test);
    }
}