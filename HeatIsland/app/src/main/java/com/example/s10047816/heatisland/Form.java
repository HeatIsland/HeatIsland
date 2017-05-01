package com.example.s10047816.heatisland;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

class SendGetTask extends AsyncTask<String, Void, Void> {

    private Exception exception;

    protected Void doInBackground(String... url) {
            Log.i("testing", "url:"+url[0]);
            String urls = url[0];
            try {
                URL obj = new URL(urls);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                //this is the http response code of the website - currently not used
                int responseCode = con.getResponseCode();
            }catch(Exception e){}
            return null;
        }
}

public class Form extends AppCompatActivity{

    HashMap<String,Boolean> descValues = new HashMap<>();

    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("info", "you got here!");
        if (!item.isChecked()){
            descValues.put(item.getTitle().toString(), true);
        } else{
            descValues.put(item.getTitle().toString(),false);
        }
        return true;
    }

    public void submit(View view){
        Log.i("submitting", "sending data to database");
        //just to clarify, temp is for temperature NOT temporary
        final TextView temp = (TextView) findViewById(R.id.temp);
        final TextView desc = (TextView) findViewById(R.id.desc);
        try {
            String parameters = "?location="; //
            parameters += "12.3%2C23.5";
            parameters += "&description="+desc.getText().toString();
            parameters += "&temperature="+temp.getText().toString();
            URL url = new URL("http://carter-lasa-guess.appspot.com/" + parameters);
            String urls = "http://carter-lasa-guess.appspot.com/" + parameters;
            Log.i("url", "sending: "+url);
            new SendGetTask().execute(urls);
        }catch(Exception e){Log.i("error", ""+e);}
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Button button = (Button) findViewById(R.id.menuButton);
        registerForContextMenu(button);
    }
}