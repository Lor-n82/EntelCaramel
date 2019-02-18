package com.example.entelcaramel.Objetos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entelcaramel.R;

public class CaramelosViewHolder extends RecyclerView.ViewHolder {

   public ImageView envoltorio;
    public ImageView caramelo;

    public CaramelosViewHolder(View itemView) {
        super(itemView);
        envoltorio = itemView.findViewById(R.id.img_envoltorio);
        caramelo = itemView.findViewById(R.id.img_caramelo);

    }
}
