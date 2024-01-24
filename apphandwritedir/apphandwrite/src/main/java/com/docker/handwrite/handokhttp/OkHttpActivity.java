package com.docker.handwrite.handokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.docker.handwrite.R;

public class OkHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        //equest request = new Request.Builder()
        //                .setHttpUrl("http://gank.io/api/today")
        //                .get()
        //                .build();
        //
        //        HttpClient httpClient = new HttpClient.Builder()
        //                .setRetryTimes(3)
        //                .build();
        //        Call call = httpClient.newCall(request);
        //        call.enqueue(new CallBack() {
        //            @Override
        //            public void onFailure(Call call, Throwable throwable) {
        //
        //            }
        //
        //            @Override
        //            public void onResponse(Call call, Response response) {
        //                L.i("get body = " + response.getBody());
        //            }
        //        });



        //RequestBody requestBody = new RequestBody()
        //                .add("username", "hh123")
        //                .add("password", "123456");
        //        Request request = new Request.Builder()
        //                .setHttpUrl("http://www.wanandroid.com/user/login")
        //                .post(requestBody)
        //                .build();
        //
        //        HttpClient httpClient = new HttpClient.Builder()
        //                .setRetryTimes(3)
        //                .build();
        //        Call call = httpClient.newCall(request);
        //        call.enqueue(new CallBack() {
        //            @Override
        //            public void onFailure(Call call, Throwable throwable) {
        //
        //            }
        //
        //            @Override
        //            public void onResponse(Call call, Response response) {
        //                L.i("post body = " + response.getBody());
        //            }
        //        });
    }
}