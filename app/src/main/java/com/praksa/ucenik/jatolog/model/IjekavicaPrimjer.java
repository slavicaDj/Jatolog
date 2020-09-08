package com.praksa.ucenik.jatolog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IjekavicaPrimjer {
    @SerializedName("IjekavicaPrimjerId")
    @Expose
    private Integer ijekavicaPrimjerId;
    @SerializedName("IjekavicaId")
    @Expose
    private Integer ijekavicaId;
    @SerializedName("Ijekavica")
    @Expose
    private Ijekavica ijekavica;
    @SerializedName("Sadrzaj")
    @Expose
    private String sadrzaj;

    public IjekavicaPrimjer() {
    }

    public IjekavicaPrimjer(Integer ijekavicaPrimjerId, Integer ijekavicaId, Ijekavica ijekavica, String sadrzaj) {
        this.ijekavicaPrimjerId = ijekavicaPrimjerId;
        this.ijekavicaId = ijekavicaId;
        this.ijekavica = ijekavica;
        this.sadrzaj = sadrzaj;
    }

    public Integer getIjekavicaPrimjerId() {
        return ijekavicaPrimjerId;
    }

    public Integer getIjekavicaId() {
        return ijekavicaId;
    }

    public Ijekavica getIjekavica() {
        return ijekavica;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setIjekavicaPrimjerId(Integer ijekavicaPrimjerId) {
        this.ijekavicaPrimjerId = ijekavicaPrimjerId;
    }

    public void setIjekavicaId(Integer ijekavicaId) {
        this.ijekavicaId = ijekavicaId;
    }

    public void setIjekavica(Ijekavica ijekavica) {
        this.ijekavica = ijekavica;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }
}
