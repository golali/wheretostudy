package de.hska.wheretostudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TopResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_results);

        TextView mFirstCountry = findViewById(R.id.firstCountrytop);
        TextView mSecondCountry = findViewById(R.id.secondCountrytop);
        TextView mThirdCountry = findViewById(R.id.thirdCountrytop);

        mFirstCountry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CountryDetailsActivity.class);
                intent.putExtra("country", "Mexico");
                startActivity(intent);
            }
        });

        mSecondCountry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CountryDetailsActivity.class);
                intent.putExtra("country", "USA");
                startActivity(intent);
            }
        });

        mThirdCountry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CountryDetailsActivity.class);
                intent.putExtra("country", "Hong Kong");
                startActivity(intent);
            }
        });
    }


}
