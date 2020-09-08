package com.praksa.ucenik.jatolog.fragment;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.adapter.RecyclerViewAdapterTopRijeci;
import com.praksa.ucenik.jatolog.db.EkavicaDBHelper;
import com.praksa.ucenik.jatolog.model.Ijekavica;

import java.util.ArrayList;


public class FragmentTopRijeci extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterTopRijeci adapter;

    public static FragmentTopRijeci newInstance() {
        return new FragmentTopRijeci();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.top_rijeci, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewTopRijeci);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        ArrayList<Ijekavica> lista = ucitajRijeci();
        adapter = new RecyclerViewAdapterTopRijeci(getContext(), lista);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public ArrayList<Ijekavica> ucitajRijeci(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return new EkavicaDBHelper(getContext()).selectTopRijeci(Integer.parseInt(preferences.getString(getString(R.string.editTextPreferenceBrojRijeci),"10")));
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<Ijekavica> lista = ucitajRijeci();
        adapter = new RecyclerViewAdapterTopRijeci(getContext(),lista);
        recyclerView.setAdapter(adapter);
    }
}
