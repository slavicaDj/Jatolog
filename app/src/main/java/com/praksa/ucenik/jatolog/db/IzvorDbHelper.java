package com.praksa.ucenik.jatolog.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.praksa.ucenik.jatolog.model.Izvor;

import java.util.ArrayList;

/**
 * Created by ucenik on 4/9/2018.
 */

public class IzvorDbHelper {

    private  DBHelper dbHelper;
    private SQLiteDatabase database;

    private String[] kolone = {DBHelper.COLUMN_IZVOR_ID, DBHelper.COLUMN_IZVOR_NAZIV, DBHelper.COLUMN_IZVOR_AUTOR, DBHelper.COLUMN_IZVOR_GODINA_IZDAVANJA, DBHelper.COLUMN_IZVOR_MJESTO_IZDAVANJA };

    public IzvorDbHelper (Context context) {
        dbHelper = new DBHelper(context);
    }

    private void open() {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public boolean create(Izvor izvor) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_IZVOR_ID, izvor.getIzvorId());
        values.put(DBHelper.COLUMN_IZVOR_NAZIV, izvor.getNaziv());
        values.put(DBHelper.COLUMN_IZVOR_AUTOR, izvor.getAutor());
        values.put(DBHelper.COLUMN_IZVOR_GODINA_IZDAVANJA, izvor.getGodinaIzdavanja());
        values.put(DBHelper.COLUMN_IZVOR_MJESTO_IZDAVANJA, izvor.getMjestoIzdavanja());

        open();
        database.insertWithOnConflict(DBHelper.TABELA_IZVOR, null, values,SQLiteDatabase.CONFLICT_IGNORE);
        close();

        return true;
    }

    public Izvor selectById(int id) {

        this.open();

        //Cursor cursor = database.query(DBHelper.TABELA_IZVOR, kolone, "IzvorId=?", new String[]{"" + id}, null, null, null, null);
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABELA_IZVOR + " WHERE " + DBHelper.COLUMN_IZVOR_ID + " = ?",new String[]{id+""});

        Izvor izvor = new Izvor();

        if (cursor.moveToFirst()) {
            do {
                izvor.setIzvorId(cursor.getInt(0));
                izvor.setNaziv(cursor.getString(1));
                izvor.setAutor(cursor.getString(2));
                izvor.setGodinaIzdavanja(cursor.getInt(3));
                izvor.setMjestoIzdavanja(cursor.getString(4));
            }
            while (cursor.moveToNext());
        }

        close();

        return izvor;
    }

}
