package com.example.daniel.entregableservwebfirebasedanielmora.model.dao;

import com.example.daniel.entregableservwebfirebasedanielmora.model.pojo.ContenedorObraDeArte;
import com.example.daniel.entregableservwebfirebasedanielmora.model.pojo.ObraDeArte;
import com.example.daniel.entregableservwebfirebasedanielmora.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaoObraDeArte {
    private Retrofit retrofit;
    private ServiceDao serviceRetrofit;
    private static final String BASE_URL = "https://api.myjson.com/";
 //   private static final String BASE_URL = "https://entregableservwebfirebasedanie.firebaseio.com/";

    public DaoObraDeArte() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient.build()).build();
        serviceRetrofit = retrofit.create(ServiceDao.class);
    }

    public void obtenerObrasDeArte(final ResultListener<List<ObraDeArte>> resultListenerDelController) {
        Call<ContenedorObraDeArte> call = serviceRetrofit.obtenerObras();
        call.enqueue(new Callback<ContenedorObraDeArte>() {
            @Override
            public void onResponse(Call<ContenedorObraDeArte> call, Response<ContenedorObraDeArte> response) {
                List<ObraDeArte> obrasObtenidas = response.body().getObras();
                resultListenerDelController.finish(obrasObtenidas);
            }

            @Override
            public void onFailure(Call<ContenedorObraDeArte> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<ObraDeArte>());
            }
        });
    }
}
