package com.ingrid.quotes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ingrid.quotes.R;
import com.ingrid.quotes.adapters.QuotesAdapter;
import com.ingrid.quotes.model.Quote;
import com.ingrid.quotes.viewmodels.QuotesViewModel;
import com.ingrid.quotes.viewmodels.QuotesViewModelProvider;

import java.util.List;

public class QuotesActivity extends AppCompatActivity {

    private QuotesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        viewModel = new ViewModelProvider(this, new QuotesViewModelProvider(this)).get(QuotesViewModel.class);

        initList();
        initRegistry();
    }

    private void initList() {
        RecyclerView rvQuotes = findViewById(R.id.rvQuotes);
        QuotesAdapter quotesAdapter = new QuotesAdapter();
        rvQuotes.setAdapter(quotesAdapter);

        viewModel.quotesLiveData.observe(this, new Observer<List<Quote>>() {
            @Override
            public void onChanged(List<Quote> quotes) {
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
                viewModel.addQuote(quote);
            }
        });
    }
}