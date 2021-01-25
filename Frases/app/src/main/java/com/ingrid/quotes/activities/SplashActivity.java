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
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    private static final long DELAY_TIME = 3000;
    private Handler handler;
    private CompositeDisposable disposables = new CompositeDisposable();

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
        Disposable showQuoteDisposable = Observable.fromCallable(this::chooseQuote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::showQuote)
                .subscribe();
        disposables.add(showQuoteDisposable);
    }

    private void showQuote(QuoteWithAuthor quoteWithAuthor) {
        if (quoteWithAuthor != null) {
            String quoteText = quoteWithAuthor.quote.getQuote();
            String authorText = quoteWithAuthor.author.getName();

            TextView tvQuote = findViewById(R.id.tvQuoteSplash);
            TextView tvAuthor = findViewById(R.id.tvAuthorSplash);

            tvQuote.setText(quoteText);
            tvAuthor.setText(authorText);
        }
    }

    private QuoteWithAuthor chooseQuote() {
        QuotesRepository quotesRepository = new QuotesRepository(SplashActivity.this);
        List<QuoteWithAuthor> quotesWithAuthors = quotesRepository.allQuotes();
        int maxIndex = quotesWithAuthors.size();
        QuoteWithAuthor quoteWithAuthor = null;

        if (maxIndex != 0) {
            int index = getNextIndex();
            index = index % maxIndex;
            quoteWithAuthor = quotesWithAuthors.get(index);
        }
        return quoteWithAuthor;
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
        disposables.dispose();
    }
}