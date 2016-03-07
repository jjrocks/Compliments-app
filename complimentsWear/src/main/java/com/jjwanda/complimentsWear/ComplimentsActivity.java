package com.jjwanda.complimentsWear;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;
import com.jjwanda.complimentscommon.Compliments;

public class ComplimentsActivity extends Activity {

    private String compliment;
    private String currentColor;

    private TextView mTextView;
    private View backgroundView;
    private Compliments compliments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compliments);
        compliments = new Compliments(this);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                backgroundView = stub.findViewById(R.id.wear_background);
                backgroundView.setClickable(true);

                backgroundView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        randomizeAll();
                        changeCompliment();
                    }
                });

                randomizeAll();
                changeCompliment();
            }
        });


/*
        backgroundView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                randomizeAll();
                changeCompliment();
            }
        });

        randomizeAll();
        changeCompliment();
        */
    }

    private void randomizeAll()
    {
        compliment = compliments.randomizeString();
        currentColor = compliments.randomizeColor();
    }

    private void changeCompliment()
    {
        mTextView.setText(compliment);
        backgroundView.setBackgroundColor(Color.parseColor(currentColor));
    }
}
