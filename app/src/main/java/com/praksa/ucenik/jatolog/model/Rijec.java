package com.praksa.ucenik.jatolog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ucenik on 09.04.2018..
 */

public class Rijec {

    @SerializedName("EkavicaLatinica")
    @Expose
    private String ekavicaLatinica;

    @SerializedName("EkavicaCirilica")
    @Expose
    private String ekavicaCirilica;

    @SerializedName("IjekavicaLatinica")
    @Expose
    private String ijekavicaLatinica;

    @SerializedName("IjekavicaCirilica")
    @Expose
    private String ijekavicaCirilica;

    @SerializedName("Izvor")
    @Expose
    private String izvor;

    @SerializedName("Napomena")
    @Expose
    private String napomena;

    @SerializedName("Predlozio")
    @Expose
    private String predlozio;

    @SerializedName("Primjer")
    @Expose
    private String primjer;

    @SerializedName("VrstaRijeciId")
    @Expose
    private int vrstaRijeciId;

    public Rijec() {

    }

    public Rijec(String ekavicaLatinica, String ekavicaCirilica, String ijekavicaLatinica, String ijekavicaCirilica, String izvor, String napomena, String predlozio, String primjer, int vrstaRijeciId) {
        this.ekavicaLatinica = ekavicaLatinica;
        this.ekavicaCirilica = ekavicaCirilica;
        this.ijekavicaLatinica = ijekavicaLatinica;
        this.ijekavicaCirilica = ijekavicaCirilica;
        this.izvor = izvor;
        this.napomena = napomena;
        this.predlozio = predlozio;
        this.primjer = primjer;
        this.vrstaRijeciId = vrstaRijeciId;
    }

    public String getEkavicaLatinica() {
        return ekavicaLatinica;
    }

    public void setEkavicaLatinica(String ekavicaLatinica) {
        this.ekavicaLatinica = ekavicaLatinica;
    }

    public String getEkavicaCirilica() {
        return ekavicaCirilica;
    }

    public void setEkavicaCirilica(String ekavicaCirilica) {
        this.ekavicaCirilica = ekavicaCirilica;
    }

    public String getIjekavicaLatinica() {
        return ijekavicaLatinica;
    }

    public void setIjekavicaLatinica(String ijekavicaLatinica) {
        this.ijekavicaLatinica = ijekavicaLatinica;
    }

    public String getIjekavicaCirilica() {
        return ijekavicaCirilica;
    }

    public void setIjekavicaCirilica(String ijekavicaCirilica) {
        this.ijekavicaCirilica = ijekavicaCirilica;
    }

    public String getIzvor() {
        return izvor;
    }

    public void setIzvor(String izvor) {
        this.izvor = izvor;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getPredlozio() {
        return predlozio;
    }

    public void setPredlozio(String predlozio) {
        this.predlozio = predlozio;
    }

    public String getPrimjer() {
        return primjer;
    }

    public void setPrimjer(String primjer) {
        this.primjer = primjer;
    }

    public int getVrstaRijeciId() {
        return vrstaRijeciId;
    }

    public void setVrstaRijeciId(int vrstaRijeciId) {
        this.vrstaRijeciId = vrstaRijeciId;
    }
}

