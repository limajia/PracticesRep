package com.mlj.practicesrep.snaphelptest;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.mlj.practicesrep.R;

//https://www.jianshu.com/p/069f1ffb81be
//SnapHelper硬核讲解
public class SnapHelpTestActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_help_test);
        initView();
        initGridLayoutManagerRv();
    }

    private void initGridLayoutManagerRv() {
        RecyclerView recyclerViewForGridLayoutManager = findViewById(R.id.recyclerViewForGridLayoutManager);
        recyclerViewForGridLayoutManager.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewForGridLayoutManager.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                TextView inflate = new TextView(parent.getContext());
                inflate.setLayoutParams(new ViewGroup.LayoutParams(400, 100));
                RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(inflate) {
                };
                //获得随机颜色值
                inflate.setBackgroundColor(Color.argb(255, (int) (Math.random() * 255),
                        (int) (Math.random() * 255), (int) (Math.random() * 255)));
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                TextView itemView = (TextView) holder.itemView;
                itemView.setText(position + "");
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Handler().post(() -> {
                            //这里需要延迟一下，不然会出现滑动不到位的情况
                            recyclerViewForGridLayoutManager.smoothScrollToPosition(position);
                        });
                    }
                });
            }

            @Override
            public int getItemCount() {
                return 50;
            }
        });
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        new LinearSnapHelper().attachToRecyclerView(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                TextView inflate = new TextView(parent.getContext());
                inflate.setLayoutParams(new ViewGroup.LayoutParams(400, 100));
                RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(inflate) {
                };
                //获得随机颜色值
                inflate.setBackgroundColor(Color.argb(255, (int) (Math.random() * 255),
                        (int) (Math.random() * 255), (int) (Math.random() * 255)));
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                TextView itemView = (TextView) holder.itemView;
                itemView.setText(position + "");
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Handler().post(() -> {
                            //这里需要延迟一下，不然会出现滑动不到位的情况
                            mRecyclerView.smoothScrollToPosition(position);
                        });
                    }
                });
            }

            @Override
            public int getItemCount() {
                return 50;
            }
        });
    }
}