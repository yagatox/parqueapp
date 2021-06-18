package com.example.alexander.tesis;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link Fragmento_Zona.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragmento_Zona#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento_Zona extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PlusOneButton mPlusOneButton;
    private Button zona1,zona2,zona3,zona4;

    private OnFragmentInteractionListener mListener;

    public Fragmento_Zona() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmento_Zona.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmento_Zona newInstance(String param1, String param2) {
        Fragmento_Zona fragment = new Fragmento_Zona();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento__zona, container, false);
        //Find the +1 button

        zona1=(Button)view.findViewById(R.id.zona1);
        zona2=(Button)view.findViewById(R.id.zona2);
        zona3=(Button)view.findViewById(R.id.zona3);
        zona4=(Button)view.findViewById(R.id.zona4);

        return view;
    }
    String nombre_arbol;
    String origen_arbol;
    String zona_arbol;
    String expecie_arbol;
    Intent intent;
    @Override
    public void onResume() {

        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.



        zona1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Fragment fragment = new Fragmento_Busqueda();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.pantalla_fragmento, new Fragmento_Busqueda()).commit();
                */
                intent = new Intent(getActivity(), Busqueda_Libre.class);
                Valores_de_uso_y_tipo.Filtro_por_zona_elegida="2";
                startActivity(intent);
            }
        });

        zona2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                Fragment fragment = new Fragmento_Busqueda();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.pantalla_fragmento, new Fragmento_Busqueda()).commit();
                */
                intent = new Intent(getActivity(), Busqueda_Libre.class);
                Valores_de_uso_y_tipo.Filtro_por_zona_elegida="3";
                startActivity(intent);
            }
        });

        zona3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                Fragment fragment = new Fragmento_Busqueda();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.pantalla_fragmento, new Fragmento_Busqueda()).commit();
                */
                intent = new Intent(getActivity(), Busqueda_Libre.class);
                Valores_de_uso_y_tipo.Filtro_por_zona_elegida="1";
                startActivity(intent);
            }
        });

        zona4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                Fragment fragment = new Fragmento_Busqueda();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.pantalla_fragmento, new Fragmento_Busqueda()).commit();
                */
                intent = new Intent(getActivity(), Busqueda_Libre.class);
                Valores_de_uso_y_tipo.Filtro_por_zona_elegida="4";
                startActivity(intent);
            }
        });


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
