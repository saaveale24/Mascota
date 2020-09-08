package com.saaveale.mascota.pojo;

import java.io.Serializable;

public class Mascota implements Serializable {
    private String nombre;
    private int imagen;
    private int rating;
    private boolean favorite;

    public Mascota(int imagen, String nombre, int rating,boolean favorite) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.rating = rating;
        this.favorite=favorite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void favorite(){
        this.favorite=!this.favorite;
        if(this.favorite){
            this.rating++;
        }else{
            this.rating--;
        }
    }
}
