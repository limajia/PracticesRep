###
{
    private View mCustomView;
    private Runnable runnable = new Runnable() {
        int origin = 0;
//        float origin = 1.0f;

        @Override
        public void run() {
            origin += 5;
//            origin *= 1.1f;
  //        mCustomView.setTranslationX(origin++); // mRenderNode 单独的绘制不会触发重新绘制 view相对于parent移动
            mCustomView.scrollBy(10, 0); // 会导致一直onDraw View内部的Text什么的移动
            mCustomView.postDelayed(this, 1000);
        }
    };
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_test);
        mCustomView = findViewById(R.id.custom_view);
        rootView = findViewById(R.id.root_view);
        mCustomView.post(runnable);
    }
}

//貌似动画中都是使用的 setTranslationX 这种形式 mRenderNode都是单独进行渲染的。
//
D/CustomView: onMeasure:
2020-12-31 18:43:08.546 15382-15382/com.mlj.practicesrep D/CustomView: onMeasure:
2020-12-31 18:43:08.547 15382-15382/com.mlj.practicesrep D/CustomViewGroup: onMeasure:
2020-12-31 18:43:08.555 15382-15382/com.mlj.practicesrep D/CustomView: onMeasure:
2020-12-31 18:43:08.555 15382-15382/com.mlj.practicesrep D/CustomView: onMeasure:
2020-12-31 18:43:08.555 15382-15382/com.mlj.practicesrep D/CustomViewGroup: onMeasure:
2020-12-31 18:43:08.557 15382-15382/com.mlj.practicesrep D/CustomView: onLayout:
2020-12-31 18:43:08.557 15382-15382/com.mlj.practicesrep D/CustomViewGroup: onLayout:
2020-12-31 18:43:08.562 15382-15382/com.mlj.practicesrep D/CustomView: onDraw:
2020-12-31 18:43:08.562 15382-15382/com.mlj.practicesrep D/CustomView: onDraw:
2020-12-31 18:43:08.591 15382-15382/com.mlj.practicesrep D/CustomView: onDraw:
2020-12-31 18:43:09.593 15382-15382/com.mlj.practicesrep D/CustomView: onDraw:
2020-12-31 18:43:10.590 15382-15382/com.mlj.practicesrep D/CustomView: onDraw:
2020-12-31 18:43:11.607 15382-15382/com.mlj.practicesrep D/CustomView: onDraw:


getX =  getLeft -getTranslateX
getLeft
getTranslateX  //单独的 不该变left值
getScrollX // 移动内容  和自己的left没有关系，直接影响内部的View的各个属性