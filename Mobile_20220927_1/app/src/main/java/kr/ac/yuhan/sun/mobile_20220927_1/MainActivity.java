package kr.ac.yuhan.sun.mobile_20220927_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.edit1);
        ed2 = findViewById(R.id.edit2);
        int[] btnIds = {R.id.btn_plus, R.id.btn_sub, R.id.btn_mul, R.id.btn_des, R.id.btn_mod};
        Button[] btns = new Button[btnIds.length];
        for(int i=0; i<btns.length; i++){
            btns[i] = findViewById(btnIds[i]);
            btns[i].setOnClickListener(btnListener);
        }

        textResult = findViewById(R.id.text);

    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String str1 = ed1.getText().toString();
            String str2 = ed2.getText().toString();
            if(str1.equals("") || str2.equals("")){
                Toast.makeText(MainActivity.this, R.string.toate_error1, Toast.LENGTH_LONG).show();
                return;
            }
            // 조건이 없으면 숫자로 전한시 numberformatException이 발생함
            double num1 = Double.parseDouble(str1);
            double num2 = Double.parseDouble(str2);
            double result = 0.0;
            switch (view.getId()){
                case R.id.btn_plus:
                    result = num1 + num2;
                    break;
                case R.id.btn_sub:
                    result = num1 - num2;
                    break;
                case R.id.btn_mul:
                    result = num1 * num2;
                    break;
                case R.id.btn_des:
                    // ArithmeticExcetion 방지 코드
                    if(num2 == 0){
                        Toast.makeText(MainActivity.this, R.string.toate_error2, Toast.LENGTH_LONG).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
                case R.id.btn_mod:
                    result = num1 % num2;
                    break;
            }
            textResult.setText("계산결과:");
            textResult.append(" "+result);
        }
    };
}