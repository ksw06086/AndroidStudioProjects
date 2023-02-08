package kr.ac.yuhan.sun.mobile2_20220920_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direct_4_2);
        // edit1 = findViewById(R.id.edt1);
        // Button btn = findViewById(R.id.btn);

        // btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        // String name = edit1.getText().toString();
        // Toast.makeText(this,"안녕하세요~"+name+"님 반갑습니다.", Toast.LENGTH_LONG).show();
    }
}