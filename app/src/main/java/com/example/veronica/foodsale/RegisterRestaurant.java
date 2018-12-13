package com.example.veronica.foodsale;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.veronica.foodsale.utils.Data;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class RegisterRestaurant extends AppCompatActivity implements OnMapReadyCallback {
    private MapView map;
    private  GoogleMap nMap;
    private Geocoder geocoder;
    private TextView street;
    private Button next;
    private LatLng mainposition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_restaurant);
        map = findViewById(R.id.mapView);
        map.onCreate(savedInstanceState);
        map.onResume();
        MapsInitializer.initialize(this);
        map.getMapAsync(this );

        geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
        street = findViewById(R.id.street);
        next = findViewById(R.id.next);
        next.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });


    }
    public void  sendData (){
        TextView nombre = findViewById(R.id.nombre);
        TextView nit= findViewById(R.id.ci);
        TextView street = findViewById(R.id.street);
        TextView propietario = findViewById(R.id.propietario);
        TextView telefono = findViewById(R.id.telefono);

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("authorization", Data.TOKEN);

        RequestParams params = new RequestParams();
        params.add("nombre", nombre.getText().toString());
        params.add("nit", nit.getText().toString());
        params.add("street",street.getText().toString());
        params.add("propietario", propietario.getText().toString());
        params.add("telefono", telefono.getText().toString());
        params.add("lat", String.valueOf(mainposition.latitude));
        params.add("log", String.valueOf(mainposition.longitude));


        client.post(Data.REGISTER_RESTAURANT, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                AlertDialog alertDialog = new AlertDialog.Builder(RegisterRestaurant.this).create();
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
                Intent camera = new Intent( RegisterRestaurant.this, CameraPhoto.class);
                startActivity(camera);

                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        nMap = googleMap;

        LatLng potosi = new LatLng(-19.5783329, -65.7563853);
        mainposition = potosi;
        nMap.addMarker(new MarkerOptions().position(potosi).title("Lugar").zIndex(17).draggable(true));
        nMap.setMinZoomPreference(16);
        nMap.moveCamera(CameraUpdateFactory.newLatLng(potosi));
        nMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                mainposition = marker.getPosition();
                String street_string = getStreet(marker.getPosition().latitude, marker.getPosition().longitude);
                street.setText(street_string);

            }
        });


    }
    public String  getStreet (Double lat, Double lon){
        List<Address> address;
        String result = "";
        try {
            address = geocoder.getFromLocation(lat, lon, 2);

            for (int i=0; i < address.size(); i ++)  {
                result += address.get(i).getThoroughfare() +",";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
