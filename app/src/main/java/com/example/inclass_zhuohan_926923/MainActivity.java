package com.example.inclass_zhuohan_926923;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.inclass_zhuohan_926923.InClass_01.InClass01;
import com.example.inclass_zhuohan_926923.InClass_02.InClass02;
import com.example.inclass_zhuohan_926923.Practice.PracticeActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonPractice;
    private Button buttonInClass01;

    private Button buttonInClass_02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonPractice = findViewById(R.id.button);
        buttonInClass01 = findViewById(R.id.button_Inclass_01);
        buttonInClass_02 = findViewById(R.id.button_InClass_02);



        buttonPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPracticeActivity = new Intent(MainActivity.this,
                        PracticeActivity.class);

                startActivity(toPracticeActivity);
            }


        });

        buttonInClass01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass01Activity = new Intent(MainActivity.this,
                        InClass01.class);

                startActivity(toInClass01Activity);
            }
        });

        buttonInClass_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass02Activity = new Intent(MainActivity.this,
                        InClass02.class);

                startActivity(toInClass02Activity);
            }
        });
    }
}