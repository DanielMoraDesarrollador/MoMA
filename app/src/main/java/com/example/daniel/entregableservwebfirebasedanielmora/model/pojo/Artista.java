package com.example.daniel.entregableservwebfirebasedanielmora.model.pojo;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Artista implements Serializable {

    @PropertyName("artistId")
    private String id;

    @SerializedName("name")
    @PropertyName("name")
    private String nombreArtista;


    @SerializedName("nationality")
    @PropertyName("nationality")
    private String nacionalidad;

    @SerializedName("Influenced_by")
    @PropertyName("Influenced_by")
    private String influenciadoPor;

    public Artista() {
    }

    public String getId() {
        return id;
    }

    @PropertyName("name")
    public String getNombreArtista() {
        return nombreArtista;
    }

    @PropertyName("nationality")
    public String getNacionalidad() {
        return nacionalidad;
    }

    @PropertyName("Influenced_by")
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
