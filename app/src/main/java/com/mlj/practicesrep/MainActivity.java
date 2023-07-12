package com.mlj.practicesrep;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mlj.practicesrep.FileProvidertest.FileProviderTestActivity;
import com.mlj.practicesrep.PaintFunctionsTest.PaintFunctionsTestActivity;
import com.mlj.practicesrep.PropertyanimTest.PropertyanimTestActivity;
import com.mlj.practicesrep.RecyclerViewTest.RecyclerViewTestActivity;
import com.mlj.practicesrep.bindertest.client.BinderTestActivity;
import com.mlj.practicesrep.blockcanary.BlockCanaryTestActivity;
import com.mlj.practicesrep.bottomsheet.CustomBottomSheetDialog;
import com.mlj.practicesrep.broadcast.BroadCastActivity;
import com.mlj.practicesrep.configurationtest.ConfigurationTestActivity;
import com.mlj.practicesrep.constraint.ConstraintTestActivity;
import com.mlj.practicesrep.customdialog.CustomDialogActivity;
import com.mlj.practicesrep.customview.CustomViewActivity;
import com.mlj.practicesrep.customviewexttest.CustomViewExtTestActivity;
import com.mlj.practicesrep.drawabletest.DrawableTestActivity;
import com.mlj.practicesrep.floatbtntest.FloatBtnTestActivity;
import com.mlj.practicesrep.floatwindowtest.FloatWidowTestActivity;
import com.mlj.practicesrep.fragmentt.FragmentTestActivity;
import com.mlj.practicesrep.hooktest.HookTestActivity;
import com.mlj.practicesrep.includetest.IncludeTestActivity;
import com.mlj.practicesrep.intentservice.IntentServiceTestActivity;
import com.mlj.practicesrep.leakcanary.LeakCanaryTestActivity;
import com.mlj.practicesrep.lottietest.LottieTestActivity;
import com.mlj.practicesrep.matrixtest.MatrixTestActivity;
import com.mlj.practicesrep.mvppattern.MvpActivity;
import com.mlj.practicesrep.nestedscrolltopattract.NestedScrollAndTopAttractActivity;
import com.mlj.practicesrep.notificationtest.NotificationTestActivity;
import com.mlj.practicesrep.overtransitionanim.TestOverAnimationActivity;
import com.mlj.practicesrep.pagejump.PageJumpTestActivity;
import com.mlj.practicesrep.player.PlayerActivity;
import com.mlj.practicesrep.richtexttest.RichTextTestActivity;
import com.mlj.practicesrep.scrollertest.ScrollerTestActivity;
import com.mlj.practicesrep.sonparnesviewtest.SonParentViewTestActivity;
import com.mlj.practicesrep.soundPoolTest.SoundPoolActivity;
import com.mlj.practicesrep.sqllite.SqlLiteTestTestActivity;
import com.mlj.practicesrep.tabhosttest.TabHostTestActivity;
import com.mlj.practicesrep.testnovlenoedgescroll.TestNoEdgeScrollActivity;
import com.mlj.practicesrep.touchevent.EventActivity;
import com.mlj.practicesrep.translucentTest.TranslucentActivity;
import com.mlj.practicesrep.viewmainfun.TestViewMainFunsActivity;
import com.mlj.practicesrep.viewpage2setcurrent.ViewPager2CurrentPageTestActivity;
import com.mlj.practicesrep.viewpagesetcurrent.ViewPagerCurrentPageTestActivity;
import com.mlj.practicesrep.webview.WebViewTestActivity;

