package com.praksa.ucenik.jatolog.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Jatolog.db";
    public static final int DATABASE_VERSION = 1;


    /*-----------------------TABELA VRSTA RIJECI-----------------------------------*/


    public static final String TABELA_VRSTA_RIJECI = "VrstaRijeci";

    public static final String COLUMN_VRSTA_RIJECI_ID = "VrstaRijeciId";
    public static final String COLUMN_VRSTA_RIJECI_NAZIV_LATINICA = "NazivLatinica";
    public static final String COLUMN_VRSTA_RIJECI_NAZIV_CIRILICA = "NazivCirilica";

    public static final String CREATE_VRSTA_RIJECI = "CREATE TABLE "+TABELA_VRSTA_RIJECI+"(" +
            COLUMN_VRSTA_RIJECI_ID + " SMALLINT PRIMARY KEY," +
            COLUMN_VRSTA_RIJECI_NAZIV_LATINICA + " VARCHAR(50) NOT NULL," +
            COLUMN_VRSTA_RIJECI_NAZIV_CIRILICA + " VARCHAR(50) NOT NULL" +
            ");";

    public static final String DROP_VRSTA_RIJECI="DROP TABLE IF EXISTS "+TABELA_VRSTA_RIJECI;

    /*-----------------------TABELA KORISNIK---------------------------------------*/


    public static final String TABELA_KORISNIK = "Korisnik";

    public static final String COLUMN_KORISNIK_ID = "KorisnikId";
    public static final String COLUMN_KORISNIK_IME = "Ime";
    public static final String COLUMN_KORISNIK_PREZIME = "Prezime";
    public static final String COLUMN_KORISNIK_KORISNICKO_IME = "KorisnickoIme";
    public static final String COLUMN_KORISNIK_LOZINKA = "Lozinka";
    public static final String COLUMN_KORISNIK_EMAIL = "Email";


    public static final String CREATE_KORISNIK = "CREATE TABLE "+TABELA_KORISNIK+" (" +
            COLUMN_KORISNIK_ID + " SMALLINT PRIMARY KEY,"+
            COLUMN_KORISNIK_IME + " VARCHAR(50) NOT NULL," +
            COLUMN_KORISNIK_PREZIME + " VARCHAR(50) NOT NULL,"+
            COLUMN_KORISNIK_KORISNICKO_IME + " VARCHAR(50) NOT NULL," +
            COLUMN_KORISNIK_LOZINKA + " VARCHAR(512) NOT NULL,"+
            COLUMN_KORISNIK_EMAIL + " VARCHAR(100) NULL" +
            ");";

    public static final String DROP_KORISNIK="DROP TABLE IF EXISTS "+TABELA_KORISNIK;

    /*-----------------------TABELA EKAVICA---------------------------------------*/


    public static final String TABELA_EKAVICA = "Ekavica";

    public static final String COLUMN_EKAVICA_ID = "EkavicaId";
    public static final String COLUMN_EKAVICA_RIJEC_LATINICA = "RijecLatinica";
    public static final String COLUMN_EKAVICA_RIJEC_CIRILICA = "RijecCirilica";
    public static final String COLUMN_EKAVICA_OBRISANO = "Obrisano";
    public static final String COLUMN_EKAVICA_EKAVICA_KORISNIK_ID = "KorisnikId";
    public static final String COLUMN_EKAVICA_BROJAC = "Brojac";


    public static final String CREATE_EKAVICA = "CREATE TABLE "+TABELA_EKAVICA+" (" +
            COLUMN_EKAVICA_ID + " INTEGER PRIMARY KEY,"+
            COLUMN_EKAVICA_RIJEC_LATINICA + " VARCHAR(50) NOT NULL," +
            COLUMN_EKAVICA_RIJEC_CIRILICA + " VARCHAR(50) NOT NULL,"+
            COLUMN_EKAVICA_OBRISANO + " int NOT NULL," +
            COLUMN_EKAVICA_EKAVICA_KORISNIK_ID + " SMALLINT NOT NULL,"+
            COLUMN_EKAVICA_BROJAC + " int NOT NULL" +
            ");";

    public static final String DROP_EKAVICA="DROP TABLE IF EXISTS "+TABELA_EKAVICA;

    /*-----------------------TABELA IZVOR---------------------------------------*/


    public static final String TABELA_IZVOR = "Izvor";

    public static final String COLUMN_IZVOR_ID = "IzvorId";
    public static final String COLUMN_IZVOR_NAZIV = "Naziv";
    public static final String COLUMN_IZVOR_AUTOR = "Autor";
    public static final String COLUMN_IZVOR_GODINA_IZDAVANJA= "GodinaIzdavanja";
    public static final String COLUMN_IZVOR_MJESTO_IZDAVANJA = "MjestoIzdavanja";


    public static final String CREATE_IZVOR = "CREATE TABLE "+TABELA_IZVOR+" (" +
            COLUMN_IZVOR_ID + " INTEGER PRIMARY KEY,"+
            COLUMN_IZVOR_NAZIV + " VARCHAR(200) NOT NULL," +
            COLUMN_IZVOR_AUTOR + " VARCHAR(100) NOT NULL,"+
            COLUMN_IZVOR_GODINA_IZDAVANJA + " SMALLINT NOT NULL," +
            COLUMN_IZVOR_MJESTO_IZDAVANJA + " VARCHAR(100) NOT NULL"+
            ");";

    public static final String DROP_IZVOR="DROP TABLE IF EXISTS  "+TABELA_IZVOR;

    /*-----------------------TABELA IJEKAVICA---------------------------------------*/


    public static final String TABELA_IJEKAVICA = "Ijekavica";

    public static final String COLUMN_IJEKAVICA_ID = "IjekavicaId";
    public static final String COLUMN_IJEKAVICA_EKAVICA_ID = "EkavicaId";
    public static final String COLUMN_IJEKAVICA_CIRILICA = "IjekavicaCirilica";
    public static final String COLUMN_IJEKAVICA_LATINICA= "IjekavicaLatinica";
    public static final String COLUMN_IJEKAVICA_VRSTA_RIJECI_ID = "VrstaRijeciId";
    public static final String COLUMN_IJEKAVICA_IZVOR_ID = "IzvorId";
    public static final String COLUMN_IJEKAVICA_KORISNIK_ID_MIJENJAO = "KorisnikIdMijenjao";
    public static final String COLUMN_IJEKAVICA_OPIS_CIRILICA = "OpisCirilica";
    public static final String COLUMN_IJEKAVICA_OPIS_LATINICA= "OpisLatinica";
    public static final String COLUMN_IJEKAVICA_BROJAC = "Brojac";
    public static final String COLUMN_IJEKAVICA_BROJ_STRANICE= "BrojStranice";
    public static final String COLUMN_IJEKAVICA_IZVOR_TEKST = "IzvorTekst";


    public static final String CREATE_IJEKAVICA = "CREATE TABLE "+TABELA_IJEKAVICA+" (" +
            COLUMN_IJEKAVICA_ID + " INTEGER PRIMARY KEY,"+
            COLUMN_IJEKAVICA_EKAVICA_ID + " INTEGER NOT NULL," +
            COLUMN_IJEKAVICA_CIRILICA + " VARCHAR(200) NOT NULL,"+
            COLUMN_IJEKAVICA_LATINICA + " VARCHAR(200) NOT NULL," +
            COLUMN_IJEKAVICA_VRSTA_RIJECI_ID + " SMALLINT NULL,"+
            COLUMN_IJEKAVICA_IZVOR_ID + " SMALLINT NULL," +
            COLUMN_IJEKAVICA_KORISNIK_ID_MIJENJAO + " SMALLINT NOT NULL,"+
            COLUMN_IJEKAVICA_OPIS_CIRILICA + " VARCHAR(4000) NULL," +
            COLUMN_IJEKAVICA_OPIS_LATINICA + " VARCHAR(4000) NULL,"+
            COLUMN_IJEKAVICA_BROJAC + " INT NOT NULL," +
            COLUMN_IJEKAVICA_BROJ_STRANICE + " SMALLINT NULL,"+
            COLUMN_IJEKAVICA_IZVOR_TEKST + " VARCHAR(4000) NULL" +
            ");";


    public static final String DROP_IJEKAVICA="DROP TABLE IF EXISTS "+TABELA_IJEKAVICA;
    /*-----------------------TABELA IJEKAVICA PRIMJER---------------------------------------*/


    public static final String TABELA_IJEKAVICA_PRIMJER = "IjekavicaPrimjer";

    public static final String COLUMN_IJEKAVICA_PRIMJER_ID = "IjekavicaPrimjerId";
    public static final String COLUMN_IJEKAVICA_PRIMJER_IJEKAVICA_ID = "IjekavicaId";
    public static final String COLUMN_IJEKAVICA_PRIMJER_SADRZAJ = "Sadrzaj";


    public static final String CREATE_IJEKAVICA_PRIMJER = "CREATE TABLE "+TABELA_IJEKAVICA_PRIMJER+" (" +
            COLUMN_IJEKAVICA_PRIMJER_ID + " INTEGER PRIMARY KEY,"+
            COLUMN_IJEKAVICA_PRIMJER_IJEKAVICA_ID + " INTEGER NOT NULL," +
            COLUMN_IJEKAVICA_PRIMJER_SADRZAJ + " VARCHAR(4000) NOT NULL"+
            ");";

    public static final String DROP_IJEKAVICA_PRIMJER="DROP TABLE IF EXISTS "+TABELA_IJEKAVICA_PRIMJER;

    /*-----------------------TABELA PRIJAVA GRESKE---------------------------------------*/


    public static final String TABELA_PRIJAVA_GRESKE = "PrijavaGreske";

    public static final String COLUMN_PRIJAVA_GRESKE_ID = "PrijavaGreskeId";
    public static final String COLUMN_PRIJAVA_GRESKE_EKAVICA_ID = "EkavicaId";
    public static final String COLUMN_PRIJAVA_GRESKE_IJEKAVICA_ID = "IjekavicaId";
    public static final String COLUMN_PRIJAVA_GRESKE_NAPOMENA = "Nampomena";
    public static final String COLUMN_PRIJAVA_GRESKE_EMAIL_POSILJAOCA = "EmailPosiljaoca";

    public static final String CREATE_PRIJAVA_GRESKE = "CREATE TABLE "+TABELA_PRIJAVA_GRESKE+" (" +
            COLUMN_PRIJAVA_GRESKE_ID + " INTEGER PRIMARY KEY,"+
            COLUMN_PRIJAVA_GRESKE_EKAVICA_ID + " INTEGER NULL," +
            COLUMN_PRIJAVA_GRESKE_IJEKAVICA_ID + " INTEGER NULL,"+
            COLUMN_PRIJAVA_GRESKE_NAPOMENA + " VARCHAR(4000) NULL," +
            COLUMN_PRIJAVA_GRESKE_EMAIL_POSILJAOCA + " VARCHAR(100) NULL"+
            ");";


    //METODE


    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_VRSTA_RIJECI);
        sqLiteDatabase.execSQL(CREATE_EKAVICA);
        sqLiteDatabase.execSQL(CREATE_IZVOR);
        sqLiteDatabase.execSQL(CREATE_IJEKAVICA);
        sqLiteDatabase.execSQL(CREATE_IJEKAVICA_PRIMJER);
        Log.i("debug baze","nesto---------------------------------------------");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(DROP_IJEKAVICA_PRIMJER);
        sqLiteDatabase.execSQL(DROP_IJEKAVICA);
        sqLiteDatabase.execSQL(DROP_IZVOR);
        sqLiteDatabase.execSQL(DROP_VRSTA_RIJECI);
        sqLiteDatabase.execSQL(DROP_EKAVICA);
        //onCreate(sqLiteDatabase);
    }

    public void clearData(SQLiteDatabase database) {

        database.execSQL("DELETE FROM " + DBHelper.TABELA_IJEKAVICA);
        database.execSQL("DELETE FROM " + DBHelper.TABELA_IJEKAVICA_PRIMJER);
        database.execSQL("DELETE FROM " + DBHelper.TABELA_IZVOR);
        database.execSQL("DELETE FROM " + DBHelper.TABELA_EKAVICA);
        database.execSQL("DELETE FROM " + DBHelper.TABELA_VRSTA_RIJECI);
    }
}
