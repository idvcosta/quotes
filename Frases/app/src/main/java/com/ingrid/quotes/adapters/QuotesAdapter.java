package com.ingrid.quotes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.quotes.R;
import com.ingrid.quotes.model.Quote;
import com.ingrid.quotes.model.QuoteWithAuthor;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuotesHolder> {

    public class QuotesHolder extends RecyclerView.ViewHolder {

        TextView tvQuote;
        TextView tvAuthorName;

        public QuotesHolder(@NonNull View itemView) {
            super(itemView);
            tvQuote = itemView.findViewById(R.id.tvQuote);
            tvAuthorName = itemView.findViewById(R.id.tvAuthorName);
        }

    }

    public List<QuoteWithAuthor> quotes;

    @NonNull
    @Override
    public QuotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quote, parent, false);
        return new QuotesHolder(view);
    }

    public void updateQuotes(List<QuoteWithAuthor> quotes) {
        this.quotes = quotes;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesHolder holder, int position) {
        QuoteWithAuthor quoteWithAuthor = quotes.get(position);
        String quoteText = quoteWithAuthor.quote.getQuote();
        String authorName = quoteWithAuthor.author.getName();

        holder.tvQuote.setText(quoteText);
        holder.tvAuthorName.setText(authorName);

    }

    @Override
    public int getItemCount() {
        return quotes == null ? 0 : quotes.size();
    }
}
