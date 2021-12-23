package com.example.cvmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cvmaker.api.ApiClient;
import com.example.cvmaker.api.ApiInterface;
import com.example.cvmaker.data.Company;
import com.example.cvmaker.data.User;
import com.example.cvmaker.dialog.LoadDialog;

import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCV extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_c_v);
        phoneIcon = (ImageButton) findViewById(R.id.phoneicon);
        emailIcon = (ImageButton) findViewById(R.id.emailicon);
        delete=(Button)findViewById(R.id.deleteUser);
        phoneIcon.setOnClickListener(this);
        emailIcon.setOnClickListener(this);
        delete.setOnClickListener(this);
        apiInterface = ApiClient.getClient(ApiInterface.class);
        Intent intent=getIntent();
        if(intent!=null) {

            Username = intent.getStringExtra("PASS");

        }


         name_label=(TextView)findViewById(R.id.get_name);
         phone_label=(TextView)findViewById(R.id.getPhoneNumber);
         email_label=(TextView)findViewById(R.id.getEmailAddress);
         aboutMe_label=(TextView)findViewById(R.id.getAboutMe);
         bachelor_label=(TextView)findViewById(R.id.getBachelor);
         master_label=(TextView)findViewById(R.id.getMaster);
         languages_label=(TextView)findViewById(R.id.getLanguages);
         high_label=(TextView)findViewById(R.id.getHigh);
         companies_label=(TextView)findViewById(R.id.getCompanies);

        getUser();
        getCompanies();
    }

    TextView name_label;
    TextView phone_label;
    TextView email_label;
    TextView aboutMe_label;
    TextView bachelor_label;
    TextView master_label;
    TextView languages_label;
    TextView high_label;
    TextView companies_label;
    ImageButton phoneIcon;
    ImageButton emailIcon;
    Button delete;
    ApiInterface apiInterface;
    String Username;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phoneicon:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
                break;
            case R.id.emailicon:
                Intent intent1 = new Intent(Intent.ACTION_SENDTO);
                intent1.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent1.putExtra(Intent.EXTRA_EMAIL, "some email adress");
                if (intent1.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent1);
                }
                break;
            case R.id.deleteUser:
                deleteUser();
                break;

        }


    }
    private void getUser() {
            Call<User> callLanguage = apiInterface.getUserByUsername(Username);
            final Dialog dialog = LoadDialog.loadDialog(GetCV.this);
            dialog.show();
            callLanguage.enqueue(new Callback<User>()
            {
                @Override
                public void onResponse(Call<User> call, Response<User> response)
                {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    if(response.code() == HttpURLConnection.HTTP_OK && response.body() != null){
                        name_label.setText(response.body().getName()+" "+response.body().getSurname());
                        phone_label.setText(response.body().getContact().getPhoneNumber());
                        email_label.setText(response.body().getContact().getEmail());
                        aboutMe_label.setText(response.body().getAboutMe());
                        if(response.body().getEducation().getBachelor().isEmpty()){
                            bachelor_label.setText("/");
                        }else{
                            bachelor_label.setText(response.body().getEducation().getBachelor());
                        }
                        if(response.body().getEducation().getMaster().isEmpty()){
                            master_label.setText("/");
                        }else{
                            master_label.setText(response.body().getEducation().getMaster());
                        }
                        if(response.body().getEducation().getLanguages().isEmpty()){
                            languages_label.setText("/");
                        }else{
                            languages_label.setText(response.body().getEducation().getLanguages());
                        }
                        if(response.body().getEducation().getHighSchool().isEmpty()){
                            high_label.setText("/");
                        }else{
                            high_label.setText(response.body().getEducation().getHighSchool());
                        }

                    }else{
                        name_label.setText(getString(R.string.noUser));
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t)
                {
                    t.printStackTrace();
                    Toast.makeText(GetCV.this, R.string.errornetwork, Toast.LENGTH_SHORT).show();
                    if (dialog.isShowing())
                        dialog.dismiss();

                }
            });
        }


    private void getCompanies() {
        Call<List<Company>> callLanguage = apiInterface.findCompaniesByUsername(Username);
        final Dialog dialog = LoadDialog.loadDialog(GetCV.this);
        dialog.show();
        callLanguage.enqueue(new Callback<List<Company>>()
        {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response)
            {
                if (dialog.isShowing())
                    dialog.dismiss();
                if(response.code() == HttpURLConnection.HTTP_OK && response.body() != null){
                   companies_label.setText(response.body().toString());
                }else{
                    companies_label.setText("/");
                }
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t)
            {
                t.printStackTrace();
                Toast.makeText(GetCV.this, R.string.errornetwork, Toast.LENGTH_SHORT).show();
                if (dialog.isShowing())
                    dialog.dismiss();

            }
        });
    }



    private void deleteUser() {
            Call<Void> callLanguage = apiInterface.deleteUser(Username);
            final Dialog dialog = LoadDialog.loadDialog(GetCV.this);
            dialog.show();
            callLanguage.enqueue(new Callback<Void>()
            {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response)
                {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    if(response.code() == HttpURLConnection.HTTP_OK) {
                        Toast.makeText(GetCV.this, R.string.successfullyDeleted, Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t)
                {
                    t.printStackTrace();
                    Toast.makeText(GetCV.this, R.string.errornetwork, Toast.LENGTH_SHORT).show();
                    if (dialog.isShowing())
                        dialog.dismiss();

                }
            });
        }
    }



