package com.epicodus.wordgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.playButton) Button mPlayButton;
    @Bind(R.id.editText) EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPlayButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mPlayButton) {
            String username = mEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("location", username);
            startActivity(intent);
        }
    }
}
