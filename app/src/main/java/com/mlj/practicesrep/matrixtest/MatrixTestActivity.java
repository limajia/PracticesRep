package com.mlj.practicesrep.matrixtest;

import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class MatrixTestActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_test);
        initView();
    }

    private void initView() {
        mImageView = findViewById(R.id.textView);
        Matrix matrix = new Matrix();
        matrix.setTranslate(200, 200); //参数是一个相对边的值，不是每次的dx变化量
        /*matrix.postRotate(-45);
        matrix.preRotate(-45);
        matrix.postRotate(-45，centerX,centerY);*/
        mImageView.setImageMatrix(matrix);
    }

    /*
    set方法会替换
    Matrix m = new Matrix();
    m.setRotate(45);
    m.setTranslate(80,80);
    //只有m.setTranslate(80, 80)有效,因为m.setRotate(45);被清除.*/
}