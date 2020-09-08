package com.praksa.ucenik.jatolog.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.praksa.ucenik.jatolog.model.Ijekavica;
import com.praksa.ucenik.jatolog.model.IjekavicaPrimjer;
import com.praksa.ucenik.jatolog.model.VrstaRijeci;

import java.util.ArrayList;

/**
 * Created by ucenik on 4/9/2018.
 */

public class IjekavicaPrimjerDBHelper {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private String[] kolone = {DBHelper.COLUMN_IJEKAVICA_PRIMJER_ID,DBHelper.COLUMN_IJEKAVICA_PRIMJER_IJEKAVICA_ID,DBHelper.COLUMN_IJEKAVICA_PRIMJER_SADRZAJ};

    public IjekavicaPrimjerDBHelper(Context context){
        dbHelper = new DBHelper(context);
    }

    public IjekavicaPrimjerDBHelper(SQLiteDatabase database){
        this.database = database;
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
        database = null;
    }

    public void createIjekavicaPrimjer(IjekavicaPrimjer ijekavicaPrimjer){

//        ContentValues values = new ContentValues();
//        values.put(DBHelper.COLUMN_IJEKAVICA_PRIMJER_ID, ijekavicaPrimjer.getIjekavicaPrimjerId());
//        values.put(DBHelper.COLUMN_IJEKAVICA_PRIMJER_IJEKAVICA_ID, ijekavicaPrimjer.getIjekavica().getIjekavicaId());
//        values.put(DBHelper.COLUMN_IJEKAVICA_PRIMJER_SADRZAJ, ijekavicaPrimjer.getSadrzaj());
//
//
//        database.insert(DBHelper.TABELA_IJEKAVICA_PRIMJER,null,values);

        Object[] valuesO = new Object[]{ijekavicaPrimjer.getIjekavicaPrimjerId(),ijekavicaPrimjer.getIjekavica().getIjekavicaId(),ijekavicaPrimjer.getSadrzaj()};
        database.execSQL("INSERT INTO "+DBHelper.TABELA_IJEKAVICA_PRIMJER+" VALUES (?,?,?)",valuesO);

        Log.i("ddd","IJEKPRI");

    }

    public void create(ArrayList<IjekavicaPrimjer> listaIjekavicaPrimjer){
        for (IjekavicaPrimjer ijekavicaPrimjer:listaIjekavicaPrimjer) {
            createIjekavicaPrimjer(ijekavicaPrimjer);
        }
    }


    public ArrayList<IjekavicaPrimjer> selectPrimjeriByIjekavica(Ijekavica ijekavica) {

        ArrayList<IjekavicaPrimjer> lista = new ArrayList<>();
        this.open();

        //ne mora select *
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABELA_IJEKAVICA_PRIMJER + " WHERE " + DBHelper.COLUMN_IJEKAVICA_PRIMJER_IJEKAVICA_ID + "=" + ijekavica.getIjekavicaId(),null);


        if (cursor.moveToFirst()) {
            do {
                IjekavicaPrimjer ijekavicaPrimjer = new IjekavicaPrimjer();
                ijekavicaPrimjer.setIjekavicaPrimjerId(cursor.getInt(0));
                ijekavicaPrimjer.setIjekavica(ijekavica);
                ijekavicaPrimjer.setSadrzaj(cursor.getString(2));
                lista.add(ijekavicaPrimjer);
            }
            while (cursor.moveToNext());
        }

        this.close();

        return lista;

    }


}
