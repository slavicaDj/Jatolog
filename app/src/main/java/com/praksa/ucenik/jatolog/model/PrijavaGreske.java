package com.praksa.ucenik.jatolog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrijavaGreske {

    @SerializedName("EkavicaId")
    @Expose
    private int ekavicaId;
    @SerializedName("IjekavicaId")
    @Expose
    private int ijekavicaId;
    @SerializedName("Napomena")
    @Expose
    private String napomena;
    @SerializedName("EmailPosiljaoca")
    @Expose
    private String emailPosiljaoca;

    public PrijavaGreske() {
    }

    public PrijavaGreske(int ekavicaId, int ijekavicaId, String napomena, String emailPosiljaoca) {
        this.ekavicaId = ekavicaId;
        this.ijekavicaId = ijekavicaId;
        this.napomena = napomena;
        this.emailPosiljaoca = emailPosiljaoca;
    }

    public int getEkavicaId() {
        return ekavicaId;
    }

    public void setEkavicaId(int ekavicaId) {
        this.ekavicaId = ekavicaId;
    }

    public int getIjekavicaId() {
        return ijekavicaId;
    }

    public void setIjekavicaId(int ijekavicaId) {
        this.ijekavicaId = ijekavicaId;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getEmailPosiljaoca() {
        return emailPosiljaoca;
    }

    public void setEmail(String emailPosiljaoca) {
        this.emailPosiljaoca = emailPosiljaoca;
    }
}