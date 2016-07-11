package com.epicodus.wordgame;


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
public class RepoDetailFragment extends Fragment {
    @Bind(R.id.restaurantImageView) ImageView mImageLabel;
    @Bind(R.id.restaurantNameTextView) TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;

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

        Log.d("RepoDetailFragment", mRepo.getName());
        Log.d("RepoDetailFragment", mRepo.getLanguageOne());
        Log.d("RepoDetailFragment", mRepo.getSize() + "");

        mNameLabel.setText(mRepo.getName());
        mCategoriesLabel.setText(mRepo.getLanguageOne());
        mRatingLabel.setText(Double.toString(mRepo.getSize()));
        //mPhoneLabel.setText(mRepo.getPhone());
        //mAddressLabel.setText(android.text.TextUtils.join(", ", mRepo.getAddress()));

        return view;
    }
}
