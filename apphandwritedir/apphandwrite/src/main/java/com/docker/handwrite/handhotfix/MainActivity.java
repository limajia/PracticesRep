package com.docker.handwrite.handhotfix;

import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

public class MainActivity extends AppCompatActivity {
  TextView titleTv;
  Button showTitleBt;
  Button hotfixBt;
  Button removeHotfixBt;
  Button killSelfBt;

  File apk;
  // 使用d8工具 将单独的class文件 打包成dex文件 然后合并进去
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);
//
//    titleTv = findViewById(R.id.titleTv);
//    showTitleBt = findViewById(R.id.showTitleBt);
//    hotfixBt = findViewById(R.id.hotfixBt);
//    removeHotfixBt = findViewById(R.id.removeHotfixBt);
//    killSelfBt = findViewById(R.id.killSelfBt);

    apk = new File(getCacheDir() + "/hotfix.dex");

    View.OnClickListener onClickListener = new View.OnClickListener() {
      @Override
      public void onClick(final View v) {
        switch (v.getId()) {
          /*case R.id.loadPluginBt:
            File apk = new File(getCacheDir() + "/plugin.apk");
            if (!apk.exists()) {
              try (Source source = Okio.source(getAssets().open("apk/plugin.apk"));
                   BufferedSink sink = Okio.buffer(Okio.sink(apk))) {
                sink.writeAll(source);
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
            if (apk.exists()) {
              try {
                DexClassLoader classLoader = new DexClassLoader(apk.getPath(), getCacheDir().getPath(), null, null);
                Class pluginUtilsClass = classLoader.loadClass("com.hencoder.plugin.Utils");
                Constructor utilsConstructor = pluginUtilsClass.getDeclaredConstructors()[0];
                Object utils = utilsConstructor.newInstance();
                Method shoutMethod = pluginUtilsClass.getDeclaredMethod("shout");
                shoutMethod.invoke(utils);
              } catch (ClassNotFoundException e) {
                e.printStackTrace();
              } catch (IllegalAccessException e) {
                e.printStackTrace();
              } catch (InstantiationException e) {
                e.printStackTrace();
              } catch (InvocationTargetException e) {
                e.printStackTrace();
              } catch (NoSuchMethodException e) {
                e.printStackTrace();
              }
            }
            break;*/
          case -1:
//                  R.id.showTitleBt:
            Title title = new Title();
            titleTv.setText(title.getTitle());
            break;
            // 热修复 添加补丁
          case 0:
//                  R.id.hotfixBt:
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                .url("https://api.hencoder.com/patch/upload/hotfix.dex")
                .build();
            client.newCall(request)
                .enqueue(new Callback() {
                  @Override
                  public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                    try (BufferedSink sink = Okio.buffer(Okio.sink(apk))) {
                      sink.write(response.body().bytes());
                    } catch (IOException e) {
                      e.printStackTrace();
                    }
                    v.post(new Runnable() {
                      @Override
                      public void run() {
                        Toast.makeText(MainActivity.this, "补丁加载成功", Toast.LENGTH_SHORT).show();
                      }
                    });
                  }

                  @Override
                  public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                    v.post(new Runnable() {
                      @Override
                      public void run() {
                        Toast.makeText(MainActivity.this, "出错了", Toast.LENGTH_SHORT).show();
                      }
                    });
                  }
                });
            break;
            // 删除补丁
          case 1:
                  //R.id.removeHotfixBt:
            if (apk.exists()) {
              apk.delete();
            }
            break;
            // 杀死程序
          case 2:
                  //R.id.killSelfBt:
            android.os.Process.killProcess(android.os.Process.myPid());
            break;
        }
      }
    };

    showTitleBt.setOnClickListener(onClickListener);
    hotfixBt.setOnClickListener(onClickListener);
    removeHotfixBt.setOnClickListener(onClickListener);
    killSelfBt.setOnClickListener(onClickListener);
  }
}
