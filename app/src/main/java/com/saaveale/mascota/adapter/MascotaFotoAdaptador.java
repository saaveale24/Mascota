package com.saaveale.mascota.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.saaveale.mascota.MainActivity;
import com.saaveale.mascota.R;
import com.saaveale.mascota.pojo.Mascota;

import java.util.ArrayList;

public class MascotaFotoAdaptador extends RecyclerView.Adapter<MascotaFotoAdaptador.MascotaFotoViewHolder> {
    ArrayList<Mascota> mascotafotos;
    Activity activity;

    public MascotaFotoAdaptador(ArrayList<Mascota> mascotafotos, Activity activity) {
        this.mascotafotos = mascotafotos;
        this.activity=activity;
    }

    @NonNull
    @Override
    public MascotaFotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_foto,parent,false);
        return new MascotaFotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaFotoViewHolder contactoViewHolder, int position) {
        final Mascota mascota=mascotafotos.get(position);
        Log.e("prueba",String.valueOf(mascota.getImagen()));
        contactoViewHolder.imgFotoFoto.setImageResource(mascota.getImagen());
        contactoViewHolder.tvRatingFoto.setText(String.valueOf(mascota.getRating()));
    }

    @Override
    public int getItemCount() {
        return mascotafotos.size();
    }

    public static class MascotaFotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFotoFoto;
        private TextView tvRatingFoto;
        private ImageView imgRatingFoto;

        public MascotaFotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFotoFoto=(ImageView) itemView.findViewById(R.id.imgFotoFoto);
            tvRatingFoto=(TextView) itemView.findViewById(R.id.tvRatingFoto);
            imgRatingFoto=(ImageView) itemView.findViewById(R.id.imgRatingFoto);
        }
    }
}
