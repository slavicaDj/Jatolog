package com.praksa.ucenik.jatolog.restRetrofit;

import com.praksa.ucenik.jatolog.model.Brojac;
import com.praksa.ucenik.jatolog.model.Ekavica;
import com.praksa.ucenik.jatolog.model.Ijekavica;
import com.praksa.ucenik.jatolog.model.IjekavicaPrimjer;
import com.praksa.ucenik.jatolog.model.PrijavaGreske;
import com.praksa.ucenik.jatolog.model.Rijec;
import com.praksa.ucenik.jatolog.model.VrstaRijeci;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {


    @GET("o6o4y")
    Call<List<Ekavica>> getEkavicaJSON();

    @GET("1br09e")
    Call<List<Ijekavica>> getIjekavicaJSON();

    @GET("1bkd2a")
    Call<List<VrstaRijeci>> getVrstaRijeciJSON();

    @GET("wbvoy")
    Call<List<IjekavicaPrimjer>> getIjekavicaPrimjerJSON();

    @POST("prijavagreske/kreiraj")
    Call<Integer> postPrijavaGreske(@Body PrijavaGreske prijavaGreske);

    @POST("predlozenarijec/kreiraj")
    Call<Integer> postPredloziRijec(@Body Rijec rijec);

    @POST("ekavica/povecajbrojac")
    Call<Integer> postPovecajBrojac(@Body Brojac brojacModel);


//    @POST("otpremnicaZaglavlje")
//    Call<ArrayList<OtpremnicaZaglavlje>> postAllOtpremnice(@Body ArrayList<OtpremnicaZaglavlje> otpremnice);
//
//    @GET("korisnik")
//    Call<Korisnik> login(
//            @Query("username") String korisnickoIme,
//            @Query("lozinka") String lozinka);
}
