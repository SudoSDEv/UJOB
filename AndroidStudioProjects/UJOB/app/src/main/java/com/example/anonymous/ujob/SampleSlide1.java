package com.example.anonymous.ujob;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by anonymous on 4/23/16.
 */
public class SampleSlide1 extends android.support.v4.app.Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";

    public static SampleSlide1 newInstance(int layoutResId) {
        SampleSlide1 sampleSlide = new SampleSlide1();

        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID,  layoutResId);
        sampleSlide.setArguments(args);

        return sampleSlide;
    }

    private int layoutResId;

    public SampleSlide1() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID))
            layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(layoutResId, container, false);

        FloatingActionButton fb= (FloatingActionButton) v.findViewById(R.id.view);
        fb.setImageResource(R.drawable.github);
        fb.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF39F7C1")));
        fb.setRippleColor(Color.parseColor("#FF0AA56C"));
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
startActivity(new Intent(getContext(),git_startup.class));
            }
        });
        TextView tx= (TextView) v.findViewById(R.id.textView);
        Typeface typeface=Typeface.createFromAsset(getContext().getAssets(),"fonts/Ailerons-Typeface.otf");
        tx.setTypeface(typeface);
        tx.setGravity(Gravity.CENTER);
        return  v;
    }

}

