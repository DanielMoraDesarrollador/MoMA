package com.example.daniel.entregableservwebfirebasedanielmora.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Artista implements Serializable {

    private String id;

    @SerializedName("name")
    private String nombreArtista;

    @SerializedName("nationality")
    private String nacionalidad;

    @SerializedName("Influenced_by")
    private String influenciadoPor;

    public Artista() {
    }

    public String getId() {
        return id;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getInfluenciadoPor() {
        return influenciadoPor;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id='" + id + '\'' +
                ", nombreArtista='" + nombreArtista + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", influenciadoPor='" + influenciadoPor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Artista)) {
            return false;
        }
        Artista artistaAComparar = (Artista) obj;
        return artistaAComparar.getId().equals(this.id);
    }
}
