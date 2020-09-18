package com.saaveale.mascota.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.saaveale.mascota.pojo.Mascota;

import java.util.ArrayList;

public class BaseDato extends SQLiteOpenHelper {
    private Context context;

    public BaseDato(@Nullable Context context) {
        super(context, ConstanteBaseDato.DATABASE_NAME, null, ConstanteBaseDato.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota="CREATE TABLE "+ConstanteBaseDato.TABLE_MASCOTA+"("+
                                       ConstanteBaseDato.TABLE_MASCOTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                       ConstanteBaseDato.TABLE_MASCOTA_NOMBRE+" TEXT, "+
                                       ConstanteBaseDato.TABLE_MASCOTA_IMAGEN+" INTEGER, "+
                                       ConstanteBaseDato.TABLE_MASCOTA_RATING+" INTEGER, "+
                                       ConstanteBaseDato.TABLE_MASCOTA_FAVORITE+" INTEGER"+
                                       ")";
        db.execSQL(queryCrearTablaMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST "+ConstanteBaseDato.TABLE_MASCOTA);
        onCreate(db);
    }
    public ArrayList<Mascota> obtenerTodosLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query="SELECT * FROM "+ConstanteBaseDato.TABLE_MASCOTA;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor registros= db.rawQuery(query,null);
        while(registros.moveToNext()){
            Mascota mascotaActual= new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setImagen(registros.getInt(2));
            mascotaActual.setRating(registros.getInt(3));
            mascotaActual.setFavorite(registros.getInt(4)==1?true:false);

            mascotas.add(mascotaActual);
        }
        db.close();
        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db= this.getWritableDatabase();
        db.insert(ConstanteBaseDato.TABLE_MASCOTA,null,contentValues);
        db.close();
    }

    public int obtenerCantidadMascotaFavoritas(){
        int favoritos=0;
        String query="SELECT COUNT("+ConstanteBaseDato.TABLE_MASCOTA_ID+")"+
                " FROM "+ConstanteBaseDato.TABLE_MASCOTA;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);
        if(registros.moveToNext()){
            favoritos=registros.getInt(0);
        }
        db.close();
        return favoritos;
    }

    public int obtenerMascotaFavoritasMasVieja(){
        int id=0;
        String query="SELECT "+ConstanteBaseDato.TABLE_MASCOTA_ID+
                " FROM "+ConstanteBaseDato.TABLE_MASCOTA+
                " ORDER BY "+ConstanteBaseDato.TABLE_MASCOTA_ID+ " ASC"+
                " LIMIT 1";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);
        if(registros.moveToNext()){
            id=registros.getInt(0);
        }
        db.close();
        return id;
    }

    public int obtenerFavoritoMascota(String nombre){
        int favorito=0;
        String query="SELECT "+ConstanteBaseDato.TABLE_MASCOTA_FAVORITE+
                " FROM "+ConstanteBaseDato.TABLE_MASCOTA+
                " WHERE "+ConstanteBaseDato.TABLE_MASCOTA_NOMBRE+ "='"+nombre+"'";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);
        if(registros.moveToNext()){
            favorito=registros.getInt(0);
        }
        db.close();
        return favorito;
    }

    public void modificarMascota(ContentValues contentValues,int id){
        SQLiteDatabase db= this.getWritableDatabase();
        db.update(ConstanteBaseDato.TABLE_MASCOTA,contentValues,ConstanteBaseDato.TABLE_MASCOTA_ID+"=?",new String[] {String.valueOf(id)});
        db.close();
    }

    public void eliminarMascota(int id){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(ConstanteBaseDato.TABLE_MASCOTA,ConstanteBaseDato.TABLE_MASCOTA_ID+"=?",new String[] {String.valueOf(id)});
        db.close();
    }
    public void eliminarMascota(String nombre){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(ConstanteBaseDato.TABLE_MASCOTA,ConstanteBaseDato.TABLE_MASCOTA_NOMBRE+"=?",new String[] {nombre});
        db.close();
    }


}
