package com.docker.handwrite.handokhttp.net;


import com.docker.handwrite.handokhttp.net.http.InterceptorChain;

import java.io.IOException;

/**
 * @author : liupu.
 * @date : 2019/1/24.
 */
public interface Interceptor {

    Response intercept(InterceptorChain interceptorChain) throws IOException;
}
