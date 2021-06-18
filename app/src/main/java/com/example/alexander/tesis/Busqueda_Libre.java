package com.example.alexander.tesis;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
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

import java.util.ArrayList;
import java.util.Arrays;

public class Busqueda_Libre extends AppCompatActivity {

    private ArrayList<Persona> listapersonas;
    SearchView lector_de_texto;
    String idzona;
    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_busqueda__libre );

        lector_de_texto = (SearchView) findViewById ( R.id.lector_de_texto );
        // lector_de_texto.findFocus();
        listapersonas = new ArrayList <Persona> ();

        idzona = Valores_de_uso_y_tipo.Filtro_por_zona_elegida;
        Valores_de_uso_y_tipo.Filtro_por_zona_elegida = "ninguna";

        // listapersonas.add(new Persona("aromo","m","CORREA","Zona1"));

        /**INICIO CONEXIÓN MEDIANTE VOLLEY**/

        ConnectivityManager connectivity = (ConnectivityManager)getSystemService ( Context.CONNECTIVITY_SERVICE);
        NetworkInfo info_wifi=connectivity.getNetworkInfo ( ConnectivityManager.TYPE_WIFI);
        NetworkInfo info_datos=connectivity.getNetworkInfo ( ConnectivityManager.TYPE_MOBILE);

        if (String.valueOf ( info_wifi.getState ()).equals ( "CONNECTED")) {
        }
        else{

            if  (String.valueOf ( info_datos.getState ()).equals ( "CONNECTED")) {
            }

            else{
                Toast.makeText ( this,"¡Información no disponible! Intentar mas tarde. ",Toast.LENGTH_LONG).show();

            }
        }

        String consulta = "";

        listapersonas.clear ();
        if (idzona.equals ( "ninguna" )) {
            consulta = Constantes.GET_ARBOLES;
        } else {

            consulta = Constantes.GET_ZONA + idzona;
        }
        VolleySingleton.
                getInstance ( this ).
                addToRequestQueue (
                        new JsonObjectRequest (
                                Request.Method.GET,
                                consulta,
                                null,
                                new Response.Listener <JSONObject> () {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Procesar la respuesta Json
                                        procesarRespuesta ( response );
                                    }
                                },
                                new Response.ErrorListener () {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d ( "VOLLEY", "Error Volley: " + error.toString () );

                                    }


                                }

                        )
                );



        //FILTRO





        lector_de_texto.setOnQueryTextListener ( new SearchView.OnQueryTextListener () {


            @Override
            public boolean onQueryTextSubmit(String query) {
                listapersonas.clear ();
                 buscar ( query );

                return false;
            }

            @Override
            public boolean onQueryTextChange(String texsto) {
                listapersonas.clear ();
                 buscar ( texsto );

                return false;
            }

        } );
    }


    /**FIN CONEXIÓN MEDIANTE VOLLEY**/



public void buscar(String texto){



if(texto.length()>=1) {


    listapersonas.clear ();
   // Toast.makeText ( this,texto,Toast.LENGTH_LONG).show();

    String consulta = Constantes.GET_FILTRO + texto;
// Toast.makeText ( this,texto,Toast.LENGTH_LONG).show();
    consulta=consulta.replaceAll(" ", "%20");
    // Petición GET
    VolleySingleton.
            getInstance ( this ).
            addToRequestQueue (
                    new JsonObjectRequest (
                            Request.Method.GET,
                            consulta,
                            null,
                            new Response.Listener <JSONObject> () {

                                @Override
                                public void onResponse(JSONObject response) {
                                    // Procesar la respuesta Json
                                    procesarRespuesta ( response );
                                }
                            },
                            new Response.ErrorListener () {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d ( "VOLLEY", "Error Volley: " + error.toString () );
                                }
                            }

                    )
            );



}
///////////////////////////////////////////////////////////////////////////////
else{
    String consulta;
    listapersonas.clear ();
    if (idzona.equals ( "ninguna" )) { consulta = Constantes.GET_ARBOLES;}
    else{consulta = Constantes.GET_ZONA + idzona;}
    consulta=consulta.replaceAll(" ", "%20");
    // Petición GET
    VolleySingleton.
            getInstance ( this ).
            addToRequestQueue (
                    new JsonObjectRequest (
                            Request.Method.GET,
                            consulta,
                            null,
                            new Response.Listener <JSONObject> () {

                                @Override
                                public void onResponse(JSONObject response) {
                                    // Procesar la respuesta Json
                                    procesarRespuesta ( response );
                                }
                            },
                            new Response.ErrorListener () {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d ( "VOLLEY", "Error Volley: " + error.toString () );
                                }
                            }

                    )
            );

}




};



    /**
     * Interpreta los resultados de la respuesta para así
     * realizar las operaciones correspondientes
     *
     * @param response Objeto Json con la respuesta
     */
    public void procesarRespuesta(JSONObject response) {
        try {
            // Obtener atributo "estado"
            String estado = response.getString("estado");
            String mensaje2 ="";

            switch (estado) {
                case "1": // EXITO
                    // Obtener array "datos" Json
                    JSONArray arboles = response.getJSONArray("datos");
                    listapersonas.clear ();
                    //ciclo para llenar la lista de árboles
                    for (int i = 0; i < arboles.length(); i++) {
                        JSONObject arbol = arboles.getJSONObject(i);
                        Integer idarbol = Integer.parseInt(arbol.getString("idarbol"));
                        String nombre = arbol.getString("Nombre");
                        String genero = arbol.getString("Especie");
                        String origen = arbol.getString("Origen");
                        String z_elegida = "";

                        Valores_de_uso_y_tipo.Catidad_de_arboles=arboles.length ();
                        listapersonas.add(new Persona(nombre,genero,origen,z_elegida,idarbol));

                    }
                   //  if (arboles.length ()==0){listapersonas.clear ();}



                    //adaptador
                    AdaptadorPersonas adaptador = new AdaptadorPersonas(this);
                    ListView lv1 = (ListView)findViewById(R.id.lista_de_resultados);
                    lv1.setAdapter(adaptador);


                    lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Valores_de_uso_y_tipo.Id_numero_arbol_elegido=listapersonas.get(position).getIdarbol();
                            startActivity(new Intent(Busqueda_Libre.this,PantallaResultadoBusqueda.class));



                        }

                    });

                    break;
                case "0": // FALLIDO
                    listapersonas.clear ();
                    mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            this,
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;
            }

        } catch (JSONException e) {
            Log.d("VOLLEY", e.getMessage());
        }

    }






    class AdaptadorPersonas extends ArrayAdapter<Persona> {

        AppCompatActivity appCompatActivity;

        AdaptadorPersonas(AppCompatActivity context) {
            super(context, R.layout.persona, listapersonas);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.persona, null);

            TextView textView1 = (TextView)item.findViewById(R.id.textView);
            textView1.setText(listapersonas.get(position).getNombre());

            TextView textView2 = (TextView)item.findViewById(R.id.textView2);
            textView2.setText(listapersonas.get(position).getGenero ());

            TextView textView3 = (TextView)item.findViewById(R.id.textView3);
            textView3.setText(listapersonas.get(position).getZ_elegida());

            ImageView imageView1 = (ImageView)item.findViewById(R.id.imageView);

            Integer numero_de_arbol_para_foto=listapersonas.get(position).getIdarbol();

    Picasso.with ( Busqueda_Libre.this )
            .load ( "http://desarrollo.krma.cl/~campusudec/fotos/"+numero_de_arbol_para_foto+"a.png" )
            .error ( R.mipmap.ic_launcher )
            .fit ()
            .into ( imageView1 );
            return(item);
        }

    }
}
