package de.hska.wheretostudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.auth.FirebaseAuth;

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
        createCollegesTable(db);
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
            populateAuswertung("12345", s, db);
        }
        populateCountry(1, "Singapore", "English", "Singapore dollar", "18", db);
        populateCountry(2, "Egypt", "Arabic", "Egyptian pound", "18", db);
        populateCountry(3, "France", "French", "Euro", "18", db);
        populateCountry(4, "Hong Kong", "Chinese", "Hong Kong dollar", "18", db);
        populateCountry(5, "Mexico", "Spanish", "Mexican peso", "18", db);
        populateCountry(6, "Spain", "Spanish", "Euro", "16", db);
        populateCountry(7, "Thailand", "Thai", "Thai baht", "20", db);
        populateCountry(8, "Turkey", "Turkish", "Turkish lira", "18", db);
        populateCountry(9, "Australia", "English", "Australian dollar", "18", db);
        populateCountry(10, "USA", "English", "US dollar", "21", db);

        populateColleges(1, "Singapore", "National University of Singapore", db);
        populateColleges(2, "Singapore", "Nanyang Technological University", db);
        populateColleges(3, "Singapore", "Singapore Management University", db);
        populateColleges(4, "Egypt", "Ain Shams University", db);
        populateColleges(5, "Egypt", "Military Technical College", db);
        populateColleges(6, "Egypt", "Port Said University", db);
        populateColleges(7, "France", "Ecole Polytechnique", db);
        populateColleges(8, "France", "Mines et ponts", db);
        populateColleges(9, "France", "Centrale Paris", db);
        populateColleges(10, "Hong Kong", "Chinese University", db);
        populateColleges(11, "Hong Kong", "City University of Hong Kong", db);
        populateColleges(12, "Hong Kong", "Lingnan University", db);
        populateColleges(13, "Mexico", "Tecnologico de Moterrey", db);
        populateColleges(14, "Mexico", "UDAM", db);
        populateColleges(15, "Mexico", "Puebla Universidad", db);
        populateColleges(16, "Spain", "University of Almería", db);
        populateColleges(17, "Spain", "University of Barcelona", db);
        populateColleges(18, "Spain", "University of Vigo", db);
        populateColleges(19, "Thailand", "Naresuan University", db);
        populateColleges(20, "Thailand", "Maejo University", db);
        populateColleges(21, "Thailand", "Thammasat University", db);
        populateColleges(22, "Turkey", "Ankara University", db);
        populateColleges(23, "Turkey", "Galatasaray University", db);
        populateColleges(24, "Turkey", "Kadir Has University", db);
        populateColleges(25, "Australia", "University of Melbourne", db);
        populateColleges(26, "Australia", "Central Queensland University", db);
        populateColleges(27, "Australia", "Edith Cowan University", db);
        populateColleges(28, "USA", "MIT", db);
        populateColleges(29, "USA", "Stanford University", db);
        populateColleges(30, "USA", "Harvard University", db);

        System.out.println("success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_RANKING);
        db.execSQL("DROP TABLE IF EXISTS " + "Country");
        db.execSQL("DROP TABLE IF EXISTS " + "College");
        onCreate(db);
    }

    public void createAuswertungTable(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TBL_RANKING + "("+ COLUSER +" varchar(200) NOT NULL, " + COLCOUNTRY + " varchar(200) NOT NULL, "+ COLRATING + " INTEGER NOT NULL)";
        db.execSQL(createUserTable);
    }

    public void createCountryTable(SQLiteDatabase db) {
        String createCountryTable = "CREATE TABLE Country(c_id Integer PRIMARY KEY, c_name varchar(200) NOT NULL, c_language varchar(200) NOT NULL, c_currency varchar(200) NOT NULL, c_drink varchar(200) NOT NULL)";
        db.execSQL(createCountryTable);
    }

    public void createCollegesTable(SQLiteDatabase db) {
        String createCollegesTable = "CREATE TABLE Colleges(college_id Integer PRIMARY KEY, country_name varchar(200) NOT NULL, college_name varchar(200) NOT NULL)";
        db.execSQL(createCollegesTable);
    }

    boolean populateCountry(int id, String name, String language, String currency, String drinking, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("c_id", id);
        contentValues.put("c_name", name);
        contentValues.put("c_language", language);
        contentValues.put("c_currency", currency);
        contentValues.put("c_drink", drinking);
        return db.insert("Country", null, contentValues) != -1;
    }

    boolean populateColleges(int college_id, String country, String name, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("college_id", college_id);
        contentValues.put("country_name", country);
        contentValues.put("college_name", name);
        return db.insert("Colleges", null, contentValues) != -1;
    }

    boolean populateAuswertung(String user, String country, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUSER, user);
        contentValues.put(COLCOUNTRY, country);
        contentValues.put(COLRATING, 0);
        return db.insert(TBL_RANKING, null, contentValues) != -1;
    }




}
