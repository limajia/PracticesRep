package com.mlj.practicesrep.viewpagesetcurrent;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class TestAdapter extends PagerAdapter {
    private int pagerCount = 10;

    public void updateCount() {
        pagerCount = 20;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pagerCount;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        System.out.println("docker11  instantiateItem position =" + position);
        TextView textView = new TextView(container.getContext());
        textView.setText(position + "");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        container.addView(textView);
        return textView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        System.out.println("docker11  destroyItem position =" + position);
        if (object instanceof View) {
            container.removeView((View) object);
        }
    }
}
