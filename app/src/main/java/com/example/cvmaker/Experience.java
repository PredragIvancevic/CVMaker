package com.example.cvmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cvmaker.api.ApiClient;
import com.example.cvmaker.api.ApiInterface;
import com.example.cvmaker.data.Company;
import com.example.cvmaker.data.User;
import com.example.cvmaker.dialog.LoadDialog;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Experience extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        apiInterface = ApiClient.getClient(ApiInterface.class);
        buttonExp = findViewById(R.id.additionComapany);
        buttonExp.setOnClickListener(this);
        getCV = findViewById(R.id.finish);
        getCV.setOnClickListener(this);
        home=findViewById(R.id.goHome);
        home.setOnClickListener(this);
        outCompany = (TextView) findViewById(R.id.out_company);
        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(this);
        getNumber=(EditText)findViewById(R.id.number);
        relative = (LinearLayout) findViewById(R.id.Companies);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("USER_USERNAME");
            Username=username;
            User user_temp=(User)intent.getSerializableExtra("USER");
            user=user_temp;

        }



    }
    String Username;
    User user;
    LinearLayout relative;
    LayoutInflater inflater;
    TextView outCompany;
    Button buttonExp;
    Button getCV;
    ApiInterface apiInterface;
    ImageButton home;
    EditText getNumber;
    Button submit;



    //int number = Integer.parseInt("0"+getNumber.getText().toString());






    private Company createCompany(String companyName, String position, String period){
        Company company=new Company();
        company.setName(companyName);
        company.setPeriod(position);
        company.setPosition(period);
        if(user==null){
            return null;
        }
        company.setUser(user);
        return company;

    }

    //RelativeLayout vi[]=new RelativeLayout[2];
    ArrayList<RelativeLayout>vi=new ArrayList<>();

    @Override
    public void onClick(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout relative = (LinearLayout) findViewById(R.id.Companies);

        if(getNumber.getText().toString().isEmpty()){
            getNumber.setError("This field cannot be empty");
        }

        int number = Integer.parseInt("0"+getNumber.getText().toString());

       // RelativeLayout vi[]=new RelativeLayout[number];



        switch (v.getId()) {

            case R.id.additionComapany:
                 int i;
                for(i=0;i<number;i++) {
                    RelativeLayout element = (RelativeLayout) inflater.inflate(R.layout.element, null);
                    relative.addView(element);
                   // vi[i] = element;
                    vi.add(element);
                    }
                break;
            case R.id.submit:
                    for (int counter = 0; counter < number; counter++) {
                        String companyName=((EditText) vi.get(counter).findViewById(R.id.companyName)).getText().toString();
                        String position = ((EditText) vi.get(counter).findViewById(R.id.position)).getText().toString();
                        String period = ((EditText) vi.get(counter).findViewById(R.id.period)).getText().toString();
                        Company company = createCompany(companyName, position, period);
                        if (company != null) {
                            Call<Void> callLanguage = apiInterface.addCompany(company);
                            final Dialog dialog = LoadDialog.loadDialog(Experience.this);
                            dialog.show();
                            callLanguage.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (dialog.isShowing())
                                        dialog.dismiss();
                                    if (response.code() == HttpURLConnection.HTTP_OK) {
                                        Toast.makeText(Experience.this, "Company/ies added", Toast.LENGTH_SHORT).show();
                                        outCompany.setText("company added");
                                    } else {
                                        outCompany.setText("company hasn't added");
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    t.printStackTrace();
                                    Toast.makeText(Experience.this, R.string.errornetwork, Toast.LENGTH_SHORT).show();
                                    if (dialog.isShowing())
                                        dialog.dismiss();

                                }
                            });
                        }
                    }


                break;
            case R.id.finish:
                Intent intent = new Intent(this, GetCV.class);
                Bundle extra=new Bundle();
                extra.putString("PASS",Username);
                intent.putExtras(extra);
                startActivity(intent);
                break;
            case R.id.goHome:
                Intent j=new Intent(this,MainActivity.class);
                startActivity(j);
                break;

        }
    }

}
