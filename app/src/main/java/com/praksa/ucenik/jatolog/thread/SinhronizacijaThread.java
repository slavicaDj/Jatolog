package com.praksa.ucenik.jatolog.thread;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.praksa.ucenik.jatolog.db.DBHelper;
import com.praksa.ucenik.jatolog.db.EkavicaDBHelper;
import com.praksa.ucenik.jatolog.db.IjekavicaDBHelper;
import com.praksa.ucenik.jatolog.db.IjekavicaPrimjerDBHelper;
import com.praksa.ucenik.jatolog.db.VrstaRijeciDBHelper;
import com.praksa.ucenik.jatolog.model.Ekavica;
import com.praksa.ucenik.jatolog.model.Ijekavica;
import com.praksa.ucenik.jatolog.model.IjekavicaPrimjer;
import com.praksa.ucenik.jatolog.model.VrstaRijeci;
import com.praksa.ucenik.jatolog.restRetrofit.ApiService;
import com.praksa.ucenik.jatolog.restRetrofit.RetrofitClient;
import com.praksa.ucenik.jatolog.util.BrojacSharedPreference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SinhronizacijaThread extends Thread {
    private Context context;
    private boolean force;


    public SinhronizacijaThread(Context context){
        this.context = context;
        force = false;
    }

    @Override
    public void run(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(BrojacSharedPreference.PREFS_NAME,Context.MODE_PRIVATE);
        final String TIME_KEY = "TIME";
        long latestUpdateTime = sharedPreferences.getLong(TIME_KEY,System.currentTimeMillis()-100000000); //mora biti veci broj od 1_000*60*60*24 da bi se prilikom prvog instaliranja aplikacije povukla baza
        long time = System.currentTimeMillis();
        if(time-latestUpdateTime>1_000*60*60*24 || force) {
            DBHelper dbHelper = new DBHelper(context);

            ApiService service = RetrofitClient.getApiService();
            try {
                //Start transaction
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.beginTransaction();

                //Sinhronizacija brojaca ekavskih rijeci
                //            BrojacSharedPreference brojacSharedPreference = new BrojacSharedPreference();
                //            ArrayList<Brojac> listaBrojaca = brojacSharedPreference.getListaBrojaca(context);
                //            for(Brojac bm : listaBrojaca){
                //                Call<Integer> callBrojacModel = service.postPovecajBrojac(bm);
                //                callBrojacModel.execute().body();
                //            }
                //            brojacSharedPreference.reset(context);

                //Brisanje Baze

                dbHelper.clearData(database);

                //VrstaRijeci
                Call<List<VrstaRijeci>> callVrstaRijeci = service.getVrstaRijeciJSON();
                ArrayList<VrstaRijeci> listaVrstaRijeci = (ArrayList<VrstaRijeci>) callVrstaRijeci.execute().body();
                VrstaRijeciDBHelper vrstaRijeciDBHelper = new VrstaRijeciDBHelper(database);
                vrstaRijeciDBHelper.create(listaVrstaRijeci);

                //Ekavica
                Call<List<Ekavica>> callEkavica = service.getEkavicaJSON();
                ArrayList<Ekavica> listaEkavica = (ArrayList<Ekavica>) callEkavica.execute().body();
                EkavicaDBHelper ekavicaDBHelper = new EkavicaDBHelper(database);
                ekavicaDBHelper.create(listaEkavica);

                //Ijekavica
                Call<List<Ijekavica>> callIjekavica = service.getIjekavicaJSON();
                ArrayList<Ijekavica> listaIjekavica = (ArrayList<Ijekavica>) callIjekavica.execute().body();
                IjekavicaDBHelper ijekavicaDBHelper = new IjekavicaDBHelper(database);
                ijekavicaDBHelper.create(listaIjekavica);

                //IjekavicaPrimjer
                Call<List<IjekavicaPrimjer>> callIjekavicaPrimjer = service.getIjekavicaPrimjerJSON();
                ArrayList<IjekavicaPrimjer> listaIjekavicaPrimjer = (ArrayList<IjekavicaPrimjer>) callIjekavicaPrimjer.execute().body();
                IjekavicaPrimjerDBHelper ijekavicaPrimjerDBHelper = new IjekavicaPrimjerDBHelper(database);
                ijekavicaPrimjerDBHelper.create(listaIjekavicaPrimjer);

                database.setTransactionSuccessful();
                database.endTransaction();
                database.close();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong(TIME_KEY,System.currentTimeMillis());
                editor.apply();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }
}
