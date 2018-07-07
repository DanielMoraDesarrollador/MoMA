package com.example.daniel.entregableservwebfirebasedanielmora.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daniel.entregableservwebfirebasedanielmora.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleObra extends Fragment {


    public FragmentDetalleObra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_obra, container, false);
        return view;
    }

}
