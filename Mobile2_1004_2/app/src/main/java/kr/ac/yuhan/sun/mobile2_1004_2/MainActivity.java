package kr.ac.yuhan.sun.mobile2_1004_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton rb1, rb2;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rb1 = findViewById(R.id.radio_male);
        rb2 = findViewById(R.id.radio_female);
        rg = findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(checkListener);
    }

    RadioGroup.OnCheckedChangeListener checkListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            String result = "";
            switch (id){
                case R.id.radio_male:
                    result = "삐빅! 남성입니다.";
                    break;
                case R.id.radio_female:
                    result = "삐빅! 여성입니다.";
                    break;
            }
            Toast.makeText(MainActivity.this, result,Toast.LENGTH_LONG).show();
        }
    };
}