package com.example.anonymous.ujob;

import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by anonymous on 4/23/16.
 */
public class MyIntro extends AppIntro {

    //int a[]={R.id.frag1,R.id.frag2};
    // Please DO NOT override onCreate. Use init.
    @Override
    public void init(Bundle savedInstanceState) {


        // Add your slide's fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(SampleSlide2.newInstance(R.layout.frag2));
        addSlide(SampleSlide1.newInstance(R.layout.frag1));


        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
       // addSlide(AppIntroFragment.newInstance("Hello", "TESTING", android.R.drawable.arrow_up_float, 0xff0000ff));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));//3F51B5
        setSeparatorColor(Color.parseColor("#2196F3"));//2196F3

        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(false);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed() {
        //addSlide(SampleSlide2.newInstance(a[1]));

    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged() {
        //setFadeAnimation(); // OR
        //setZoomAnimation(); // OR
        setFlowAnimation(); // OR
        //setSlideOverAnimation(); // OR
        //setDepthAnimation(); // OR
    }
    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

}