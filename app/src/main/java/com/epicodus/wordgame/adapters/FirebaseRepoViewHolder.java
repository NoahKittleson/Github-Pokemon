package com.epicodus.wordgame.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.wordgame.Constants;
import com.epicodus.wordgame.R;
import com.epicodus.wordgame.models.Repo;
import com.epicodus.wordgame.ui.RepoDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by noahkittleson on 7/16/16.
 */
public class FirebaseRepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_HEIGHT = 200;
    private static final int MAX_WIDTH = 200;

    View mView;
    Context mContext;

    public FirebaseRepoViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindRepo(Repo repo) {
        ImageView repoImageView = (ImageView) mView.findViewById(R.id.repoImageView);
        TextView languageTextView = (TextView) mView.findViewById(R.id.languageTextView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.repoNameTextView);
        TextView sizeTextView = (TextView) mView.findViewById(R.id.sizeTextView);

        //Picasso.with(mContext).load(repo.getImageUrl()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(repoImageView);
        nameTextView.setText(repo.getName());
        languageTextView.setText(repo.getLanguageOne());
        sizeTextView.setText("Size: " + repo.getSize() + "Kb");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Repo> restaurants = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_REPOS)
                .child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    restaurants.add(snapshot.getValue(Repo.class));
                }
                Log.d("RestaurantViewHolder", restaurants.size() + "");
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, RepoDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("restaurants", Parcels.wrap(restaurants));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
