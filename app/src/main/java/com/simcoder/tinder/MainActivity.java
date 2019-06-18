package com.simcoder.tinder;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.simcoder.tinder.Cards.arrayAdapter;
import com.simcoder.tinder.Cards.cards;
import com.simcoder.tinder.Matches.MatchesActivity;
import com.simcoder.tinder.DatabaseHelper;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private cards cards_data[];
    private com.simcoder.tinder.Cards.arrayAdapter arrayAdapter;
    private int i;

    private FirebaseAuth mAuth;

    private String currentUId;
    private Button mYes;

    private DatabaseReference usersDb;
    DatabaseHelper myDb;


    ListView listView;
    List<cards> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        usersDb = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();
        currentUId = mAuth.getCurrentUser().getUid();
        checkUserSex();

        rowItems = new ArrayList<cards>();
        createCards();
        arrayAdapter = new arrayAdapter(this, R.layout.item, rowItems );
        int question = 1;

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {

                cards obj = (cards) dataObject;
                String userId = obj.getUserId();
                usersDb.child(userId).child("connections").child("nope").child(currentUId).setValue(true);
                Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                cards obj = (cards) dataObject;
                String userId = obj.getUserId();
                usersDb.child(userId).child("connections").child("yeps").child(currentUId).setValue(true);
                isConnectionMatch(userId);
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }

            Object dataObject

            public final void onButtonYes (Object dataObject){
                cards obj = (cards) dataObject;
                String userId = obj.getUserId();
                usersDb.child(userId).child("connections").child("yeps").child(currentUId).setValue(true);
                isConnectionMatch(userId);
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }


        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "Item Clicked", Toast.LENGTH_SHORT).show();
            }

        });

        public final void onButtonYes (Object dataObject){
            cards obj = (cards) dataObject;
            String userId = obj.getUserId();
            usersDb.child(userId).child("connections").child("yeps").child(currentUId).setValue(true);
            isConnectionMatch(userId);
            Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
        }


    }

    private void createCards() {

        rowItems.add(new cards("001", "image 1", Uri.parse("android.resource://com.simcoder.tinder/" + R.drawable.image1).toString()));
        rowItems.add(new cards("002", "image 2", Uri.parse("android.resource://com.simcoder.tinder/" + R.drawable.image2).toString()));
        rowItems.add(new cards("003", "image 3", Uri.parse("android.resource://com.simcoder.tinder/" + R.drawable.image3).toString()));
        rowItems.add(new cards("004", "image 4", Uri.parse("android.resource://com.simcoder.tinder/" + R.drawable.image4).toString()));
        rowItems.add(new cards("005", "image 5", Uri.parse("android.resource://com.simcoder.tinder/" + R.drawable.image5).toString()));
        rowItems.add(new cards("006", "image 6", Uri.parse("android.resource://com.simcoder.tinder/" + R.drawable.image6).toString()));
        rowItems.add(new cards("007", "image 7", Uri.parse("android.resource://com.simcoder.tinder/" + R.drawable.image7).toString()));
        rowItems.add(new cards("008", "image 8", Uri.parse("android.resource://com.simcoder.tinder/" + R.drawable.image8).toString()));
        rowItems.add(new cards("009", "image 9", Uri.parse("android.resource://com.simcoder.tinder/" + R.drawable.image9).toString()));
        rowItems.add(new cards("010", "image 10", Uri.parse("android.resource://com.simcoder.tinder/" + R.drawable.image10).toString()));

    }

    public void onNoButton(Object dataObject){
        cards obj = (cards) dataObject;
        String userId = obj.getUserId();
        List<String> countrieList = obj.countries;
        //coutryDbHelper.increaseRating(usersDb, countriesList, 1);
        // oder eine schleife für jeden Country was für ein "Bild" hinterlegt ist
    }

    public void onYesButton(View view){
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        flingContainer.getTopCardListener().selectRight();
        // Log.i("Bensalim", question);
        // List<String> countrieList = obj.countries;
        //coutryDbHelper.increaseRating(usersDb, countriesList, 4);
        // oder eine schleife für jeden Country was für ein "Bild" hinterlegt ist
    }

    public void onMaybeButton(Object dataObject){
        cards obj = (cards) dataObject;
        String userId = obj.getUserId();
        List<String> countrieList = obj.countries;
        //coutryDbHelper.increaseRating(usersDb, countriesList, 2);
        // oder eine schleife für jeden Country was für ein "Bild" hinterlegt ist
    }

    public void onPossibleButton(Object dataObject){
        cards obj = (cards) dataObject;
        String userId = obj.getUserId();
        List<String> countrieList = obj.countries;
        //coutryDbHelper.increaseRating(usersDb, countriesList, 3);
        // oder eine schleife für jeden Country was für ein "Bild" hinterlegt ist
    }

    private void isConnectionMatch(String userId) {
        DatabaseReference currentUserConnectionsDb = usersDb.child(currentUId).child("connections").child("yeps").child(userId);
        currentUserConnectionsDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(MainActivity.this, "new Connection", Toast.LENGTH_LONG).show();

                    String key = FirebaseDatabase.getInstance().getReference().child("Chat").push().getKey();

                    usersDb.child(dataSnapshot.getKey()).child("connections").child("matches").child(currentUId).child("ChatId").setValue(key);
                    usersDb.child(currentUId).child("connections").child("matches").child(dataSnapshot.getKey()).child("ChatId").setValue(key);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private String userSex;
    private String oppositeUserSex;
    public void checkUserSex(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference userDb = usersDb.child(user.getUid());
        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    if (dataSnapshot.child("sex").getValue() != null){
                        userSex = dataSnapshot.child("sex").getValue().toString();
                        switch (userSex){
                            case "Male":
                                oppositeUserSex = "Female";
                                break;
                            case "Female":
                                oppositeUserSex = "Male";
                                break;
                        }
                        getOppositeSexUsers();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getOppositeSexUsers(){
        usersDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.child("sex").getValue() != null) {
                    if (dataSnapshot.exists() && !dataSnapshot.child("connections").child("nope").hasChild(currentUId) && !dataSnapshot.child("connections").child("yeps").hasChild(currentUId) && dataSnapshot.child("sex").getValue().toString().equals(oppositeUserSex)) {
                        String profileImageUrl = "default";
                        if (!dataSnapshot.child("profileImageUrl").getValue().equals("default")) {
                            profileImageUrl = dataSnapshot.child("profileImageUrl").getValue().toString();
                        }
                        cards item = new cards(dataSnapshot.getKey(), dataSnapshot.child("name").getValue().toString(), profileImageUrl);
                        rowItems.add(item);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    public void logoutUser(View view) {
        mAuth.signOut();
        Intent intent = new Intent(MainActivity.this, ChooseLoginRegistrationActivity.class);
        startActivity(intent);
        finish();
        return;
    }

    public void goToSettings(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
        return;
    }

    public void goToFinal(View view) {
        Intent intent = new Intent(MainActivity.this, FinalActivity.class);
        startActivity(intent);
        return;
    }

    public void goToMatches(View view) {
        Intent intent = new Intent(MainActivity.this, MatchesActivity.class);
        startActivity(intent);
        return;
    }
}