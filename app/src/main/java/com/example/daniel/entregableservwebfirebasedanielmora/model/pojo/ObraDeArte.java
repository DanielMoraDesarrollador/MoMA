package com.example.daniel.entregableservwebfirebasedanielmora.model.pojo;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ObraDeArte implements Serializable {

    @PropertyName("name")
    @SerializedName("name")
    private String nombreObra;

    private String image;

    private String artistId;

    public ObraDeArte() {
    }

    @PropertyName("artistId")
    public String getArtistId() {
        return artistId;
    }

    @PropertyName("name")
    public String getNombreObra() {
        return nombreObra;
    }

    @PropertyName("image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "ObraDeArte{" +
                "nombreObra='" + nombreObra +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ObraDeArte)) {
            return false;
        }
        ObraDeArte obraDeArteAComparar = (ObraDeArte) obj;
        return obraDeArteAComparar.getNombreObra().equals(this.nombreObra);
    }
}
