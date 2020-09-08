package com.praksa.ucenik.jatolog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ekavica {
    @SerializedName("EkavicaId")
    @Expose
    private int ekavicaId;
    @SerializedName("RijecLatinica")
    @Expose
    private String rijecLatinica;
    @SerializedName("RijecCirilica")
    @Expose
    private String rijecCirilica;
    @SerializedName("Obrisano")
    @Expose
    private boolean obrisano;
    @SerializedName("KorisnikId")
    @Expose
    private int korisnikId;
    @SerializedName("Brojac")
    @Expose
    private int brojac;

    public Ekavica() {
    }

    public Ekavica(int ekavicaId, String rijecLatinica, String rijecCirilica, boolean obrisano, int korisnikId, int brojac) {
        this.ekavicaId = ekavicaId;
        this.rijecLatinica = rijecLatinica;
        this.rijecCirilica = rijecCirilica;
        this.obrisano = obrisano;
        this.korisnikId = korisnikId;
        this.brojac = brojac;
    }

    public int getEkavicaId() {
        return ekavicaId;
    }

    public void setEkavicaId(int ekavicaId) {
        this.ekavicaId = ekavicaId;
    }

    public String getRijecLatinica() {
        return rijecLatinica;
    }

    public void setRijecLatinica(String rijecLatinica) {
        this.rijecLatinica = rijecLatinica;
    }

    public String getRijecCirilica() {
        return rijecCirilica;
    }

    public void setRijecCirilica(String rijecCirilica) {
        this.rijecCirilica = rijecCirilica;
    }

    public boolean isObrisano() {
        return obrisano;
    }

    public void setObrisano(boolean obrisano) {
        this.obrisano = obrisano;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

    public int getBrojac() {
        return brojac;
    }

    public void setBrojac(int brojac) {
        this.brojac = brojac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ekavica ekavica = (Ekavica) o;

        return ekavicaId == ekavica.ekavicaId;
    }

    @Override
    public int hashCode() {
        return ekavicaId;
    }

    @Override
    public String toString() {
        return "Ekavica{" +
                "ekavicaId=" + ekavicaId +
                ", rijecLatinica='" + rijecLatinica + '\'' +
                ", rijecCirilica='" + rijecCirilica + '\'' +
                ", obrisano=" + obrisano +
                ", korisnikId=" + korisnikId +
                ", brojac=" + brojac +
                '}';
    }
}
