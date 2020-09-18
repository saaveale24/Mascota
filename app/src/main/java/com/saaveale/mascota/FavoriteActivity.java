package com.saaveale.mascota;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saaveale.mascota.adapter.MascotaAdaptador;
import com.saaveale.mascota.pojo.Mascota;
import com.saaveale.mascota.presentador.FavoriteActivityPresenter;
import com.saaveale.mascota.presentador.IFavoriteActivityPresenter;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity implements IFavoriteActivityView {
    private RecyclerView listaMascotaFavorita;
    private IFavoriteActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_activity);

        Toolbar miActionBar=(Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotaFavorita=(RecyclerView)findViewById(R.id.rvMascotaFavorita);
        presenter= new FavoriteActivityPresenter(this,this.getBaseContext());

    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotaFavorita.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> contactos) {
        MascotaAdaptador adaptador= new MascotaAdaptador(contactos,this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotaFavorita.setAdapter(adaptador);
    }
}