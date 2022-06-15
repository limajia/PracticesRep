package com.mlj.practicesrep.richtexttest;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

// https://www.jb51.net/article/249372.htm#_lab2_1_1 spannableString基本使用
// https://blog.csdn.net/ITxiaodong/article/details/82229643 spannableString整块删除逻辑
public class RichTextTestActivity extends AppCompatActivity {

    private Button mHtmlBtn;
    private Button mSpanBtn;
    private TextView mPreviewText;
    private EditText mPreviewEditText;
    private View mSpanBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich_text_test);
        initView();
    }

    private void initView() {
        mPreviewEditText = findViewById(R.id.preview_edit_text);
        mPreviewText = findViewById(R.id.preview_text);
        mHtmlBtn = findViewById(R.id.html_btn);
        // 1.占位符的处理 %1，%2为占位符，后面跟随数据类型
        // <string name="string_test_1">学号：%1$d ;姓名：%2$s ;成绩：%3$.2f</string>
        /*String testStr = getResources().getString(R.string.string_test_1);
        String result = String.format(testStr,1001,"张三",9.235);
        System.out.println(result);*/
        // HTML占位符 s%为占位符
       /* <string name="purchase_points">
       <![CDATA[ <font color="#767676">Purchase with</font>
        <font color="#FF5E75">%s</font><font color="#767676"> points?</font>]]>
        </string>*/
       /* String result = String.format(getResources().getString(R.string.purchase_points),formatPoints);
        tv_message.setText(Html.fromHtml(result));*/
        mHtmlBtn.setOnClickListener(v -> {
            String formatHtml = String.format(getString(R.string.html_content), "12334");
            mPreviewText.setText(Html.fromHtml(formatHtml));
            // 注：Html.fromHtml还分Android N的兼容处理，需要传入Model，不同的Model展示的效果有所不同
            mPreviewEditText.setText(Html.fromHtml(formatHtml));
        });

        /*SpannableStringBuilder，SpannableString其实和String⼀样，都是⼀种字符串类型。不同的是SpannableString可以通过使⽤其⽅法
        setSpan⽅法实现字符串各种形式风格的显⽰。⽐如在原来String上加下划线、加背景⾊、改变字体颜⾊、⽤图⽚把指定的⽂字给替换掉，总
        之，SpannableString、SpannableStringBuilder与String⼀样，可以认为是String的升级版。SpannableString与SpannableStringBuilder
        区别就⽐如String和StringBuilder⼀样。*/
        mSpanBtn = findViewById(R.id.span_btn);
        mSpanBtn.setOnClickListener(v -> {
            CharSequence spanString = SpanUtils.getInstance().toColorSpan("的沙发等你发顺丰和烧烤粉红色客服号上岛咖啡和", 10, 20, Color.RED);
            mPreviewText.setText(spanString);
            mPreviewEditText.setText(spanString);
        });
        mSpanBtn1 = findViewById(R.id.span_btn1);
        mSpanBtn1.setOnClickListener(v -> {
            Drawable drawable = getDrawable(R.drawable.block_canary_icon);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            CharSequence spanString = SpanUtils.getInstance().toImageSpan("的沙发等你发顺丰和烧烤粉红色客服号上岛咖啡和", 10, 20, drawable);
            mPreviewText.setText(spanString);
            mPreviewEditText.setText(spanString);
            System.out.println("docker211:" + spanString.length());
            System.out.println("docker211:" + spanString.toString());
        });
        mPreviewEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                System.out.println("docker211: s = " + s + ", start = " + start + ", count = " + count + ", after = " + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("docker211: s = " + s + ", start = " + start + ", before = " + before + ", count = " + count);
                System.out.println("docker985:" + mPreviewEditText.getText().toString());
                System.out.println("docker985:" + mPreviewEditText.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("docker211: s = " + s);
            }
        });
    }
}