package com.example.daniel.entregableservwebfirebasedanielmora.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragmentDetallePagerAdapterObra extends FragmentStatePagerAdapter {

    private List<FragmentDetalleObra> listaObrasDeArte;

    public FragmentDetallePagerAdapterObra(FragmentManager fm, List<FragmentDetalleObra> obras) {
        super(fm);
        this.listaObrasDeArte = obras;
    }

    @Override
    public Fragment getItem(int position) {
        return listaObrasDeArte.get(position);
    }

    @Override
    public int getCount() {
        return listaObrasDeArte.size();
    }
}
