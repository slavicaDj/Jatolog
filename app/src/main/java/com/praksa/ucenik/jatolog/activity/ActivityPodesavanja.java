package com.praksa.ucenik.jatolog.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.util.ProgressDialogAsycTask;

import java.util.Locale;

import static com.praksa.ucenik.jatolog.util.Util.getCurrentLocale;

public class ActivityPodesavanja extends PreferenceActivity {


    private SwitchPreference pismoCirilica;
    private Preference sinhronizacija;
    private EditTextPreference brojRijeci;
    private Preference oNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        pismoCirilica = (SwitchPreference) findPreference(this.getResources().getString(R.string.cirilica));

        if (getCurrentLocale(getApplicationContext()).getLanguage().equals("en")) {
            pismoCirilica.setChecked(false);
        } else {
            pismoCirilica.setChecked(true);
        }

//        Toast.makeText(this, getCurrentLocale(getApplicationContext()).getLanguage(), Toast.LENGTH_LONG).show();

        pismoCirilica.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {

                Locale locale = null;

                if (o.toString().equals("true")) {
//                    Toast.makeText(ActivityPodesavanja.this, "CHECKED", Toast.LENGTH_SHORT).show();
                    locale = new Locale("sr");
                } else {
//                    Toast.makeText(ActivityPodesavanja.this, "not CHECKED", Toast.LENGTH_SHORT).show();
                    locale = new Locale("en");
                }


                //Toast.makeText(ActivityPodesavanja.this, getCurrentLocale(getApplicationContext()).getLanguage(), Toast.LENGTH_LONG).show();

                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                SharedPreferences sharedPreferences = getSharedPreferences("util", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("locale", locale.getLanguage());
                editor.apply();

                Intent intent = new Intent(getApplication(), ActivityHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);

                return false;
            }
        });

        sinhronizacija = (Preference) findPreference(this.getResources().getString(R.string.Sinhronizacija));
        sinhronizacija.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {
                    ProgressDialogAsycTask progress = new ProgressDialogAsycTask(ActivityPodesavanja.this);
                    progress.execute();

                    return true;
                } else {
                    Toast.makeText(ActivityPodesavanja.this, ActivityPodesavanja.this.getResources().getString(R.string.nemaInternetKonekcije), Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

        brojRijeci = (EditTextPreference) findPreference(this.getResources().getString(R.string.editTextPreferenceBrojRijeci));
        brojRijeci.setSummary(brojRijeci.getText());
        brojRijeci.getEditText().setFilters(new InputFilter[]{new InputFilterMinMax(1,50)});
        brojRijeci.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object changedValue) {
                int brojTopRijeci = Integer.parseInt(changedValue.toString());
                brojRijeci.setSummary(brojTopRijeci+"");
                return true;
            }
        });

        oNama = (Preference) findPreference(this.getResources().getString(R.string.oNamaPreference));
        oNama.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(ActivityPodesavanja.this,ActivityONama.class);
                startActivity(intent);
                return true;
            }
        });
    }

    public static class InputFilterMinMax implements InputFilter {
        private int min;
        private int max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            //noinspection EmptyCatchBlock
            try {
                int input = Integer.parseInt(dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length()));
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
            return "";
        }
        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
