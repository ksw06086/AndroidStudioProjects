package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
// 추가된 라이브러리
// 자동적 변환 refactor -> Migrate to androidx / Migrate to AppCompat(3.4  이하)
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
// 이 클래스를 상속받아서 화면이 나타나졌다
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}