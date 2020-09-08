package com.praksa.ucenik.jatolog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ijekavica {

    @SerializedName("IjekavicaId")
    @Expose
    private int ijekavicaId;
    @SerializedName("EkavicaId")
    @Expose
    private Integer ekavicaId;
    @SerializedName("Ekavica")
    @Expose
    private Ekavica ekavica;
    @SerializedName("IjekavicaLatinica")
    @Expose
    private String ijekavicaLatinica;
    @SerializedName("IjekavicaCirilica")
    @Expose
    private String ijekavicaCirilica;
    @SerializedName("VrstaRijeciId")
    @Expose
    public Integer vrstaRijeciId;
    @SerializedName("VrstaRijeci")
    @Expose
    public VrstaRijeci vrstaRijeci;
    @SerializedName("IzvorId")
    @Expose
    public Integer izvorId;
    @SerializedName("Izvor")
    @Expose
    public Izvor izvor;
    @SerializedName("KorisnikIdMijenjao")
    @Expose
    public Integer korisnikIdMijenjao;
    @SerializedName("Korisnik")
    @Expose
    public Object korisnik;
    @SerializedName("OpisLatinica")
    @Expose
    private String opisLatinica;
    @SerializedName("OpisCirilica")
    @Expose
    private String opisCirilica;
    @SerializedName("Brojac")
    @Expose
    private int brojac;
    @SerializedName("BrojStranice")
    @Expose
    private int brojStranice;
    @SerializedName("IzvorTekst")
    @Expose
    private String izvorTekst;

    public Ijekavica() {
        super();
    }

    public Ijekavica(int ijekavicaId, Integer ekavicaId, Ekavica ekavica, String ijekavicaLatinica, String ijekavicaCirilica, Integer vrstaRijeciId, VrstaRijeci vrstaRijeci, Integer izvorId, Izvor izvor, Integer korisnikIdMijenjao, Object korisnik, String opisLatinica, String opisCirilica, int brojac, int brojStranice, String izvorTekst) {
        this.ijekavicaId = ijekavicaId;
        this.ekavicaId = ekavicaId;
        this.ekavica = ekavica;
        this.ijekavicaLatinica = ijekavicaLatinica;
        this.ijekavicaCirilica = ijekavicaCirilica;
        this.vrstaRijeciId = vrstaRijeciId;
        this.vrstaRijeci = vrstaRijeci;
        this.izvorId = izvorId;
        this.izvor = izvor;
        this.korisnikIdMijenjao = korisnikIdMijenjao;
        this.korisnik = korisnik;
        this.opisLatinica = opisLatinica;
        this.opisCirilica = opisCirilica;
        this.brojac = brojac;
        this.brojStranice = brojStranice;
        this.izvorTekst = izvorTekst;
    }

    public int getIjekavicaId() {
        return ijekavicaId;
    }

    public Integer getEkavicaId() {
        return ekavicaId;
    }

    public Ekavica getEkavica() {
        return ekavica;
    }

    public String getIjekavicaLatinica() {
        return ijekavicaLatinica;
    }

    public String getIjekavicaCirilica() {
        return ijekavicaCirilica;
    }

    public Integer getVrstaRijeciId() {
        return vrstaRijeciId;
    }

    public VrstaRijeci getVrstaRijeci() {
        return vrstaRijeci;
    }

    public Integer getIzvorId() {
        return izvorId;
    }

    public Izvor getIzvor() {
        return izvor;
    }

    public Integer getKorisnikIdMijenjao() {
        return korisnikIdMijenjao;
    }

    public Object getKorisnik() {
        return korisnik;
    }

    public String getOpisLatinica() {
        return opisLatinica;
    }

    public String getOpisCirilica() {
        return opisCirilica;
    }

    public int getBrojac() {
        return brojac;
    }

    public int getBrojStranice() {
        return brojStranice;
    }

    public String getIzvorTekst() {
        return izvorTekst;
    }

    public void setIjekavicaId(int ijekavicaId) {
        this.ijekavicaId = ijekavicaId;
    }

    public void setEkavicaId(Integer ekavicaId) {
        this.ekavicaId = ekavicaId;
    }

    public void setEkavica(Ekavica ekavica) {
        this.ekavica = ekavica;
    }

    public void setIjekavicaLatinica(String ijekavicaLatinica) {
        this.ijekavicaLatinica = ijekavicaLatinica;
    }

    public void setIjekavicaCirilica(String ijekavicaCirilica) {
        this.ijekavicaCirilica = ijekavicaCirilica;
    }

    public void setVrstaRijeciId(Integer vrstaRijeciId) {
        this.vrstaRijeciId = vrstaRijeciId;
    }

    public void setVrstaRijeci(VrstaRijeci vrstaRijeci) {
        this.vrstaRijeci = vrstaRijeci;
    }

    public void setIzvorId(Integer izvorId) {
        this.izvorId = izvorId;
    }

    public void setIzvor(Izvor izvor) {
        this.izvor = izvor;
    }

    public void setKorisnikIdMijenjao(Integer korisnikIdMijenjao) {
        this.korisnikIdMijenjao = korisnikIdMijenjao;
    }

    public void setKorisnik(Object korisnik) {
        this.korisnik = korisnik;
    }

    public void setOpisLatinica(String opisLatinica) {
        this.opisLatinica = opisLatinica;
    }

    public void setOpisCirilica(String opisCirilica) {
        this.opisCirilica = opisCirilica;
    }

    public void setBrojac(int brojac) {
        this.brojac = brojac;
    }

    public void setBrojStranice(int brojStranice) {
        this.brojStranice = brojStranice;
    }

    public void setIzvorTekst(String izvorTekst) {
        this.izvorTekst = izvorTekst;
    }
}
