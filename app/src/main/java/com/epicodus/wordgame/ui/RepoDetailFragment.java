package com.epicodus.wordgame.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.wordgame.R;
import com.epicodus.wordgame.models.Repo;
import com.epicodus.wordgame.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RepoDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.repoImageView) ImageView mImageLabel;
    @Bind(R.id.repoNameTextView) TextView mNameLabel;
    @Bind(R.id.languageTextView) TextView mLanguageLabel;
    @Bind(R.id.sizeTextView) TextView mSizeLabel;
    @Bind(R.id.urlTextView) TextView mUrlLabel;
    //@Bind(R.id.phoneTextView) TextView mPhoneLabel;
    //@Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveRepoButton) TextView mSaveRepoButton;

    private Repo mRepo;

    public RepoDetailFragment() {

    }

    public static RepoDetailFragment newInstance(Repo repo) {
        RepoDetailFragment repoDetailFragment = new RepoDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("repo", Parcels.wrap(repo));
        repoDetailFragment.setArguments(args);
        return repoDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepo = Parcels.unwrap(getArguments().getParcelable("repo"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_detail, container, false);
        ButterKnife.bind(this, view);

        mNameLabel.setText(mRepo.getName());
        mLanguageLabel.setText(mRepo.getLangPrimary());
        mSizeLabel.setText(mRepo.getSize() + " Kb");
        mUrlLabel.setOnClickListener(this);
        mSaveRepoButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mUrlLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRepo.getUrlAddress()));
            startActivity(webIntent);
        }
        if (v == mSaveRepoButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference repoRef = FirebaseDatabase.getInstance()
                    .getReference(Constants.FIREBASE_CHILD_REPOS)
                    .child(uid);
            DatabaseReference pushRef = repoRef.push();
            String pushId = pushRef.getKey();
            mRepo.setPushId(pushId);
            pushRef.setValue(mRepo);

            Toast.makeText(getContext(), "Added to Team", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), TeamActivity.class);
            startActivity(intent);
        }
    }
}
