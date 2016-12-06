package com.example.anonymous.ujob;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by anonymous on 4/25/16.
 */
public class git_startup extends Activity {
    String country_codes[];
    ArrayList<String> country_name=new ArrayList<String>();
    String git_API="https://jobs.github.com/positions.json?description=python&location=sf&full_time=true";
    String response;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<String> logo_urls=new ArrayList<>();
    ArrayList<String> descriptons=new ArrayList<>();
    ArrayList<String> title=new ArrayList<>();
    ArrayList<String> locations=new ArrayList<>();
    ArrayList<String> how_to_apply=new ArrayList<>();
    ArrayList<String> urls=new ArrayList<>();
    ArrayList<String> company=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.git_startup);
        int c=0;
        country_codes=Locale.getISOCountries();

        for (String a : country_codes) {
            Locale obj = new Locale("", a);
            country_name.add(c++, obj.getDisplayCountry());
        }

        final EditText et1= (EditText) findViewById(R.id.topic);

        final AutoCompleteTextView autoCompleteTextView= (AutoCompleteTextView) findViewById(R.id.country);
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(git_startup.this, android.R.layout.simple_list_item_1, country_name));

        ImageButton imageButton= (ImageButton) findViewById(R.id.imageButton1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et1.getText().length() == 0 || autoCompleteTextView.getText().toString().length() == 0)
                    Toast.makeText(getBaseContext(), "Fields not mentioned!", Toast.LENGTH_SHORT).show();
                else {
                    //String c_code=find_code(autoCompleteTextView.getText().toString());
                    String temp = git_API.replace(git_API.substring(git_API.indexOf("python"), git_API.indexOf("python") + "python".length()), et1.getText().toString());
                    temp = temp.replace(git_API.substring(git_API.indexOf("sf"), git_API.indexOf("sf") + "sf".length()), autoCompleteTextView.getText().toString());
                    Toast.makeText(getBaseContext(), temp, Toast.LENGTH_LONG).show();

                    new Mynew_task().execute(temp);
                }

            }
        });
    }



    /*public void MyJsonTask(String a)
    {


        Toast.makeText(getBaseContext(),a,Toast.LENGTH_LONG).show();


        try{
            jsonArray=new JSONArray(a);

            for(int i=0;i<jsonArray.length();i++)
            {
                jsonObject=jsonArray.getJSONObject(i);
                title.add(i,jsonObject.getString("title"));
                locations.add(i,jsonObject.getString("location"));
                descriptons.add(i,jsonObject.getString("description"));
                logo_urls.add(i,jsonObject.getString("company_logo"));
                how_to_apply.add(i,jsonObject.getString("how_to_apply"));
                urls.add(i,jsonObject.getString("url"));
                company.add(i,jsonObject.getString("company"));
                //publishProgress(i+1);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(getBaseContext(),"Completed",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(git_startup.this, git_details.class).putStringArrayListExtra("logo_urls", logo_urls).putStringArrayListExtra("descriptions", descriptons).putStringArrayListExtra("title", title).putStringArrayListExtra("locations", locations).putStringArrayListExtra("how_to_apply", how_to_apply).putStringArrayListExtra("urls", urls).putStringArrayListExtra("company", company));
        // lv.setAdapter(new ArrayAdapter<>(git_job.this,android.R.layout.simple_list_item_1,locations));

    }*/

   /* public void MyJsonTask(String a)
    {

        try {
            jsonArray=new JSONArray(a);
            int length=jsonArray.length();
            jsonObject=jsonArray.getJSONObject(0);
            String title=jsonObject.getString("title");
            String description=jsonObject.getString("description");
            String logo_url=jsonObject.getString("company_logo");
            String location=jsonObject.getString("location");
            String how_to_apply=jsonObject.getString("how_to_apply");
            String url=jsonObject.getString("url");
            String company=jsonObject.getString("company");
            Toast.makeText(getApplicationContext(),title+company,Toast.LENGTH_SHORT).show();
            startActivity(new Intent(git_startup.this, git_details.class).putExtra("logo_url", logo_url).putExtra("description", description).putExtra("title", title).putExtra("location", location).putExtra("how_to_apply", how_to_apply).putExtra("url", url).putExtra("comapny", company).putExtra("length", length));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }*/





class Mynew_task extends AsyncTask<String,Integer,String>
{
    ProgressDialog dialog;


    @Override
    protected String doInBackground(String... params) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url=new URL(params[0]);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String a;
            while((a=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(a);
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }



        return stringBuilder.toString().trim();
    }



    @Override
    protected void onProgressUpdate(Integer... values) {

        //dialog.setProgress(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
            dialog=new ProgressDialog(git_startup.this);
            dialog.setTitle("Fetching data...");
            dialog.setCancelable(false);
            dialog.setIndeterminate(true);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setProgress(0);
            dialog.show();
    }

    @Override
    protected void onPostExecute(String aVoid) {
        dialog.hide();
        response=aVoid;
        try {
            jsonArray=new JSONArray(aVoid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int length=jsonArray.length();
        startActivity(new Intent(git_startup.this,git_details.class).putExtra("length",length).putExtra("json",aVoid));
        //MyJsonTask(aVoid);
        //dialog.hide();

    }
}
}
