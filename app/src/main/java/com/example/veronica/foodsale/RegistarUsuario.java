package com.example.veronica.foodsale;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.veronica.foodsale.utils.Data;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegistarUsuario extends AppCompatActivity {
    private Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_usuario);
        next = findViewById(R.id.next);
        next.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }
    public void  sendData () {
        TextView nombre = findViewById(R.id.nombre);
        TextView ci =findViewById(R.id.ci);
        TextView password = findViewById(R.id.propietario);
        TextView telefono = findViewById(R.id.telefono);


        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();
        params.add("nombre", nombre.getText().toString());
        params.add("ci", ci.getText().toString());
        params.add("password", password.getText().toString());
        params.add("telefono", telefono.getText().toString());


        client.post(Data.REGISTER_USURIOUS, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                AlertDialog alertDialog = new AlertDialog.Builder(RegistarUsuario.this).create();
                /*try {
                    String msn = response.getString("msn");
                    alertDialog.setTitle("RESPONSE SERVER");
                    alertDialog.setMessage(msn);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                Intent registrar= new Intent( RegistarUsuario.this, tablas.class);
                startActivity(registrar);

                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
            }
        });

    }
}
