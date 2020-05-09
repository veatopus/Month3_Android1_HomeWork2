package com.example.month3_android1_homework2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button buttonCalculator;
    Button buttonShare;
    TextView textViewStory;
    String textForShape;
    public static final String PUT_EXTRA_MAIN_ACTIVITY = "key for put extra MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalculator = findViewById(R.id.buttonCalculator);
        buttonShare = findViewById(R.id.buttonShare);
        textViewStory = findViewById(R.id.story);

        buttonCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForStartCalculator = new Intent(MainActivity.this, Calculator.class);
                startActivityForResult(intentForStartCalculator, Calculator.REQUEST_CODE_CALCULATOR);
            }
        });

        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForShare = new Intent();
                intentForShare.setAction(Intent.ACTION_SEND);
                intentForShare.setType("text/plain");
                intentForShare.putExtra(Intent.EXTRA_TEXT, textForShape);
                if (intentForShare.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intentForShare, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Calculator.REQUEST_CODE_CALCULATOR){
            if (resultCode == RESULT_OK){
                assert data != null;
                textForShape = data.getStringExtra(PUT_EXTRA_MAIN_ACTIVITY);
                textViewStory.setText(textForShape);
            }
        }
    }
}
