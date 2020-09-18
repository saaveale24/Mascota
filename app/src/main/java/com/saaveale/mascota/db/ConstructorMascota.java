package com.saaveale.mascota.db;

import android.content.ContentValues;
import android.content.Context;

import com.saaveale.mascota.R;
import com.saaveale.mascota.pojo.Mascota;

import java.util.ArrayList;

public class ConstructorMascota {
    private static final int LIKE=1;
    private Context context;
    public ConstructorMascota(Context context) {
        this.context=context;
    }

    public ArrayList<Mascota> obtenerDatos(){
        BaseDato db= new BaseDato(context);
        return db.obtenerTodosLasMascotas();
    }
    public void insertarMascotaFavorita(Mascota mascota){
        BaseDato db= new BaseDato(context);

        //Se busca la cantidad de mascotas favoritas
        int cmf=db.obtenerCantidadMascotaFavoritas();

        ContentValues contentValues= new ContentValues();
        contentValues.put(ConstanteBaseDato.TABLE_MASCOTA_NOMBRE,mascota.getNombre());
        contentValues.put(ConstanteBaseDato.TABLE_MASCOTA_IMAGEN,mascota.getImagen());
        contentValues.put(ConstanteBaseDato.TABLE_MASCOTA_RATING,mascota.getRating());
        contentValues.put(ConstanteBaseDato.TABLE_MASCOTA_FAVORITE,mascota.getFavorite()?1:0);
        if(cmf>=5){
            //Se busca la mascota mas vieja y se elimina
            int imv=db.obtenerMascotaFavoritasMasVieja();
            db.eliminarMascota(imv);

            //Se inserta la nueva mascota
            db.insertarMascota(contentValues);
        }else{
            db.insertarMascota(contentValues);
        }
    }
    public void eliminarMascotaFavorita(Mascota mascota) {
        BaseDato db = new BaseDato(context);
        db.eliminarMascota(mascota.getNombre());
    }

    public int obtenerFavoritoMascota(Mascota mascota){
        BaseDato db= new BaseDato(context);
        return db.obtenerFavoritoMascota(mascota.getNombre());
    }
}
