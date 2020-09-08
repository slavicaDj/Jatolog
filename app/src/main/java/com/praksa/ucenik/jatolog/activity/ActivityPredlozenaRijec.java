package com.praksa.ucenik.jatolog.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.adapter.SpinAdapter;
import com.praksa.ucenik.jatolog.db.VrstaRijeciDBHelper;
import com.praksa.ucenik.jatolog.model.Rijec;
import com.praksa.ucenik.jatolog.thread.PrijedlogRijeciThread;

public class ActivityPredlozenaRijec extends AppCompatActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predlozena_rijec);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        this.setSupportActionBar(toolbar);

        spinner = (Spinner) findViewById(R.id.spnVrstaRijeci);
        VrstaRijeciDBHelper vrstaRijeciDBHelper = new VrstaRijeciDBHelper(getApplicationContext());
        SpinAdapter spinAdapter = new SpinAdapter(getApplicationContext(), R.layout.vrsta_rijeci_spinner_item, vrstaRijeciDBHelper.selectAll());
        spinner.setAdapter(spinAdapter);

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.btnPredloziRijec);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ekavskaRijec = ((EditText)findViewById(R.id.edtPredlozenaRijecEkavica)).getText().toString();
                String ijekavskaRijec = ((EditText)findViewById(R.id.edtPredlozenaRijecIjekavica)).getText().toString();
                if ("".equals(ekavskaRijec.trim()) || "".equals(ijekavskaRijec.trim()) ) {
                    Toast.makeText(ActivityPredlozenaRijec.this, getString(R.string.unesite_predlozene_rijeci), Toast.LENGTH_SHORT).show();
                }
                else {
                    posaljiPredlozenuRijec();
                }
            }
        });

    }

    private void posaljiPredlozenuRijec(){

            EditText ekavskaRijec = (EditText) findViewById(R.id.edtPredlozenaRijecEkavica);
            EditText ijekavskaRijec = (EditText) findViewById(R.id.edtPredlozenaRijecIjekavica);
            EditText predlozio = (EditText) findViewById(R.id.edtPredlozenaRijecPredlozio);
            EditText izvor = (EditText) findViewById(R.id.edtPredlozenaRijecIzvor);
            EditText napomena = (EditText) findViewById(R.id.edtPredlozenaRijecNapomena);
            EditText primjer = (EditText) findViewById(R.id.edtPredlozenaRijecPrimjer);

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
            String locale = ims.getLocale();

            Rijec rijec = new Rijec();
            rijec.setPredlozio(predlozio.getText().toString());
            rijec.setNapomena(napomena.getText().toString());
            rijec.setPrimjer(primjer.getText().toString());
            rijec.setIzvor(izvor.getText().toString());
            if ("sr".equals(locale)) {
                rijec.setEkavicaCirilica(ekavskaRijec.getText().toString());
                rijec.setIjekavicaCirilica(ijekavskaRijec.getText().toString());
            }
            else {
                rijec.setEkavicaLatinica(ekavskaRijec.getText().toString());
                rijec.setIjekavicaLatinica(ijekavskaRijec.getText().toString());
            }

            ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {
                PrijedlogRijeciThread prijedlogRijeciThread = new PrijedlogRijeciThread(getApplicationContext(), rijec);
                prijedlogRijeciThread.start();
                try {
                    prijedlogRijeciThread.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(this, R.string.nemaInternetKonekcije, Toast.LENGTH_LONG).show();
            }
            finish();
        }

}
