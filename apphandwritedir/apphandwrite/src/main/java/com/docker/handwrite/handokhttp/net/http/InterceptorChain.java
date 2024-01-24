package com.docker.handwrite.handokhttp.net.http;


import com.docker.handwrite.handokhttp.net.Call;
import com.docker.handwrite.handokhttp.net.Interceptor;
import com.docker.handwrite.handokhttp.net.Response;
import com.docker.handwrite.handokhttp.net.connection.HttpConnection;

import java.io.IOException;
import java.util.List;

/**
 * @author : liupu.
 * @date : 2019/1/24.
 */
public class InterceptorChain {

    final List<Interceptor> interceptors;
    final int index;
    final Call call;
    HttpConnection httpConnection;


    public InterceptorChain(List<Interceptor> interceptors, int index, Call call, HttpConnection httpConnection) {
        this.interceptors = interceptors;
        this.index = index;
        this.call = call;
        this.httpConnection = httpConnection;
    }

    public Response proceed(HttpConnection httpConnection) throws IOException {
        this.httpConnection = httpConnection;
        return proceed();
    }

    public Response proceed() throws IOException {
        if (index > interceptors.size()) {
            throw new IOException("Interceptor Chain Error");
        }
        Interceptor interceptor = interceptors.get(index);
        InterceptorChain next = new InterceptorChain(interceptors, index + 1, call, httpConnection);
        Response response = interceptor.intercept(next);
        return response;
    }

    public Call call(){
        return call;
    }

    public HttpConnection connection() {
        return httpConnection;
    }
}
