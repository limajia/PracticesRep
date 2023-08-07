package com.mlj.practicesrep.beyondoutertest;

import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

//所以这里的验证子view超过父view的onmeasure
//TextureView和SurfaceView实现裁剪是一致的，超过了父层的view的大小，在另外的surface中变形了，类似view的clipChildren属性，被遮挡了。所以meidiaContorller可以放在父view的布局中，不随着surfaceview的大小而变形。
public class BeyondOuterTestActivity extends AppCompatActivity {

    private FrameLayout wrapperview;
    private BeyondView beyondView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beyond_outer_test);
        initView();
    }

    private void initView() {
        wrapperview = (FrameLayout) findViewById(R.id.wrapperview);
        beyondView = (BeyondView) findViewById(R.id.beyondView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("docker123:wrapperview.getWidth() = " + wrapperview.getWidth() + " wrapperview.getHeight() = " + wrapperview.getHeight());
                System.out.println("docker123:beyondView.getWidth() = " + beyondView.getWidth() + " beyondView.getHeight() = " + beyondView.getHeight());
            }
        }, 3000);
    }
}

//补充结论：也不会超过屏幕宽度
//docker123:wrapperview.getWidth() = 720 wrapperview.getHeight() = 1386 //设置wrap_content后的大小
//2023-08-01 18:06:15.940 24016-24016/com.mlj.practicesrep I/System.out: docker123:beyondView.getWidth() = 2000 beyondView.getHeight() = 2000

/*
* 如果surfaceHolder.setFixedSize(800, 800)，而SurfaceView控件的宽高为400,400. 视频画面会如何显示？
*
*如果您调用 `surfaceHolder.setFixedSize(800, 800)`，而 `SurfaceView` 控件的宽度和高度仅为 400x400，那么视频画面会在 `SurfaceView` 内以 400x400 的尺寸进行显示，并且画面将居中显示。在这种情况下，您设置的绘制表面大小 800x800 超过了 `SurfaceView` 控件的实际尺寸，因此画面将被裁剪并只显示中间的 400x400 区域。

在我们之前的示例代码中，我们会通过计算 `newWidth` 和 `newHeight` 来确保绘制表面大小与 `SurfaceView` 控件的宽高比例匹配。但如果设置的绘制表面大小大于 `SurfaceView` 控件的尺寸，画面会被裁剪，显示的部分取决于 `SurfaceView` 控件的大小和 `SurfaceHolder.setFixedSize()` 方法设置的大小之间的差异。

如果要保持视频画面的完整性并且在 `SurfaceView` 控件内完整显示视频，需要确保 `SurfaceView` 控件的尺寸与设置的绘制表面大小匹配。例如，如果您希望视频画面以 800x800 的尺寸在 `SurfaceView` 中显示，请将 `SurfaceView` 的布局尺寸设置为 800x800。

在实际应用中，确保 `SurfaceView` 的尺寸与设置的绘制表面大小匹配，以便视频画面能够正确地填充 `SurfaceView` 控件并保持原始视频的比例。如果尺寸不匹配，可能会导致视频画面被裁剪或拉伸，影响观看体验。
* */