package com.example.myactivity1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OtherAcitivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        double v1 = intent.getDoubleExtra("V1", 0);
        double v2 = intent.getDoubleExtra("V2", 0);
        double max = v1 > v2 ? v1 : v2;
        Intent data = new Intent();
        data.putExtra("RESULT", max);
        setResult(RESULT_OK, data);
        finish();
    }
}