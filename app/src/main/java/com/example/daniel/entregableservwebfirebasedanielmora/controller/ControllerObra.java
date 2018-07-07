package com.example.daniel.entregableservwebfirebasedanielmora.controller;

import android.content.Context;

import com.example.daniel.entregableservwebfirebasedanielmora.model.dao.DaoObraDeArte;
import com.example.daniel.entregableservwebfirebasedanielmora.model.pojo.ObraDeArte;
import com.example.daniel.entregableservwebfirebasedanielmora.utils.ResultListener;

import java.util.List;

public class ControllerObra {

    private Context context;
    private Integer offset;

    public ControllerObra(Context context) {
        this.context = context;
        offset = 0;
    }


    private boolean hayInternet() {
        return true;
    }

    public void obtenerObrasOnLine(final ResultListener<List<ObraDeArte>> resultListenerDeLaVista) {
        if (hayInternet()) {
            DaoObraDeArte daoObraDeArte = new DaoObraDeArte();
            daoObraDeArte.obtenerObrasDeArte(new ResultListener<List<ObraDeArte>>() {
                @Override
                public void finish(List<ObraDeArte> resultado) {
                    resultListenerDeLaVista.finish(resultado);
                }
            });
        }
    }
}
