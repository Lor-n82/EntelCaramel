package com.example.entelcaramel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.entelcaramel.Objetos.Caramelos;
import com.example.entelcaramel.Objetos.CaramelosViewHolder;

import java.util.ArrayList;

public class CaramelosAdapter extends RecyclerView.Adapter<CaramelosViewHolder> {

    private ArrayList<Caramelos> caramelos;

    CaramelosAdapter(ArrayList<Caramelos> caramelos){
        this.caramelos=caramelos;
    }

    @NonNull
    @Override
    public CaramelosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View vista = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.list_item_caramelos,parent,false);
        return new CaramelosViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CaramelosViewHolder holder, int i) {
       holder.textoEnvoltorioR.setText(this.caramelos.get(i).getEnvoltorio());
       holder.textoSaborR.setText(this.caramelos.get(i).getSabor());
    }

    @Override
    public int getItemCount() { return this.caramelos.size(); }
}
