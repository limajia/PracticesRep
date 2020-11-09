package com.mlj.practicesrep.customdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mlj.practicesrep.R;

public class CustomDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
    }

    public void openDialog(View view) {
        // android.R.style.Theme_Material_Dialog
        BaseDialog baseDialog = new BaseDialog(view.getContext(), R.style.CustomDialogTheme);
        //
        View baseView = LayoutInflater.from(view.getContext()).inflate(R.layout.activity_dialog_base_layout, null);
        baseDialog.setContentView(baseView);
        //
        LinearLayout wrapper = baseView.findViewById(R.id.wrapper);
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(view.getContext())
                .inflate(R.layout.activity_dialog_content, null);
        wrapper.addView(frameLayout);
        //
        baseDialog.showDialog();
    }
}