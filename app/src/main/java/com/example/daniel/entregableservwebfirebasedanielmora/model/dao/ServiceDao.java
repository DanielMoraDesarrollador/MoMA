package com.example.daniel.entregableservwebfirebasedanielmora.model.dao;


import com.example.daniel.entregableservwebfirebasedanielmora.model.pojo.ContenedorObraDeArte;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceDao {

    @GET("bins/x858r")
    Call<ContenedorObraDeArte>obtenerObras();
}
