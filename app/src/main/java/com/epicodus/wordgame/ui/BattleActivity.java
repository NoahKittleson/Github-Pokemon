package com.epicodus.wordgame.ui;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.wordgame.Constants;
import com.epicodus.wordgame.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BattleActivity extends AppCompatActivity {
    DatabaseReference mPlayerTeamRef;
    DatabaseReference mOpponentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Intent intent = getIntent();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mPlayerTeamRef = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_REPOS)
                .child(uid);

        String OpponentID = intent.getStringExtra("OpponentID");
        mOpponentRef = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_REPOS)
                .child(OpponentID);
    }
}
