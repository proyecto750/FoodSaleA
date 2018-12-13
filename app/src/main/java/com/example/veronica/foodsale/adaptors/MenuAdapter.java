package com.example.veronica.foodsale.adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.veronica.foodsale.R;
import com.example.veronica.foodsale.items.MenuResItem;
import com.example.veronica.foodsale.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {


    private Context context;
    private ArrayList<MenuResItem> listData;



    public MenuAdapter(Context context, ArrayList<MenuResItem> listData) {
        this.context = context;
        this.listData = listData;

    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu,parent,false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, final int position) {
        holder.setData(listData.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,listData.get(position),Toast.LENGTH_SHORT).show();
                //context.startActivity(new Intent(context,Main2Activity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nombre,precio,descripcion;
        ImageView foto;
        Button btnBorrar;
        String id,idRestaurant;
        private ConstraintLayout parentLayout;

        public MenuViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.textNombre);
            precio = itemView.findViewById(R.id.textPrecio);
            foto = itemView.findViewById(R.id.imageFoto);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);
            btnBorrar.setOnClickListener(this);
            //nombre = itemView.findViewById(R.id.textNombre);

            parentLayout = itemView.findViewById(R.id.parent_layout);

        }

        public void setData(MenuResItem item) {
            nombre.setText(item.getNombre());
            precio.setText(item.getPrecio().toString());
            Glide.with(context).load(Data.URL_IMG + item.getFoto()).into(foto);
            id = item.getId();

        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            // Add the buttons
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    deleteMenu();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            builder.setTitle("Esta seguro de eliminar el menu");

            AlertDialog dialog = builder.create();
            dialog.show();




            //Toast.makeText(context,id,Toast.LENGTH_LONG).show();
        }

        private void deleteMenu() {
            AsyncHttpClient client = new AsyncHttpClient();

            client.delete(Data.MENU_URL + id,new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        String message = response.getString("message");
                        if (message != null){
                            Toast.makeText(context,message,Toast.LENGTH_LONG).show();


                        }else   {
                            Toast.makeText(context,"Error al borrar",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            });
        }
    }

}
