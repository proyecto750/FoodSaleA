package com.example.veronica.foodsale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.veronica.foodsale.Host.host;
import com.example.veronica.foodsale.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegistroUsuario extends AppCompatActivity {

    private EditText Name;
    private  EditText Email;
    private  EditText Phone;
    private  EditText Ci;
    private Button Login;

    private host HOST =new host();
    private int confir=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        Phone= findViewById(R.id.phone);
        Ci = findViewById(R.id.ci);
        Login = findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendDataRegister(Name.getText().toString(), Email.getText().toString(),
                        Phone.getText().toString(), Ci.getText().toString());

                if(confir==1) {

//                    Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
//                    startActivity(intent);

                    Name.setText("");
                    Email.setText("");
                    Phone.setText("");
                    Ci.setText("");

                }
            }

        });

    }

    private void sendDataRegister(final String Name, String Email, String Phone, String Ci) {



        if(!Name.isEmpty()&&!Email.isEmpty()&& !Phone.isEmpty()&& !Ci.isEmpty()) {

            confir=1;

            RequestParams params = new RequestParams();
            params.put("name", Name);
            params.put("email", Email);
            params.put("telefono", Phone);
            params.put("ci", Ci);




            AsyncHttpClient Client = new AsyncHttpClient();
            Client.post(HOST.getIp()+":8888/api/apirestfoodsale", params, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {

                        Toast.makeText(getApplicationContext(),"Registro realizado", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), response.getString("name"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistroUsuario.this,RegisterRestaurant.class);
                        startActivity(intent);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                    Toast.makeText(getApplicationContext(),"Usuario ya existente",Toast.LENGTH_SHORT);
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),"fill in the empty spaces", Toast.LENGTH_SHORT).show();
            confir=0;

        }
    }


    }




