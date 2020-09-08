package com.praksa.ucenik.jatolog.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.praksa.ucenik.jatolog.model.VrstaRijeci;

import java.util.ArrayList;

public class VrstaRijeciDBHelper {
    private DBHelper dbHelper;
   private SQLiteDatabase database;
   private String[] kolone = {DBHelper.COLUMN_VRSTA_RIJECI_ID,DBHelper.COLUMN_VRSTA_RIJECI_NAZIV_LATINICA,DBHelper.COLUMN_VRSTA_RIJECI_NAZIV_CIRILICA};

   public VrstaRijeciDBHelper(Context context){
       dbHelper = new DBHelper(context);
   }

   public VrstaRijeciDBHelper(SQLiteDatabase database){
       this.database = database;
   }

   public void open(){
       database = dbHelper.getWritableDatabase();
   }

   public void close(){
       dbHelper.close();
       database=null;
   }

   public boolean createVrstaRijeci(VrstaRijeci vrstaRijeci){
       ContentValues values = new ContentValues();
       values.put(DBHelper.COLUMN_VRSTA_RIJECI_ID,vrstaRijeci.getVrstaRijeciId());
       values.put(DBHelper.COLUMN_VRSTA_RIJECI_NAZIV_LATINICA,vrstaRijeci.getNazivLatinica());
       values.put(DBHelper.COLUMN_VRSTA_RIJECI_NAZIV_CIRILICA,vrstaRijeci.getNazivCirilica());
       this.open();
       database.insert(DBHelper.TABELA_VRSTA_RIJECI,null,values);
       this.close();
       return true;
   }

    public void create(ArrayList<VrstaRijeci> listaVrstaRijeci){
       for (VrstaRijeci vrstaRijeci : listaVrstaRijeci) {
//           ContentValues values = new ContentValues();
//           values.put(DBHelper.COLUMN_VRSTA_RIJECI_ID, vrstaRijeci.getVrstaRijeciId());
//           values.put(DBHelper.COLUMN_VRSTA_RIJECI_NAZIV_LATINICA, vrstaRijeci.getNazivLatinica());
//           values.put(DBHelper.COLUMN_VRSTA_RIJECI_NAZIV_CIRILICA, vrstaRijeci.getNazivCirilica());
//           database.insert(DBHelper.TABELA_VRSTA_RIJECI, null, values);

           Object[] valuesO = new Object[]{vrstaRijeci.getVrstaRijeciId(),vrstaRijeci.getNazivLatinica(),vrstaRijeci.getNazivCirilica()};
           database.execSQL("INSERT INTO "+DBHelper.TABELA_VRSTA_RIJECI+" VALUES (?,?,?)",valuesO);
           Log.i("ddd","VR");

       }
    }

   public VrstaRijeci selectById(int vrstaRijeciId){
       open();

//       Cursor cursor = database.query(DBHelper.TABELA_VRSTA_RIJECI,kolone,DBHelper.COLUMN_VRSTA_RIJECI_ID+"=?",new String[]{vrstaRijeciId+""},null,null,null,null);
       Cursor cursor = database.rawQuery("SELECT * FROM "+DBHelper.TABELA_VRSTA_RIJECI+" WHERE "+DBHelper.COLUMN_VRSTA_RIJECI_ID + " = ?",new String[]{"" + vrstaRijeciId});
       cursor.moveToFirst();
       String latinica = cursor.getString(1);
       String cirilica = cursor.getString(2);

       close();
       return new VrstaRijeci(vrstaRijeciId,latinica,cirilica);
   }

   public ArrayList<VrstaRijeci> selectAll(){
       open();
       Cursor cursor = database.rawQuery("SELECT * FROM "+DBHelper.TABELA_VRSTA_RIJECI,null);
       ArrayList<VrstaRijeci> lista = new ArrayList<>();
       if(cursor.moveToFirst()){
           do{
                int vrstaRijeciId = cursor.getInt(0);
                String nazivLatinica = cursor.getString(1);
                String nazivCirilica = cursor.getString(2);

                lista.add(new VrstaRijeci(vrstaRijeciId,nazivLatinica,nazivCirilica));
           }
           while (cursor.moveToNext());
       }
       close();
       return lista;
   }
}
