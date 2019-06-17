package com.simcoder.tinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "DbSAMA";

    private static final String TBL_RANKING = "auswertung_table";
    private static final String COLUSER = "user";
    private static final String COLCOUNTRY = "country";
    private static final String COLRATING = "rating";
    private List<String> countries = new ArrayList<>();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAuswertungTable(db);
        createCountryTable(db);
        countries.add("Singapore");
        countries.add("Egypt");
        countries.add("France");
        countries.add("Hong Kong");
        countries.add("Mexico");
        countries.add("Spain");
        countries.add("Thailand");
        countries.add("Turkey");
        countries.add("Australia");
        countries.add("USA");
        for(String s : countries) {
            populateAuswertung("12345", s);
        }
        populateCountry(1, "Singapore", "English", "Singapore dollar", 18);
        populateCountry(2, "Egypt", "Arabic", "Egyptian pound", 18);
        populateCountry(3, "France", "French", "Euro", 18);
        populateCountry(4, "Hong Kong", "Chinese", "Hong Kong dollar", 18);
        populateCountry(5, "Mexico", "Spanish", "Mexican peso", 18);
        populateCountry(6, "Spain", "Spanish", "Euro", 16);
        populateCountry(7, "Thailand", "Thai", "Thai baht", 20);
        populateCountry(8, "Turkey", "Turkish", "Turkish lira", 18);
        populateCountry(9, "Australia", "English", "Australian dollar", 18);
        populateCountry(10, "USA", "English", "US dollar", 21);

        System.out.println("success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_RANKING);
        onCreate(db);
    }

    public void createAuswertungTable(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TBL_RANKING + "("+ COLUSER +" varchar(200) NOT NULL, " + COLCOUNTRY + " varchar(200) NOT NULL, "+ COLRATING + " INTEGER NOT NULL)";
        db.execSQL(createUserTable);
    }

    public void createCountryTable(SQLiteDatabase db) {
        String createCountryTable = "CREATE TABLE Country(c_id Integer PRIMARY KEY, c_name varchar(200) NOT NULL, c_language varchar(200) NOT NULL, c_currency varchar(200) NOT NULL, c_drink Integer NOT NULL)";
        db.execSQL(createCountryTable);
    }

    boolean populateCountry(Integer id, String name, String language, String currency, Integer drinking) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("c_id", id);
        contentValues.put("c_name", name);
        contentValues.put("c_language", language);
        contentValues.put("c_currency", currency);
        contentValues.put("c_drink", drinking);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert("Country", null, contentValues) != -1;
    }

    boolean populateAuswertung(String user, String country) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUSER, user);
        contentValues.put(COLCOUNTRY, country);
        contentValues.put(COLRATING, 0);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TBL_RANKING, null, contentValues) != -1;
    }

    boolean increaseRating(String user, String country, Integer increase) {
        try {
            SQLiteDatabase readDb = getReadableDatabase();
            Cursor queryCursor = readDb.rawQuery("SELECT " + COLRATING + " FROM " + TBL_RANKING + " WHERE " + COLUSER + " = " + user + " AND " + COLCOUNTRY + " = " + country, null);
            Integer oldRating = Integer.parseInt(queryCursor.getString(0));
            SQLiteDatabase writeDb = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLCOUNTRY, country);
            contentValues.put(COLRATING, oldRating + increase);
            return writeDb.update(TBL_RANKING, contentValues,COLUSER + "=?", new String[]{String.valueOf(user)}) == 1;
        }catch (Exception ex){
            return false;
        }

    }

    Cursor getTopCountries(String user) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT TOP(5) * FROM " + TBL_RANKING + " WHERE " + COLUSER + " IS " + user , null);
    }
}
