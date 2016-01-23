package com.jjwanda.compliments;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jjwanda.complimentscommon.Compliments;


public class ComplimentsActivity extends ActionBarActivity {

    private static final String complimentTag = "COMPLIMENT_TAG";
    private static final String colorTag = "COLOR_TAG";
    private ShareActionProvider mShareActionProvider;

    String compliment = "";

    String currentColor;

    Compliments complimentClass;

    TextView complimentTextView;
    RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        complimentClass = new Compliments(this);
        currentColor = complimentClass.randomizeColor();

        setContentView(R.layout.activity_compliments);
        setupUI();
        if(savedInstanceState != null)
        {
            compliment = savedInstanceState.getString(complimentTag);
            currentColor = savedInstanceState.getString(colorTag);
            changeCompliment();
        }
        if (compliment.equals(""))
        {
            randomizeAll();
            changeCompliment();
        }
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomizeAll();
                changeCompliment();
            }
        });
    }

    private void setupUI()
    {
        complimentTextView = (TextView) findViewById(R.id.compliment_textview);
        background = (RelativeLayout) findViewById(R.id.compliments_relativeView);
    }

    private void randomizeAll()
    {
        compliment = complimentClass.randomizeString();
        currentColor = complimentClass.randomizeColor();
    }

    private void changeCompliment()
    {
        complimentTextView.setText(compliment);
        background.setBackgroundColor(Color.parseColor(currentColor));

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
}
