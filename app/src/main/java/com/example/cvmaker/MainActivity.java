package com.example.cvmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.mainButton)).setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,Basic.class);
        startActivity(intent);
    }

}
