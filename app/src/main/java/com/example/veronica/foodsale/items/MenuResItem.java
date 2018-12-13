package com.example.veronica.foodsale.items;

public class MenuResItem {
    private String restaurant,nombre,descripcion,foto,id;
    private Double precio;

    public MenuResItem(String restaurant, String nombre, String foto, Double precio, String id) {
        this.restaurant = restaurant;
        this.nombre = nombre;
        this.descripcion = "descripcion";
        this.foto = foto;
        this.precio = precio;
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
