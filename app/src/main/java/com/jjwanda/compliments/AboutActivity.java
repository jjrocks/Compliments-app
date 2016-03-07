package com.jjwanda.compliments;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;


public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        linkAllTexts();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat
                    .getColor(getApplicationContext(), R.color.about_status_bar_color)));
        }
    }

    /**
     * This is used in order to basically make working links in a textbox. We put this all in a
     * method cause it's easier.
     */
    private void linkAllTexts()
    {
        // If this gets longer than 5 items
        linkText((TextView) findViewById(R.id.add_compliment));
        linkText((TextView) findViewById(R.id.donate_text));
        linkText((TextView) findViewById(R.id.inspired_by_text));
        linkText((TextView) findViewById(R.id.website_text));
    }

    /**
     * This is the method that links the text. In a string it has
     * <a href="http://google.com">What</a> for the link to work
     * @param textView The text view needs to already have a string set.
     */
    private void linkText(TextView textView)
    {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
