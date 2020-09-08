package com.praksa.ucenik.jatolog.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.praksa.ucenik.jatolog.model.Ekavica;
import com.praksa.ucenik.jatolog.model.Ijekavica;
import com.praksa.ucenik.jatolog.model.Izvor;
import com.praksa.ucenik.jatolog.model.VrstaRijeci;

import java.util.ArrayList;
import java.util.List;

public class IjekavicaDBHelper {
    public static final String CIRILICA = "CIRILICA";
    public static final String LATINICA = "LATINICA";

    private String[] kolone = {DBHelper.COLUMN_IJEKAVICA_ID,DBHelper.COLUMN_IJEKAVICA_EKAVICA_ID,DBHelper.COLUMN_IJEKAVICA_CIRILICA,DBHelper.COLUMN_IJEKAVICA_LATINICA,DBHelper.COLUMN_IJEKAVICA_VRSTA_RIJECI_ID,
            DBHelper.COLUMN_IJEKAVICA_IZVOR_ID,DBHelper.COLUMN_IJEKAVICA_KORISNIK_ID_MIJENJAO,DBHelper.COLUMN_IJEKAVICA_OPIS_CIRILICA,DBHelper.COLUMN_IJEKAVICA_OPIS_LATINICA,DBHelper.COLUMN_IJEKAVICA_BROJAC,
            DBHelper.COLUMN_IJEKAVICA_BROJ_STRANICE,DBHelper.COLUMN_IJEKAVICA_IZVOR_TEKST};

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;

    public IjekavicaDBHelper(Context context){
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public IjekavicaDBHelper(SQLiteDatabase database){
        this.database = database;
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
        database = null;
    }

    public void createIjekavica(Ijekavica ijekavica){
//        ContentValues values = new ContentValues();
//        values.put(DBHelper.COLUMN_IJEKAVICA_ID,ijekavica.getIjekavicaId());
//        values.put(DBHelper.COLUMN_IJEKAVICA_EKAVICA_ID,ijekavica.getEkavica().getEkavicaId());
//        values.put(DBHelper.COLUMN_IJEKAVICA_CIRILICA,ijekavica.getIjekavicaCirilica());
//        values.put(DBHelper.COLUMN_IJEKAVICA_LATINICA,ijekavica.getIjekavicaLatinica());
//        values.put(DBHelper.COLUMN_IJEKAVICA_VRSTA_RIJECI_ID,ijekavica.getVrstaRijeciId());
//        values.put(DBHelper.COLUMN_IJEKAVICA_IZVOR_ID,ijekavica.getIzvorId());
//        values.put(DBHelper.COLUMN_IJEKAVICA_KORISNIK_ID_MIJENJAO,ijekavica.getKorisnikIdMijenjao());
//        values.put(DBHelper.COLUMN_IJEKAVICA_OPIS_CIRILICA,ijekavica.getOpisCirilica());
//        values.put(DBHelper.COLUMN_IJEKAVICA_OPIS_LATINICA,ijekavica.getOpisLatinica());
//        values.put(DBHelper.COLUMN_IJEKAVICA_BROJAC,ijekavica.getBrojac());
//        values.put(DBHelper.COLUMN_IJEKAVICA_BROJ_STRANICE,ijekavica.getBrojStranice());
//        values.put(DBHelper.COLUMN_IJEKAVICA_IZVOR_TEKST,ijekavica.getIzvorTekst());
//
//
//        database.insert(DBHelper.TABELA_IJEKAVICA,null,values);

        Object[] valuesO = new Object[]{ijekavica.getIjekavicaId(),ijekavica.getEkavica().getEkavicaId(),ijekavica.getIjekavicaCirilica(),ijekavica.getIjekavicaLatinica(),
                ijekavica.getVrstaRijeciId(),ijekavica.getIzvorId(),ijekavica.getKorisnikIdMijenjao(),ijekavica.getOpisCirilica(),ijekavica.getOpisLatinica(),ijekavica.getBrojac(),
                ijekavica.getBrojStranice(),ijekavica.getIzvorTekst()};

        database.execSQL("INSERT INTO "+DBHelper.TABELA_IJEKAVICA+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",valuesO);


        Log.i("ddd","IJEK");
    }

    public void create(ArrayList<Ijekavica> listaIjekavica){
        for (Ijekavica ijekavica:listaIjekavica) {
            createIjekavica(ijekavica);
        }
    }

    public List<Ijekavica> selectByEkavica(Ekavica ekavica){
        open();

        Cursor cursor = database.rawQuery("SELECT * FROM "+DBHelper.TABELA_IJEKAVICA+" WHERE "+DBHelper.COLUMN_IJEKAVICA_EKAVICA_ID+"="+ekavica.getEkavicaId(),null);

        List<Ijekavica> lista = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                int ijekavicaId = cursor.getInt(0);
                String rijecCirilica = cursor.getString(2);
                String rijecLatinica = cursor.getString(3);
                VrstaRijeci vrstaRijeci = null;
                if(!cursor.isNull(4))
                    vrstaRijeci = new VrstaRijeciDBHelper(context).selectById(cursor.getInt(4));
                Izvor izvor = null;
                if(!cursor.isNull(5))
                    izvor = new IzvorDbHelper(context).selectById(cursor.getInt(5));
                int korisnikIdMijenjao = cursor.getInt(6);
                String opisCirilica = cursor.getString(7);
                String opisLatinica = cursor.getString(8);
                int brojac = cursor.getInt(9);
                int brojStranice = cursor.getInt(10);
                String izvorTekst = cursor.getString(11);

                lista.add(new Ijekavica(ijekavicaId,
                        ekavica.getEkavicaId(),
                        ekavica,
                        rijecLatinica,
                        rijecCirilica,
                        vrstaRijeci!=null?vrstaRijeci.getVrstaRijeciId():-1,
                        vrstaRijeci,
                        izvor!=null?izvor.getIzvorId():-1,
                        izvor,
                        korisnikIdMijenjao,
                        null,
                        opisLatinica,
                        opisCirilica,
                        brojac,
                        brojStranice,
                        izvorTekst));
            }
            while (cursor.moveToNext());
        }

        return lista;
    }

