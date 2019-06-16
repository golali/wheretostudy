package com.simcoder.tinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String AUSWERTUNG_TABLE_NAME = "auswertung_table";
    private static final String COLUSER = "user";
    private static final String COLCOUNTRY = "country";
    private static final String COLRATING = "rating";
    private List<String> countries = new ArrayList<>();

    public DatabaseHelper(Context context) {
        super(context, AUSWERTUNG_TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
        countries.add("Spanien");
        countries.add("Australien");
        countries.add("Bolivien");
        countries.add("Kanada");
        countries.add("Malaysiaien");
        countries.add("Mongolei");
        countries.add("Norwegen");
        for(String s : countries) {
            addCountry("12345", s);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + AUSWERTUNG_TABLE_NAME);
        onCreate(db);
    }

    public void createTables(SQLiteDatabase db) {
        //Usertable

        String createUserTable = "CREATE TABLE " + AUSWERTUNG_TABLE_NAME + "("+ COLUSER +" varchar(200) NOT NULL, " + COLCOUNTRY + " varchar(200) NOT NULL, "+ COLRATING + " INTEGER NOT NULL)";
        db.execSQL(createUserTable);
    }

    boolean addCountry(String user, String country) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUSER, user);
        contentValues.put(COLCOUNTRY, country);
        contentValues.put(COLRATING, 0);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(AUSWERTUNG_TABLE_NAME, null, contentValues) != -1;
    }

    //Wir brauchen einen Wert welchen wir hier übergeben, zb. 3 bei sehr gut und 1 bei mittel usw
    //
    boolean increaseRating(String user, String country) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLCOUNTRY, country);
        return db.update(AUSWERTUNG_TABLE_NAME, contentValues, COLUSER + "=?", new String[]{String.valueOf(user)}) == 1;
    }

    public String getTopCountries(int rang){
        // Funktion damit wir die besten 3-5 Countries bekommen oder rang = 3 -> 3.Platz bekommen
        return "testvalue";
    }
}
