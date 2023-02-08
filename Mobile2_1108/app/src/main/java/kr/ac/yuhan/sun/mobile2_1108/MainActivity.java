package kr.ac.yuhan.sun.mobile2_1108;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    CalendarView calendar;
    TimePicker timepick;
    Chronometer chrono;
    int year, month, day;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = findViewById(R.id.rg);
        calendar = findViewById(R.id.caleader);
        timepick = findViewById(R.id.timepick);
        chrono = findViewById(R.id.chron);
        Button btnStart = findViewById(R.id.btn);
        Button btnDone = findViewById(R.id.btn_done);
        textResult = findViewById(R.id.text_result);

        calendar.setOnDateChangeListener(calendarL);
        btnStart.setOnClickListener(btnL);
        btnDone.setOnClickListener(btnL);
        rg.setOnCheckedChangeListener(rgL);

    }

    CalendarView.OnDateChangeListener calendarL = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
            MainActivity.this.year = year;
            MainActivity.this.month = month + 1;
            MainActivity.this.day = day;

        }
    };

    View.OnClickListener btnL = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn:
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                    chrono.setTextColor(Color.RED);
                    break;
                case R.id.btn_done:
                    chrono.stop();
                    chrono.setTextColor(Color.BLUE);
                    textResult.setText(year+"년 " + month + "월 " + day + "일 ");
                    textResult.append(timepick.getCurrentHour()+"시 "+ timepick.getCurrentMinute()+"분 ");
                    textResult.append("예약 완료됨");
                    break;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener rgL = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedRadioId) {
            calendar.setVisibility(View.INVISIBLE);
            timepick.setVisibility(View.INVISIBLE);
            switch (checkedRadioId){
                case R.id.radio_date:
                    calendar.setVisibility(View.VISIBLE);
                    break;
                case R.id.radio_time:
                    timepick.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
}