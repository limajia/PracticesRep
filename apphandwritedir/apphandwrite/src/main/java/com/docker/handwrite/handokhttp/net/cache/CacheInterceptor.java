package com.docker.handwrite.handokhttp.net.cache;


import com.docker.handwrite.handokhttp.net.Interceptor;
import com.docker.handwrite.handokhttp.net.Response;
import com.docker.handwrite.handokhttp.net.http.InterceptorChain;
import com.docker.handwrite.handokhttp.net.utils.L;

import java.io.IOException;

/**
 * @author : liupu.
 * @date : 2019/1/24.
 */
public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(InterceptorChain interceptorChain) throws IOException {
        L.i("CacheInterceptor");


        return interceptorChain.proceed();
    }
}
