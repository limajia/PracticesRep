package com.mlj.practicesrep.wrapradiogrouptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mlj.practicesrep.R;

public class WrapperRadioGroupTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapper_radio_group_test);
    }

    //创建了两个 RadioButton，但它们都没有被包含在同一个 RadioGroup 中，因此它们不会相互排斥。RadioGroup 用于确保其中的 RadioButton 是互斥的，
    // 即只能选择其中一个。你需要将这两个 RadioButton 放置在同一个 RadioGroup 下才能实现单选效果。
    //
    //尝试将这两个 RadioButton 包裹在同一个 RadioGroup 内，这样只能选择其中一个。像这样修改代码：

    /*
    <RadioGroup
        android:id="@+id/rg_theme_prayer_theme"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintTop_toTopOf="parent">

        <!-- 第一个 RadioButton -->
        <RadioButton
            android:id="@+id/rb_theme_prayer_theme_healthy"
            android:layout_width="64dp"
            android:layout_height="64dp"
            android:background="@drawable/selector_theme_prayer_theme_healthy"
            android:button="@null" />

        <!-- 第二个 RadioButton -->
        <RadioButton
            android:id="@+id/rb_theme_prayer_theme_emotion"
            android:layout_width="64dp"
            android:layout_height="64dp"
            android:background="@drawable/selector_theme_prayer_theme_healthy"
            android:button="@null" />
    </RadioGroup>
    */

    /*
    实现代码效果使用DrawableTop来实现。
     */
}