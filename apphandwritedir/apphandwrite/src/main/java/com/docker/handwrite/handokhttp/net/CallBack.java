package com.docker.handwrite.handokhttp.net;

/**
 * @author : liupu.
 * @date : 2019/1/22.
 */
public interface CallBack {

    void onFailure(Call call, Throwable throwable);

    void onResponse(Call call, Response response);
}
