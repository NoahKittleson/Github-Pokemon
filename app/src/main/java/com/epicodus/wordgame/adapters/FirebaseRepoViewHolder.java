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
import com.epicodus.wordgame.models.Pokemon;
import com.epicodus.wordgame.models.Repo;
import com.epicodus.wordgame.ui.RepoDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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

    public void bindPokemon(Pokemon pokemon) {
        ImageView pokemonImageView = (ImageView) mView.findViewById(R.id.pokemonImageView);
        TextView typeTextView = (TextView) mView.findViewById(R.id.typeTextView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.pokemonNameTextView);
        TextView healthTextView = (TextView) mView.findViewById(R.id.hpTextView);

        //Picasso.with(mContext).load(pokemon.getImgURL()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(repoImageView);
        chooseImage(pokemon, pokemonImageView);
        //Picasso.with(mContext).load(R.drawable.java).into(pokemonImageView);
        nameTextView.setText(pokemon.getName());
        typeTextView.setText(pokemon.getTypePrimary());
        healthTextView.setText("Size: " + pokemon.getHP() + "HP");
    }

    private void chooseImage(Pokemon pokemon, ImageView imgView) {
        switch (pokemon.getTypePrimary().toLowerCase()) {
            case "java": Picasso.with(mContext).load(R.drawable.java).into(imgView);
                break;
            case "c++": Picasso.with(mContext).load(R.drawable.cplusplus).into(imgView);
                break;
            case "c#": Picasso.with(mContext).load(R.drawable.csharp).into(imgView);
                break;
            case "ruby": Picasso.with(mContext).load(R.drawable.ruby).into(imgView);
                break;
            case "html": Picasso.with(mContext).load(R.drawable.html).into(imgView);
                break;
            case "sql": Picasso.with(mContext).load(R.drawable.sql).into(imgView);
                break;
            case "php": Picasso.with(mContext).load(R.drawable.php).into(imgView);
                break;
            case "javascript": Picasso.with(mContext).load(R.drawable.javascript).into(imgView);
                break;
            case "python": Picasso.with(mContext).load(R.drawable.python).into(imgView);
                break;
            default: Picasso.with(mContext).load(R.drawable.missingno).into(imgView);
                break;

        }
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Pokemon> pokemen = new ArrayList<>();

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
                    pokemen.add(snapshot.getValue(Pokemon.class));
                }
                Log.d("RepoViewHolder", pokemen.size() + "");
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, RepoDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("repos", Parcels.wrap(pokemen));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
