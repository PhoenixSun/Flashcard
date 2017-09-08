package com.example.android.flashcard;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDirectoryReference = mDatabase.getReference("directory");
    private DatabaseReference mFolderReference = mDatabase.getReference("card_lists/testing");

    private ArrayList<String> mFolders = new ArrayList<>();
    private ArrayList<String> mFolderKeys = new ArrayList<>();
    private ArrayList<Flashcard> mCards = new ArrayList<>();
    private ArrayList<String> mCardKeys = new ArrayList<>();
    private CountDownLatch countDownLatch= new CountDownLatch(1);

    @Before
    public void setUp() {

        mDirectoryReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mFolderKeys.add(dataSnapshot.getKey());
                mFolders.add(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                int index = mFolderKeys.indexOf(dataSnapshot.getKey());
                mFolders.set(index, dataSnapshot.getValue(String.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int index = mFolderKeys.indexOf(dataSnapshot.getKey());
                mFolderKeys.remove(index);
                mFolders.remove(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mFolderReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mCardKeys.add(dataSnapshot.getKey());
                mCards.add(dataSnapshot.getValue(Flashcard.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Flashcard value = dataSnapshot.getValue(Flashcard.class);
                String key = dataSnapshot.getKey();

                int index = mCardKeys.indexOf(key);
                mCards.set(index, value);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = mCardKeys.indexOf(key);

                mCardKeys.remove(index);
                mCards.remove(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Test
    public void folderOperation() throws Exception {

        countDownLatch.await(5, TimeUnit.SECONDS);

        // add a new folder of flashcards
        int numFoldersBeforeAdd = mFolders.size();
        mDirectoryReference.push().setValue("Just for testing");

        countDownLatch.await(5, TimeUnit.SECONDS);
        assertEquals(numFoldersBeforeAdd + 1, mFolders.size());
        assertEquals("Just for testing", mFolders.get(numFoldersBeforeAdd));

        // change the name of a folder
        int targetIndex = mFolders.size() - 1;
        mDirectoryReference.child(mFolderKeys.get(targetIndex)).setValue("Testing folder");

        countDownLatch.await(5, TimeUnit.SECONDS);
        assertEquals("Testing folder", mFolders.get(targetIndex));

        // remove a folder
        int numFoldersBeforeDelete = mFolders.size();
        String keyRemove = mFolderKeys.get(numFoldersBeforeDelete - 1);
        mDirectoryReference.child(keyRemove).setValue(null);
        mFolderReference.child("card_lists/" + keyRemove).setValue(null);

        countDownLatch.await(5, TimeUnit.SECONDS);
        assertEquals(numFoldersBeforeDelete - 1, mFolders.size());
    }

    @Test
    public void cardOperation() throws Exception {

        // add a flashcard into the list
        int numCardsBeforeAdd = mCards.size();
        Flashcard card = new Flashcard("term3", "definition3");
        mFolderReference.push().setValue(card);

        countDownLatch.await(5, TimeUnit.SECONDS);
        assertEquals(numCardsBeforeAdd + 1, mCards.size());
        assertEquals(card, mCards.get(numCardsBeforeAdd));

        // change the content of a card
        int targetIndex = mCards.size() - 1;
        Flashcard anotherCard = new Flashcard("new term", " new definition");
        mFolderReference.child(mCardKeys.get(targetIndex)).setValue(anotherCard);

        countDownLatch.await(5, TimeUnit.SECONDS);
        assertEquals(anotherCard, mCards.get(targetIndex));

        // remove a card from list
        int numCardsBeforeDelete = mCards.size();
        mFolderReference.child(mCardKeys.get(numCardsBeforeDelete - 1)).setValue(null);

        countDownLatch.await(5, TimeUnit.SECONDS);
        assertEquals(numCardsBeforeDelete - 1, mCards.size());
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.android.flashcard", appContext.getPackageName());
    }

}
