package com.praksa.ucenik.jatolog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Izvor {

    @SerializedName("IzvorId")
    @Expose
    public int izvorId;
    @SerializedName("Naziv")
    @Expose
    public String naziv;
    @SerializedName("Autor")
    @Expose
    public String autor;
    @SerializedName("GodinaIzdavanja")
    @Expose
    public Integer godinaIzdavanja;
    @SerializedName("MjestoIzdavanja")
    @Expose
    public String mjestoIzdavanja;
    @SerializedName("Obrisan")
    @Expose
    public Boolean obrisan;

    public Izvor() {
    }

    public Izvor(int izvorId, String naziv, String autor, int godinaIzdavanja, String mjestoIzdavanja) {

        this.izvorId = izvorId;
        this.naziv = naziv;
        this.autor = autor;
        this.godinaIzdavanja = godinaIzdavanja;
        this.mjestoIzdavanja = mjestoIzdavanja;
    }

    public int getIzvorId() {
        return izvorId;
    }

    public void setIzvorId(int izvorId) {
        this.izvorId = izvorId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getGodinaIzdavanja() {
        return godinaIzdavanja;
    }

    public void setGodinaIzdavanja(int godinaIzdavanja) {
        this.godinaIzdavanja = godinaIzdavanja;
    }

    public String getMjestoIzdavanja() {
        return mjestoIzdavanja;
    }

    public void setMjestoIzdavanja(String mjestoIzdavanja) {
        this.mjestoIzdavanja = mjestoIzdavanja;
    }
}
