package com.example.entelcaramel.Objetos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.entelcaramel.R;

public class CaramelosViewHolder extends RecyclerView.ViewHolder {

    public TextView textoEnvoltorioT;
    public TextView textoSaborT;
    public TextView textoEnvoltorioR;
    public TextView textoSaborR;

    public CaramelosViewHolder(View itemView) {
        super(itemView);
        textoEnvoltorioR = itemView.findViewById(R.id.textViewEnvoltorioR);
        textoEnvoltorioT = itemView.findViewById(R.id.textViewEnvoltorioR);
        textoSaborR = itemView.findViewById(R.id.textViewEnvoltorioR);
        textoSaborT = itemView.findViewById(R.id.textViewEnvoltorioR);
    }
}
