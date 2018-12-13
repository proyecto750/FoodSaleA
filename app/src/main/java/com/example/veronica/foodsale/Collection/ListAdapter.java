package com.example.veronica.foodsale.Collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.veronica.foodsale.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<Item> items;
    public ListAdapter(Context context, ArrayList<Item> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int i) {
        return this.items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.items.get(i).getId();
    }



    @Override

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_ui, null);
        }
        TextView    restaurante = (TextView)view.findViewById(R.id.restaurante);
        TextView nombre = (TextView)view.findViewById(R.id.nombre);
        TextView precio = (TextView)view.findViewById(R.id.precio) ;
        TextView descripcion = (TextView)view.findViewById(R.id.descripcion);
        restaurante.setText(this.items.get(i).getRestaurant());
        nombre.setText(this.items.get(i).getNombre());
        precio.setText(this.items.get(i).getPrecio());
        descripcion.setText(this.items.get(i).getDescripcion());
        return  view;


    }
}
