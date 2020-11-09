package com.mlj.practicesrep.customdialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BaseDialog extends Dialog {

    private Context mContext;

    private OnDialogCreateListener mOnCreateListener;


    public void setOnCreateListener(OnDialogCreateListener onCreateListener) {
        this.mOnCreateListener = onCreateListener;
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        p.width = metrics.widthPixels;
        p.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(p);
        // 解决dialog背景四个直角有黑边问题
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);// 背景透明
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);// 全屏（注：内容区域全屏，有状态显示）
        if (null != mOnCreateListener) {
            mOnCreateListener.onDialogCreate(getWindow());
        }

//        以下是沉浸式显示
        //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间(有效)
//        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//        getWindow().getDecorView().setSystemUiVisibility(option);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.YELLOW);
        //设置导航栏颜
        //内容扩展到导航栏（有效）
//        getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
    }


    public void showDialog() {
        setCanceledOnTouchOutside(true);
        show();
    }

    public interface OnDialogCreateListener {
        void onDialogCreate(Window dialogWindow);
    }
}