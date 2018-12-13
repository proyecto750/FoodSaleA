package com.example.veronica.foodsale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.veronica.foodsale.Collection.Item;
import com.example.veronica.foodsale.Collection.ListAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class seleccionar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar);


    }

    private void loadComponents()
    {
        final ListView list =(ListView)this.findViewById(R.id.list_main);
        final ArrayList<Item> list_data = new ArrayList<Item>();

        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://192.168.42.239:8888/", new JsonHttpResponseHandler()

        {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){

                try
                {

                    JSONArray data = response.getJSONArray("data");

                    for(int i=0; i< data.length(); i++){
                        Item p= new Item();
                        JSONObject object = data.getJSONObject(i);
                        p.id=i;
                        p.restaurant= object.getString("restaurant");
                        p.nombre= object.getString("nombre");
                        p.precio= object.getString("precio");
                        p.descripcion= object.getString("descripcion");
                        list_data.add(p);
                    }

                    ListAdapter adapter = new ListAdapter(seleccionar.this, list_data);
                    list.setAdapter(adapter);

                } catch (JSONException e)
                {
                    e.printStackTrace();

                }


            }

            public void onFailure(int statusCode , Header[] headers, Throwable throwable,JSONObject errorResponse){
            }
        });


    }
}
