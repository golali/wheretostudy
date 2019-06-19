package de.hska.wheretostudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        System.out.println("current user: " + currentUser);

        setUid(currentUser);
        resetRating(currentUser);

        SQLiteDatabase db = new DatabaseHelper(getApplicationContext()).getReadableDatabase();

        /*Cursor c = db.rawQuery("select * from Colleges", null);

        if(c != null){
            c.moveToFirst();
            System.out.println("name: " + c.getString(c.getColumnIndex("college_name")) + " Bensalim");
            c.close();
        }*/

        // increaseRating(currentUser, "USA", 2);
        /*Cursor c = db.rawQuery("select * from auswertung_table where country=?", new String[] {"USA"});

        if(c != null){
            c.moveToFirst();
            System.out.println("name: " + c.getInt(c.getColumnIndex("rating")) + " Bensalim");
            c.close();
        }*/
        Cursor c = db.rawQuery("SELECT * FROM Colleges WHERE country_name = ?", new String[]{"USA"});

        if(c != null){
            c.moveToFirst();
            System.out.println("name: " + c.getString(c.getColumnIndex("country_name")) + " Bensalim");
            c.close();
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String currentUId = mAuth.getCurrentUser().getUid();

    }

    public void onYes(View view) {
        ImageView image = findViewById(R.id.imageView);
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        switch(counter) {
            case 1:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 4);
                increaseRating(currentUser, "Singapore", 4);
                increaseRating(currentUser, "Hong Kong", 4);

                // move to the next
                image.setImageResource(R.drawable.image2);
                counter ++;
                break;
            case 2:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 4);
                increaseRating(currentUser, "Egypt", 4);
                // move to the next
                image.setImageResource(R.drawable.image3);
                counter ++;
                break;
            case 3:
                // increase what should be increased
                increaseRating(currentUser, "France", 4);
                // move to the next
                image.setImageResource(R.drawable.image4);
                counter ++;
                break;
            case 4:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 4);
                increaseRating(currentUser, "Singapore", 4);
                increaseRating(currentUser, "Hong Kong", 4);
                // move to the next
                image.setImageResource(R.drawable.image5);
                counter ++;
                break;
            case 5:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 4);
                increaseRating(currentUser, "Spain", 4);
                // move to the next
                image.setImageResource(R.drawable.image6);
                counter ++;
                break;
            case 6:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 4);
                increaseRating(currentUser, "Spain", 4);
                // move to the next
                image.setImageResource(R.drawable.image7);
                counter ++;
                break;
            case 7:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 4);
                increaseRating(currentUser, "Singapore", 4);
                increaseRating(currentUser, "Mexico", 4);
                // move to the next
                image.setImageResource(R.drawable.image8);
                counter ++;
                break;
            case 8:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 4);
                increaseRating(currentUser, "Turkey", 4);
                // move to the next
                image.setImageResource(R.drawable.image9);
                counter ++;
                break;
            case 9:
                // increase what should be increased
                increaseRating(currentUser, "Australia", 4);
                increaseRating(currentUser, "USA", 4);
                // move to the next
                image.setImageResource(R.drawable.image10);
                counter ++;
                break;
            case 10:
                // increase what should be increased
                increaseRating(currentUser, "Singapore", 4);
                increaseRating(currentUser, "USA", 4);
                increaseRating(currentUser, "Hong Kong", 4);
                // move to the results
                Intent intent = new Intent(MainActivity.this, FinalActivity.class);
                startActivity(intent);
                finish();
                counter = 1;
                break;
            default:
                System.out.println("no way!");
                break;
        }
    }

    public void onPossible(View view) {
        ImageView image = findViewById(R.id.imageView);
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        switch(counter) {
            case 1:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 3);
                increaseRating(currentUser, "Singapore", 3);
                increaseRating(currentUser, "Hong Kong", 3);
                // move to the next
                image.setImageResource(R.drawable.image2);
                counter ++;
                break;
            case 2:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 3);
                increaseRating(currentUser, "Egypt", 3);
                // move to the next
                image.setImageResource(R.drawable.image3);
                counter ++;
                break;
            case 3:
                // increase what should be increased
                increaseRating(currentUser, "France", 3);
                // move to the next
                image.setImageResource(R.drawable.image4);
                counter ++;
                break;
            case 4:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 3);
                increaseRating(currentUser, "Singapore", 3);
                increaseRating(currentUser, "Hong Kong", 3);
                // move to the next
                image.setImageResource(R.drawable.image5);
                counter ++;
                break;
            case 5:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 3);
                increaseRating(currentUser, "Spain", 3);
                // move to the next
                image.setImageResource(R.drawable.image6);
                counter ++;
                break;
            case 6:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 3);
                increaseRating(currentUser, "Spain", 3);
                // move to the next
                image.setImageResource(R.drawable.image7);
                counter ++;
                break;
            case 7:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 3);
                increaseRating(currentUser, "Singapore", 3);
                increaseRating(currentUser, "Mexico", 3);
                // move to the next
                image.setImageResource(R.drawable.image8);
                counter ++;
                break;
            case 8:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 3);
                increaseRating(currentUser, "Turkey", 3);
                // move to the next
                image.setImageResource(R.drawable.image9);
                counter ++;
                break;
            case 9:
                // increase what should be increased
                increaseRating(currentUser, "Australia", 3);
                increaseRating(currentUser, "USA", 3);
                // move to the next
                image.setImageResource(R.drawable.image10);
                counter ++;
                break;
            case 10:
                // increase what should be increased
                increaseRating(currentUser, "Singapore", 3);
                increaseRating(currentUser, "USA", 3);
                increaseRating(currentUser, "Hong Kong", 3);
                // move to the results
                Intent intent = new Intent(MainActivity.this, FinalActivity.class);
                startActivity(intent);
                finish();
                counter = 1;
                break;
            default:
                System.out.println("no way!");
                break;
        }
    }

    public void onMaybe(View view) {
        ImageView image = findViewById(R.id.imageView);
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        switch(counter) {
            case 1:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 2);
                increaseRating(currentUser, "Singapore", 2);
                increaseRating(currentUser, "Hong Kong", 2);

                // move to the next
                image.setImageResource(R.drawable.image2);
                counter ++;
                break;
            case 2:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 2);
                increaseRating(currentUser, "Egypt", 2);
                // move to the next
                image.setImageResource(R.drawable.image3);
                counter ++;
                break;
            case 3:
                // increase what should be increased
                increaseRating(currentUser, "France", 2);
                // move to the next
                image.setImageResource(R.drawable.image4);
                counter ++;
                break;
            case 4:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 2);
                increaseRating(currentUser, "Singapore", 2);
                increaseRating(currentUser, "Hong Kong", 2);
                // move to the next
                image.setImageResource(R.drawable.image5);
                counter ++;
                break;
            case 5:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 2);
                increaseRating(currentUser, "Spain", 2);
                // move to the next
                image.setImageResource(R.drawable.image6);
                counter ++;
                break;
            case 6:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 2);
                increaseRating(currentUser, "Spain", 2);
                // move to the next
                image.setImageResource(R.drawable.image7);
                counter ++;
                break;
            case 7:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 2);
                increaseRating(currentUser, "Singapore", 2);
                increaseRating(currentUser, "Mexico", 2);
                // move to the next
                image.setImageResource(R.drawable.image8);
                counter ++;
                break;
            case 8:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 2);
                increaseRating(currentUser, "Turkey", 2);
                // move to the next
                image.setImageResource(R.drawable.image9);
                counter ++;
                break;
            case 9:
                // increase what should be increased
                increaseRating(currentUser, "Australia", 2);
                increaseRating(currentUser, "USA", 2);
                // move to the next
                image.setImageResource(R.drawable.image10);
                counter ++;
                break;
            case 10:
                // increase what should be increased
                increaseRating(currentUser, "Singapore", 2);
                increaseRating(currentUser, "USA", 2);
                increaseRating(currentUser, "Hong Kong", 2);
                // move to the results
                Intent intent = new Intent(MainActivity.this, FinalActivity.class);
                startActivity(intent);
                finish();
                counter = 1;
                break;
            default:
                System.out.println("no way!");
                break;
        }
    }

    public void onNo (View view) {
        ImageView image = findViewById(R.id.imageView);
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        switch(counter) {
            case 1:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 1);
                increaseRating(currentUser, "Singapore", 1);
                increaseRating(currentUser, "Hong Kong", 1);
                // move to the next
                image.setImageResource(R.drawable.image2);
                counter ++;
                break;
            case 2:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 1);
                increaseRating(currentUser, "Egypt", 1);
                // move to the next
                image.setImageResource(R.drawable.image3);
                counter ++;
                break;
            case 3:
                // increase what should be increased
                increaseRating(currentUser, "France", 1);
                // move to the next
                image.setImageResource(R.drawable.image4);
                counter ++;
                break;
            case 4:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 1);
                increaseRating(currentUser, "Singapore", 1);
                increaseRating(currentUser, "Hong Kong", 1);
                // move to the next
                image.setImageResource(R.drawable.image5);
                counter ++;
                break;
            case 5:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 1);
                increaseRating(currentUser, "Spain", 1);
                // move to the next
                image.setImageResource(R.drawable.image6);
                counter ++;
                break;
            case 6:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 1);
                increaseRating(currentUser, "Spain", 1);
                // move to the next
                image.setImageResource(R.drawable.image7);
                counter ++;
                break;
            case 7:
                // increase what should be increased
                increaseRating(currentUser, "Thailand", 1);
                increaseRating(currentUser, "Singapore", 1);
                increaseRating(currentUser, "Mexico", 1);
                // move to the next
                image.setImageResource(R.drawable.image8);
                counter ++;
                break;
            case 8:
                // increase what should be increased
                increaseRating(currentUser, "Mexico", 1);
                increaseRating(currentUser, "Turkey", 1);
                // move to the next
                image.setImageResource(R.drawable.image9);
                counter ++;
                break;
            case 9:
                // increase what should be increased
                increaseRating(currentUser, "Australia", 1);
                increaseRating(currentUser, "USA", 1);
                // move to the next
                image.setImageResource(R.drawable.image10);
                counter ++;
                break;
            case 10:
                // increase what should be increased
                increaseRating(currentUser, "Singapore", 1);
                increaseRating(currentUser, "USA", 1);
                increaseRating(currentUser, "Hong Kong", 1);
                // move to the results
                Intent intent = new Intent(MainActivity.this, FinalActivity.class);
                startActivity(intent);
                finish();
                counter = 1;
                break;
            default:
                System.out.println("no way!");
                break;
        }
    }

    void increaseRating(String user, String country, int increase) {
        int oldRating = 0;
        SQLiteDatabase readDb = new DatabaseHelper(getApplicationContext()).getReadableDatabase();
        Cursor queryCursor = readDb.rawQuery("SELECT rating FROM auswertung_table WHERE user = ? AND country = ?", new String[]{user, country});
        if(queryCursor != null){
            queryCursor.moveToFirst();
            oldRating = queryCursor.getInt(queryCursor.getColumnIndex("rating"));
            queryCursor.close();
        }
        SQLiteDatabase writeDb = new DatabaseHelper(getApplicationContext()).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int newRating = oldRating + increase;
        contentValues.put("rating", newRating);
        writeDb.update("auswertung_table", contentValues,"user=? and country=?", new String[]{user, country});


    }

    public void resetRating(String uid) {
        SQLiteDatabase writeDb = new DatabaseHelper(getApplicationContext()).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rating", 0);
        writeDb.update("auswertung_table", contentValues,"user=?", new String[]{uid});
    }

    public void setUid(String uid) {
        SQLiteDatabase writeDb = new DatabaseHelper(getApplicationContext()).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", uid);
        writeDb.update("auswertung_table", contentValues,"user=?", new String[]{"12345"});
    }
}
