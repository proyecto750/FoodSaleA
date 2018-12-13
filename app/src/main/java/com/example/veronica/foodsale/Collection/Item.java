package com.example.veronica.foodsale.Collection;

public class Item {
    public  String restaurant;
    public  String nombre;
    public String precio;
    public String descripcion;
    public int id;

    public  void setRestaurant(String restaurant){
        this.restaurant= restaurant;
    }
    public  void setNombre(String nombre){
        this.nombre= nombre;
    }
    public  void setPrecio(String precio){
        this.precio= precio;
    }
    public  void setDescripcion(String descripcion){
        this.descripcion= descripcion;
    }
    public  void setId(int id){
        this.id= id;
    }

    public int getId(){
        return this.id;
    }
    public String getRestaurant(){
        return  this.restaurant;
    }
    public String getNombre(){
        return  this.nombre;
    }
    public String getPrecio(){
        return this.precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
