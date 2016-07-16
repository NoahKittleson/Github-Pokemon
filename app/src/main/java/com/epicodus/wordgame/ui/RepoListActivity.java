package com.epicodus.wordgame.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.epicodus.wordgame.Constants;
import com.epicodus.wordgame.R;
import com.epicodus.wordgame.adapters.RepoListAdapter;
import com.epicodus.wordgame.models.Repo;
import com.epicodus.wordgame.services.GithubService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RepoListActivity extends AppCompatActivity /*implements View.OnClickListener*/ {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private RepoListAdapter mAdapter;
    public ArrayList<Repo> mRepos = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentUsername;

    void getRepos (String username) {
        final GithubService githubService = new GithubService();
        githubService.findRepos(username, 1, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mRepos = githubService.processResults(response);
                Log.d("Game Activity: ", mRepos.size() + "");
                for (int i = 0; i < mRepos.size(); i++) {
                    Log.d("Game Activity: ", mRepos.get(i).getName());
                }

                RepoListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Log.d("Game Activity: ", "got to RUN");
                        mAdapter = new RepoListAdapter(getApplicationContext(), mRepos);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RepoListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    private void addToSharedPreferences(String username) {
        mEditor.putString(Constants.PREFERENCES_USERNAME_KEY, username).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        //mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getRepos(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        addToSharedPreferences(username);
        getRepos(username);
    }

//    @Override
//    public void onClick(View v) {
//        if (v == mSubmitButton) {
//            String word = mTextView.getText().toString();
//            mTextView.setText("");
//            submittedWords.add(word);
//        }
//        else if (v == mScoreButton) {
//            Intent intent = new Intent(RepoListActivity.this, ScoreActivity.class);
//            intent.putExtra("submittedWords", submittedWords);
//            startActivity(intent);
//        }
//    }
}
