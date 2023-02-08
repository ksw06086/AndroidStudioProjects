package kr.ac.yuhan.sun.mobile2_1101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout l1, l2, l3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = findViewById(R.id.linear1);
        l2 = findViewById(R.id.linear2);
        l3 = findViewById(R.id.linear3);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        btn1.setOnClickListener(btnL);
        btn2.setOnClickListener(btnL);
        btn3.setOnClickListener(btnL);
    }

    View.OnClickListener btnL = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btn1){
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.INVISIBLE);
                l3.setVisibility(View.INVISIBLE);
            } else if(view.getId() == R.id.btn2){
                l1.setVisibility(View.INVISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.INVISIBLE);
            } else {
                l1.setVisibility(View.INVISIBLE);
                l2.setVisibility(View.INVISIBLE);
                l3.setVisibility(View.VISIBLE);
            }
        }
    };
}