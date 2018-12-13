package com.example.veronica.foodsale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class tablas extends AppCompatActivity {
    Button siguiente1;
    Button siguiente2;
    Button siguiente3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablas);

        siguiente1 = (Button)findViewById(R.id.btn1);
        siguiente2= (Button)findViewById(R.id.btn2);
        siguiente3 = (Button)findViewById(R.id.btn3);

        siguiente1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente1= new Intent(tablas.this, seleccionar.class);
                startActivity(siguiente1);
            }
        });
        siguiente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente2 = new Intent(tablas.this, seleccionar.class);
                startActivity(siguiente2);
            }
        });
        siguiente3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente3 = new Intent(tablas.this, seleccionar.class);
                startActivity(siguiente3);
            }
        });
    }
}
