package com.saaveale.mascota.presentador;

import android.content.Context;

import com.saaveale.mascota.IFavoriteActivityView;
import com.saaveale.mascota.db.ConstructorMascota;
import com.saaveale.mascota.pojo.Mascota;

import java.util.ArrayList;

public class FavoriteActivityPresenter implements IFavoriteActivityPresenter {
    private IFavoriteActivityView iFavoriteActivityView;
    private Context context;
    private ConstructorMascota constructorMascota;
    private ArrayList<Mascota> mascotas;

    public FavoriteActivityPresenter(IFavoriteActivityView iFavoriteActivityView, Context context) {
        this.iFavoriteActivityView=iFavoriteActivityView;
        this.context=context;
        obtenerMascotaBaseDato();
    }

    @Override
    public void obtenerMascotaBaseDato() {
        constructorMascota= new ConstructorMascota(context);
        mascotas= constructorMascota.obtenerDatos();
        mostrarMascotaRV();
    }

    @Override
    public void mostrarMascotaRV() {
        iFavoriteActivityView.inicializarAdaptadorRV(iFavoriteActivityView.crearAdaptador(mascotas));
        iFavoriteActivityView.generarLinearLayoutVertical();
    }
}
