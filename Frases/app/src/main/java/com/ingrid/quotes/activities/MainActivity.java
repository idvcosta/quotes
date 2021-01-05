package com.ingrid.quotes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ingrid.quotes.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btAuthors).setOnClickListener(button -> {
            onAuthorsClicked();
        });
        findViewById(R.id.btQuotes).setOnClickListener(button -> {
            onQuotesClicked();
        });
    }

    private void onAuthorsClicked() {
        startActivity(new Intent(this, AuthorsActivity.class));
    }

    private void onQuotesClicked() {
        startActivity(new Intent(this, QuotesActivity.class));
    }
}