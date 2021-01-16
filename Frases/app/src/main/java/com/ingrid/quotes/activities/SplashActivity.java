package com.ingrid.quotes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.ingrid.quotes.R;
import com.ingrid.quotes.model.QuoteWithAuthor;
import com.ingrid.quotes.repository.QuotesRepository;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final long DELAY_TIME = 3000;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, DELAY_TIME);

        updateQuote();
    }

    private void updateQuote() {
        new Thread() {
            @Override
            public void run() {
                QuotesRepository quotesRepository = new QuotesRepository(SplashActivity.this);
                List<QuoteWithAuthor> quotesWithAuthors = quotesRepository.allQuotes();
                int maxIndex = quotesWithAuthors.size();

                if (maxIndex != 0) {
                    int index = getNextIndex();
                    index = index % maxIndex;
                    QuoteWithAuthor quoteWithAuthor = quotesWithAuthors.get(index);

                    String quoteText = quoteWithAuthor.quote.getQuote();
                    String authorText = quoteWithAuthor.author.getName();

                    TextView tvQuote = findViewById(R.id.tvQuoteSplash);
                    TextView tvAuthor = findViewById(R.id.tvAuthorSplash);

                    tvQuote.setText(quoteText);
                    tvAuthor.setText(authorText);
                }
            }
        }.start();
    }

    private int getNextIndex() {
        SharedPreferences preferences = getSharedPreferences("app", Context.MODE_PRIVATE);
        int nextQuote = preferences.getInt("nextQuote", 0);

        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt("nextQuote", nextQuote + 1);
        edit.apply();

        return nextQuote;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}