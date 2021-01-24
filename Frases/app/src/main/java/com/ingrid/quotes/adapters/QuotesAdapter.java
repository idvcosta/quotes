package com.ingrid.quotes.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.quotes.R;
import com.ingrid.quotes.activities.QuotesActivity;
import com.ingrid.quotes.model.Quote;
import com.ingrid.quotes.model.QuoteWithAuthor;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuotesHolder> implements View.OnLongClickListener {

    public class QuotesHolder extends RecyclerView.ViewHolder {

        TextView tvQuote;
        TextView tvAuthorName;

        public QuotesHolder(@NonNull View itemView) {
            super(itemView);
            tvQuote = itemView.findViewById(R.id.tvQuote);
            tvAuthorName = itemView.findViewById(R.id.tvAuthorName);
        }

    }

    private List<QuoteWithAuthor> quotes;
    private int darkBackground;
    private int lightBackground;
    private DeleteListener<Quote> deleteQuoteListener;

    public QuotesAdapter(Context context, DeleteListener<Quote> deleteQuoteListener) {
        darkBackground = context.getColor(R.color.backgroud_row_dark);
        lightBackground = context.getColor(R.color.background_row_light);
        this.deleteQuoteListener = deleteQuoteListener;
    }

    @Override
    public boolean onLongClick(View source) {
        Quote quote = (Quote) source.getTag();
        Context context = source.getContext();
        String confirmationMessage = context.getString(R.string.message_delete, quote.getQuote());

        new AlertDialog.Builder(context)
                .setTitle(R.string.title_confirmation)
                .setMessage(confirmationMessage)
                .setPositiveButton(R.string.bt_confirmation, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteQuoteListener.delete(quote);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.bt_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
        return true;
    }

    @NonNull
    @Override
    public QuotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quote, parent, false);
        view.setOnLongClickListener(this);
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

        int color;
        if(position % 2 == 0){
            color = darkBackground;
        }else{
            color = lightBackground;
        }

        holder.tvQuote.setText(quoteText);
        holder.tvAuthorName.setText(authorName);
        holder.itemView.setBackgroundColor(color);
        holder.itemView.setTag(quoteWithAuthor.quote);
    }

    @Override
    public int getItemCount() {
        return quotes == null ? 0 : quotes.size();
    }
}
