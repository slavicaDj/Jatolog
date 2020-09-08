package com.praksa.ucenik.jatolog.fragment;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.praksa.ucenik.jatolog.activity.ActivityPredlozenaRijec;
import com.praksa.ucenik.jatolog.model.Brojac;
import com.praksa.ucenik.jatolog.model.Ijekavica;
import com.praksa.ucenik.jatolog.util.BrojacSharedPreference;
import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.adapter.RecyclerViewAdapter;
import com.praksa.ucenik.jatolog.db.EkavicaDBHelper;
import com.praksa.ucenik.jatolog.db.IjekavicaDBHelper;
import com.praksa.ucenik.jatolog.db.VrstaRijeciDBHelper;
import com.praksa.ucenik.jatolog.model.Ekavica;
import com.praksa.ucenik.jatolog.model.VrstaRijeci;
import com.praksa.ucenik.jatolog.util.Util;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {
    private View inflaterView;
    private RecyclerView recyclerView;
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayList<VrstaRijeci> listaVrstaRijeci = new ArrayList<>();
    private VrstaRijeciDBHelper vrstaRijeciDBHelper;
    private ArrayList<Ekavica> listaEkavica = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private TextView ekavica;

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public Context getContext() {
        return super.getContext();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflaterView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=(RecyclerView) inflaterView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        FloatingActionButton btnFab = (FloatingActionButton)  inflaterView.findViewById(R.id.fab);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ActivityPredlozenaRijec.class);
                startActivity(intent);
            }
        });

        ekavica=inflaterView.findViewById(R.id.txtEkavica);


        autoCompleteTextView =  (AutoCompleteTextView) inflaterView.findViewById(R.id.autoCompleteTextView);

        EkavicaDBHelper ekavicaDBHelper = new EkavicaDBHelper(getContext());
        final ArrayList<Ekavica> listaEkavica = ekavicaDBHelper.selectAll();
        final ArrayList<String> listaLatinicaCirilica = new ArrayList<>();
        for (Ekavica ekavica : listaEkavica) {
            listaLatinicaCirilica.add(ekavica.getRijecLatinica());
            listaLatinicaCirilica.add(ekavica.getRijecCirilica());
        }


        final ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, listaLatinicaCirilica);
        autoCompleteTextView.setAdapter(autoCompleteAdapter);
        autoCompleteAdapter.notifyDataSetChanged();

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(autoCompleteTextView.getText().length()>0){
                    autoCompleteTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cancel, 0);
                }
                else{
                    if(adapter!=null)
                        adapter.clear();
                    autoCompleteTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }
        });

        autoCompleteTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if(autoCompleteTextView.getCompoundDrawables()[2]!=null && (motionEvent.getRawX() >= (autoCompleteTextView.getRight() - autoCompleteTextView.getCompoundDrawables()[2].getBounds().width()))) {
                        autoCompleteTextView.setText("");
                        return false;
                    }
                }
                return false;
            }

        });



        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                IjekavicaDBHelper ijekavicaDBHelper = new IjekavicaDBHelper(getContext());
                int index = listaLatinicaCirilica.indexOf(autoCompleteTextView.getText().toString()) / 2;
                int idEkavica = listaEkavica.get(index).getEkavicaId();

                Locale locale = Util.getCurrentLocale(getContext());
                String pismo = (locale.getLanguage().equals("en"))?IjekavicaDBHelper.LATINICA:IjekavicaDBHelper.CIRILICA;
                adapter = new RecyclerViewAdapter(getContext(), ijekavicaDBHelper.selectByEkavicaRijeci(idEkavica, pismo));
                recyclerView.setAdapter(adapter);

                InputMethodManager in = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(arg1.getApplicationWindowToken(), 0);


                ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {

                    Brojac b = new Brojac(idEkavica);
//                    Toast.makeText(getContext(), b.toString(), Toast.LENGTH_SHORT).show();
//                    ApiService service = RetrofitClient.getApiService();
//                    Brojac b = new Brojac(idEkavica);  //Ovo ce uvecati ekavsku rijec za 1
//                    Call<Integer> callBrojacModel = service.postPovecajBrojac(b);
//                    callBrojacModel.enqueue(new Callback<Integer>() {
//                        @Override
//                        public void onResponse(Call<Integer> call, Response<Integer> response) {
//                            Integer rezultat = response.body();
//                            if(rezultat==1){
//                                //Super
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<Integer> call, Throwable t) {
//
//                        }
//                    });
                }
                else {
                    BrojacSharedPreference brojacSharedPreference = new BrojacSharedPreference();
                    brojacSharedPreference.increment(getContext(), idEkavica);
//                    Toast.makeText(getContext(), brojacSharedPreference.getListaBrojaca(getContext()).toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        ImageView imgCopy = (ImageView) inflaterView.findViewById(R.id.imgCopy);
        imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(autoCompleteTextView.getText().length()>0) {
                    ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText(null, autoCompleteTextView.getText());
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(getContext(), getString(R.string.kopirano) + autoCompleteTextView.getText(), Toast.LENGTH_LONG).show();
                }
            }
        });

        ImageView imgPaste = (ImageView) inflaterView.findViewById(R.id.imgPaste);
        imgPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = clipboardManager.getPrimaryClip();
                if(clipData!=null) {
                    autoCompleteTextView.setText(autoCompleteTextView.getText().toString() + clipData.getItemAt(0).getText());
                    autoCompleteTextView.setSelection(autoCompleteTextView.getText().length());
                }
            }
        });


        return inflaterView;
    }
    }
