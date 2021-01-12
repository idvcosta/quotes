package com.ingrid.quotes.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.quotes.R;
import com.ingrid.quotes.adapters.AuthorsAdapter;
import com.ingrid.quotes.model.Author;
import com.ingrid.quotes.viewmodels.AuthorsViewModel;
import com.ingrid.quotes.viewmodels.QuotesViewModelProvider;

import java.util.List;

public class AuthorsActivity extends AppCompatActivity {

    private AuthorsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
        viewModel = new ViewModelProvider(this, new QuotesViewModelProvider(this)).get(AuthorsViewModel.class);

        initList();
        initRegistry();
    }

    private void initList() {
        RecyclerView rvAuthors = findViewById(R.id.rvAuthors);
        AuthorsAdapter adapter = new AuthorsAdapter(this);
        rvAuthors.setAdapter(adapter);
        rvAuthors.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        viewModel.authorsLiveData.observe(this, new Observer<List<Author>>() {
            @Override
            public void onChanged(List<Author> authors) {
                adapter.updateAuthors(authors);
            }
        });
    }

    private void initRegistry() {
        EditText etAuthor = findViewById(R.id.etAuthor);
        Button btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String authorName = etAuthor.getText().toString();
                etAuthor.setText("");
                viewModel.addAuthor(authorName);
            }
        });
    }
}