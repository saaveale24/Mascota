package com.saaveale.mascota;

import com.saaveale.mascota.pojo.Mascota;

import java.util.ArrayList;

public class Data {
    private static final int MAX_NUMBER=99;
    private static final int MIN_NUMBER=1;

    public static ArrayList<Mascota> mascotas= new ArrayList<Mascota>(){
        {
            add(new Mascota(R.drawable.ic_perro_1, "Wouff", 5, false));
            add(new Mascota(R.drawable.ic_gato_1, "Sr Bigotes", 3, false));
            add(new Mascota(R.drawable.ic_perro_2, "Doug", 0, false));
            add(new Mascota(R.drawable.ic_perro_3, "Perrencio", 1, false));
            add(new Mascota(R.drawable.ic_perro_4, "Tajash", 0, false));
            add(new Mascota(R.drawable.ic_parrot, "Tito", 0, false));
            add(new Mascota(R.drawable.ic_gato_2, "Muñungo", 0, false));
            add(new Mascota(R.drawable.ic_turtle, "Clementina", 0, false));
        }
    };
    public static ArrayList<Mascota> mascotafotos= new ArrayList<Mascota>(){
        {
            add(new Mascota(R.drawable.ic_perro_1, "", getRandomNumber(), false));
            add(new Mascota(R.drawable.ic_perro_1, "", getRandomNumber(), false));
            add(new Mascota(R.drawable.ic_perro_1, "", getRandomNumber(), false));
            add(new Mascota(R.drawable.ic_perro_1, "", getRandomNumber(), false));
            add(new Mascota(R.drawable.ic_perro_1, "", getRandomNumber(), false));
            add(new Mascota(R.drawable.ic_perro_1, "", getRandomNumber(), false));
            add(new Mascota(R.drawable.ic_perro_1, "", getRandomNumber(), false));
            add(new Mascota(R.drawable.ic_perro_1, "", getRandomNumber(), false));
            add(new Mascota(R.drawable.ic_perro_1, "", getRandomNumber(), false));
        }
    };
    public static int getItemFavorite(){
        int cnt=0;
        for (Mascota m:mascotas) {
            if(m.getFavorite()){
                cnt++;
            }
        }
        return cnt;
    }
    private static int getRandomNumber(){
        return (int) (Math.random() * (MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER);
    }

}
