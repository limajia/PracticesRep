package com.mlj.practicesrep;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mlj.practicesrep.bottomsheet.CustomBottomSheetDialog;
import com.mlj.practicesrep.broadcast.BroadCastActivity;
import com.mlj.practicesrep.customdialog.CustomDialogActivity;
import com.mlj.practicesrep.mvppattern.MvpActivity;
import com.mlj.practicesrep.player.playerActivity;

/**
 * @author docker
 */
public class MainActivity extends AppCompatActivity {

    private Button mAnimationDrawTestBtn;
    private Button mIjkplayerTestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //0
        View root = findViewById(R.id.root_view);
        root.setEnabled(false);
        root.setClickable(false); // 子view不受影响 就是自己不处理onTouchEvent方法事件了 其他的事件照样传递
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        //1.
        View broadCastTestBtn = findViewById(R.id.broadCastTestBtn);
        broadCastTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BroadCastActivity.class);
            startActivity(intent);
        });
        //2.
        View customDialogTestBtn = findViewById(R.id.customDialogTestBtn);
        customDialogTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CustomDialogActivity.class);
            startActivity(intent);
        });
        //3.
        // 75-13975/com.mlj.practicesrep D/docker: 288
        // 2020-11-11 11:13:58.146 13975-13975/com.mlj.practicesrep D/docker----: 288
        mAnimationDrawTestBtn = findViewById(R.id.animationDrawTestBtn);
        mAnimationDrawTestBtn.postDelayed(() -> {
           /* Log.d("docker", mAnimationDrawTestBtn.getTop() + "");
            mAnimationDrawTestBtn.setTranslationY(400);
            Log.d("docker----", mAnimationDrawTestBtn.getTop() + "");*/
        }, 3000);

        // 看来是没有改原来View的属性，单独维护TranslationY值，在绘制的时候，添加这个offset。点击的位置矩阵也跟着变化了。
        // view动画 也是这样的原理，但是没有修改点击的位置矩阵
        // property动画，则同时修改了位置矩阵
        //4
        mIjkplayerTestBtn = findViewById(R.id.ijkplayerTestBtn);
        mIjkplayerTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, playerActivity.class);
            startActivity(intent);
        });

        // 5.
        final View bottomSheetDialogBtn = findViewById(R.id.bottomSheetDialog);
        bottomSheetDialogBtn.setOnClickListener(v -> {
            View view = View.inflate(MainActivity.this, R.layout.view_bottom_sheet_dialog, null);
            RecyclerView recyclerView = view.findViewById(R.id.dialog_recycleView);
            ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
            layoutParams.height = getPeekHeight() - (int) (40 * getResources().getDisplayMetrics().density);
            recyclerView.setLayoutParams(layoutParams);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            mainAdapter = new MainAdapter(R.layout.item_main, titleList);
            recyclerView.setAdapter(new RecyclerView.Adapter() {
                @NonNull
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View inflate = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                    RecyclerView.ViewHolder viewHolder = new MyViewHolder(inflate);
                    return viewHolder;
                }

                @Override
                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                    holder.itemView.setBackgroundColor(Color.RED);
                    RecyclerView.LayoutParams layoutParams1 = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
                    layoutParams1.bottomMargin = 20;
                    holder.itemView.setLayoutParams(layoutParams1);
                    holder.itemView.setOnClickListener(v1 -> {
                        Toast.makeText(holder.itemView.getContext(), "点击了Item", Toast.LENGTH_SHORT).show();
                    });
                }

                @Override
                public int getItemCount() {
                    return 100;
                }
            });

            CustomBottomSheetDialog bottomSheetDialog = new CustomBottomSheetDialog(MainActivity.this, R.style.BottomSheetDialogStyle);
            bottomSheetDialog.setContentView(view);
            BottomSheetBehavior<View> mDialogBehavior = BottomSheetBehavior.from((View) view.getParent());
            mDialogBehavior.setPeekHeight(getPeekHeight());
//            bottomSheetDialog.setDismissWithAnimation(true); exit强制使用下滑消失动画
            bottomSheetDialog.show();
        });

        // 6 mvp设计模式
        View mvpTestBtn = findViewById(R.id.mvpTestBtn);
        mvpTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MvpActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 弹窗高度，默认为屏幕高度的四分之三
     * 子类可重写该方法返回peekHeight
     *
     * @return height
     */
    protected int getPeekHeight() {
        int peekHeight = getResources().getDisplayMetrics().heightPixels;
        //设置弹窗高度为屏幕高度的3/4
        return peekHeight - peekHeight / 3;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("docker", "onPause() called");
        // dialog 不会影响activity的生命周期
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("docker", "onStop() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("docker", "onRestart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("docker", "onResume() called");
    }
}