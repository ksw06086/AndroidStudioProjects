package com.example.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageview;
    ImageView imageview2;

    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageview = findViewById(R.id.imageView);
        imageview2 = findViewById(R.id.imageView2);
    }

    public void onButtonClicked(View v){
        changeImage();
    }

    public void changeImage(){
        if(imageIndex == 0){
            imageview.setVisibility((View.VISIBLE));
            imageview2.setVisibility(View.INVISIBLE);

            imageIndex = 1;
        } else if (imageIndex == 1){
            imageview.setVisibility((View.INVISIBLE));
            imageview2.setVisibility(View.VISIBLE);

            imageIndex = 0;
        }
    }
}