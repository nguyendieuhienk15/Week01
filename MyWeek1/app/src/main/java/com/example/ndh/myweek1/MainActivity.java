package com.example.ndh.myweek1;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFileClick(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.example.lap10559.filestorage","com.example.lap10559.filestorage.MainActivity"));
        startActivity(intent);
    }

    public void onBtnClick(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.example.myactivity1","com.example.myactivity1.MainActivity"));
        startActivity(intent);
    }

    public void onServiceClick(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.example.serviceapplication","com.example.serviceapplication.MainActivity"));
        startActivity(intent);
    }

    public void onBrClick(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.example.ndh.broadcastreceiver","com.example.ndh.broadcastreceiver.MainActivity"));
        startActivity(intent);
    }

    public void onCpClick(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.example.ndh.contentprovider","com.example.ndh.contentprovider.MainActivity"));
        startActivity(intent);
    }
}
