package kr.ac.yuhan.sun.class_score;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final int CLASS_CNT = 7;
    TextView[] className = new TextView[CLASS_CNT];
    EditText[] first = new EditText[CLASS_CNT];
    EditText[] second = new EditText[CLASS_CNT];
    EditText[] exam = new EditText[CLASS_CNT];
    EditText[] tardy = new EditText[CLASS_CNT];
    TextView[] result = new TextView[CLASS_CNT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button res = findViewById(R.id.btn_result);
        res.setOnClickListener(btnL);

        className[0] = findViewById(R.id.jsp);
        first[0] = findViewById(R.id.jsp_first);
        second[0] = findViewById(R.id.jsp_second);
        exam[0] = findViewById(R.id.jsp_exam);
        tardy[0] = findViewById(R.id.jsp_tardy);

        className[1] = findViewById(R.id.db);
        first[1] = findViewById(R.id.db_first);
        second[1] = findViewById(R.id.db_second);
        exam[1] = findViewById(R.id.db_exam);
        tardy[1] = findViewById(R.id.db_tardy);

        className[2] = findViewById(R.id.os);
        first[2] = findViewById(R.id.os_first);
        second[2] = findViewById(R.id.os_second);
        exam[2] = findViewById(R.id.os_exam);
        tardy[2] = findViewById(R.id.os_tardy);

        className[3] = findViewById(R.id.vc);
        first[3] = findViewById(R.id.vc_first);
        second[3] = findViewById(R.id.vc_second);
        exam[3] = findViewById(R.id.vc_exam);
        tardy[3] = findViewById(R.id.vc_tardy);

        className[4] = findViewById(R.id.mobile);
        first[4] = findViewById(R.id.mobile_first);
        second[4] = findViewById(R.id.mobile_second);
        exam[4] = findViewById(R.id.mobile_exam);
        tardy[4] = findViewById(R.id.mobile_tardy);

        className[5] = findViewById(R.id.java);
        first[5] = findViewById(R.id.java_first);
        second[5] = findViewById(R.id.java_second);
        exam[5] = findViewById(R.id.java_exam);
        tardy[5] = findViewById(R.id.java_tardy);

        className[6] = findViewById(R.id.system);
        first[6] = findViewById(R.id.system_first);
        second[6] = findViewById(R.id.system_second);
        exam[6] = findViewById(R.id.system_exam);
        tardy[6] = findViewById(R.id.system_tardy);

        result[0] = findViewById(R.id.jsp_result);
        result[1] = findViewById(R.id.db_result);
        result[2] = findViewById(R.id.os_result);
        result[3] = findViewById(R.id.vc_result);
        result[4] = findViewById(R.id.mobile_result);
        result[5] = findViewById(R.id.java_result);
        result[6] = findViewById(R.id.system_result);

    }

    View.OnClickListener btnL = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i<CLASS_CNT; i++){
                int first_score = Integer.parseInt(String.valueOf(first[i].getText()));
                int second_score = Integer.parseInt(String.valueOf(second[i].getText()));
                int exam_score = Integer.parseInt(String.valueOf(exam[i].getText()));
                int tardy_score = Integer.parseInt(String.valueOf(tardy[i].getText()));
                int hap = first_score + second_score + exam_score + 20-(tardy_score/3);

                if(tardy_score >= 12){
                    result[i].setText(className[i].getText() + " 합계: " + hap);
                    result[i].append(" 학점: F");
                } else {
                    result[i].setText(className[i].getText() + " 합계: " + hap + " 학점: ");
                    if(hap >= 95){
                        result[i].append("A+");
                    } else if(hap >= 90){
                        result[i].append("A0");
                    } else if(hap >= 85){
                        result[i].append("B+");
                    } else if(hap >= 80){
                        result[i].append("B0");
                    } else if(hap >= 75){
                        result[i].append("C+");
                    } else if(hap >= 70){
                        result[i].append("C0");
                    } else if(hap >= 65){
                        result[i].append("D+");
                    } else if(hap >= 60){
                        result[i].append("D0");
                    } else {
                        result[i].append("F");
                    }
                }
            }
        }
    };
}