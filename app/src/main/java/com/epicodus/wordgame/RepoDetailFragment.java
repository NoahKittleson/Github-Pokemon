package com.epicodus.wordgame;


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
    //@Bind(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;

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

        //Picasso.with(view.getContext()).load(mRepo.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mRepo.getName());
        mLanguageLabel.setText(mRepo.getLanguageOne());
        mSizeLabel.setText(Double.toString(mRepo.getSize()));
        mUrlLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mUrlLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRepo.getUrlAddress()));
            startActivity(webIntent);
        }
    }
}
