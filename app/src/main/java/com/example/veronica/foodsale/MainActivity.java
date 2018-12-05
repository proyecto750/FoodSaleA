package com.example.veronica.foodsale;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener , View.OnClickListener{
private GoogleApiClient api;
private  final int RC_SIGN_IN = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RegisterService();
        registerEvents();
    }

    private void registerEvents() {
        Button btn = (Button)this.findViewById(R.id.google);
        btn.setOnClickListener((View.OnClickListener) this);
    }

    private void RegisterService() {

        GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
               // .requestProfile()
                .requestId()
                .build();
        api = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,options)
                .build();


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.google){
            Intent google = Auth.GoogleSignInApi.getSignInIntent(api);
            this.startActivityForResult(google,RC_SIGN_IN);
        }

    }

    @Override
    protected void onActivityResult (int r1 , int r2 , Intent data) {
    super.onActivityResult(r1,r2,data);
    if (r1 == RC_SIGN_IN){
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        loadData(result);
    }
    }

    private void loadData(GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount datos = result.getSignInAccount();
            TextView txt = (TextView)this.findViewById(R.id.correo);
            txt.setText(datos.getEmail());
            //ImageView Img = (ImageView)this.findViewById(R.id.imageView2);
            //Img.setImageBitmap(datos.getPhotoUrl());




        }else {
            Toast.makeText(this,"error no conoces el pasword", Toast.LENGTH_SHORT).show();
        }
    }
}
