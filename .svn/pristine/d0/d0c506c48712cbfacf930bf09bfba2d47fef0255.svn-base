package com.example.android.flashcard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tianyus2 on 4/20/2017.
 */

public class CardBrowseAdapter extends RecyclerView.Adapter<CardBrowseAdapter.ViewHolder> {

    private ArrayList<Flashcard> cards;

    public CardBrowseAdapter(ArrayList<Flashcard> cards) {
        this.cards = cards;
    }

    @Override
    public CardBrowseAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_list_item, viewGroup, false);
        return new CardBrowseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardBrowseAdapter.ViewHolder holder, int position) {
        final Flashcard card = cards.get(position);

        holder.termView.setText(card.getTerm());
        holder.definitionView.setText(card.getDefinition());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView termView;
        private TextView definitionView;

        private ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            termView = (TextView) itemView.findViewById(R.id.termTextView);
            definitionView = (TextView) itemView.findViewById(R.id.definitionTextView);
        }
    }

}