    public ArrayList<Ijekavica> selectByEkavicaRijeci(int ekavicaId, String izbor){
        open();

        Cursor cursor = null;
        if (LATINICA.equals(izbor)) {
            cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABELA_IJEKAVICA + " WHERE " + DBHelper.COLUMN_EKAVICA_ID + "=" + ekavicaId, null);
        }
        else {
            cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABELA_IJEKAVICA + " WHERE " + DBHelper.COLUMN_EKAVICA_ID + "=" + ekavicaId, null);
        }

        ArrayList<Ijekavica> lista = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                int ijekavicaId = cursor.getInt(0);
                String rijecCirilica = cursor.getString(2);
                String rijecLatinica = cursor.getString(3);
                VrstaRijeci vrstaRijeci = null;
                if(!cursor.isNull(4))
                    vrstaRijeci = new VrstaRijeciDBHelper(context).selectById(cursor.getInt(4));
                Izvor izvor = null;
                if(!cursor.isNull(5)) {
                    int i = cursor.getInt(5);
                    Log.i("DEBUG", "" + i);
                    izvor = new IzvorDbHelper(context).selectById(cursor.getInt(5));
                }
                int korisnikIdMijenjao = cursor.getInt(6);
                String opisCirilica = cursor.getString(7);
                String opisLatinica = cursor.getString(8);
                int brojac = cursor.getInt(9);
                int brojStranice = cursor.getInt(10);
                String izvorTekst = cursor.getString(11);

                EkavicaDBHelper ekavicaDBHelper = new EkavicaDBHelper(context);
                Ekavica ekavica = ekavicaDBHelper.selectById(ekavicaId);

                lista.add(new Ijekavica(ijekavicaId,
                        ekavicaId,
                        ekavica,
                        rijecLatinica,
                        rijecCirilica,
                        vrstaRijeci!=null?vrstaRijeci.getVrstaRijeciId():-1,
                        vrstaRijeci,
                        (izvor!=null)?izvor.getIzvorId():(-1),
                        izvor,
                        korisnikIdMijenjao,
                        null,
                        opisLatinica,
                        opisCirilica,
                        brojac,
                        brojStranice,
                        izvorTekst));
            }
            while (cursor.moveToNext());
        }

        return lista;
    }

}
