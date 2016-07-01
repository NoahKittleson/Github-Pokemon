package com.epicodus.wordgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.letterGrid) GridView mLetterGrid;
    @Bind(R.id.submitButton) Button mSubmitButton;
    @Bind(R.id.editText) TextView mTextView;
    @Bind(R.id.scoreButton) Button mScoreButton;
    private Character[] letters = new Character[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
    private ArrayList<String> submittedWords = new ArrayList<String> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        mSubmitButton.setOnClickListener(this);
        mScoreButton.setOnClickListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, letters);
        mLetterGrid.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v == mSubmitButton) {
            String word = mTextView.getText().toString();
            mTextView.setText("");
            submittedWords.add(word);
        }
        else if (v == mScoreButton) {
            Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
            intent.putExtra("submittedWords", submittedWords);
            startActivity(intent);
        }
    }
}
