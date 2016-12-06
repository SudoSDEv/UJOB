package com.example.anonymous.ujob;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import me.biubiubiu.justifytext.library.JustifyTextView;

/**
 * Created by anonymous on 4/1/16.
 */
public class git_details extends Activity {
    //String response;
    JSONObject jsonObject;
    JSONArray jsonArray;
   /* ArrayList<String> logo_urls=new ArrayList<>();
    ArrayList<String> descriptions=new ArrayList<>();
    ArrayList<String> title=new ArrayList<>();
    ArrayList<String> locations=new ArrayList<>();
    ArrayList<String> how_to_apply=new ArrayList<>();
    ArrayList<String> urls=new ArrayList<>();
    ArrayList<String> companies=new ArrayList<>();*/
     ImageView logot;

    int length;

    String title="",json;
    String description="";
    String logo_url="";
    String location="";
    String how_to_apply="";
    String url="";
    String company="";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.git_details);
        Bundle b=getIntent().getExtras();
        length=b.getInt("length");
        json=b.getString("json");
        try {
            jsonArray=new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final Typeface typeface1=Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");





        final ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager1);
        PagerAdapter myadpater=new PagerAdapter() {

            @Override
            public int getCount() {
                return length;
            }




            @Override
            public Object instantiateItem(ViewGroup container, final int position) {

                try {
                    jsonObject=jsonArray.getJSONObject(position);
                    title=jsonObject.getString("title");
                    location=jsonObject.getString("location");
                    description=jsonObject.getString("description");
                    logo_url=jsonObject.getString("company_logo");
                    how_to_apply=jsonObject.getString("how_to_apply");
                    url=jsonObject.getString("url");
                    company=jsonObject.getString("company");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                LayoutInflater lay= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View view=lay.inflate(R.layout.re_new_xml,null);


                CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);


                collapsingToolbarLayout.setTitle(title);
                collapsingToolbarLayout.setExpandedTitleTypeface(Typeface.createFromAsset(getAssets(), "fonts/GeosansLight.ttf"));
                collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#FFF7EFEF"));


                JustifyTextView t= (JustifyTextView) view.findViewById(R.id.description);
                String a= Jsoup.parse(description).text();
                t.setText(a);
                t.setGravity(Gravity.CENTER_VERTICAL);



                TextView companyt= (TextView) view.findViewById(R.id.company);
                companyt.setText(company);
                companyt.setTypeface(typeface1);

                TextView loc= (TextView) view.findViewById(R.id.location);
                loc.setText(location);
                loc.setTypeface(typeface1);


                logot= (ImageView) view.findViewById(R.id.imageview);
                int rndno= 1+(int) (Math.random()*((3-1)+1));
                int id=getResources().getIdentifier("chair"+rndno,"drawable",getPackageName());
                logot.setImageResource(id);
                logot.setScaleType(ImageView.ScaleType.CENTER_CROP);

                new logo_download().execute(logo_url);
                FloatingActionButton fb1= (FloatingActionButton) view.findViewById(R.id.fab);
                fb1.setImageResource(R.drawable.ribbon);
                fb1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                fb1.setRippleColor(Color.parseColor("#FFF97CB2"));

                fb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(),"Item added to wish list",Toast.LENGTH_SHORT).show();
                    }
                });
                ((ViewPager) container).addView(view, 0);
                return view;


            }


            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
            }

            @Override
            public void finishUpdate(ViewGroup container) {
                super.finishUpdate(container);
            }

            @Override
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView((View) arg2);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == ((View) arg1);
            }

            @Override
            public Parcelable saveState() {
                return null;
            }



        };
        

        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(myadpater);
        viewPager.setCurrentItem(0,true);
    }



    class logo_download extends AsyncTask<String,Void,Bitmap>
    {
        Bitmap bitmap;
        @Override
        protected Bitmap doInBackground(String... params) {
            if(!params[0].trim().equals("null")) {
                try {
                    URL url = new URL(params[0]);
                    HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                    InputStream inputStream=httpURLConnection.getInputStream();
                    bitmap= BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    httpURLConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*else
                Toast.makeText(getBaseContext(),"No image available.",Toast.LENGTH_SHORT).show();*/
            if(!params[0].trim().equals("null"))
            return Bitmap.createScaledBitmap(bitmap, 420, 350, false);
            else
                return null;
        }

        @Override
        protected void onPostExecute(final Bitmap bitmap) {

        logot.setImageBitmap(bitmap);

        }

    }




}