import java.io.File;
import java.io.IOException;

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
        //44 paint_functions_test
        View paint_functions_test = findViewById(R.id.paint_functions_test);
        paint_functions_test.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PaintFunctionsTestActivity.class);
            startActivity(intent);
        });
        //43 drawable_test
        View soundPool_test = findViewById(R.id.soundPool_test);
        soundPool_test.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SoundPoolActivity.class);
            startActivity(intent);
        });
        //42 drawable_test
        View drawable_test = findViewById(R.id.drawable_test);
        drawable_test.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DrawableTestActivity.class);
            startActivity(intent);
        });
        //41 propertyanim_test
        View propertyanim_test = findViewById(R.id.propertyanim_test);
        propertyanim_test.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PropertyanimTestActivity.class);
            startActivity(intent);
        });
        //41recyclerviewtest
        View recyclerview_test = findViewById(R.id.recyclerview_test);
        recyclerview_test.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RecyclerViewTestActivity.class);
            startActivity(intent);
        });
        //40 FloatWidowTest
        View float_window_test = findViewById(R.id.float_window_test);
        float_window_test.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FloatWidowTestActivity.class);
            startActivity(intent);
        });
        //39 file_provider_test
        View file_provider_test = findViewById(R.id.file_provider_test);
        file_provider_test.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FileProviderTestActivity.class);
            startActivity(intent);
        });
        //38 pageJumpTest理解测试
        View page_jump_test = findViewById(R.id.page_jump_test);
        page_jump_test.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PageJumpTestActivity.class);
            startActivity(intent);
        });
        //37 notification_test
        View notification_test = findViewById(R.id.notification_test);
        notification_test.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NotificationTestActivity.class);
            startActivity(intent);
        });
        //36 iew_pager2_current理解测试
        View viewPage2TestBtn = findViewById(R.id.view_pager2_current_test);
        viewPage2TestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewPager2CurrentPageTestActivity.class);
            startActivity(intent);
        });
        //35 no_edge_scroll_test_btn理解测试
        View noEdgeScrollTestBtn = findViewById(R.id.no_edge_scroll_test_btn);
        noEdgeScrollTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TestNoEdgeScrollActivity.class);
            startActivity(intent);
        });
        //34 custom_view_ext_test_btn理解测试
        View customViewExtTestBtn = findViewById(R.id.custom_view_ext_test_btn);
        customViewExtTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CustomViewExtTestActivity.class);
            startActivity(intent);
        });
        //33 constraint测试
        View constraintTestBtn = findViewById(R.id.constraint_test_btn);
        constraintTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ConstraintTestActivity.class);
            startActivity(intent);
        });
        //32 webview测试
        View webviewTestBtn = findViewById(R.id.webview_test_btn);
        webviewTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WebViewTestActivity.class);
            startActivity(intent);
        });
        //31 matrix测试
        View matrixTestBtn = findViewById(R.id.matrix_test_btn);
        matrixTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MatrixTestActivity.class);
            startActivity(intent);
        });
        //30 富文本测试
        View richTextTest = findViewById(R.id.richText_test_btn);
        richTextTest.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RichTextTestActivity.class);
            startActivity(intent);
        });
        // 29 son parents布局测试
        View sonParentTest = findViewById(R.id.sonParentView_test_btn);
        sonParentTest.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SonParentViewTestActivity.class);
            startActivity(intent);
        });
        // 28 viewPagerSetCurrntPage_test_btn测试
        View currentPageBtn = findViewById(R.id.viewPagerSetCurrntPage_test_btn);
        currentPageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewPagerCurrentPageTestActivity.class);
            startActivity(intent);
        });
        // 27 float_test_btn测试
        View floatBtnTestBtn = findViewById(R.id.float_test_btn);
        floatBtnTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FloatBtnTestActivity.class);
            startActivity(intent);
        });
        // 27 Hook测试
        View hookTestBtn = findViewById(R.id.hookTestBtn);
        hookTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HookTestActivity.class);
            startActivity(intent);
        });
        // 26 半透明测试
        View translucentTestBtn = findViewById(R.id.translucentTestBtn);
        translucentTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TranslucentActivity.class);
            startActivity(intent);
        });
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
        //4 ijkplayerTest
        mIjkplayerTestBtn = findViewById(R.id.ijkplayerTestBtn);
        mIjkplayerTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
            startActivity(intent);
        });

        // 5.bottomSheetDialog
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
        // 7 touchEvnetTest事件传递
        View eventTestBtn = findViewById(R.id.touchEvnetTest);
        eventTestBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EventActivity.class);
            startActivity(intent);
        });
        // 8 scrollerTest 滑动帮助类理解
        View scrollerTestBtn = findViewById(R.id.scrollerTest);
        scrollerTestBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ScrollerTestActivity.class);
            startActivity(intent);
        });
        // 9 lottieAnimaTest lottie动画测试
        View lottieAnimaTestBtn = findViewById(R.id.lottieAnimaTest);
        lottieAnimaTestBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LottieTestActivity.class);
            startActivity(intent);
        });
        // 10 logdtest 测试
        View logdTestBtn = findViewById(R.id.logdtest);
        logdTestBtn.setOnClickListener(view -> {
            Log.d("docker11", "输出日志了 ");
            // android 提供的这个工具 在debug 和release 都可以看到日志。应是去除了 打包类型的判断
            // 想要去掉的方式1.在工具类上添加 类型判断进行输出 2.在混淆文件中将日志工具类方法移除掉
            //-assumenosideeffects class android.util.Log{
            //    public static *** v(...);
            //    public static *** i(...);
            //    public static *** d(...);
            //    public static *** w(...);
            //    public static *** e(...);
            //}
            // 参考网址为：https://blog.csdn.net/hp910315/article/details/52488474?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.control
        });
        //11 customView 测试
        View customViewTestBtn = findViewById(R.id.customViewTest);
        customViewTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomViewActivity.class);
                startActivity(intent);
            }
        });
        //12 overAnimationTest 测试
        View overAnimationTestBtn = findViewById(R.id.overAnimationTest);
        overAnimationTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestOverAnimationActivity.class);
                startActivity(intent);
                // 实现淡入浅出的效果
                // overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                // 由左向右滑入的效果
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                // 实现zoommin 和 zoomout (自定义的动画)  MainActivity 会执行R.anim.zoom_out 下一个activity会执行R.anim.zoom_in
                // 只要这一个进入的过程会执行 activity的其他的阶段 不会执行
                // overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
            }
        });
        //13 viewMainFunsTest 测试
        View viewMainFunsTestBtn = findViewById(R.id.viewMainFunsTest);
        viewMainFunsTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestViewMainFunsActivity.class);
                startActivity(intent);
            }
        });
        //14 restartAppTest 测试
        View restartAppTestBtn = findViewById(R.id.restartAppTest);
        restartAppTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getBaseContext().startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        //15 includeTest 测试
        View includeTestBtn = findViewById(R.id.includeTest);
        includeTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IncludeTestActivity.class);
                startActivity(intent);
            }
        });

        //16 clickViewGroupTest 测试
        View tabhostTest = findViewById(R.id.tabHostTest);
        tabhostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabHostTestActivity.class);
                startActivity(intent);
            }
        });

        //17 binderTest
        View binderTest = findViewById(R.id.binderTest);
        binderTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BinderTestActivity.class);
                startActivity(intent);
            }
        });

        //18 asmTest
        View asmTest = findViewById(R.id.asmTest);
        asmTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BinderTestActivity.class);
                startActivity(intent);
            }
        });

        //19 configurationTest 测试旋转屏幕
        View configurationTest = findViewById(R.id.configurationTest);
        configurationTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfigurationTestActivity.class);
                startActivity(intent);
            }
        });
        //20 FragmentTest 测试Fragment的一些属性
        View fragmentTest = findViewById(R.id.fragementTest);
        fragmentTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentTestActivity.class);
                startActivity(intent);
            }
        });
        //21 NestedScrollAndTopAttractTest
        View nestedScrollAndTopAttractTest = findViewById(R.id.NestedScrollAndTopAttractTest);
        nestedScrollAndTopAttractTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NestedScrollAndTopAttractActivity.class);
                startActivity(intent);
            }
        });
        //22 leakCanaryTest
        View leakCanaryTest = findViewById(R.id.leakCanaryTest);
        leakCanaryTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeakCanaryTestActivity.class);
                startActivity(intent);
            }
        });
        //23 blockCanaryTest
        View blockCanaryTest = findViewById(R.id.blockCanaryTest);
        blockCanaryTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BlockCanaryTestActivity.class);
                startActivity(intent);
            }
        });
        //24 intentServiceTest
        View intentServiceTest = findViewById(R.id.intentServiceTest);
        intentServiceTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IntentServiceTestActivity.class);
                startActivity(intent);
            }
        });
        //25 sqlliteTest
        View sqlliteTest = findViewById(R.id.sqlliteTest);
        sqlliteTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SqlLiteTestTestActivity.class);
                startActivity(intent);
            }
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
        //

        int i = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("docker", "权限" + i);
        if (i != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        } else {
            ///https://www.jianshu.com/p/9155a0ff0726
            // Android 10 不能在内存根目录创建文件夹的问题 不同系统不同适配 使用不同的可访问的路径即可 不是非得是同一个目录
//            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            File newFile = new File(getSDPath(MainActivity.this) + "/abc.text");
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getSDPath(Context context) {

       /* File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);// 判断sd卡是否存在
        if (sdCardExist) {
            if (Build.VERSION.SDK_INT >= 29) {
                //Android10之后
                sdDir = context.getExternalFilesDir(null);//获取应用所在根目录/Android/data/your.app.name/file/ 也可以根据沙盒机制传入自己想传的参数，存放在指定目录
            } else {
                sdDir = Environment.getExternalStorageDirectory();// 获取SD卡根目录
            }
        } else {
            sdDir = Environment.getRootDirectory();// 获取跟目录
        }
        return sdDir.toString();*/
        return "";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String absolutePath = "";
        File newFile = new File(absolutePath + "/abc.text");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
//        不要在onResume中申请权限
        //进入页面时会弹出一个权限申请弹框，如果点击允许一切正常，如果点击拒绝，会重新弹出权限申请对话框，一直拒绝的话会一直弹出。如果点击拒绝并且不再提醒，不会继续弹框，但是页面返回按钮不响应，观察日志发现onResume中请求权限的代码循环执行。
        // 拒绝的话会重复执行onpause 和 onresume
        //解决棒法：
//        解决办法：
//        1.使用flag设置标志位，申请权限只执行一次，
//        2.在onStart中请求权限。
        // https://blog.csdn.net/u010457514/article/details/100518834

        //
    }

}