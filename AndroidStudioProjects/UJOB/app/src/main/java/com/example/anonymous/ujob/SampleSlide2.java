package com.example.anonymous.ujob;

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
import android.widget.Toast;

/**
 * Created by anonymous on 4/23/16.
 */
public class SampleSlide2 extends android.support.v4.app.Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";

    public static SampleSlide2 newInstance(int layoutResId) {
        SampleSlide2 sampleSlide = new SampleSlide2();

        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        sampleSlide.setArguments(args);

        return sampleSlide;
    }

    private int layoutResId;

    public SampleSlide2() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID))
            layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(layoutResId,container,false);
        FloatingActionButton fb2= (FloatingActionButton) v.findViewById(R.id.view2);
        fb2.setImageResource(R.drawable.fasa);
        fb2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF39F7C1")));
        fb2.setRippleColor(Color.parseColor("#FF0AA56C"));
        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        TextView tx2= (TextView) v.findViewById(R.id.textView2);
        tx2.setGravity(Gravity.CENTER);
        Typeface typeface=Typeface.createFromAsset(getContext().getAssets(),"fonts/Ailerons-Typeface.otf");
        tx2.setTypeface(typeface);
        return v;
    }

}





