package com.example.cvmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Basic extends AppCompatActivity implements View.OnClickListener{

    private EditText username;
    private EditText name;
    private EditText surname;
    private EditText aboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        username=(EditText)findViewById(R.id.username);
        name=(EditText)findViewById(R.id.name);
        surname=(EditText)findViewById(R.id.surname);
        aboutMe=(EditText)findViewById(R.id.aboutMe);
        (findViewById(R.id.addDetails)).setOnClickListener(this);
        (findViewById(R.id.goHome)).setOnClickListener(this);

    }





    @Override
    public void onClick(View v) {

            if (username.getText().toString().isEmpty()) {
                username.setError("username cannot be empty");
            }

            if (name.getText().toString().isEmpty()) {
                name.setError("Name cannot be empty!");
            }
            if (surname.getText().toString().isEmpty()) {
                surname.setError("Surname cannot be empty!");
            }
            switch (v.getId()){
                case R.id.addDetails:
            if ((!name.getText().toString().isEmpty()) && (!surname.getText().toString().isEmpty())) {
                Intent intent = new Intent(this, Details.class);
                Bundle extra = new Bundle();
                extra.putString("USERNAME", username.getText().toString());
                extra.putString("NAME", name.getText().toString());
                extra.putString("SURNAME", surname.getText().toString());
                extra.putString("ABOUTME", aboutMe.getText().toString());
                intent.putExtras(extra);
                startActivity(intent);
            }
            break;

            case R.id.goHome:
                Intent i=new Intent(this, MainActivity.class);
                startActivity(i);
                break;
        }
    }
}
