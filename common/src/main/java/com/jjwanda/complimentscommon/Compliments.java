package com.jjwanda.complimentscommon;

import android.content.Context;

import java.util.Random;

/**
 * This helps constantly switch the compliments.
 */
public class Compliments {

//    private static final String complimentTag = "COMPLIMENT_TAG";
//    private static final String colorTag = "COLOR_TAG";

    private String[] complimentsArray; // We get them from a compliments array
    private int complimentIndex;

    private String[] lightColorArray;
    private String currentColor;
    private int colorIndex;

    private Context context;
    private Random randomGen;

    public Compliments(Context context)
    {
        this.context = context;
        initializeVariables();
    }

    private void initializeVariables()
    {
        complimentsArray = context.getResources().getStringArray(R.array.compliments_string_array);
        randomGen = new Random();
        lightColorArray = context.getResources().getStringArray(R.array.light_colors);
        colorIndex = randomGen.nextInt(lightColorArray.length);
        currentColor = lightColorArray[colorIndex];
    }

    public String randomizeString()
    {
        int randomNumber = complimentIndex;
        while (randomNumber == complimentIndex)
        {
            randomNumber = randomGen.nextInt(complimentsArray.length);
        }
        complimentIndex = randomNumber;
        return complimentsArray[complimentIndex];
    }

    public String randomizeColor()
    {
        int randomNumber = colorIndex;
        while(randomNumber == colorIndex)
        {
            randomNumber = randomGen.nextInt(lightColorArray.length);
        }
        colorIndex = randomNumber;
        currentColor = lightColorArray[colorIndex];
        return currentColor;
    }
}
