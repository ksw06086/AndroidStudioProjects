package kr.ac.yuhan.sun.moblie2_1025_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] btnIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    Button[] btns = new Button[btnIds.length];
    Button btnPlus, btnSub, btnMul, btnDes;
    EditText ed1, ed2;
    String num1, num2;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.edit1);
        ed2 = findViewById(R.id.edit2);

        ed1.setOnFocusChangeListener(focusL);
        ed2.setOnFocusChangeListener(focusL);

        btnPlus = findViewById(R.id.btn_plus);
        btnSub = findViewById(R.id.btn_sub);
        btnMul = findViewById(R.id.btn_mul);
        btnDes = findViewById(R.id.btn_des);
        for(int i=0; i<btns.length; i++){
            final int index = i;
            btns[i] = findViewById(btnIds[i]);
            btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ed1.isFocused()){
                        num1 = ed1.getText().toString() + btns[index].getText().toString();
                        ed1.setText(num1);
                    } else if(ed2.isFocused()){
                        num2 = ed2.getText().toString() + btns[index].getText().toString();
                        ed2.setText(num2);
                    } else {
                        Toast.makeText(getApplicationContext(),"에디트 텍스트를 먼저 선택하세요.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        textResult = findViewById(R.id.text);
    }

    View.OnFocusChangeListener focusL = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if(b) {
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    };

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
            }
            textResult.setText("계산결과:");
            textResult.append(" "+result);
        }
    };
}