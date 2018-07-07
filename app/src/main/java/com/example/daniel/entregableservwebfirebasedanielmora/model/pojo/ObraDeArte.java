package com.example.daniel.entregableservwebfirebasedanielmora.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ObraDeArte implements Serializable {

    @SerializedName("name")
    private String nombreObra;

    private String image;

    private String artistId;



    public ObraDeArte() {
    }

    public String getNombreObra() {
        return nombreObra;
    }




    @Override
    public String toString() {
        return "ObraDeArte{" +
                "nombreObra='" + nombreObra +
                '}';
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
}
