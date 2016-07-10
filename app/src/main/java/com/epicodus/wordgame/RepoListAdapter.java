package com.epicodus.wordgame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by noahkittleson on 7/10/16.
 */
public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoViewHolder> {
    private ArrayList<Repo> mRepos = new ArrayList<>();
    private Context mContext;

    public RepoListAdapter(Context context, ArrayList<Repo> repos) {
        mContext = context;
        mRepos = repos;
    }

    @Override
    public RepoListAdapter.RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item, parent, false);
        RepoViewHolder viewHolder = new RepoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RepoListAdapter.RepoViewHolder holder, int position) {
        holder.bindRepo(mRepos.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.repoImageView) ImageView mImageTextView;
        @Bind(R.id.repoNameTextView) TextView mNameTextView;
        @Bind(R.id.languageTextView) TextView mLanguageTextView;
        @Bind(R.id.sizeTextView) TextView mSizeTextView;

        private Context mContext;

        public RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRepo(Repo repo) {
            mNameTextView.setText(repo.getName());
            mLanguageTextView.setText(repo.getLanguageOne());
            mSizeTextView.setText("Size: " + repo.getSize());
        }
    }
}