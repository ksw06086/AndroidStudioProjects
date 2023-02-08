package kr.ac.yuhan.sun.mobile2_1004_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox check1, check2, check3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);
        check1.setOnCheckedChangeListener(checkListener);
        check2.setOnCheckedChangeListener(checkListener);
        check3.setOnCheckedChangeListener(checkListener);
    }

    CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            CheckBox eCheck = (CheckBox) compoundButton;
            String name = eCheck.getText().toString();
            String checkState = "";
            if(b){
                checkState = "체크된 상태";
            } else {
                checkState = "체크가 해제된 상태";
            }
            Toast.makeText(MainActivity.this, name + "과목이 "+checkState, Toast.LENGTH_LONG).show();
        }
    };
}