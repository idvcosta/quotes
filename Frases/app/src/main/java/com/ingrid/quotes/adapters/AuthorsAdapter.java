package com.ingrid.quotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.quotes.R;
import com.ingrid.quotes.activities.AuthorsActivity;
import com.ingrid.quotes.model.Author;

import java.util.List;

public class AuthorsAdapter extends RecyclerView.Adapter<AuthorsAdapter.AuthorHolder> {

    public class AuthorHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public AuthorHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
        }
    }

    private List<Author> authors;
    private int darkBackground;
    private int lightBackground;

    public AuthorsAdapter(Context context) {
        darkBackground = context.getColor(R.color.backgroud_row_dark);
        lightBackground = context.getColor(R.color.background_row_light);
    }

    public void updateAuthors(List<Author> authors) {
        this.authors = authors;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AuthorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_author, parent, false);
        return new AuthorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorHolder authorHolder, int position) {
        Author author = authors.get(position);
        String authorName = author.getName();

        int color;
        if(position % 2 ==0){
            color = darkBackground;
        }else{
            color = lightBackground;
        }

        authorHolder.tvName.setText(authorName);
        authorHolder.itemView.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return authors == null ? 0 : authors.size();
    }
}