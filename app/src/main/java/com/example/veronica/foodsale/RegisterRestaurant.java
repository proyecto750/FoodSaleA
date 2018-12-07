package com.example.veronica.foodsale;

import android.content.Context;
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

import org.json.JSONArray;

import java.io.IOException;
import java.util.Date;
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();

            }
        });



    }
    public void  sendData (){
        TextView name = findViewById(R.id.name);
        TextView nit= findViewById(R.id.nit);
        TextView street = findViewById(R.id.street);
        TextView property = findViewById(R.id.property);
        TextView telf = findViewById(R.id.telf);

        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);
        RequestParams params = new RequestParams();
        params.add("name", name.getText().toString());
        params.add("nit", nit.getText().toString());
        params.add("street", street.getText().toString());
        params.add("property", property .getText().toString());
        params.add("phone", telf.getText().toString());
        params.add("Lat", String.valueOf(mainposition.latitude));
        params.add("Log", String.valueOf(mainposition.longitude));

      // client.post(Data.REGISTER_RESTAURANT, params, new JsonHttpResponseHandler() {
         //  public void  onSuccess(int statusCode)

       //}

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
