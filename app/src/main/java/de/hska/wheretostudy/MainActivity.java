package de.hska.wheretostudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);


        DatabaseReference usersDb = FirebaseDatabase.getInstance().getReference().child("Users");

        SQLiteDatabase db = new DatabaseHelper(getApplicationContext()).getReadableDatabase();

        Cursor c = db.rawQuery("select * from auswertung_table", null);

        if(c != null){
            c.moveToFirst();
            System.out.println("name: " + c.getInt(c.getColumnIndex("rating")) + " Bensalim");
            c.close();
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String currentUId = mAuth.getCurrentUser().getUid();

    }

    public void onYes(View view) {
        ImageView image = findViewById(R.id.imageView);

        switch(counter) {
            case 1:
                // increase what should be increased
                // move to the next
                image.setImageResource(R.drawable.image2);
                counter ++;
                break;
            case 2:
                // increase what should be increased
                // move to the next
                image.setImageResource(R.drawable.image3);
                counter ++;
                break;
            case 3:
                // increase what should be increased
                // move to the next
                image.setImageResource(R.drawable.image4);
                counter ++;
                break;
            case 4:
                // increase what should be increased
                // move to the next
                image.setImageResource(R.drawable.image5);
                counter ++;
                break;
            case 5:
                // increase what should be increased
                // move to the next
                image.setImageResource(R.drawable.image6);
                counter ++;
                break;
            case 6:
                // increase what should be increased
                // move to the next
                image.setImageResource(R.drawable.image7);
                counter ++;
                break;
            case 7:
                // increase what should be increased
                // move to the next
                image.setImageResource(R.drawable.image8);
                counter ++;
                break;
            case 8:
                // increase what should be increased
                // move to the next
                image.setImageResource(R.drawable.image9);
                counter ++;
                break;
            case 9:
                // increase what should be increased
                // move to the next
                image.setImageResource(R.drawable.image10);
                counter ++;
                break;
            case 10:
                // increase what should be increased
                // move to the results
                Intent intent = new Intent(MainActivity.this, StarterActivity.class);
                startActivity(intent);
                finish();
                counter = 1;
                break;
            default:
                System.out.println("no way!");
                break;
        }
    }
}
