package com.docker.handwrite.handglide.diapatcher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.docker.handwrite.handglide.cache.DoubleLruCache;
import com.docker.handwrite.handglide.request.BitmapRequest;
import com.docker.handwrite.handglide.request.RequestListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;

//3.转发 每个银行人员资源 去处理 办业务人员
public class BitmapDispatcher extends Thread {

    Handler handler = new Handler(Looper.getMainLooper());

    // 办业务的人员集合 get过程阻塞线程
    private final LinkedBlockingQueue<BitmapRequest> requestQueue;

    // 三级缓存对象
    private DoubleLruCache doubleLruCache;

    public BitmapDispatcher(LinkedBlockingQueue<BitmapRequest> requestQueue, Context context) {
        this.requestQueue = requestQueue;
        // 缓存对象
        doubleLruCache = new DoubleLruCache(context);
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()) {
            if (requestQueue == null) {
                continue;
            }
            try {
                BitmapRequest bitmapRequest = requestQueue.take();
                if (bitmapRequest == null) {
                    continue;
                }
                // 设置占位图片
                showLoaddingImg(bitmapRequest);
                // 网络加载获取图片资源
                Bitmap bitmap = findBitmap(bitmapRequest);
                // 将图片显示到ImageView
                showImageView(bitmapRequest, bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void showImageView(final BitmapRequest bitmapRequest, final Bitmap bitmap) {
        final ImageView imageView = bitmapRequest.getImageView();
        // 注意这里的判断
        if (bitmap != null && imageView != null && bitmapRequest.getUrlMd5().equals(imageView.getTag())) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    RequestListener requestListener = bitmapRequest.getRequestListener();
                    if (requestListener != null) {
                        requestListener.onSuccess(bitmap);
                    }
                }
            });
        } else {
            RequestListener requestListener = bitmapRequest.getRequestListener();
            if (requestListener != null) {
                requestListener.onFaile();
            }
        }
    }

    private Bitmap findBitmap(BitmapRequest bitmapRequest) {
        // 这里需要通过三级缓存缓存图片
        Bitmap bitmap = null;
        bitmap = doubleLruCache.get(bitmapRequest);
        // 三级缓存中都没有图片的时候去下载
        if (bitmap == null) {
            bitmap = downloadBitmap(bitmapRequest.getUrl());
            // 下载完成后放入三级缓存中
            if (bitmap != null) {
                doubleLruCache.put(bitmapRequest, bitmap);
            }
        }
        return bitmap;
    }

    private void showLoaddingImg(BitmapRequest bitmapRequest) {
        final ImageView imageView = bitmapRequest.getImageView();
        if (bitmapRequest.getResId() > 0 && imageView != null) {
            final int resId = bitmapRequest.getResId();
            // 主线程更新
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(resId);
                    // 最终的更新 都会根据tag是否相同去显示bitmap，且设置tag会早于网络请求。
                }
            });
        }
    }

    private Bitmap downloadBitmap(String uri) {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            // 直接Url 进行下载
            URL url = new URL(uri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            is = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }
}
