package com.ingrid.quotes.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
        getSupportActionBar().setTitle(R.string.authors);
        viewModel = new ViewModelProvider(this, new QuotesViewModelProvider(this)).get(AuthorsViewModel.class);

        initList();
        initRegistry();
    }

    private void initList() {
        RecyclerView rvAuthors = findViewById(R.id.rvAuthors);
        AuthorsAdapter adapter = new AuthorsAdapter(this, viewModel);
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
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> {
            showRegistryDialog();
        });
    }

    private void showRegistryDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.dialog_add_author, null);
        EditText etName = view.findViewById(R.id.etName);

        dialogBuilder
                .setTitle(R.string.add_author_title)
                .setView(view)
                .setPositiveButton(R.string.bt_add, (dialog, id) -> {
                    String authorName = etName.getText().toString();
                    viewModel.addAuthor(authorName);
                })
                .setNegativeButton(R.string.bt_cancel, (dialog, id) -> {

                });
        dialogBuilder.create().show();
    }
}