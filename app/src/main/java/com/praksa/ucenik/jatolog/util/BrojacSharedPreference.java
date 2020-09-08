package com.praksa.ucenik.jatolog.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.praksa.ucenik.jatolog.model.Brojac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.content.SharedPreferences.Editor;

public class BrojacSharedPreference {
    public static final String PREFS_NAME = "JATOLOG";
    public static final String BROJAC = "BROJAC";

    public void save(Context context,List<Brojac> list){
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String json = gson.toJson(list);

        editor.putString(BROJAC,json);

        editor.commit();
    }

    public void increment(Context context, int ekavicaId){
        ArrayList<Brojac> list = getListaBrojaca(context);
        boolean temp = false;
        for(Brojac b : list){
            if(b.getEkavicaId()==ekavicaId){
                b.increment();
                temp = true;
                break;
            }
        }

        if(!temp){
            Brojac bm = new Brojac(ekavicaId);
            list.add(bm);
        }

        save(context,list);
    }

    public void reset(Context context){
        save(context,new ArrayList<Brojac>());
    }

    public ArrayList<Brojac> getListaBrojaca(Context context){
        SharedPreferences settings;
        List<Brojac> list;

        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        if(settings.contains(BROJAC)){
            String json = settings.getString(BROJAC,null);
            Gson gson = new Gson();
            Brojac[] nizBrojacModel = gson.fromJson(json,Brojac[].class);

            list = Arrays.asList(nizBrojacModel);
            list = new ArrayList<>(list);
        }
        else
            list = new ArrayList<>();
        return (ArrayList<Brojac>) list;
    }
}
