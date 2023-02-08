package kr.ac.yuhan.sun.mobile2_1108_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnFood;
    Button btnJuice;
    ImageView food;
    ImageView juice;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFood = findViewById(R.id.btn_food);
        btnJuice = findViewById(R.id.btn_juice);
        food = findViewById(R.id.img_food);
        juice = findViewById(R.id.img_juice);

        btnFood.setOnClickListener(btnL);
        btnJuice.setOnClickListener(btnL);
    }

    View.OnClickListener btnL = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_food:
                    switch (random.nextInt(7)+1){
                        case 1:
                            food.setImageResource(R.drawable.bab1);
                            break;
                        case 2:
                            food.setImageResource(R.drawable.bab2);
                            break;
                        case 3:
                            food.setImageResource(R.drawable.bab3);
                            break;
                        case 4:
                            food.setImageResource(R.drawable.bab4);
                            break;
                        case 5:
                            food.setImageResource(R.drawable.bab5);
                            break;
                        case 6:
                            food.setImageResource(R.drawable.bab6);
                            break;
                        case 7:
                            food.setImageResource(R.drawable.bab7);
                            break;
                    }
                    break;
                case R.id.btn_juice:
                    switch (random.nextInt(7)+1){
                        case 1:
                            juice.setImageResource(R.drawable.ju1);
                            break;
                        case 2:
                            juice.setImageResource(R.drawable.ju2);
                            break;
                        case 3:
                            juice.setImageResource(R.drawable.ju3);
                            break;
                        case 4:
                            juice.setImageResource(R.drawable.ju4);
                            break;
                        case 5:
                            juice.setImageResource(R.drawable.ju5);
                            break;
                        case 6:
                            juice.setImageResource(R.drawable.ju6);
                            break;
                        case 7:
                            juice.setImageResource(R.drawable.ju7);
                            break;
                    }
                    break;
            }
        }
    };
}