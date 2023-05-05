package com.example.mydentalinstrumentshelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelperP extends SQLiteOpenHelper {



    public static final String INSTRUMENTS_TABLE = " INSTRUMENTS_TABLE";
    public static final String COLUMN_NOMBRE_INSTRUMENTO = "NOMBRE_INSTRUMENTO";
    public static final String COLUMN_CANTIDAD = "CANTIDAD";
    public static final String COLUMN_MARCA_INSTRUMENTO = "MARCA_INSTRUMENTO";
    public static final String COLUMN_ID = "ID";


    public DataBaseHelperP(@Nullable Context context) {

        super(context, "instrumentsp.db", null, 1);

    }

    //// Esta es la primera vez que se accesa a la base de datos.
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = " CREATE TABLE " + INSTRUMENTS_TABLE + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_CANTIDAD + " INTEGER(60), " + COLUMN_NOMBRE_INSTRUMENTO + " TEXT(60), " + COLUMN_MARCA_INSTRUMENTO + " TEXT(60))";
        db.execSQL(createTableStatement);


    }
    /// Esto es para que se mantenga la base de datos aun con las actualizaciones
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + INSTRUMENTS_TABLE);
        onCreate(db);
    }

    public boolean addOneP (Instruments instruments) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, instruments.getId());
        cv.put(COLUMN_CANTIDAD, instruments.getCantidad());
        cv.put(COLUMN_NOMBRE_INSTRUMENTO, instruments.getNombre());
        cv.put(COLUMN_MARCA_INSTRUMENTO, instruments.getMarca());

        long insert = db.insert(INSTRUMENTS_TABLE, null, cv);

        if (insert== -1){
            return false;
        } else
        {
            return true;
        }

    }

    public boolean modificarOneP (Instruments instruments) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, instruments.getId());
        cv.put(COLUMN_CANTIDAD, instruments.getCantidad());
        cv.put(COLUMN_NOMBRE_INSTRUMENTO, instruments.getNombre());
        cv.put(COLUMN_MARCA_INSTRUMENTO, instruments.getMarca());

        int cantidad = db.update(INSTRUMENTS_TABLE, cv, COLUMN_ID + " = ?",null);
        if(cantidad!=0){
            return true;
        }else{
            return false;
        }

    }



    public List<Instruments> getEveryoneP (){

        List<Instruments> returnList = new ArrayList<>();

        String queryString = " SELECT * FROM " + INSTRUMENTS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {

            do {
                int idInstruments = cursor.getInt(0);
                int cantidadInstruments = cursor.getInt(1);
                String nomInstruments= cursor.getString(2);
                String marcaInstruments = cursor.getString(3);

                Instruments nuevoInstrumento = new Instruments( idInstruments,nomInstruments, marcaInstruments, cantidadInstruments);

                returnList.add(nuevoInstrumento);

            }
            while (cursor.moveToNext());
        }else{
            //// no agrega nada
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public boolean deleteOneP (Instruments instruments){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = " DELETE  FROM "  + INSTRUMENTS_TABLE + " WHERE " + COLUMN_ID + " = ?";

        Cursor cursor= db.rawQuery(queryString, new String []{String.valueOf(instruments.getId())});

        if(cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }


    }


    public String[] buscarOneP (String buscar){
        String[] datos = new String[6];
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT FROM " + datos + " WHERE " + COLUMN_NOMBRE_INSTRUMENTO + "=" + buscar;

        Cursor cursor= db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            for (int i = 0 ; i<5; i++) {
                datos[i] = cursor.getString(i);
            }
            datos[5] = "Encontrado";
        } else{
            datos[5] = "No se encontro " + buscar;
        }
        return datos;
    }

}
