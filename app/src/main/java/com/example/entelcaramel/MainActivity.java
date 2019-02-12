package com.example.entelcaramel;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner miSpinnerCaramelo, miSpinnerEnvoltorio;
    private ImageView bola;
    private MediaPlayer sonido;
    private Typeface tipoLetra;
    private TextView txtCaramelo, txtEnvoltorio, txt;
    String [] listaCaramelos, listaEnvoltorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bola = findViewById(R.id.imageViewBola);

        txtEnvoltorio = findViewById(R.id.textViewEligeEnvoltorio);
        txtCaramelo = findViewById(R.id.textViewEligeCaramelo);
        txt = findViewById(R.id.txt);


        String tipoFuente = "fuentes/tipo1.ttf";
        this.tipoLetra = Typeface.createFromAsset(getAssets(),tipoFuente);
        txtEnvoltorio.setTypeface(tipoLetra);
        txtCaramelo.setTypeface(tipoLetra);
        txtEnvoltorio.setTextSize(24);
        txtCaramelo.setTextSize(24);



        //Spinner Envoltorio
        miSpinnerEnvoltorio = findViewById(R.id.spinnerEnvoltorio);
        listaEnvoltorios = getResources().getStringArray(R.array.colores);
        ArrayAdapter<String> adapterEnvoltorio = new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,listaEnvoltorios);
        miSpinnerEnvoltorio.setAdapter(adapterEnvoltorio);

        //Spinner Caramelo
        miSpinnerCaramelo = findViewById(R.id.spinnerCaramelo);
        listaCaramelos = getResources().getStringArray(R.array.sabores);
        ArrayAdapter<String> adapterCaramelo = new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,listaCaramelos);
        miSpinnerCaramelo.setAdapter(adapterCaramelo);



        miSpinnerCaramelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: Toast.makeText(getApplicationContext(),listaCaramelos[0].toString(),Toast.LENGTH_LONG).show();
                    bola.setImageResource(R.drawable.bolaroja);
                    break;
                    case 1: Toast.makeText(getApplicationContext(),listaCaramelos[1].toString(),Toast.LENGTH_LONG).show();
                        bola.setImageResource(R.drawable.bolaverde);
                        break;
                    case 2: Toast.makeText(getApplicationContext(),listaCaramelos[2].toString(),Toast.LENGTH_LONG).show();
                        bola.setImageResource(R.drawable.bolaazul);
                        break;
                    case 3: Toast.makeText(getApplicationContext(),listaCaramelos[3].toString(),Toast.LENGTH_LONG).show();
                        bola.setImageResource(R.drawable.bolanaranja);
                        break;
                    case 4: Toast.makeText(getApplicationContext(),listaCaramelos[4].toString(),Toast.LENGTH_LONG).show();
                        bola.setImageResource(R.drawable.bolavioleta);
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
                    case 0: Toast.makeText(getApplicationContext(),listaEnvoltorios[0].toString(),Toast.LENGTH_LONG).show();
                    break;
                    case 1: Toast.makeText(getApplicationContext(),listaEnvoltorios[1].toString(),Toast.LENGTH_LONG).show();
                        break;
                    case 2: Toast.makeText(getApplicationContext(),listaEnvoltorios[2].toString(),Toast.LENGTH_LONG).show();
                        break;
                    case 3: Toast.makeText(getApplicationContext(),listaEnvoltorios[3].toString(),Toast.LENGTH_LONG).show();
                        break;
                    case 4: Toast.makeText(getApplicationContext(),listaEnvoltorios[4].toString(),Toast.LENGTH_LONG).show();
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sonido = MediaPlayer.create(getApplicationContext(),R.raw.dramatic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Ojo que viene !!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                sonido.start();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
