package com.praksa.ucenik.jatolog.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brojac {
    @SerializedName("EkavicaId")
    @Expose
    private int ekavicaId;
    @SerializedName("Inkrement")
    @Expose
    private int inkrement;

    public Brojac(){
        super();
    }

    public Brojac(int ekavicaId){
        this.ekavicaId = ekavicaId;
        inkrement = 1;
    }

    public void increment(){
        ++inkrement;
    }

    public int getEkavicaId() {
        return ekavicaId;
    }

    public void setEkavicaId(int ekavicaId) {
        this.ekavicaId = ekavicaId;
    }

    public int getInkrement() {
        return inkrement;
    }

    @Override
    public String toString() {
        return "Brojac{" +
                "ekavicaId=" + ekavicaId +
                ", broj=" + inkrement +
                '}';
    }
}
