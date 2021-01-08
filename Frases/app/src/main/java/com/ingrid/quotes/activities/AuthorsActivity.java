package com.ingrid.quotes.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.quotes.R;
import com.ingrid.quotes.adapters.AuthorsAdapter;
import com.ingrid.quotes.model.Author;
import com.ingrid.quotes.viewmodels.AuthorsViewModel;

import java.util.List;

public class AuthorsActivity extends AppCompatActivity {
    private AuthorsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
        viewModel = new ViewModelProvider(this).get(AuthorsViewModel.class);

        RecyclerView rvAuthors = findViewById(R.id.rvAuthors);
        AuthorsAdapter adapter = new AuthorsAdapter();
        rvAuthors.setAdapter(adapter);

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

        viewModel.authorsLiveData.observe(this, new Observer<List<Author>>() {
            @Override
            public void onChanged(List<Author> authors) {
                adapter.setAuthors(authors);
            }
        });
    }
}