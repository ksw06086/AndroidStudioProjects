package kr.ac.yuhan.sun.mobile2_20220920_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView1 = findViewById(R.id.text1);
        TextView textView2 = findViewById(R.id.text2);
        TextView textView3 = findViewById(R.id.text3);

        textView1.setTextColor(Color.RED);
        textView1.setText("이번 예제만 하면 수업이 끝나요~");
        textView1.setTextSize(25);
        textView2.setTextSize(35);
        textView2.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
        textView3.setSingleLine(true);
        textView3.setText("이번 예제만 하면 수업이 끝나요~ 이번 예제만 하면 수업이 끝나요~ 이번 예제만 하면 수업이 끝나요~");
    }
}