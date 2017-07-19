package com.example.esong02.swmsoftlofi.SampleForm.CardStackUI;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by esong02 on 2017-07-19.
 */

public class CardStackTransformer implements ViewPager.PageTransformer
{

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void transformPage(View page, float position)
    {
        if(position>=0)
        {
            //Toast.makeText(, "Moving" , Toast.LENGTH_SHORT).show();
            //Log.d("Moving",position+"");
            page.setScaleX(0.9f - 0.02f * position);

            page.setScaleY(0.9f);

            page.setTranslationX(- page.getWidth()*position);

            page.setTranslationY(30 * position);

        }

    }
}
