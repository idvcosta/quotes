package com.ingrid.quotes.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.quotes.R;
import com.ingrid.quotes.model.Author;

import java.util.List;

public class AuthorsAdapter extends RecyclerView.Adapter<AuthorsAdapter.AuthorHolder>
        implements View.OnLongClickListener {

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
    private DeleteListener<Author> deleteAuthorListener;

    public AuthorsAdapter(Context context, DeleteListener<Author> deleteAuthorListener) {
        darkBackground = context.getColor(R.color.backgroud_row_dark);
        lightBackground = context.getColor(R.color.background_row_light);
        this.deleteAuthorListener = deleteAuthorListener;
    }

    public void updateAuthors(List<Author> authors) {
        this.authors = authors;
        notifyDataSetChanged();
    }

    @Override
    public boolean onLongClick(View source) {
        Author author = (Author) source.getTag();
        Context context = source.getContext();
        String confirmationMessage = context.getString(R.string.message_delete, author.getName());

        AlertDialog alertDialog = new AlertDialog
                .Builder(context)
                .setTitle(R.string.title_confirmation)
                .setMessage(confirmationMessage)
                .setPositiveButton(R.string.bt_confirmation, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAuthorListener.delete(author);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.bt_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

        alertDialog.show();

        return true;
    }

    @NonNull
    @Override
    public AuthorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_author, parent, false);
        view.setOnLongClickListener(this);

        return new AuthorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorHolder authorHolder, int position) {
        Author author = authors.get(position);
        String authorName = author.getName();

        int color;
        if (position % 2 == 0) {
            color = darkBackground;
        } else {
            color = lightBackground;
        }

        authorHolder.tvName.setText(authorName);
        authorHolder.itemView.setBackgroundColor(color);
        authorHolder.itemView.setTag(author);
    }

    @Override
    public int getItemCount() {
        return authors == null ? 0 : authors.size();
    }
}