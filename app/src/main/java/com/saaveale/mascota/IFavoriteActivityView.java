package com.saaveale.mascota;

import com.saaveale.mascota.adapter.MascotaAdaptador;
import com.saaveale.mascota.pojo.Mascota;

import java.util.ArrayList;

public interface IFavoriteActivityView {
    public void generarLinearLayoutVertical();
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> contactos);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
