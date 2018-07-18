package com.example.daniel.entregableservwebfirebasedanielmora.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daniel.entregableservwebfirebasedanielmora.R;
import com.example.daniel.entregableservwebfirebasedanielmora.model.pojo.ObraDeArte;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetalle
        extends AppCompatActivity {

    public static final String OBRA_KEY = "lista_obras";
    public static final String POSICION_KEY = "posicion_key";
    private List<FragmentDetalleObra> listaDeFragments;
    private List<ObraDeArte> listaDeFragmentsRecibida;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        viewPager = findViewById(R.id.view_pager_detalle);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        listaDeFragmentsRecibida = (List<ObraDeArte>) bundle.getSerializable(OBRA_KEY);
        crearListaDeFragments();

        FragDetallePAdObra fragDetallePAdObra = new FragDetallePAdObra(
                getSupportFragmentManager(),
                listaDeFragments);

        viewPager.setAdapter(fragDetallePAdObra);
        int posicioDelItem = bundle.getInt(POSICION_KEY);
        viewPager.setCurrentItem(posicioDelItem);
    }

    public void crearListaDeFragments() {
        listaDeFragments = new ArrayList<>();
        for (ObraDeArte obraDeArte : listaDeFragmentsRecibida) {
            listaDeFragments.add(FragmentDetalleObra.dameUnFragment(obraDeArte));
        }
    }
}
