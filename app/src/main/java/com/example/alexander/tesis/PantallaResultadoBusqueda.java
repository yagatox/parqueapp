
package com.example.alexander.tesis;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.alexander.tesis.ws.Constantes;
import com.example.alexander.tesis.ws.VolleySingleton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PantallaResultadoBusqueda extends AppCompatActivity {

    private TextView mTextMessage;
    Integer validar=0;
    TextView nombre_comun;
    TextView nombre_cientifico;
    TextView origen_arbol;
    TextView zona_ubicada;
    TextView detalles_de_arbol;
    TextView nombre_cientifico_titulo;
    TextView in_situ_titulo;
    TextView origen_titulo;
    TextView usos_del_arbol;
    TextView usos_titulo;
    TextView mapa_titulo;
    TextView detalles_titulo;
    TextView division;
    TextView division_titulo;
    TextView familia;
    TextView familia_titulo;
    ImageView foto_simulada;
    ImageButton map;
    String idzona;
    private Gson gson = new Gson();

    PhotoViewAttacher photoViewAttacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_resultado_busqueda);

        mTextMessage = (TextView) findViewById(R.id.message);
      //  BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        nombre_comun=(TextView)findViewById(R.id.nombre_comun);
        nombre_cientifico=(TextView)findViewById(R.id.nombre_cientifico);
        origen_arbol=(TextView)findViewById(R.id.origen_arbol);
        zona_ubicada=(TextView)findViewById(R.id.zona_ubicada);
        detalles_de_arbol=(TextView)findViewById(R.id.detalles_de_arbol);
        usos_del_arbol=(TextView)findViewById(R.id.usos_del_arbol);
        usos_titulo=(TextView)findViewById(R.id.usos_titulo);
        detalles_titulo=(TextView)findViewById(R.id.detalles_titulo);
        in_situ_titulo=(TextView)findViewById(R.id.in_situ_titulo);
        origen_titulo=(TextView)findViewById(R.id.origen_titulo);
        nombre_cientifico_titulo=(TextView)findViewById(R.id.nombre_cientifico_titulo);
        division=(TextView)findViewById(R.id.division);
        division_titulo=(TextView)findViewById(R.id.division_titulo);
        familia=(TextView)findViewById(R.id.familia);
        familia_titulo=(TextView)findViewById(R.id.familia_titulo);
        mapa_titulo=(TextView)findViewById(R.id.mapa_titulo);

        foto_simulada=(ImageView) findViewById(R.id.foto_simulada);
        map=(ImageButton)findViewById ( R.id.map);


        mapa_titulo.setVisibility( View.INVISIBLE );
        map.setVisibility( View.INVISIBLE );
        usos_titulo.setVisibility( View.INVISIBLE );
        nombre_cientifico_titulo.setVisibility( View.INVISIBLE );
        origen_titulo.setVisibility( View.INVISIBLE );
        in_situ_titulo.setVisibility( View.INVISIBLE );
        division_titulo.setVisibility( View.INVISIBLE );
        familia_titulo.setVisibility( View.INVISIBLE );
        detalles_titulo.setVisibility( View.INVISIBLE );
        int numero_de_arbol=Valores_de_uso_y_tipo.Id_numero_arbol_elegido;


        map.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

               startActivity ( new Intent ( PantallaResultadoBusqueda.this,MapsActivity.class ) );

            }
        } );

        /**INICIO CONEXIÓN MEDIANTE VOLLEY**/

        String consulta = "";

        consulta = Constantes.GET_FULL_DATOS_ARBOL+numero_de_arbol;
        Log.d("VOLLEY", consulta);
        // Petición GET
        VolleySingleton.
                getInstance(this).
                addToRequestQueue(
                        new JsonObjectRequest (
                                Request.Method.GET,
                                consulta,
                                null,
                                new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Procesar la respuesta Json
                                        Log.d("VOLLEY", response.toString ());
                                        procesarRespuesta(response);
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d("VOLLEY", "Error Volley: " + error.toString());
                                    }
                                }

                        )
                );


        ConnectivityManager connectivity = (ConnectivityManager)getSystemService ( Context.CONNECTIVITY_SERVICE);
        NetworkInfo info_wifi=connectivity.getNetworkInfo ( ConnectivityManager.TYPE_WIFI);
        NetworkInfo info_datos=connectivity.getNetworkInfo ( ConnectivityManager.TYPE_MOBILE);

        if (String.valueOf ( info_wifi.getState ()).equals ( "CONNECTED")) {
            Toast.makeText ( this,"La Imagen Aparecera En Breve",Toast.LENGTH_SHORT).show();
            Picasso.with ( PantallaResultadoBusqueda.this )
                    .load ( "http://desarrollo.krma.cl/~campusudec/fotos/"+numero_de_arbol+"b.png" )
                    .error ( R.mipmap.ic_launcher )
                    .fit ()
                    .into ( foto_simulada );


            photoViewAttacher=new PhotoViewAttacher (foto_simulada);
        }
        else{

            if  (String.valueOf ( info_datos.getState ()).equals ( "CONNECTED")) {
                Toast.makeText ( this,"La Imagen Aparecera En Breve",Toast.LENGTH_SHORT).show();
                Picasso.with ( PantallaResultadoBusqueda.this )
                        .load ( "http://desarrollo.krma.cl/~campusudec/fotos/"+numero_de_arbol+"b.png" )
                        .error ( R.mipmap.ic_launcher )
                        .fit ()
                        .into ( foto_simulada );


                photoViewAttacher=new PhotoViewAttacher (foto_simulada);
            }

            else{
                Toast.makeText ( this,"Error, Información No Encontrada",Toast.LENGTH_LONG).show();
                Picasso.with ( PantallaResultadoBusqueda.this )
                        .load ( R.drawable.blanco )
                        .error ( R.mipmap.ic_launcher )
                        .fit ()
                        .into ( foto_simulada );
            }
        }

    }


    /**FIN CONEXIÓN MEDIANTE VOLLEY**/






    /**
     * Interpreta los resultados de la respuesta para así
     * realizar las operaciones correspondientes
     *
     * @param response Objeto Json con la respuesta
     */
    private void procesarRespuesta(JSONObject response) {
        try {
            // Obtener atributo "estado"
            String estado = response.getString ( "estado" );
            String mensaje2 = "";

            switch (estado) {
                case "1": // EXITO
                    // Obtener array "datos" Json
                    JSONArray arboles = response.getJSONArray ( "datos" );
                    JSONArray zonas = response.getJSONArray ( "zona" );
                    Log.d ( "DATOS", arboles.toString () );

                    //ciclo para llenar la lista de árboles


                    JSONObject arbol = arboles.getJSONObject ( 0 );
                    Log.d ( "DATOS", arbol.toString () );
                    Integer idarbol = Integer.parseInt ( arbol.getString ( "idarbol" ) );
                    nombre_comun.setText ( arbol.getString ( "Nombre" ) );
                    nombre_cientifico.setText ( arbol.getString ( "Especie" ) );
                    division.setText ( arbol.getString ( "Division" ) );
                    familia.setText ( arbol.getString ( "Familia" ) );
                    String nombre_expecie= arbol.getString ( "Especie" );
                    origen_arbol.setText ( arbol.getString ( "Origen" ) );
                    String latitud= String.format (arbol.getString ( "latitud" ));
                    String longitud= String.format (arbol.getString ( "longitud" ));
                    usos_del_arbol.setText ( arbol.getString ( "Usos" ) );
                    // zona_ubicada.setText (arbol.getString("zona"));

                    detalles_de_arbol.setText ( arbol.getString ( "Caracteristicas" ) );




                    if (nombre_comun.getText ().toString () == "null" || nombre_comun.getText ().toString () == "NULL" || nombre_comun.getText ().toString () == "Null") {
                        nombre_comun.setText ( "" );

                    }

                    if (nombre_cientifico.getText ().toString () == "null" || nombre_cientifico.getText ().toString () == "NULL" || nombre_cientifico.getText ().toString () == "Null") {
                        nombre_cientifico.setText ( "" );

                    } else {
                        nombre_cientifico_titulo.setVisibility ( View.VISIBLE );
                    }
                    if (division.getText ().toString () == "null" || division.getText ().toString () == "NULL" || division.getText ().toString () == "Null") {
                        division.setText ( "" );

                    } else {
                        division_titulo.setVisibility ( View.VISIBLE );
                    }

                    if (familia.getText ().toString () == "null" || familia.getText ().toString () == "NULL" || familia.getText ().toString () == "Null") {
                        familia.setText ( "" );

                    } else {
                        familia_titulo.setVisibility ( View.VISIBLE );
                    }

                    if (origen_arbol.getText ().toString () == "null" || origen_arbol.getText ().toString () == "NULL" || origen_arbol.getText ().toString () == "Null") {
                        origen_arbol.setText ( "" );

                    } else {
                        origen_titulo.setVisibility ( View.VISIBLE );
                    }

                    if (usos_del_arbol.getText ().toString () == "null" || usos_del_arbol.getText ().toString () == "NULL" || usos_del_arbol.getText ().toString () == "Null") {
                        usos_del_arbol.setText ( "" );
                    } else {
                        usos_titulo.setVisibility ( View.VISIBLE );
                    }
                    if (detalles_de_arbol.getText ().toString () == "null" || detalles_de_arbol.getText ().toString () == "NULL" || detalles_de_arbol.getText ().toString () == "Null") {
                        detalles_de_arbol.setText ( "" );
                    } else {
                        detalles_titulo.setVisibility ( View.VISIBLE );
                    }

                    if (familia.getText ().toString () == "null" || familia.getText ().toString () == "NULL" || familia.getText ().toString () == "Null" || familia.getText ().toString () == "") {
                        familia.setText ( "" );

                    } else {
                        familia_titulo.setVisibility ( View.VISIBLE );
                    }

                    if (latitud == "null" || latitud == "NULL" || latitud  == "Null" || latitud == "" || longitud == "null" || longitud == "NULL" || longitud  == "Null" || longitud == "" ) {
                        //no dejara pasar sin mapa
                    } else {
                        Valores_de_uso_y_tipo.Cordenada_de_map_latitud=latitud;
                        Valores_de_uso_y_tipo.Cordenada_de_map_longitud=longitud;
                        Valores_de_uso_y_tipo.Nombre_del_arbol= nombre_expecie;
                        mapa_titulo.setVisibility( View.VISIBLE );
                        map.setVisibility( View.VISIBLE );
                    }


                    Valores_de_uso_y_tipo.Catidad_de_arboles = arboles.length ();

                    String z = "";
                    for (int i = 0; i < zonas.length (); i++) {
                        JSONObject zona = zonas.getJSONObject ( i );
                        z += zona.getString ( "nombre" ) + ", ";
                    }

                    z += "@@@";
                    z = z.replace ( ", @@@", "" );

                    zona_ubicada.setText ( z );

                    if (zona_ubicada.getText ().toString () == "null" || zona_ubicada.getText ().toString () == "NULL" || zona_ubicada.getText ().toString () == "Null") {
                        zona_ubicada.setText ( "" );
                    } else {
                        in_situ_titulo.setVisibility ( View.VISIBLE );
                    }

                    break;
                case "0": // FALLIDO
                    mensaje2 = response.getString ( "mensaje" );
                    Toast.makeText (
                            this,
                            mensaje2,
                            Toast.LENGTH_LONG ).show ();
                    break;
            }

        } catch (JSONException e) {
            Log.d ( "VOLLEY", e.getMessage () );


        }




    }

}


