package com.praksa.ucenik.jatolog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VrstaRijeci {
    @SerializedName("VrstaRijeciId")
    @Expose
    private int vrstaRijeciId;
    @SerializedName("NazivLatinica")
    @Expose
    private String nazivLatinica;
    @SerializedName("NazivCirilica")
    @Expose
    private String nazivCirilica;

    public VrstaRijeci() {
        super();
    }

    public VrstaRijeci(int vrstaRijeciId, String nazivLatinica, String nazivCirilica) {
        this.vrstaRijeciId = vrstaRijeciId;
        this.nazivLatinica = nazivLatinica;
        this.nazivCirilica = nazivCirilica;
    }

    public int getVrstaRijeciId() {
        return vrstaRijeciId;
    }

    public void setVrstaRijeciId(int vrstaRijeciId) {
        this.vrstaRijeciId = vrstaRijeciId;
    }

    public String getNazivLatinica() {
        return nazivLatinica;
    }

    public void setNazivLatinica(String nazivLatinica) {
        this.nazivLatinica = nazivLatinica;
    }

    public String getNazivCirilica() {
        return nazivCirilica;
    }

    public void setNazivCirilica(String nazivCirilica) {
        this.nazivCirilica = nazivCirilica;
    }
}
