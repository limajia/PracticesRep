package com.mlj.practicesrep.testnovlenoedgescroll;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mlj.practicesrep.R;

import java.util.ArrayList;

//这就是仿照一个手写viewPager实现无限滑动的例子
public class TestNoEdgeScrollActivity extends AppCompatActivity {
    private MyBetterPager mMyPager;
    private ArrayList<View> test = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_no_edge_scroll);
        mMyPager = findViewById(R.id.myViewPager);

        listView = new ListView(mMyPager.getContext());

        ArrayList<Integer> test1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            test1.add(i);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, test1);
        listView.setAdapter(adapter);

        View view0;
        View view1;
        View view2;
        View view3;
        View view4;
        view0 = new View(mMyPager.getContext());
        view0.setBackgroundColor(Color.RED);
        view1 = new View(mMyPager.getContext());
        view1.setBackgroundColor(Color.BLACK);
        view2 = new View(mMyPager.getContext());
        view2.setBackgroundColor(Color.BLUE);
        view3 = new View(mMyPager.getContext());
        view3.setBackgroundColor(Color.YELLOW);
        view4 = new View(mMyPager.getContext());
        view4.setBackgroundColor(Color.GREEN);
        test.add(view0);
        test.add(view1);
        test.add(listView);
        test.add(view2);
        test.add(view3);
        test.add(view4);
        //将view的集合赋值给自定义的viewPager
        mMyPager.setViewList(test);
    }
}