package com.mlj.practicesrep.textviewlayouttest;

import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class TextViewLayoutTestActivity extends AppCompatActivity {

    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_layout_test);
        initView();
    }

    private void initView() {
        mTv1 = findViewById(R.id.tv_1);
        mTv2 = findViewById(R.id.tv_2);
        mTv3 = findViewById(R.id.tv_3);
        mTv4 = findViewById(R.id.tv_4);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("mtv1=" + mTv1.getLayout().toString());
                System.out.println("mtv2=" + mTv2.getLayout().toString());
                System.out.println("mtv3=" + mTv3.getLayout().toString());
                System.out.println("mtv4=" + mTv4.getLayout().toString());

                //mtv1=android.text.BoringLayout@1162ec9
                //mtv2=android.text.StaticLayout@12959ce
                //mtv3=android.text.StaticLayout@b4c30ef
                //mtv4=android.text.BoringLayout@e33d7fc
            }
        }, 2000);
        //关键
        mTv4.setSelected(true);

        //更新一下文本
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mTv1.setText("收到好看好看水电费海口市福克斯首付款上课上收到好看好看水电费海口市福克斯首付款上课上课好看课好看收到好看好看水电费海口市福克斯首付款上课上课好看");
                mTv2.setText("收到好看好看水电费海口市福克斯首付款上课上收到好看好看水电费海口市福克斯首付款上课上课好看课好看收到好看好看水电费海口市福克斯首付款上课上课好看");
                mTv3.setText("mTv3mTv3mTv3mTv3mTv3mTv3mTv3");
                mTv4.setText("huwweyruweruywrywrywehskfhsfhskjfhskfhsksfhksfhskfhskjfhskfhskjhfskeywoworweyroweyrowyrowyoslfsjdlfjslfjslfjsl");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("mtv1=" + mTv1.getLayout().toString());
                        System.out.println("mtv2=" + mTv2.getLayout().toString());
                        System.out.println("mtv3=" + mTv3.getLayout().toString());
                        System.out.println("mtv4=" + mTv4.getLayout().toString());

                        //mtv1 = android.text.BoringLayout @584777d//a.原来的一行的长度
                        //mtv2 = android.text.StaticLayout @d7d2672
                        //mtv3 = android.text.StaticLayout @e2c4ac3
                        //mtv4 = android.text.BoringLayout @d756f40 //d.跑马灯的长度
                        //mtv1 = android.text.BoringLayout @584777d//a.也是一行新的长度,对象没有变，就是有时候不变，咱没有找到规律。
                        //mtv2 = android.text.StaticLayout @d4a4f3b //每次调用setText都会重新生成一个对象，不管文字相同不相同
                        //mtv3 = android.text.BoringLayout @f69e658
                        //mtv4 = android.text.BoringLayout @45613b1 //d.一行的长度
                    }
                }, 2000);
            }
        }, 4000);

        //再次更新一下文本
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder().append("111111111111111111111111").append("22222222222222222222222222").append("333333333333333333333333");//可变的
                mTv1.setText(spannableStringBuilder);
                mTv2.setText(spannableStringBuilder);
                mTv3.setText(spannableStringBuilder);
                SpannableString spannableString = new SpannableString("444444444444444");
                mTv4.setText(spannableString);
                //关键!!!!!!  这是创建DynamicLayout的关键；不管设置是String 还是SpannableString，都会创建DynamicLayout。
                //mTv1.setTextIsSelectable(true);
                //mTv2.setTextIsSelectable(true);
                mTv3.setTextIsSelectable(true);
                mTv4.setTextIsSelectable(true);
                //mtv1=android.text.DynamicLayout@6898da0
                //mtv2=android.text.DynamicLayout@b783d59
                //mtv3=android.text.DynamicLayout@b96d1e
                //mtv4=android.text.DynamicLayout@166c9ff //只要调用settext()就会创建DynamicLayout,应该是在布局或者view属性变化的时候，会自动调整。而StaticLayout是不会自动调整的。

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("mtv1=" + mTv1.getLayout().toString());
                        System.out.println("mtv2=" + mTv2.getLayout().toString());
                        System.out.println("mtv3=" + mTv3.getLayout().toString());
                        System.out.println("mtv4=" + mTv4.getLayout().toString());

                        //如果长度都不换行，则打印结果都是BoringLayout
                        //mtv1=android.text.StaticLayout@e043b46
                        //mtv2=android.text.StaticLayout@c107
                        //mtv3=android.text.StaticLayout@d8c8234
                        //mtv4=android.text.BoringLayout@cb7785d
                    }
                }, 2000);
            }
        }, 8000);

        //再次更新一下View的属性
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams = mTv1.getLayoutParams();
                layoutParams.width = 200;
                mTv1.setLayoutParams(layoutParams);

                ViewGroup.LayoutParams layoutParams2 = mTv2.getLayoutParams();
                layoutParams2.width = 200;
                mTv2.setLayoutParams(layoutParams2);

                ViewGroup.LayoutParams layoutParams3 = mTv3.getLayoutParams();
                layoutParams3.width = 200;
                mTv3.setLayoutParams(layoutParams3);

                ViewGroup.LayoutParams layoutParams4 = mTv4.getLayoutParams();
                layoutParams4.width = 200;
                mTv4.setLayoutParams(layoutParams4);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("mtv1=" + mTv1.getLayout().toString());
                        System.out.println("mtv2=" + mTv2.getLayout().toString());
                        System.out.println("mtv3=" + mTv3.getLayout().toString());
                        System.out.println("mtv4=" + mTv4.getLayout().toString());
                        //也会创建新的Layout对象
                    }
                }, 2000);
            }
        }, 12000);
    }
}

/*
StaticLayout和DynamicLayout区别
官方网解释的是DynamicLayout用于可变字符串，而StaticLayout用于不变的字符串。但是找了DynamicLayout也没有找到类似updateText这样的方法用来更新字符串，重新计算长度的。

于是google到这样一段解释，我觉得可以用来参考。
In order to be updated on text change, DynamicLayout expects Spannable as first parameter.
In that case it creates instance of internal static class DynamicLayout.ChangeWatcher,
and attaches it to the Spannable. The Spannable, in turn, needs to implement Editable in order to be updated.

Example:

SpannableStringBuilder base = new SpannableStringBuilder("a");
DynamicLayout dynamicLayout = new DynamicLayout(base, base, paint, width, Alignment.ALIGN_NORMAL, 1.0, 0, true);
int firstHeight = dynamicLayout.getHeight();
base.append("\nA");
int secondHeight = dynamicLayout.getHeight();
*/
