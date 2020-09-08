package com.praksa.ucenik.jatolog.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.praksa.ucenik.jatolog.model.Brojac;
import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.restRetrofit.ApiService;
import com.praksa.ucenik.jatolog.restRetrofit.RetrofitClient;
import com.praksa.ucenik.jatolog.thread.SinhronizacijaThread;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED){

            SinhronizacijaThread st = new SinhronizacijaThread(this);
            st.start();
            try {
                st.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this, "No internet conection", Toast.LENGTH_LONG).show();
        }

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(MainActivity.this, ActivityHome.class));
                finish();
            }
        }, secondsDelayed * 1000);


        String[] args = new String[]{};
    }

    public static void main(String[] args){
        ApiService service = RetrofitClient.getApiService();
        Brojac bm = new Brojac(2);
        bm.increment();
        bm.increment();
        bm.increment();
        Call<Integer> call = service.postPovecajBrojac(bm);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

    }
}
