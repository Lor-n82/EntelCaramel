package com.example.entelcaramel;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.entelcaramel.Objetos.Caramelos;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner miSpinnerCaramelo, miSpinnerEnvoltorio;
    private ImageView bola, envoltorio;
    private MediaPlayer sonido;
    String [] listaCaramelos, listaEnvoltorios;
    DatabaseReference caramelosDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtCaramelo, txtEnvoltorio;
        FirebaseApp.initializeApp(this) ;
        caramelosDB = FirebaseDatabase.getInstance().getReference("caramelos");

        bola = findViewById(R.id.imageViewBola);
        envoltorio = findViewById(R.id.imageViewEnvoltorio);

        txtEnvoltorio = findViewById(R.id.textViewEligeEnvoltorio);
        txtCaramelo = findViewById(R.id.textViewEligeCaramelo);

        txtEnvoltorio.setTextSize(20);
        txtCaramelo.setTextSize(20);

        //Spinner Envoltorio
        miSpinnerEnvoltorio = findViewById(R.id.spinnerEnvoltorio);
        listaEnvoltorios = getResources().getStringArray(R.array.colores);
        ArrayAdapter<String> adapterEnvoltorio = new ArrayAdapter<>(this,R.layout.spinner_layout,R.id.txt,listaEnvoltorios);
        miSpinnerEnvoltorio.setAdapter(adapterEnvoltorio);
        miSpinnerEnvoltorio.getPopupBackground().setAlpha(111);

        //Spinner Caramelo
        miSpinnerCaramelo = findViewById(R.id.spinnerCaramelo);
        listaCaramelos = getResources().getStringArray(R.array.sabores);
        ArrayAdapter<String> adapterCaramelo = new ArrayAdapter<>(this,R.layout.spinner_layout,R.id.txt,listaCaramelos);
        miSpinnerCaramelo.setAdapter(adapterCaramelo);
        miSpinnerCaramelo.getPopupBackground().setAlpha(111);

        miSpinnerCaramelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        bola.setImageResource(R.drawable.bolaroja);
                        break;
                    case 1:
                        bola.setImageResource(R.drawable.bolaverde);
                        break;
                    case 2:
                        bola.setImageResource(R.drawable.bolaazul);
                        break;
                    case 3:
                        bola.setImageResource(R.drawable.bolanaranja);
                        break;
                    case 4:
                        bola.setImageResource(R.drawable.bolavioleta);
                        break;
                    case 5:
                        bola.setImageResource(R.drawable.bolagris);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        miSpinnerEnvoltorio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        envoltorio.setImageResource(R.drawable.envoltoriorojo);
                    break;
                    case 1:
                        envoltorio.setImageResource(R.drawable.envoltorioverde);
                        break;
                    case 2:
                        envoltorio.setImageResource(R.drawable.envoltorioazul);
                        break;
                    case 3:
                        envoltorio.setImageResource(R.drawable.envoltorionaranja);
                        break;
                    case 4:
                        envoltorio.setImageResource(R.drawable.envoltoriovioleta);
                        break;
                    case 5:
                        envoltorio.setImageResource(R.drawable.envoltoriogris);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sonido = MediaPlayer.create(getApplicationContext(),R.raw.dramatic);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recojo el valor del item seleccionado en los Spinners
                String envoltorio = miSpinnerEnvoltorio.getSelectedItem().toString();
                String sabor = miSpinnerCaramelo.getSelectedItem().toString();

                //Enviamos a la DB
                String newid = caramelosDB.push().getKey();
                Caramelos caramelo = new Caramelos(envoltorio,sabor);
                if(newid!=null) {
                    caramelosDB.child(newid).setValue(caramelo);
                }

                Snackbar.make(view, "Datos enviados", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                sonido.start();

                Intent intento = new Intent(getApplicationContext(), Estadisticas.class);
                startActivity(intento);

            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
