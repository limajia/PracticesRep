package com.mlj.practicesrep.bindertest.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;
import com.mlj.practicesrep.bindertest.Book;
import com.mlj.practicesrep.bindertest.server.BookManager;
import com.mlj.practicesrep.bindertest.server.RemoteService;
import com.mlj.practicesrep.bindertest.server.Stub;

import java.util.List;
import java.util.Random;

//https://zhuanlan.zhihu.com/p/35519585
//写给 Android 应用工程师的 Binder 原理剖析
public class BinderTestActivity extends AppCompatActivity {

    private BookManager bookManager;
    private boolean isConnection = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_test);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(view -> {
            if (!isConnection) {
                attemptToBindService();
                return;
            }

            if (bookManager == null) {
                return;
            }

            try {
                Book book = new Book();
                book.setPrice(new Random().nextInt(100));
                book.setName("docker");
                bookManager.addBook(book);
                Log.d("ClientActivity", bookManager.getBooks().toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        });
    }

    private void attemptToBindService() {
        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction("com.baronzhang.ipc");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnection = true;
            bookManager = Stub.asInterface(service);
            if (bookManager != null) {
                try {
                    List<Book> books = bookManager.getBooks();
                    Log.d("ClientActivity", books.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnection = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if (!isConnection) {
            attemptToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isConnection) {
            unbindService(serviceConnection);
        }
    }
}