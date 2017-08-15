package com.bitland.golfscorecardreloaded;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "com.bitland.golfscorecardreloaded";
    private static final String KEY_STROKE_COUNT = "key_stroke_count";
    private SharedPreferences  mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private HoleAdapter mAdapter;

    private Hole[] mHoles = new Hole[18];

    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Initializing the SharedPreferences object and SharedPreferences editor object.*
         * These help with saving values of views when an activity is destroyed.
         * The values are the re-used when the activity is re-created.
         */

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        ButterKnife.bind(this);



        //initialize holes
        int strokes = 0;
        for(int i = 0; i < mHoles.length; i++){
            //Setting strokes to the previously saved values before the activity was destroyed
            strokes = mSharedPreferences.getInt(KEY_STROKE_COUNT + i, 0);
            mHoles[i] = new Hole("Hole: " + (i+1),strokes );
        }

        //Set adapter the adapter which was declared as a member variable
        mAdapter = new HoleAdapter(mHoles);
        mRecyclerView.setAdapter(mAdapter);

        /**Define and set a layout manager for the recycler view.*
         * A layout manager determines when a list item is no longer visible and can be reused.
         * The Linear layout manager defined below shows items on a vertical or horizontal scroll
         * lists.
         */

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);


        /**When dealing with data of fixed size, for example, an array,
         * adding the line of code below helps with performance.*/
        mRecyclerView.setHasFixedSize(true);


    }

    @Override
    protected void onPause() {
        super.onPause();

        //Saving the stroke count value for each hole
        for (int i = 0; i < mHoles.length; i ++){
            mEditor.putInt(KEY_STROKE_COUNT + i, mHoles[i].getStrokeCount());
        }

        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_strokes) {
            //clear values stored in SharedPreferences object and apply changes.
            mEditor.clear();
            mEditor.apply();

            //Loop through each hole object and set the stroke count to 0
            for (Hole hole : mHoles){
                hole.setStrokeCount(0);
            }

            mAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
