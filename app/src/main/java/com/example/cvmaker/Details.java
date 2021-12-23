package com.example.cvmaker;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import retrofit2.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cvmaker.api.ApiClient;
import com.example.cvmaker.api.ApiInterface;
import com.example.cvmaker.data.Contact;
import com.example.cvmaker.data.Education;
import com.example.cvmaker.data.User;
import com.example.cvmaker.dialog.LoadDialog;

import java.io.Serializable;
import java.net.HttpURLConnection;

import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Button addUser=findViewById(R.id.addUser);
        addUser.setOnClickListener(this);
        Button updateUser=findViewById(R.id.updateUser);
        updateUser.setOnClickListener(this);
        ImageButton home=findViewById(R.id.goHome);
        home.setOnClickListener(this);

        apiInterface= ApiClient.getClient(ApiInterface.class);




        Intent intent=getIntent();
        if(intent!=null){
             username=intent.getStringExtra("USERNAME");
             name=intent.getStringExtra("NAME");
             surname=intent.getStringExtra("SURNAME");
             aboutMe=intent.getStringExtra("ABOUTME");
             bachelor=(EditText)findViewById(R.id.bachelor);
             doctoral=(EditText)findViewById(R.id.doctoral);
             master=(EditText)findViewById(R.id.master);
             languages=(EditText)findViewById(R.id.languages);
             highSchool=(EditText)findViewById(R.id.highSchool);
             phone=(EditText)findViewById(R.id.phoneNumber);
             skype=(EditText)findViewById(R.id.skypeProfile);
             email=(EditText)findViewById(R.id.emailAdress);
             linkedin=(EditText)findViewById(R.id.linkedinProfile);
             outUser=(TextView)findViewById(R.id.out_user);


        }





    }
    private EditText bachelor;
    private EditText master;
    private EditText doctoral;
    private EditText languages;
    private EditText highSchool;
    private EditText phone;
    private EditText skype;
    private EditText email;
    private EditText linkedin;
    private TextView outUser;
    String name, surname,aboutMe,username;
    User main_user;

    ApiInterface apiInterface;


    private User createUser(){
        User user=new User();
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);

        if(aboutMe.isEmpty()){
        user.setAboutMe("/");
        }else{
            user.setAboutMe(aboutMe);
        }


        Education education=createEducation();
            if(education==null){
                return null;
            }
            user.setEducation(education);

            Contact contact=createContact();
            if(contact==null){
                return null;
            }
            user.setContact(contact);

            return user;

    }



    private Education createEducation(){
        Education education=new Education();
        if(bachelor.getText().toString().isEmpty()){
            education.setBachelor("/");
        }else{
            education.setBachelor(bachelor.getText().toString());
        }
        if(master.getText().toString().isEmpty()){
            education.setMaster("/");
        }else{
        education.setMaster(master.getText().toString());
        }
        if(highSchool.getText().toString().isEmpty()){
            education.setHighSchool("/");
        }else{
        education.setHighSchool(highSchool.getText().toString());
        }
        if(doctoral.getText().toString().isEmpty()){
            education.setDoctoral("/");
        }else{
        education.setDoctoral(doctoral.getText().toString());
        }
        if(languages.getText().toString().isEmpty()){
            languages.setError("Languages cannot be empty");
            return null;
        }else{
        education.setLanguages(languages.getText().toString());
        }
        return education;
    }

    private Contact createContact(){
        Contact contact=new Contact();
        if(phone.getText().toString().isEmpty()){
            phone.setError("Phone number cannot be empty!");
            return null;
        }
        contact.setPhoneNumber(phone.getText().toString());

        if(email.getText().toString().isEmpty()){
            email.setError("Email cannot be empty!");
            return null;
        }
        contact.setEmail(email.getText().toString());


        if(linkedin.getText().toString().isEmpty()){
            contact.setLinkedin("/");
        }else{
        contact.setLinkedin(linkedin.getText().toString());
        }

        if(skype.getText().toString().isEmpty()){
            contact.setSkype("/");
        }else{
        contact.setSkype(skype.getText().toString());
        }

        return contact;



    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.addUser:
                addUser();
                Intent intent = new Intent(this, Experience.class);
                Bundle extra=new Bundle();
                extra.putString("USER_USERNAME",username);
                intent.putExtra("USER",main_user);
                intent.putExtras(extra);
                startActivity(intent);
                break;

            /*case R.id.addExperience:
                Intent intent = new Intent(this, Experience.class);
                Bundle extra=new Bundle();
                extra.putString("USER_USERNAME",username);
                intent.putExtra("USER",main_user);
                startActivity(intent);
                break;*/
            case R.id.updateUser:
                updateUser();
                break;

            case R.id.goHome:
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
                break;


        }


    }

    private void addUser(){
        User user=createUser();
         main_user=user;
        if(user != null){
                Call<Void> callLanguage = apiInterface.addUser(user);
                final Dialog dialog = LoadDialog.loadDialog(Details.this);
                dialog.show();
                callLanguage.enqueue(new Callback<Void>()
            {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response)
                {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    if(response.code() == HttpURLConnection.HTTP_OK){
                        Toast.makeText(Details.this, "User added", Toast.LENGTH_SHORT).show();
                        outUser.setText(R.string.addedUser);
                    }else{
                        outUser.setText(R.string.noUser);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t)
                {
                    t.printStackTrace();
                    Toast.makeText(Details.this, R.string.errornetwork, Toast.LENGTH_SHORT).show();
                    if (dialog.isShowing())
                        dialog.dismiss();

                }
            });

        }

    }
    private void updateUser(){

        User user = createUser();
        if(user != null){
            Call<Void> callLanguage = apiInterface.updateUser(user);
            final Dialog dialog = LoadDialog.loadDialog(Details.this);
            dialog.show();
            callLanguage.enqueue(new Callback<Void>()
            {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response)
                {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    if(response.code() == HttpURLConnection.HTTP_OK){
                        Toast.makeText(Details.this, "User updated", Toast.LENGTH_SHORT).show();
                        outUser.setText(getString(R.string.updatedUser));
                    }else{
                        outUser.setText(getString(R.string.noUser));
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t)
                {
                    t.printStackTrace();
                    Toast.makeText(Details.this, R.string.errornetwork, Toast.LENGTH_SHORT).show();
                    if (dialog.isShowing())
                        dialog.dismiss();

                }
            });
        }
    }



}
