package com.example.android.flashcard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;

public class CardReviewActivity extends FragmentActivity {

    private ArrayList<Flashcard> cards;

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_review);

        cards = getIntent().getParcelableArrayListExtra("CARDS");

        // Generate a list of fragments to be passed into the adapter
        ArrayList<CardReviewFragment> fragments = new ArrayList<>();
        for (Flashcard card : cards) {
            fragments.add(CardReviewFragment.newInstance(card.getTerm(), card.getDefinition()));
        }

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new CardReviewPagerAdapter(getSupportFragmentManager(), fragments);
        mPager.setAdapter(mPagerAdapter);
    }

}
