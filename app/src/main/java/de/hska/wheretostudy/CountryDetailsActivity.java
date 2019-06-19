package de.hska.wheretostudy;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryDetailsActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        Bundle extras = getIntent().getExtras();

        String country = extras.getString("country");

        Map<String, String> countriesInfos = getCountryDetails(country);

        TextView mNameDetails = findViewById(R.id.nameDetail);
        mNameDetails.setText(countriesInfos.get("name"));
        TextView mLanguageDetails = findViewById(R.id.languageDetail);
        mLanguageDetails.setText(countriesInfos.get("language"));
        TextView mCurrencyDetails = findViewById(R.id.currencyDetail);
        mCurrencyDetails.setText(countriesInfos.get("currency"));
        TextView mDrinkDetails = findViewById(R.id.drinkDetail);
        mDrinkDetails.setText(countriesInfos.get("drink"));

        List<String> colleges = getColleges(country);

        ListAdapter buckysAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, colleges);
        ListView buckyListView = findViewById(R.id.college_list_id);
        buckyListView.setAdapter(buckysAdapter);

        System.out.println("Bensalim: " + colleges.get(1));

    }

    public Map<String, String> getCountryDetails (String country) {
        Map<String, String> result = new HashMap<>();
        SQLiteDatabase readDb = new DatabaseHelper(getApplicationContext()).getReadableDatabase();
        Cursor c = readDb.rawQuery("SELECT * FROM Country WHERE c_name = ?", new String[]{country});
        if (c.moveToFirst()) {
            c.moveToFirst();
            result.put("name", c.getString(c.getColumnIndex("c_name")));
            result.put("language", c.getString(c.getColumnIndex("c_language")));
            result.put("currency", c.getString(c.getColumnIndex("c_currency")));
            result.put("drink", c.getString(c.getColumnIndex("c_drink")));
            c.close();
        }
        return result;
    }

    List<String> getColleges (String country) {
        List<String> result = new ArrayList<>();
        SQLiteDatabase readDb = new DatabaseHelper(getApplicationContext()).getReadableDatabase();
        Cursor c = readDb.rawQuery("SELECT * FROM Colleges WHERE country_name = ?", new String[]{country});
        if (c.moveToFirst()) {
            do {
                result.add(c.getString(c.getColumnIndex("college_name")));

            } while (c.moveToNext());
        }
        return result;
    }
}
