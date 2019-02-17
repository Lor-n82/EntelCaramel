package com.example.entelcaramel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.entelcaramel.Objetos.Caramelos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Estadisticas extends AppCompatActivity {

    private DatabaseReference fireDB;
    private RecyclerView rv;
    private ArrayList<Caramelos> caramelos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        rv = (RecyclerView)findViewById(R.id.rvEstadisticas);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new GridLayoutManager(this,1));

        caramelos = new ArrayList<Caramelos>();
        caramelos.clear();

        fireDB = FirebaseDatabase.getInstance().getReference().child("caramelos");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot nodo : dataSnapshot.getChildren()) {
                    String key = nodo.getKey(); //estos son los valores que buscas
                    String sabor1 = nodo.child("sabor").getValue().toString(); //estos son los valores que buscas
                    String envoltorio1 = nodo.child("envoltorio").getValue().toString(); //estos son los valores que buscas

                    Toast.makeText(getApplicationContext(),"El envoltorio es "+ envoltorio1 +" y el sabor es "+ sabor1,Toast.LENGTH_LONG).show();

                    for(int r = 0;r < caramelos.size(); r++ ){
                        caramelos.add(new Caramelos(
                                caramelos.get(r).getEnvoltorio(),
                                caramelos.get(r).getSabor()));
                    }

                    RecyclerView.Adapter adaptador = new CaramelosAdapter(caramelos);
                    rv.setAdapter(adaptador);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
            fireDB.addListenerForSingleValueEvent(eventListener);
    }
}

