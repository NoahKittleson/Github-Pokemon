package com.epicodus.wordgame.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.wordgame.R;
import com.epicodus.wordgame.adapters.RepoPagerAdapter;
import com.epicodus.wordgame.models.Repo;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepoDetailActivity extends AppCompatActivity {
    private RepoPagerAdapter adapterViewPager;
    ArrayList<Repo> mRepos = new ArrayList<>();
    @Bind(R.id.viewPager) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);
        ButterKnife.bind(this);

        mRepos = Parcels.unwrap(getIntent().getParcelableExtra("repos"));
        Log.d("RepoDetailActivity", mRepos.size() + "");
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new RepoPagerAdapter(getSupportFragmentManager(), mRepos);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
