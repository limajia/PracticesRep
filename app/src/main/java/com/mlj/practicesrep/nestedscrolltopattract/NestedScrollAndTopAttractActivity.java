package com.mlj.practicesrep.nestedscrolltopattract;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mlj.practicesrep.R;

public class NestedScrollAndTopAttractActivity extends AppCompatActivity {

    private RecyclerView mRvAppBarData;

//    <!--https://blog.csdn.net/qq_23191031/article/details/56666184--> 不沾满问题
//    https://juejin.cn/post/6844903842543304712 appbarlayout使用配置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_and_top_attract);

        mRvAppBarData = findViewById(R.id.rvAppBarData);
        mRvAppBarData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvAppBarData.setAdapter(new LinearAdapter(NestedScrollAndTopAttractActivity.this));
    }

    public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder> {
        //声明引用
        private Context mContext;
        private LayoutInflater mLayoutInflater;

        //创建一个构造函数
        public LinearAdapter(Context context) {
            this.mContext = context;
            //利用LayoutInflater把控件所在的布局文件加载到当前类当中
            mLayoutInflater = LayoutInflater.from(context);
        }

        //此方法要返回一个ViewHolder
        @Override
        public LinearAdapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new LinearViewHolder(mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        //通过holder设置TextView的内容
        @Override
        public void onBindViewHolder(LinearAdapter.LinearViewHolder holder, int position) {
            holder.textView.setText("Hello Yuan!");
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        class LinearViewHolder extends RecyclerView.ViewHolder {
            //声明layout_linearrv_item布局控件的变量
            private TextView textView;

            public LinearViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }
}