package com.mlj.practicesrep.viewpage2setcurrent;

import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.mlj.practicesrep.R;
import com.mlj.practicesrep.viewpagesetcurrent.TestAdapter;

import java.util.Random;

public class ViewPager2CurrentPageTestActivity extends AppCompatActivity {

    private ViewPager2 mTestViewPager2;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2_current_page_test);
        mTestViewPager2 = findViewById(R.id.test_viewPager2);
        adapter = new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                TextView textView = new TextView(parent.getContext());
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(textView) {
                };
                System.out.println("docker111: onCreateViewHolder =" + viewHolder.toString());
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView).setText(position + "");
                System.out.println("docker111: onBindViewHolder = " + position + "--holder =" + holder.toString());
            }

            @Override
            public int getItemCount() {
                return 100;
            }

            @Override
            public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
                super.onViewDetachedFromWindow(holder);
                System.out.println("docker111: onViewDetachedFromWindow= " + ((TextView) holder.itemView).getText() + "---holder =" + holder.toString());
            }

            @Override
            public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
                super.onViewRecycled(holder);
                System.out.println("docker111:onViewRecycled= " + ((TextView) holder.itemView).getText() + "--holder =" + holder.toString());
            }

            @Override
            public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
                super.onViewAttachedToWindow(holder);
                System.out.println("docker111:onViewAttachedToWindow = " + ((TextView) holder.itemView).getText() + "--holder =" + holder.toString());
            }
        };
        mTestViewPager2.setAdapter(adapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mTestViewPager2.setCurrentItem(5);
            }
        }, 3000);

    }
    
    /*
    *  docker111: onCreateViewHolder =ViewHolder{e47f300 position=-1 id=-1, oldPos=-1, pLpos:-1 unbound no parent}
 docker111: onBindViewHolder = 0--holder =ViewHolder{e47f300 position=0 id=-1, oldPos=-1, pLpos:-1 no parent}
 docker111:onViewAttachedToWindow = 0--holder =ViewHolder{e47f300 position=0 id=-1, oldPos=-1, pLpos:-1}
 
 docker111: onCreateViewHolder =ViewHolder{278b4f9 position=-1 id=-1, oldPos=-1, pLpos:-1 unbound no parent}
 docker111: onBindViewHolder = 2--holder =ViewHolder{278b4f9 position=2 id=-1, oldPos=-1, pLpos:-1 no parent}
 docker111:onViewAttachedToWindow = 2--holder =ViewHolder{278b4f9 position=2 id=-1, oldPos=-1, pLpos:-1}
 docker111: onViewDetachedFromWindow= 0---holder =ViewHolder{e47f300 position=0 id=-1, oldPos=-1, pLpos:-1 scrap [attachedScrap] not recyclable(1) no parent}
 
 docker111: onCreateViewHolder =ViewHolder{8d7f7ec position=-1 id=-1, oldPos=-1, pLpos:-1 unbound no parent}
 docker111: onBindViewHolder = 3--holder =ViewHolder{8d7f7ec position=3 id=-1, oldPos=-1, pLpos:-1 no parent}
 docker111:onViewAttachedToWindow = 3--holder =ViewHolder{8d7f7ec position=3 id=-1, oldPos=-1, pLpos:-1}
 
 docker111: onCreateViewHolder =ViewHolder{aecbe4a position=-1 id=-1, oldPos=-1, pLpos:-1 unbound no parent}
 docker111: onBindViewHolder = 4--holder =ViewHolder{aecbe4a position=4 id=-1, oldPos=-1, pLpos:-1 no parent}
 docker111:onViewAttachedToWindow = 4--holder =ViewHolder{aecbe4a position=4 id=-1, oldPos=-1, pLpos:-1}
 
 docker111: onCreateViewHolder =ViewHolder{6470a31 position=-1 id=-1, oldPos=-1, pLpos:-1 unbound no parent}
 docker111: onBindViewHolder = 5--holder =ViewHolder{6470a31 position=5 id=-1, oldPos=-1, pLpos:-1 no parent}
 docker111: onViewDetachedFromWindow= 2---holder =ViewHolder{278b4f9 position=2 id=-1, oldPos=-1, pLpos:-1}
 docker111:onViewAttachedToWindow = 5--holder =ViewHolder{6470a31 position=5 id=-1, oldPos=-1, pLpos:-1}
 
 docker111: onCreateViewHolder =ViewHolder{822be97 position=-1 id=-1, oldPos=-1, pLpos:-1 unbound no parent}
 docker111: onBindViewHolder = 6--holder =ViewHolder{822be97 position=6 id=-1, oldPos=-1, pLpos:-1 no parent}
 docker111: onViewDetachedFromWindow= 3---holder =ViewHolder{8d7f7ec position=3 id=-1, oldPos=-1, pLpos:-1}
 
 docker111:onViewRecycled= 0--holder =ViewHolder{e47f300 position=0 id=-1, oldPos=-1, pLpos:-1 no parent}
 docker111: onViewDetachedFromWindow= 4---holder =ViewHolder{aecbe4a position=4 id=-1, oldPos=-1, pLpos:-1}
 docker111:onViewRecycled= 2--holder =ViewHolder{278b4f9 position=2 id=-1, oldPos=-1, pLpos:-1 no parent}

    * */
}