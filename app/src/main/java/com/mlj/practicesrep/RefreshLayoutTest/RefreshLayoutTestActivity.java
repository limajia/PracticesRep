package com.mlj.practicesrep.RefreshLayoutTest;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mlj.practicesrep.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnMultiListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.simple.SimpleBoundaryDecider;

public class RefreshLayoutTestActivity extends AppCompatActivity {


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTextViewForSwipe;
    private LinearLayout mLlTop;
    private TextView mUseSwipeRefreshLayout;
    private TextView mUseSmartRefreshLayout;
    private SmartRefreshLayout mSmartRefreshLayout;
    private TextView mSmartTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_layout_test);
        initView();
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mTextViewForSwipe = (TextView) findViewById(R.id.textView);

        // 设置刷新监听器
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 在这里执行刷新操作，比如更新数据等
                // 这里简单地模拟刷新结束后停止刷新动画
                mTextViewForSwipe.setText("Refreshing..."); // 显示刷新状态

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 模拟刷新完成后停止刷新动画
                        mSwipeRefreshLayout.setRefreshing(false);
                        mTextViewForSwipe.setText("Refreshed!"); // 显示刷新完成状态
                    }
                }, 3000);
            }
        });
        mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.SmartRefreshLayout);
        mUseSwipeRefreshLayout = (TextView) findViewById(R.id.use_SwipeRefreshLayout);
        mUseSwipeRefreshLayout.setOnClickListener(v -> {
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
            mSmartRefreshLayout.setVisibility(View.GONE);
        });
        mUseSmartRefreshLayout = (TextView) findViewById(R.id.use_SmartRefreshLayout);
        mUseSmartRefreshLayout.setOnClickListener(v -> {
            mSwipeRefreshLayout.setVisibility(View.GONE);
            mSmartRefreshLayout.setVisibility(View.VISIBLE);
        });
        mSmartTextView = (TextView) findViewById(R.id.textView_SmartRefresh);
        mSmartRefreshLayout.setScrollBoundaryDecider(new SimpleBoundaryDecider(){
            @Override
            public boolean canRefresh(View content) {
                return true;
                //return !recyclerView.canScrollVertically(-1)
            }

            @Override
            public boolean canLoadMore(View content) {
                return true;
            }
        });
        mSmartRefreshLayout.setOnMultiListener(new OnMultiListener() {
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

            }

            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {

            }

            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //mSmartTextView.setText("onRefresh");
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //mSmartTextView.setText("onLoadMore");
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                // 在这里执行刷新操作，比如更新数据等
                // 这里简单地模拟刷新结束后停止刷新动画
                mSmartTextView.setText("Refreshing..."); // 显示刷新状态

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 模拟刷新完成后停止刷新动画
                        //mSmartRefreshLayout.finishRefresh();
                        //mSmartRefreshLayout.finishRefresh(false);//貌似没有界面的变化
                        mSmartRefreshLayout.finishRefreshWithNoMoreData();//没有数据.貌似没有界面的变化
                        mSmartTextView.setText("Refreshed!"); // 显示刷新完成状态
                    }
                }, 3000);
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mSmartTextView.setText("loadMore..."); // 显示刷新状态

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //mSmartRefreshLayout.finishLoadMore(false);
                        //mSmartRefreshLayout.resetNoMoreData();//重置没有更多数据状态
                        mSmartRefreshLayout.finishLoadMoreWithNoMoreData();//没有更多数据
                        //mSmartRefreshLayout.finishLoadMore(3000);//延迟3000毫秒后结束加载动画
                        mSmartTextView.setText("LoadMoreFinished!");
                    }
                }, 3000);
            }
        });
    }
}