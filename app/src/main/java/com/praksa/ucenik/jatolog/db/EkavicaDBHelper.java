package com.praksa.ucenik.jatolog.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.praksa.ucenik.jatolog.model.Ekavica;
import com.praksa.ucenik.jatolog.model.Ijekavica;
import com.praksa.ucenik.jatolog.model.VrstaRijeci;

import java.util.ArrayList;
import java.util.List;

public class EkavicaDBHelper {
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    public static final String CIRILICA = "CIRILICA";
    public static final String LATINICA = "LATINICA";
    private Context context;

    public EkavicaDBHelper(Context context){
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public EkavicaDBHelper(SQLiteDatabase database){
        this.database = database;
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
        database = null;
    }

    public void createEkavica(Ekavica ekavica){
//        ContentValues values = new ContentValues();
//        values.put(DBHelper.COLUMN_EKAVICA_ID,ekavica.getEkavicaId());
//        values.put(DBHelper.COLUMN_EKAVICA_RIJEC_LATINICA,ekavica.getRijecLatinica());
//        values.put(DBHelper.COLUMN_EKAVICA_RIJEC_CIRILICA,ekavica.getRijecCirilica());
        int obrisano = 0;
        if(ekavica.isObrisano())
            obrisano = 1;
//        values.put(DBHelper.COLUMN_EKAVICA_OBRISANO,obrisano);
//        values.put(DBHelper.COLUMN_EKAVICA_EKAVICA_KORISNIK_ID,ekavica.getKorisnikId());
//        values.put(DBHelper.COLUMN_EKAVICA_BROJAC,ekavica.getBrojac());
//        database.insert(DBHelper.TABELA_EKAVICA,null,values);

        Object[] valuesO = new Object[]{ekavica.getEkavicaId(),ekavica.getRijecLatinica(),ekavica.getRijecCirilica(),obrisano,ekavica.getKorisnikId(),ekavica.getBrojac()};
        database.execSQL("INSERT INTO "+DBHelper.TABELA_EKAVICA+" VALUES (?,?,?,?,?,?)",valuesO);

        Log.i("ddd","EK");
    }

    public void create(ArrayList<Ekavica> listaEkavica){
        for (Ekavica ekavica: listaEkavica) {
            createEkavica(ekavica);
        }
    }

    public ArrayList<Ekavica> selectAll(){
        open();
        String[] kolone = {DBHelper.COLUMN_EKAVICA_ID,DBHelper.COLUMN_EKAVICA_RIJEC_LATINICA,DBHelper.COLUMN_EKAVICA_RIJEC_CIRILICA,DBHelper.COLUMN_EKAVICA_OBRISANO,DBHelper.COLUMN_EKAVICA_EKAVICA_KORISNIK_ID,DBHelper.COLUMN_EKAVICA_BROJAC};
        Cursor cursor = database.query(DBHelper.TABELA_EKAVICA,kolone,null,null,null,null,null);
//        Cursor cursor = database.rawQuery("SELECT * FROM "+DBHelper.TABELA_EKAVICA,null);
        ArrayList<Ekavica> lista = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                int ekavicaId = cursor.getInt(0);
                String rijecLatinica = cursor.getString(1);
                String rijecCirilica = cursor.getString(2);
                int obrisano = cursor.getInt(3);
                int korisnikId = cursor.getInt(4);
                int brojac = cursor.getInt(5);

                Ekavica ekavica = new Ekavica(ekavicaId, rijecLatinica, rijecCirilica, (obrisano == 0)?false:true, korisnikId, brojac);
                lista.add(ekavica);
            }
            while (cursor.moveToNext());
        }

        close();

        return lista;
    }

