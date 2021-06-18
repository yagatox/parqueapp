package com.example.alexander.tesis;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.health.TimerStat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.util.Timer;

public class Portada extends AppCompatActivity {
    private final int DURACION_SPLASH =4000; // 3 segundos



    @Override
    protected void onPostResume() {
        super.onPostResume();
        getDelegate().onPostResume();

        final int DURACION_SPLASH =3000;

        ConnectivityManager connectivity = (ConnectivityManager)getSystemService (Context.CONNECTIVITY_SERVICE);
        NetworkInfo info_wifi=connectivity.getNetworkInfo ( ConnectivityManager.TYPE_WIFI);
        NetworkInfo info_datos=connectivity.getNetworkInfo ( ConnectivityManager.TYPE_MOBILE);

        if (String.valueOf ( info_wifi.getState ()).equals ( "CONNECTED")) {
            Toast.makeText ( this,"Conectado Por Wifi",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed( new Runnable(){
                public void run(){
                    // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                    if (hasWindowFocus ()==true){
                        Intent intent = new Intent(Portada.this, Arbol.class);
                        startActivity(intent);
                        finish();
                    }

                    // finish();
                };
            }, DURACION_SPLASH);
        }
        else{

            if  (String.valueOf ( info_datos.getState ()).equals ( "CONNECTED")) {
                Toast.makeText ( this,"Conectado Por Datos Móviles, Se Recomienda Conexión Wifi",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed( new Runnable(){
                    public void run(){
                        // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                        if (hasWindowFocus ()==true){
                            Intent intent = new Intent(Portada.this, Arbol.class);
                            startActivity(intent);
                            finish();
                        }

                        // finish();
                    };
                }, DURACION_SPLASH);
            }

            else{
                Toast.makeText ( this,"¡No Tiene Conexión a Internet! La aplicación estará en segundo plano y solo iniciará con internet disponible",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed( new Runnable(){
                    public void run(){
                        // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                        if (hasWindowFocus ()==true){
                            finish();
                        }

                        // finish();
                    };
                }, DURACION_SPLASH);

            }
        }



    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Tenemos una plantilla llamada splash.xml donde mostraremos la información que queramos (logotipo, etc.)
        setContentView(R.layout.activity_portada);

        ImageView foto_portada=(ImageView)findViewById ( R.id.foto_portada );
        Picasso.with(Portada.this)
                .load(R.drawable.frontis2)
                .error(R.mipmap.ic_launcher)
                .fit()
                .into(foto_portada);


      /*  new Handler().postDelayed( new Runnable(){
            public void run(){
                // Cuando pasen los 2 segundos, pasamos a la actividad principal de la aplicación
                if (hasWindowFocus ()==true){
                    Intent intent = new Intent(Portada.this, Arbol.class);
                    startActivity(intent);
                    finish();
                }

               // finish();
            };
        }, DURACION_SPLASH);*/

        ConnectivityManager connectivity = (ConnectivityManager)getSystemService (Context.CONNECTIVITY_SERVICE);
        NetworkInfo info_wifi=connectivity.getNetworkInfo ( ConnectivityManager.TYPE_WIFI);
        NetworkInfo info_datos=connectivity.getNetworkInfo ( ConnectivityManager.TYPE_MOBILE);

        if (String.valueOf ( info_wifi.getState ()).equals ( "CONNECTED")) {
            Toast.makeText ( this,"Conectado Por Wifi",Toast.LENGTH_LONG).show();
            new Handler().postDelayed( new Runnable(){
                public void run(){
                    // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                    if (hasWindowFocus ()==true){
                        Intent intent = new Intent(Portada.this, Arbol.class);
                        startActivity(intent);
                        finish();
                    }

                    // finish();
                };
            }, DURACION_SPLASH);
        }
        else{

            if  (String.valueOf ( info_datos.getState ()).equals ( "CONNECTED")) {
                Toast.makeText ( this,"Conectado Por Datos Móviles, Se Recomienda Conexión Wifi",Toast.LENGTH_LONG).show();
                new Handler().postDelayed( new Runnable(){
                    public void run(){
                        // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                        if (hasWindowFocus ()==true){
                            Intent intent = new Intent(Portada.this, Arbol.class);
                            startActivity(intent);
                            finish();
                        }

                        // finish();
                    };
                }, DURACION_SPLASH);
            }

            else{
                Toast.makeText ( this,"¡No Tiene Conexión a Internet! La aplicación estará en segundo plano y solo iniciará con internet disponible",Toast.LENGTH_LONG).show();
                new Handler().postDelayed( new Runnable(){
                    public void run(){
                        // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                        if (hasWindowFocus ()==true){
                            finish();
                        }

                        // finish();
                    };
                }, DURACION_SPLASH);

            }
        }

    }



/*
    ImageButton boton_inicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_portada );
        boton_inicio=(ImageButton) findViewById ( R.id.boton_inicio );

        boton_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (Portada.this,Arbol.class));
            }
        });
    }*/
}
