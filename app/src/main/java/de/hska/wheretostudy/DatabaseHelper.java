package de.hska.wheretostudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        populateCountry(1, "Singapore", "English", "Singapore dollar", 18, db);
        populateCountry(2, "Egypt", "Arabic", "Egyptian pound", 18, db);
        populateCountry(3, "France", "French", "Euro", 18, db);
        populateCountry(4, "Hong Kong", "Chinese", "Hong Kong dollar", 18, db);
        populateCountry(5, "Mexico", "Spanish", "Mexican peso", 18, db);
        populateCountry(6, "Spain", "Spanish", "Euro", 16, db);
        populateCountry(7, "Thailand", "Thai", "Thai baht", 20, db);
        populateCountry(8, "Turkey", "Turkish", "Turkish lira", 18, db);
        populateCountry(9, "Australia", "English", "Australian dollar", 18, db);
        populateCountry(10, "USA", "English", "US dollar", 21, db);

        populateColleges(1, 1, "National University of Singapore", db);
        populateColleges(2, 1, "Nanyang Technological University", db);
        populateColleges(3, 1, "Singapore Management University", db);
        populateColleges(4, 2, "Ain Shams University", db);
        populateColleges(5, 2, "Military Technical College", db);
        populateColleges(6, 2, "Port Said University", db);
        populateColleges(7, 3, "Ecole Polytechnique", db);
        populateColleges(8, 3, "Mines et ponts", db);
        populateColleges(9, 3, "Centrale Paris", db);
        populateColleges(10, 4, "Chinese University", db);
        populateColleges(11, 4, "City University of Hong Kong", db);
        populateColleges(12, 4, "Lingnan University", db);
        populateColleges(13, 5, "Tecnologico de Moterrey", db);
        populateColleges(14, 5, "UDAM", db);
        populateColleges(15, 5, "Puebla Universidad", db);
        populateColleges(16, 6, "University of Almería", db);
        populateColleges(17, 6, "University of Barcelona", db);
        populateColleges(18, 6, "University of Vigo", db);
        populateColleges(19, 7, "Naresuan University", db);
        populateColleges(20, 7, "Maejo University", db);
        populateColleges(21, 7, "Thammasat University", db);
        populateColleges(22, 8, "Ankara University", db);
        populateColleges(23, 8, "Galatasaray University", db);
        populateColleges(24, 8, "Kadir Has University", db);
        populateColleges(25, 9, "University of Melbourne", db);
        populateColleges(26, 9, "Central Queensland University", db);
        populateColleges(27, 9, "Edith Cowan University", db);
        populateColleges(28, 10, "MIT", db);
        populateColleges(29, 10, "Stanford University", db);
        populateColleges(30, 10, "Harvard University", db);

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

    public void createCollegesTable(SQLiteDatabase db) {
        String createCollegesTable = "CREATE TABLE Colleges(college_id Integer PRIMARY KEY, country_id Integer, college_name varchar(200) NOT NULL)";
        db.execSQL(createCollegesTable);
    }

    boolean populateCountry(int id, String name, String language, String currency, Integer drinking, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("c_id", id);
        contentValues.put("c_name", name);
        contentValues.put("c_language", language);
        contentValues.put("c_currency", currency);
        contentValues.put("c_drink", drinking);
        return db.insert("Country", null, contentValues) != -1;
    }

    boolean populateColleges(int college_id, int country_id, String name, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("college_id", college_id);
        contentValues.put("country_id", country_id);
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
