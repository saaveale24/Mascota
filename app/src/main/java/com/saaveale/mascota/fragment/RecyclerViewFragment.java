package com.saaveale.mascota.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saaveale.mascota.Data;
import com.saaveale.mascota.R;
import com.saaveale.mascota.adapter.MascotaAdaptador;

public class RecyclerViewFragment extends Fragment {
    private RecyclerView listaMascota;
    MascotaAdaptador adaptador=null;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_recyclerview,container,false);

        listaMascota=(RecyclerView)v.findViewById(R.id.rvMascota);
        LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascota.setLayoutManager(llm);
        inicializarAdaptador();

        return v;
    }
    public void inicializarAdaptador(){
        MascotaAdaptador adaptador= new MascotaAdaptador(Data.mascotas,getActivity());
        listaMascota.setAdapter(adaptador);
    }
}