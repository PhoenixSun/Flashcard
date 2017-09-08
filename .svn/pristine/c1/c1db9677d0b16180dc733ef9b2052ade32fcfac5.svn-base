package com.example.android.flashcard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CardReviewFragment extends Fragment {

    private String term;
    private String definition;
    private TextView mTermView;
    private TextView mDefinitionView;
    private boolean showingTerm;

    public static CardReviewFragment newInstance(String term, String definition) {
        CardReviewFragment cardReviewFragment = new CardReviewFragment();
        Bundle args = new Bundle();
        args.putString("term", term);
        args.putString("definition", definition);
        cardReviewFragment.setArguments(args);
        return cardReviewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        term = getArguments().getString("term");
        definition = getArguments().getString("definition");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_card_review, container, false);
        mTermView = (TextView) rootView.findViewById(R.id.term_text_view);
        mDefinitionView = (TextView) rootView.findViewById(R.id.definition_text_view);
        mTermView.setText(term);
        mDefinitionView.setText(definition);
        mDefinitionView.setVisibility(View.GONE);
        showingTerm = true;

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showingTerm) {
                    mTermView.setVisibility(View.GONE);
                    mDefinitionView.setVisibility(View.VISIBLE);
                }
                else {
                    mTermView.setVisibility(View.VISIBLE);
                    mDefinitionView.setVisibility(View.GONE);
                }
                showingTerm = !showingTerm;
            }
        });

        return rootView;
    }
}
