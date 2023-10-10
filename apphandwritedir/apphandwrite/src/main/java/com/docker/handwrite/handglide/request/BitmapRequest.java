package com.docker.handwrite.handglide.request;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.docker.handwrite.handglide.MD5;
import com.docker.handwrite.handglide.manager.RequestManager;

import java.lang.ref.SoftReference;

//1.请求
public class BitmapRequest {

    //1.请求路径
    private String url;

    //2. 上下文管理生命周期
    private Context context;

    //3. 需要加载图片的控件 面向对象 持有要显示的控件
    private SoftReference<ImageView> imageView;

    //4. 占位图片 holdView
    private int resId;

    //5. 回调对象 请求过程监控
    private RequestListener requestListener;

    //6. 图片的唯一标识 Tag
    private String urlMd5;

    public BitmapRequest(Context context) {
        this.context = context;
        // 这里是唯一的 contex 传入的入口 后边管理 转发流程使用
    }

    // 链式调度 流式配置
    // 加载url
    public BitmapRequest load(String url) {
        this.url = url;
        if (!TextUtils.isEmpty(url)) {
            this.urlMd5 = MD5.MD516(url);
        }
        return this;
    }

    // 设置占位图片
    public BitmapRequest loadding(int resId) {
        this.resId = resId;
        return this;
    }

    // 设置监听器
    public BitmapRequest setListener(RequestListener requestListener) {
        this.requestListener = requestListener;
        return this;
    }

    // 显示图片的控件
    public void into(ImageView imageView) {
        // 用来异步显示image判断
        imageView.setTag(urlMd5);
        this.imageView = new SoftReference<>(imageView);
        // 2.加入请求管理
        RequestManager.getInstance(context).addBitmapRequest(this);
    }

    // 暴露访问 配置参数 方法
    public String getUrl() {
        return url;
    }

    public Context getContext() {
        return context;
    }

    public ImageView getImageView() {
        return imageView.get();
    }

    public int getResId() {
        return resId;
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }

    public String getUrlMd5() {
        return urlMd5;
    }
}