//    public ArrayList<String> selectAllRijeci(String izbor){
//        open();
//        String[] kolone = {DBHelper.COLUMN_EKAVICA_ID,DBHelper.COLUMN_EKAVICA_RIJEC_CIRILICA,DBHelper.COLUMN_EKAVICA_RIJEC_CIRILICA,DBHelper.COLUMN_EKAVICA_OBRISANO,DBHelper.COLUMN_EKAVICA_EKAVICA_KORISNIK_ID,DBHelper.COLUMN_EKAVICA_BROJAC};
//        Cursor cursor = database.query(DBHelper.TABELA_EKAVICA,kolone,null,null,null,null,null);
////        Cursor cursor = database.rawQuery("SELECT * FROM "+DBHelper.TABELA_EKAVICA,null);
//        ArrayList<String> lista = new ArrayList<>();
//        if(cursor.moveToFirst()) {
//            do {
//
//                String rijec = null;
//                if (izbor.equals(LATINICA)) {
//                    rijec = cursor.getString(1);
//                }
//                else {
//                    rijec = cursor.getString(2);
//                }
//                lista.add(rijec);
//            }
//            while (cursor.moveToNext());
//        }
//
//        close();
//
//        return lista;
//    }


    public Ekavica selectById(int ekavicaId){
        open();

//       Cursor cursor = database.query(DBHelper.TABELA_VRSTA_RIJECI,kolone,DBHelper.COLUMN_VRSTA_RIJECI_ID+"=?",new String[]{vrstaRijeciId+""},null,null,null,null);
        Cursor cursor = database.rawQuery("SELECT * FROM "+DBHelper.TABELA_EKAVICA+" WHERE "+DBHelper.COLUMN_EKAVICA_ID + " = ?",new String[]{ekavicaId+""});
        cursor.moveToFirst();
        String rijecLatinica = cursor.getString(1);
        String rijecCirilica = cursor.getString(2);
        int obrisano = cursor.getInt(3);
        int korisnikId = cursor.getInt(4);
        int brojac = cursor.getInt(5);

        close();
        boolean o = obrisano==1?true:false;

        return new Ekavica(ekavicaId,rijecLatinica,rijecCirilica,o,korisnikId,brojac);
    }

    public List<Ekavica> selectByRijec(String rijec, String izbor){
        String kolona = DBHelper.COLUMN_EKAVICA_RIJEC_LATINICA;
        if(izbor.equals(CIRILICA)) {
            kolona = DBHelper.COLUMN_EKAVICA_RIJEC_CIRILICA;
        }
        open();

        Cursor cursor = database.rawQuery("SELECT * FROM "+DBHelper.TABELA_EKAVICA+" WHERE "+kolona+" like '"+rijec+"%';",null);

        List<Ekavica> lista = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                int ekavicaId = cursor.getInt(0);
                String rijecLatinica = cursor.getString(1);
                String rijecCirilica = cursor.getString(2);
                int obrisano = cursor.getInt(3);
                int korisnikId = cursor.getInt(4);
                int brojac = cursor.getInt(5);
                boolean o = obrisano==1?true:false;
                lista.add(new Ekavica(ekavicaId,rijecLatinica,rijecCirilica,o,korisnikId,brojac));
            }
            while (cursor.moveToNext());
        }

        return lista;
    }

    public ArrayList<Ijekavica> selectTopRijeci(int brojRijeci){
        open();

        Cursor cursor = database.rawQuery("SELECT "+DBHelper.COLUMN_EKAVICA_ID+" FROM "+DBHelper.TABELA_EKAVICA+" ORDER BY "+DBHelper.COLUMN_EKAVICA_BROJAC+" LIMIT "+brojRijeci,null);

        ArrayList<Ijekavica> lista = new ArrayList<>();

        IjekavicaDBHelper ijekavicaDBHelper = new IjekavicaDBHelper(context);
        if(cursor.moveToFirst()){
            do{
                int ekavicaId = cursor.getInt(0);

                Ekavica ekavica = selectById(ekavicaId);

                List<Ijekavica> listaPronadjenihRijeci = ijekavicaDBHelper.selectByEkavica(ekavica);
                for(Ijekavica i : listaPronadjenihRijeci){
                    lista.add(i);
                }
            }
            while (cursor.moveToNext());
        }

        close();

        return lista;
    }
}
