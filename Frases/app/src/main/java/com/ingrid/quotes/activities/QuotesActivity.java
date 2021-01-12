package com.ingrid.quotes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ingrid.quotes.R;
import com.ingrid.quotes.adapters.QuotesAdapter;
import com.ingrid.quotes.model.Author;
import com.ingrid.quotes.model.Quote;
import com.ingrid.quotes.model.QuoteWithAuthor;
import com.ingrid.quotes.viewmodels.QuotesViewModel;
import com.ingrid.quotes.viewmodels.QuotesViewModelProvider;

import java.util.List;

public class QuotesActivity extends AppCompatActivity {

    private QuotesViewModel viewModel;
    private Spinner spAuthors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        viewModel = new ViewModelProvider(this, new QuotesViewModelProvider(this)).get(QuotesViewModel.class);

        initAuthorsList();
        initQuotesList();
        initRegistry();
    }

    private void initAuthorsList() {
        spAuthors = findViewById(R.id.spAuthors);
        ArrayAdapter<Author> authorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spAuthors.setAdapter(authorAdapter);

         viewModel.authorsLiveData.observe(this, new Observer<List<Author>>() {
             @Override
             public void onChanged(List<Author> authors) {
                 authorAdapter.addAll(authors);
             }
         });
    }

    private void initQuotesList() {
        RecyclerView rvQuotes = findViewById(R.id.rvQuotes);
        QuotesAdapter quotesAdapter = new QuotesAdapter(this);
        rvQuotes.setAdapter(quotesAdapter);

        rvQuotes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        viewModel.quotesLiveData.observe(this, new Observer<List<QuoteWithAuthor>>() {
            @Override
            public void onChanged(List<QuoteWithAuthor> quotes) {
                quotesAdapter.updateQuotes(quotes);
            }
        });
    }

    private void initRegistry() {
        EditText etQuotes = findViewById(R.id.etQuotes);
        Button btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote = etQuotes.getText().toString();
                etQuotes.setText("");
                Author selectedAuthor = (Author) spAuthors.getSelectedItem();
                viewModel.addQuote(quote, selectedAuthor);
            }
        });
    }
}