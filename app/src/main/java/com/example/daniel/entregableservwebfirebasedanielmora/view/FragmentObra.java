package com.example.daniel.entregableservwebfirebasedanielmora.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daniel.entregableservwebfirebasedanielmora.R;
import com.example.daniel.entregableservwebfirebasedanielmora.controller.ControllerObra;
import com.example.daniel.entregableservwebfirebasedanielmora.model.adapter.AdapterObraDeArte;
import com.example.daniel.entregableservwebfirebasedanielmora.model.pojo.ObraDeArte;
import com.example.daniel.entregableservwebfirebasedanielmora.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentObra extends Fragment {

    private AdapterObraDeArte adapterObraDeArte;
    private LinearLayoutManager linearLayoutManagerObra;
    private RecyclerView recyclerViewObra;
    private ControllerObra controllerObra;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_obra, container, false);

        adapterObraDeArte = new AdapterObraDeArte(getActivity());
        linearLayoutManagerObra = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewObra = view.findViewById(R.id.recycler_id);

        setAdapterLinear(recyclerViewObra, linearLayoutManagerObra, adapterObraDeArte);
        controllerObra = new ControllerObra(getActivity());
        obtenerObras();

        return view;
    }

    private void obtenerObras() {
        controllerObra.obtenerObrasOnLine(new ResultListener<List<ObraDeArte>>() {
            @Override
            public void finish(List<ObraDeArte> resultado) {
                adapterObraDeArte.agregarObras(resultado);
            }
        });
    }

    public void setAdapterLinear(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
