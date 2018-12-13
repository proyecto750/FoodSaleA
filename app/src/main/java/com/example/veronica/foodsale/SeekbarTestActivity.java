package com.example.veronica.foodsale;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SeekbarTestActivity extends AppCompatActivity {
    int current=1,precio = 25;;
    Dialog alertSeekbarDialog;
    TextView textSuma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar_test);
        textSuma = findViewById(R.id.textSuma);


    }

    public void click(View view) {
        Toast.makeText(this,"anything",Toast.LENGTH_LONG).show();
        alertSeekbarDialog = new Dialog(this);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View layout = inflater.inflate(R.layout.alert_seekbar_layout, (ViewGroup)findViewById(R.id.dialog_root_element));
        alertSeekbarDialog.setContentView(layout);
        final TextView result = layout.findViewById(R.id.textResult);
        Button dialogButton = layout.findViewById(R.id.btnDialog);
        SeekBar dialogSeekBar = (SeekBar)layout.findViewById(R.id.seekbarPrecio);


        dialogSeekBar.setMax(25);

        dialogSeekBar.setProgress(1);
        result.setText("Cantidad : " + current);


        dialogSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //add code here

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //add code here

            }

            @Override
            public void onProgressChanged(SeekBar seekBark, int progress, boolean fromUser) {
                //add code here
                current = progress;


                result.setText("Total : " +  precio+" * " + current + " = " + current * precio);

            }
        });
        alertSeekbarDialog.show();
    }

    public void seleccionar(View view) {

        if (current >0){
            textSuma.setText(" Precio : "+current * precio);
            alertSeekbarDialog.dismiss();
        }

    }
}
