package com.example.entelcaramel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.entelcaramel.Objetos.Caramelos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Estadisticas extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<Caramelos> caramelos;
    private ArrayList<ArrayList> total = new ArrayList<>();
    private String[] colores = {"Rojo", "Verde", "Azul", "Naranja", "Violeta", "Gris"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        rv =  findViewById(R.id.rvEstadisticas);
        rv.setLayoutManager(new GridLayoutManager(this, 1));

        caramelos = new ArrayList<>();


        DatabaseReference fireDB = FirebaseDatabase.getInstance().getReference().child("caramelos");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot nodo : dataSnapshot.getChildren()) {
                    String sabor1="";
                    Object obj = nodo.child("sabor").getValue();
                    if(obj!=null) {
                        sabor1 = obj.toString(); //estos son los valores que buscas
                    }
                    String envoltorio1="";
                     obj = nodo.child("envoltorio").getValue();
                    if(obj!=null) {
                         envoltorio1 =obj.toString(); //estos son los valores que buscas
                    }
                    Caramelos ca = new Caramelos();
                    ca.setEnvoltorio(envoltorio1);
                    ca.setSabor(sabor1);
                    caramelos.add(ca);

                }

                divideCaramels(caramelos);
                drawCaramels();
//                caramelos.clear();
            }

            private void divideCaramels(ArrayList<Caramelos> cara) {
                for (String color : colores) {
                    ArrayList<Caramelos> caramelColor = new ArrayList<>();
                    for (Caramelos caramelos : cara) {
                        if (caramelos.getEnvoltorio().equalsIgnoreCase(color)) {
                            caramelColor.add(caramelos);
                        }
                    }
                    total.add(caramelColor);
                }
            }

            private void drawCaramels(){
                CaramelosAdapter caramelosAdapter = new CaramelosAdapter(caramelos);
                rv.setAdapter(caramelosAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        fireDB.addListenerForSingleValueEvent(eventListener);
    }
}

