package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), );
                // getApplicationContext : context = 버튼이면 버튼이 포함된 주변정보
                // 버튼이 어느 레이아웃 어느 환경 이런 것을 담을 수 잇음,
                // UI객체는 context를 받게 되어있음, 생성자 함수 시행할 때 Intent도 같음
            }
        });
    }
}