package com.praksa.ucenik.jatolog.thread;

import android.content.Context;
import android.widget.Toast;

import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.model.PrijavaGreske;
import com.praksa.ucenik.jatolog.model.Rijec;
import com.praksa.ucenik.jatolog.restRetrofit.ApiService;
import com.praksa.ucenik.jatolog.restRetrofit.RetrofitClient;

import retrofit2.Call;

public class PrijedlogRijeciThread extends Thread{

    private Rijec rijec;
    private Context context;

    public PrijedlogRijeciThread(Context context, Rijec rijec) {
        this.context = context;
        this.rijec = rijec;
    }

    @Override
    public void run() {

        try {
            ApiService service = RetrofitClient.getApiService();

            Call<Integer> callPrijedlogRijeci = service.postPredloziRijec(rijec);
            Integer izvrseno = (Integer) callPrijedlogRijeci.execute().body();
            if (izvrseno==1) {
                Toast.makeText(context, context.getString(R.string.predlozeno), Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, context.getString(R.string.nije_predlozeno), Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
