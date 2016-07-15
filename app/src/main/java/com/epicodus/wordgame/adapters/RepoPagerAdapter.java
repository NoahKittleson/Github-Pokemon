package com.epicodus.wordgame.adapters;

/**
 * Created by noahkittleson on 7/10/16.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.wordgame.models.Repo;
import com.epicodus.wordgame.ui.RepoDetailFragment;

import java.util.ArrayList;

public class RepoPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Repo> mRepos;

    public RepoPagerAdapter(FragmentManager fm, ArrayList<Repo> repos) {
        super(fm);
        mRepos = repos;
    }

    @Override
    public Fragment getItem(int position) {
        return RepoDetailFragment.newInstance(mRepos.get(position));
    }

    @Override
    public int getCount() {
        return mRepos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRepos.get(position).getName();
    }
}
