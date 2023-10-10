package com.docker.handwrite.handglide;

import android.content.Context;

import com.docker.handwrite.handglide.request.BitmapRequest;

//入口 builder模式
public class Glide {
    // 请求 管理 转发

    //创建请求  参数从入口 传到各个请求过程的对象中去
    public static BitmapRequest with(Context context) {
        return new BitmapRequest(context);
    }
}
