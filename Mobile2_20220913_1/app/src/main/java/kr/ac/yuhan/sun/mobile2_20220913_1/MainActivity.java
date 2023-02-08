package kr.ac.yuhan.sun.mobile2_20220913_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_home = findViewById(R.id.btn_home);
        Button btn_call = findViewById(R.id.btn_call);
        Button btn_gall = findViewById(R.id.btn_gall);
        Button btn_end = findViewById(R.id.btn_end);

        btn_home.setOnClickListener(btn_listener);
        btn_call.setOnClickListener(btn_listener);
        btn_gall.setOnClickListener(btn_listener);
        btn_end.setOnClickListener(btn_listener);
    } // end onCreate()

    View.OnClickListener btn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_home:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                    startActivity(intent);
                    break;
                case R.id.btn_call:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/010-1234-5678"));
                    startActivity(intent);
                    break;
                case R.id.btn_gall:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                    startActivity(intent);
                    break;
                case R.id.btn_end:
                    finish();
                    break;
            }
        }
    };


}