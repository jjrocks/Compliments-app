package com.jjwanda.complimentscommon;

import android.content.Context;

import java.util.Random;

/**
 * This helps constantly switch the compliments.
 */
public class Compliments {

//    private static final String complimentTag = "COMPLIMENT_TAG";
//    private static final String colorTag = "COLOR_TAG";

    String[] complimentsArray;
    String compliment = "";
    int complimentsSize;
    int complimentIndex;

    String[] lightColorArray;
    String currentColor;
    int colorsSize;
    int colorIndex;

    Context context;
    Random randomGen;

    public Compliments(Context context)
    {
        this.context = context;
        initializeVariables();
    }

    private void initializeVariables()
    {
        complimentsArray = context.getResources().getStringArray(R.array.compliments_string_array);
        complimentsSize = complimentsArray.length;
        randomGen = new Random();

        lightColorArray = context.getResources().getStringArray(R.array.light_colors);
        colorsSize = lightColorArray.length;
        colorIndex = randomGen.nextInt(colorsSize);
        currentColor = lightColorArray[colorIndex];
    }

    public String randomizeString()
    {
        int randomNumber = complimentIndex;
        while (randomNumber == complimentIndex)
        {
            randomNumber = randomGen.nextInt(complimentsSize);
        }
        complimentIndex = randomNumber;
        compliment = complimentsArray[complimentIndex];
        return compliment;
    }

    public String randomizeColor()
    {
        int randomNumber = colorIndex;
        while(randomNumber == colorIndex)
        {
            randomNumber = randomGen.nextInt(colorsSize);
        }
        colorIndex = randomNumber;
        currentColor = lightColorArray[colorIndex];
        return currentColor;
    }

    public void randomizeAll()
    {
        randomizeString();
        randomizeColor();
    }
}
