package com.example.orientation2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){ // 가로방향인지 확인
            showToast("가로 방향임");
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){ // 세로방향인지 확인
            showToast("세로 방향임");
        }
    }

    public void showToast(String data) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }
}