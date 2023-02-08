package kr.ac.yuhan.sun.mobile2_1122_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    ViewFlipper viewflip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewflip = findViewById(R.id.viewflip);
        viewflip.setFlipInterval(1000);
        Button btnPrev = findViewById(R.id.btn_prev);
        Button btnNext = findViewById(R.id.btn_next);
        btnPrev.setOnClickListener(btnL);
        btnNext.setOnClickListener(btnL);
    }

    View.OnClickListener btnL = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_prev:
                    viewflip.startFlipping();
                    break;
                case R.id.btn_next:
                    viewflip.stopFlipping();
                    break;
            }
        }
    };
}