package com.example.daniel.entregableservwebfirebasedanielmora.view;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.daniel.entregableservwebfirebasedanielmora.R;
import com.example.daniel.entregableservwebfirebasedanielmora.controller.ControllerObra;
import com.example.daniel.entregableservwebfirebasedanielmora.model.adapter.AdapterObraDeArte;
import com.example.daniel.entregableservwebfirebasedanielmora.model.pojo.ObraDeArte;
import com.example.daniel.entregableservwebfirebasedanielmora.utils.ResultListener;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.List;

public class ActivitySecundaria extends AppCompatActivity implements AdapterObraDeArte.NotificadorCelda {

    private AdapterObraDeArte adapterObraDeArte;
    private LinearLayoutManager linearLayoutManagerObra;
    private RecyclerView recyclerViewObra;
    private ControllerObra controllerObra;
    private Button cerrarSesionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secundaria_activity);

        cerrarSesionButton = findViewById(R.id.cerrar_sesion_button_id);
        cerrarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        //si es que esta logueado con facebook, tengo que desloguearlo
                        LoginManager.getInstance().logOut();
                    }
                    //esto es para desloguearlo de firebase, ya se que entro con facebook o nativo
                    FirebaseAuth.getInstance().signOut();
                    onBackPressed();
                }
            }
        });


        adapterObraDeArte = new AdapterObraDeArte(getApplicationContext(),this);
        linearLayoutManagerObra = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewObra = findViewById(R.id.recycler_id);

        setAdapterLinear(recyclerViewObra, linearLayoutManagerObra, adapterObraDeArte);
        controllerObra = new ControllerObra(getApplicationContext());
        obtenerObras();
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

    @Override
    public void notificarCeldaCliqueada(List<ObraDeArte> obrasDeArte, int posicion) {
        Intent intent = new Intent(ActivitySecundaria.this, ActivityDetalle.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ActivityDetalle.OBRA_KEY, (Serializable) obrasDeArte);
        bundle.putInt(ActivityDetalle.POSICION_KEY, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
