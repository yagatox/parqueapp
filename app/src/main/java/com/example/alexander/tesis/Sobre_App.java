package com.example.alexander.tesis;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoViewAttacher;

public class Sobre_App extends AppCompatActivity {
PhotoViewAttacher photoViewAttacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_sobre__app );
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView foto_escudo_sobre_app=(ImageView)findViewById (R.id.foto_escudo_sobre_app);
        ImageView foto_antigua_frontis=(ImageView)findViewById (R.id.foto_antigua_frontis);
        Picasso.with(Sobre_App.this)
                .load(R.drawable.logo)
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerInside ()
                .into(foto_escudo_sobre_app);

        Picasso.with(Sobre_App.this)
                .load(R.drawable.frontris60)
                .error(R.mipmap.ic_launcher)
                .fit()
                .into(foto_antigua_frontis);
        photoViewAttacher=new PhotoViewAttacher (foto_antigua_frontis);
    }
}
