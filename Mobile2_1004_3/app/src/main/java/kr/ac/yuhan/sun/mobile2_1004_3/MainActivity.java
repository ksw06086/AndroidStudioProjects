package kr.ac.yuhan.sun.mobile2_1004_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    ImageView imgv;
    LinearLayout linear;
    RadioGroup rg;
    Switch swichStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linear = findViewById(R.id.linear);
        swichStart = findViewById(R.id.check_start);
        rg = findViewById(R.id.rg);
        imgv = findViewById(R.id.imgv);
        Button btnEnd = findViewById(R.id.btn_end);
        Button btnStart = findViewById(R.id.btn_start);
        rg.setOnCheckedChangeListener(rgListener);

        btnEnd.setOnClickListener(btnListener);
        btnStart.setOnClickListener(btnListener);
        swichStart.setOnCheckedChangeListener(checkListener);
    }

    RadioGroup.OnCheckedChangeListener rgListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            switch (id){
                case R.id.rd_kato:
                    imgv.setImageResource(R.drawable.kato);
                    break;
                case R.id.rd_mizuhara:
                    imgv.setImageResource(R.drawable.mizuhara);
                    break;
                case R.id.rd_man:
                    imgv.setImageResource(R.drawable.man);
                    break;
            }
        }
    };

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_end:
                    finish();
                    break;
                case R.id.btn_start:
                    swichStart.setChecked(false);
                    break;
            }
        }
    };


    CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                linear.setVisibility(View.VISIBLE);
            }else{
                linear.setVisibility(View.INVISIBLE);
            }
        }
    };
}