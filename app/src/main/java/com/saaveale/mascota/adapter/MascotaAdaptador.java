package com.saaveale.mascota.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
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
import com.saaveale.mascota.pojo.Mascota;
import com.saaveale.mascota.R;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity=activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder contactoViewHolder, int position) {
        final Mascota mascota=mascotas.get(position);
        contactoViewHolder.imgFoto.setImageResource(mascota.getImagen());
        contactoViewHolder.tvNombre.setText(mascota.getNombre());
        contactoViewHolder.tvRating.setText(String.valueOf(mascota.getRating()));

        Drawable dw= (mascota.getFavorite()?ContextCompat.getDrawable(activity, R.drawable.ic_favbone):
                ContextCompat.getDrawable(activity, R.drawable.ic_unbone));
        contactoViewHolder.btnLike.setImageDrawable(dw);

        if(activity.getClass()== MainActivity.class) {
            contactoViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mascota.favorite();
                    contactoViewHolder.tvRating.setText(String.valueOf(mascota.getRating()));
                    Drawable dw = (mascota.getFavorite() ? ContextCompat.getDrawable(activity, R.drawable.ic_favbone) :
                            ContextCompat.getDrawable(activity, R.drawable.ic_unbone));
                    contactoViewHolder.btnLike.setImageDrawable(dw);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvNombre;
        private ImageButton btnLike;
        private TextView tvRating;
        private ImageView imgRating;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto=(ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombre=(TextView) itemView.findViewById(R.id.tvNombre);
            btnLike=(ImageButton) itemView.findViewById(R.id.btnLike);
            tvRating=(TextView) itemView.findViewById(R.id.tvRating);
            imgRating=(ImageView) itemView.findViewById(R.id.imgRating);
        }
    }
}
