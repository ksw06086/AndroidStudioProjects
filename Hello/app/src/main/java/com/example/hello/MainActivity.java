package com.example.hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v){
        Toast.makeText(this, "확인 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
        // 아래쪽에 메시지가 보이게 되는 함수
    }

    public void onButton2Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        // 링크 네이버로 옮기게 함
        startActivity(intent);
    }

    public void onButton3Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-0000"));
        // 링크 전화로 옮기게 함
        startActivity(intent);
    }
}