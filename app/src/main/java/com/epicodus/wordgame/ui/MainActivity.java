package com.epicodus.wordgame.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.wordgame.Constants;
import com.epicodus.wordgame.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.playButton) Button mPlayButton;
    @Bind(R.id.editText) EditText mEditText;
    @Bind(R.id.teamButton) Button mTeamButton;

    SharedPreferences mSharedPreferences;
    private String mRecentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPlayButton.setOnClickListener(this);
        mTeamButton.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentUsername = mSharedPreferences.getString(Constants.PREFERENCES_USERNAME_KEY, null);
        if (mRecentUsername != null) {
            Intent intent = new Intent(MainActivity.this, RepoListActivity.class);
            intent.putExtra(Constants.PREFERENCES_USERNAME_KEY, mRecentUsername);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mPlayButton) {
            String username = mEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, RepoListActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        if (v == mTeamButton) {
            Intent intent = new Intent(MainActivity.this, TeamActivity.class);
            startActivity(intent);
        }
    }
}
