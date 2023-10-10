package com.mlj.customviews.userEnter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.mlj.customviews.R;

import java.util.Random;

public class UserEnterActivity extends AppCompatActivity {

    private UserEnterView mUserEnterView;
    private Button mAddPerson;
    private Button mDeletePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_enter);
        mUserEnterView = findViewById(R.id.user_enter_view);
        mAddPerson = findViewById(R.id.addPerson);
        mDeletePerson = findViewById(R.id.deletePerson);

        mAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(new Random().nextInt(100) + "",
//                        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605695604921&di=d627b7c8d5f4d4be9584a89f024b3d9a&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201809%2F01%2F20180901190625_wmpeq.thumb.700_0.jpeg"
                        R.mipmap.ic_launcher);
                mUserEnterView.myZan(user);
            }
        });

        mDeletePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserEnterView.delete();
            }
        });
    }
}