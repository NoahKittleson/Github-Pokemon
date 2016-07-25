package com.epicodus.wordgame.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epicodus.wordgame.Constants;
import com.epicodus.wordgame.R;
import com.epicodus.wordgame.adapters.FirebaseRepoViewHolder;
import com.epicodus.wordgame.models.Pokemon;
import com.epicodus.wordgame.models.Repo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TeamActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mRepoReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.button) Button mBattleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mRepoReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_REPOS).child(uid);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Pokemon, FirebaseRepoViewHolder>
                (Pokemon.class, R.layout.team_list_item, FirebaseRepoViewHolder.class,
                        mRepoReference) {

            @Override
            protected void populateViewHolder(FirebaseRepoViewHolder viewHolder,
                                              Pokemon model, int position) {
                Log.d("TEAMACTIVITY", model.getName());
                viewHolder.bindPokemon(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Battle system not made yet.", Toast.LENGTH_SHORT).show();
    }
}
