package com.example.alexander.tesis;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_maps );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager ()
                .findFragmentById ( R.id.map );
        mapFragment.getMapAsync ( this );
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(googleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setZoomControlsEnabled(true);
       // String latitud=Valores_de_uso_y_tipo.Cordenada_de_map_latitud;
       // String longitud=Valores_de_uso_y_tipo.Cordenada_de_map_longitud;
        String Nombre_arobol_map=Valores_de_uso_y_tipo.Nombre_del_arbol;
        double latitud = Double.parseDouble(Valores_de_uso_y_tipo.Cordenada_de_map_latitud);
        double longitud = Double.parseDouble(Valores_de_uso_y_tipo.Cordenada_de_map_longitud);

        LatLng udecchillan = new LatLng(-36.596517, -72.086051);
        LatLng alamo = new LatLng(latitud, longitud);
      //  LatLng canelo = new LatLng(-36.59664620518064, -72.08424855554199);
     //   LatLng cydro = new LatLng(-36.597593703227666, -72.08532143914795);
      //  LatLng araucaria = new LatLng(-36.597369750193906, -72.08296109521484);
      //  LatLng eucaliptus = new LatLng(-36.596387794603, -72.08120156610107);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(udecchillan, 18));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(alamo, 18));
     /*   mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(canelo, 18));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cydro, 18));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(araucaria, 18));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eucaliptus, 18));
*/

        MarkerOptions Mudec = new MarkerOptions().title("Universidad de Concepción, campus Chillán").position(udecchillan);
        MarkerOptions Malamo = new MarkerOptions().title(Nombre_arobol_map).position(alamo).icon( BitmapDescriptorFactory.fromResource(R.drawable.marcador1));
        //MarkerOptions Mcanelo = new MarkerOptions().title("Canelo").position(canelo).icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador2));
       // MarkerOptions Mcydro = new MarkerOptions().title("Cydro").position(cydro).icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador3));
        //MarkerOptions Maraucaria = new MarkerOptions().title("Araucaria").position(araucaria).icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador4));
       // MarkerOptions Meucaliptus = new MarkerOptions().title("Eucaliptus").position(eucaliptus).icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador5));

        // mMap.addMarker(Mudec);
        mMap.addMarker(Malamo);
      /*  mMap.addMarker(Mcanelo);
        mMap.addMarker(Mcydro);
        mMap.addMarker(Maraucaria);
        mMap.addMarker(Meucaliptus);
           */

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(udecchillan)
                .zoom(17)
                .tilt(60)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
