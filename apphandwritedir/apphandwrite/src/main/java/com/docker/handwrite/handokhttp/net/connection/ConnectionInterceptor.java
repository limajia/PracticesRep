package com.docker.handwrite.handokhttp.net.connection;


import com.docker.handwrite.handokhttp.net.HttpClient;
import com.docker.handwrite.handokhttp.net.HttpUrl;
import com.docker.handwrite.handokhttp.net.Interceptor;
import com.docker.handwrite.handokhttp.net.Request;
import com.docker.handwrite.handokhttp.net.Response;
import com.docker.handwrite.handokhttp.net.http.InterceptorChain;
import com.docker.handwrite.handokhttp.net.utils.L;

import java.io.IOException;

/**
 * @author : liupu.
 * @date : 2019/1/24.
 */
public class ConnectionInterceptor implements Interceptor {
    @Override
    public Response intercept(InterceptorChain interceptorChain) throws IOException {
        L.i("ConnectionInterceptor");

        Request request = interceptorChain.call().getRequest();
        HttpClient httpClient = interceptorChain.call().getHttpClient();
        HttpUrl httpUrl = request.getHttpUrl();
        HttpConnection httpConnection =
                httpClient.getConnectionPool().getHttpConnection(httpUrl.getHost(), httpUrl.getPort());

        if (null == httpConnection) {
            httpConnection = new HttpConnection();
        }

        httpConnection.setRequest(request);

        try {
            Response response = interceptorChain.proceed(httpConnection);
            if (response.isKeepAlive()) {
                httpClient.getConnectionPool().putHttpConnection(httpConnection);
            } else {
                httpConnection.close();
            }
            return response;
        } catch (IOException e) {
            httpConnection.close();
            throw e;
        }
    }
}
