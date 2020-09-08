/**
 * Created by ucenik on 5/14/2018.
 */

package com.praksa.ucenik.jatolog.thread;

import android.content.Context;
import android.widget.Toast;

import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.model.PrijavaGreske;
import com.praksa.ucenik.jatolog.restRetrofit.ApiService;
import com.praksa.ucenik.jatolog.restRetrofit.RetrofitClient;

import retrofit2.Call;

public class PrijavaGreskeThread extends Thread {

    private PrijavaGreske prijavaGreske;
    private Context context;

    public PrijavaGreskeThread(Context context, PrijavaGreske prijavaGreske) {
        this.context = context;
        this.prijavaGreske = prijavaGreske;
    }

    @Override
    public void run() {

        try {
            ApiService service = RetrofitClient.getApiService();

            Call<Integer> callPrijavaGreske = service.postPrijavaGreske(prijavaGreske);
            Integer izvrseno = (Integer) callPrijavaGreske.execute().body();
            if (new Integer(1).equals(izvrseno)) {
                Toast.makeText(context, context.getString(R.string.prijavljeno), Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, context.getString(R.string.nije_prijavljeno), Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

