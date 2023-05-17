package com.mlj.practicesrep.RecyclerViewTest;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mlj.practicesrep.R;

// notify 会走scroll回调 RecyclerViewTestActivity.onScrolled dy=0-dx=0
//所以loadmore是没问题的

public class RecyclerViewTestActivity extends AppCompatActivity {

    private RecyclerView mRecyclervew;
    Mydptr adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        initView();
        mRecyclervew.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter = new Mydptr();
        mRecyclervew.setAdapter(adapter);
        mRecyclervew.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                System.out.println("ssssssss12: RecyclerViewTestActivity.onScrollStateChanged");
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                System.out.println("ssssssss12: RecyclerViewTestActivity.onScrolled dy="+dy +"-dx="+dx);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setSize(10);
            }
        },10000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setSize(50);
            }
        },20000);
    }


    class Mydptr extends RecyclerView.Adapter{

        int size = 5;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView inflate = new TextView(parent.getContext());
            inflate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
            inflate.setText("sssfsdfsdfsdfsf");
            RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(inflate) {
            };
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            //((TextView) holder.itemView).setText(position);//findViewById(android.R.id.text1))
        }

        @Override
        public int getItemCount() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
            notifyDataSetChanged();
        }
    }

    private void initView() {
        mRecyclervew = findViewById(R.id.recyclervew);
    }
}