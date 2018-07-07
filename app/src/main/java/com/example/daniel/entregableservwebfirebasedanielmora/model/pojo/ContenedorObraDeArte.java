package com.example.daniel.entregableservwebfirebasedanielmora.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ContenedorObraDeArte {

    @SerializedName("paints")
    private List<ObraDeArte> obras;

    public List<ObraDeArte> getObras() {
        return obras;
    }

    public void setObras(List<ObraDeArte> obras) {
        this.obras = obras;
    }

    public ContenedorObraDeArte(){

    }
}
