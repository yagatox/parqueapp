package com.example.alexander.tesis;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import uk.co.senab.photoview.PhotoViewAttacher;

public class Arbol extends AppCompatActivity {

ImageView foto_portada;
ImageView foto_escudo;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_busqueda:
                    setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_FULL_USER);
                    Valores_de_uso_y_tipo.Filtro_por_zona_elegida="ninguna";
                    foto_portada=(ImageView)findViewById (R.id.foto_portada);
                    foto_escudo=(ImageView)findViewById (R.id.foto_escudo);
                    foto_escudo.setVisibility( View.INVISIBLE);
                    startActivity(new Intent(Arbol.this,Arbol.class));
                    finish ();
                    startActivity(new Intent(Arbol.this,Busqueda_Libre.class));
                    return true;
                case R.id.navigation_home:
                    setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_FULL_USER);
                    foto_portada=(ImageView)findViewById (R.id.foto_portada);
                    foto_escudo=(ImageView)findViewById (R.id.foto_escudo);
                    foto_escudo.setVisibility( View.INVISIBLE );
                    Picasso.with(Arbol.this)
                            .load(R.drawable.frontis2)
                            .error(R.mipmap.ic_launcher)
                            .fit()
                            .into(foto_portada);

                    transaction.replace(R.id.pantalla_fragmento,new Fragmento_Zona()).commit();
                    return true;
                case R.id.navigation_desarrolladores:
                   /* foto_portada=(ImageView)findViewById (R.id.foto_portada);
                    foto_escudo=(ImageView)findViewById (R.id.foto_escudo);
                    foto_escudo.setVisibility( View.VISIBLE);
                    transaction.replace(R.id.pantalla_fragmento,new Fragmento_Desarrolladores()).commit();
                    Picasso.with(Arbol.this)
                            .load(R.drawable.udecc)
                            .error(R.mipmap.ic_launcher)
                            .fit()
                            .centerInside ()
                            .into(foto_portada);
                    Picasso.with(Arbol.this)
                            .load(R.drawable.logo)
                            .error(R.mipmap.ic_launcher)
                            .fit()
                            .centerInside ()
                            .into(foto_escudo);
                    setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    */

                    startActivity(new Intent(Arbol.this,Arbol.class));
                    finish ();
                    startActivity(new Intent(Arbol.this,Sobre_App.class));
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbol);
        foto_portada=(ImageView)findViewById (R.id.foto_portada);
        Picasso.with(Arbol.this)
                .load(R.drawable.frontis2)
                .error(R.mipmap.ic_launcher)
                .fit()
                .into(foto_portada);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }


}
