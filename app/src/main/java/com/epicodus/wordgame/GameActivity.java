package com.epicodus.wordgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {
    @Bind (R.id.letterGrid) GridView mLetterGrid;
    private Character[] letters = new Character[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, letters);
        mLetterGrid.setAdapter(adapter);
    }
}
