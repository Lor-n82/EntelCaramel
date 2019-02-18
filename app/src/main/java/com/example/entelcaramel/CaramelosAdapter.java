package com.example.entelcaramel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
                parent.getContext()).inflate(R.layout.list_item_caramelo,parent,false);
        return new CaramelosViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CaramelosViewHolder holder, int i) {
        switch (caramelos.get(i).getEnvoltorio()){
            case "Rojo":
                holder.envoltorio.setImageResource(R.drawable.bolaroja);
                break;
            case "Verde":
                holder.envoltorio.setImageResource(R.drawable.bolaverde);
                break;
            case "Azul":
                holder.envoltorio.setImageResource(R.drawable.bolaazul);
                break;
            case "Naranja":
                holder.envoltorio.setImageResource(R.drawable.bolanaranja);
                break;
            case "Violeta":
                holder.envoltorio.setImageResource(R.drawable.bolavioleta);
                break;
            case "Gris":
                holder.envoltorio.setImageResource(R.drawable.bolagris);
                break;
        }
        switch (caramelos.get(i).getSabor()){
            case "Rojo":
                holder.caramelo.setImageResource(R.drawable.envoltoriorojo);
                break;
            case "Verde":
                holder.caramelo.setImageResource(R.drawable.envoltorioverde);
                break;
            case "Azul":
                holder.caramelo.setImageResource(R.drawable.envoltorioazul);
                break;
            case "Naranja":
                holder.caramelo.setImageResource(R.drawable.envoltorionaranja);
                break;
            case "Violeta":
                holder.caramelo.setImageResource(R.drawable.envoltoriovioleta);
                break;
            case "Gris":
                holder.caramelo.setImageResource(R.drawable.envoltoriogris);
                break;
        }
    }

    @Override
    public int getItemCount() { return this.caramelos.size(); }
}
