package com.jjwanda.compliments;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;


public class ComplimentsActivity extends ActionBarActivity {

    String[] complimentsArray;
    String compliment = "";
    int complimentsSize;
    int complimentIndex;
    Random randomGen;

    TextView complimentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeVariables();
        setupUI();

        setContentView(R.layout.activity_compliments);
    }

    private void initializeVariables()
    {
        complimentsArray = getResources().getStringArray(R.array.compliments_string_array);
        complimentsSize = complimentsArray.length;
        randomGen = new Random();
        complimentIndex = randomGen.nextInt(complimentsSize);
        compliment = complimentsArray[complimentIndex];
    }

    private void setupUI()
    {
        complimentTextView = (TextView) findViewById(R.id.compliment_textview); //Change
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compliments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
