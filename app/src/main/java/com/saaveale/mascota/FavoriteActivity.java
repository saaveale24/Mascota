package com.saaveale.mascota;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saaveale.mascota.adapter.MascotaAdaptador;
import com.saaveale.mascota.pojo.Mascota;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView listaMascotaFavorita;
    private ArrayList<Mascota> mascotasFavorita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_activity);

        Toolbar miActionBar=(Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mascotasFavorita= new ArrayList<Mascota>();

        for (Mascota m:Data.mascotas) {
            if(m.getFavorite()) {
                mascotasFavorita.add(m);
            }
        }


        listaMascotaFavorita=(RecyclerView)findViewById(R.id.rvMascotaFavorita);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotaFavorita.setLayoutManager(llm);
        inicializarAdaptador();
    }
    public void inicializarAdaptador(){
        MascotaAdaptador adaptador= new MascotaAdaptador(mascotasFavorita,this);
        listaMascotaFavorita.setAdapter(adaptador);
    }
}