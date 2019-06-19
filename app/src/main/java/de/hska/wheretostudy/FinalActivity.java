package de.hska.wheretostudy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_final);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();

        final List<String> countries = getTopCountries(userId);
        List<Integer> ratings = getTopRatings(userId);

        TextView mFirstCountry = findViewById(R.id.firstCountry);
        mFirstCountry.setText(countries.get(0));
        TextView mSecondCountry = findViewById(R.id.secondCountry);
        mSecondCountry.setText(countries.get(1));
        TextView mThirdCountry = findViewById(R.id.thirdCountry);
        mThirdCountry.setText(countries.get(2));

        TextView mFirstScore = findViewById(R.id.firstScore);
        mFirstScore.setText(String.valueOf(ratings.get(0)));
        TextView mSecondScore = findViewById(R.id.secondScore);
        mSecondScore.setText(String.valueOf(ratings.get(1)));
        TextView mThirdScore = findViewById(R.id.thirdScore);
        mThirdScore.setText(String.valueOf(ratings.get(2)));

        mFirstCountry.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 Intent intent = new Intent(getBaseContext(), CountryDetailsActivity.class);
                 intent.putExtra("country", countries.get(0));
                 startActivity(intent);
             }
        });

        mSecondCountry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CountryDetailsActivity.class);
                intent.putExtra("country", countries.get(1));
                startActivity(intent);
            }
        });

        mThirdCountry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CountryDetailsActivity.class);
                intent.putExtra("country", countries.get(2));
                startActivity(intent);
            }
        });
    }

    List<String> getTopCountries(String user) {
        List<String> result = new ArrayList<String>();
        SQLiteDatabase readDb = new DatabaseHelper(getApplicationContext()).getReadableDatabase();
        Cursor c = readDb.rawQuery("SELECT * FROM auswertung_table WHERE user = ? ORDER BY rating DESC", new String[]{user});
        if (c.moveToFirst()) {
            do {
                result.add(c.getString(c.getColumnIndex("country")));

            } while (c.moveToNext());
        }
        return result;
    }

    List<Integer> getTopRatings(String user) {
        List<Integer> result = new ArrayList<Integer>();
        SQLiteDatabase readDb = new DatabaseHelper(getApplicationContext()).getReadableDatabase();
        Cursor c = readDb.rawQuery("SELECT * FROM auswertung_table WHERE user = ? ORDER BY rating DESC", new String[]{user});
        if (c.moveToFirst()) {
            do {
                result.add(c.getInt(c.getColumnIndex("rating")));

            } while (c.moveToNext());
        }
        return result;
    }

    public void startAgain (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
