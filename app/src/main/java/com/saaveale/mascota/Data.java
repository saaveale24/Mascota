package com.saaveale.mascota;

import java.util.ArrayList;

public class Data {
    public static ArrayList<Mascota> mascotas= new ArrayList<Mascota>(){
        {
            add(new Mascota(R.drawable.ic_perro_1, "Wouff", 5, false));
            add(new Mascota(R.drawable.ic_gato_1, "Sr Bigotes", 3, false));
            add(new Mascota(R.drawable.ic_perro_2, "Doug", 0, false));
            add(new Mascota(R.drawable.ic_perro_3, "Perrencio", 1, false));
            add(new Mascota(R.drawable.ic_perro_4, "Tajash", 0, false));
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
}
