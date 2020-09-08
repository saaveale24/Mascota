package com.saaveale.mascota.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saaveale.mascota.Data;
import com.saaveale.mascota.R;
import com.saaveale.mascota.adapter.MascotaAdaptador;
import com.saaveale.mascota.adapter.MascotaFotoAdaptador;

public class PerfilFragment extends Fragment {
    private RecyclerView listaMascotaFoto;
    MascotaAdaptador adaptador=null;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_perfil,container,false);

        listaMascotaFoto=(RecyclerView)v.findViewById(R.id.rvMascotaFoto);
        GridLayoutManager llm=new GridLayoutManager(getActivity(),3);
        listaMascotaFoto.setLayoutManager(llm);
        inicializarAdaptador();

        return v;
    }
    public void inicializarAdaptador(){
        MascotaFotoAdaptador adaptador= new MascotaFotoAdaptador(Data.mascotafotos,getActivity());
        listaMascotaFoto.setAdapter(adaptador);
    }
}