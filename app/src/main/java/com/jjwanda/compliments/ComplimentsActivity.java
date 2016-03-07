package com.jjwanda.compliments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jjwanda.complimentscommon.Compliments;


public class ComplimentsActivity extends AppCompatActivity {

    private static final String complimentTag = "COMPLIMENT_TAG";
    private static final String colorTag = "COLOR_TAG";
    private ShareActionProvider mShareActionProvider; // For sharing compliments

    private String compliment = "";
    private String currentColor;

    private Compliments complimentClass; // This is how we get and generate compliments.

    private TextView complimentTextView;
    private RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        complimentClass = new Compliments(this);
        currentColor = complimentClass.randomizeColor();

        setContentView(R.layout.activity_compliments);
        setupUI();
        /**
         * This is just in case there is a null instance at this moment
         */
        if(savedInstanceState != null)
        {
            compliment = savedInstanceState.getString(complimentTag);
            currentColor = savedInstanceState.getString(colorTag);
            changeCompliment();
        }
        // If there is no compliment then we go ahead and randomize and change the compliment
        if (compliment.equals(""))
        {
            randomizeAndChange();
        }
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Literally the only thing we can do is change the compliments
                randomizeAndChange();
            }
        });
    }

    // This grabs all the ids for the screen
    private void setupUI()
    {
        complimentTextView = (TextView) findViewById(R.id.compliment_textview);
        background = (RelativeLayout) findViewById(R.id.compliments_relativeView);
    }

    /**
     * A shortcut for randomize the compliments and change the compliments.
     */
    private void randomizeAndChange() {
        randomizeAll();
        changeCompliment();
    }

    /**
     * A shortcut for changing the compliments and color.
     */
    private void randomizeAll()
    {
        compliment = complimentClass.randomizeString();
        currentColor = complimentClass.randomizeColor();
    }

    /**
     * A changeCompliment()  will change the color and background and help with the share intent
     */
    private void changeCompliment()
    {
        complimentTextView.setText(compliment);
        background.setBackgroundColor(Color.parseColor(currentColor));

        tintAppBar(currentColor);

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, compliment);
        shareIntent.setType("text/plain");
        setShareIntent(shareIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compliments, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, compliment);
        shareIntent.setType("text/plain");
        setShareIntent(shareIntent);

        return true;
    }

    private void setShareIntent(Intent shareIntent)
    {
        if (mShareActionProvider != null)
        {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(complimentTag, compliment);
        outState.putString(colorTag, currentColor);
    }

    /**
     * So originally this was going to be a transparent status bar but instead it was switched to
     * making the color ourselves just for easiness sake.
     * @param unparsedColor This is supposed to be in #RRGGBB for me to change it.
     */
    private void tintAppBar(String unparsedColor) {
        float[] hsv = new float[3];
        int color = Color.parseColor(unparsedColor);
        Color.colorToHSV(color, hsv);
        hsv[2] = 0.1f + 0.8f * hsv[2];
        color = Color.HSVToColor(hsv);
        ColorDrawable colorDrawable = new ColorDrawable(color);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(colorDrawable);
        }
    }
}
