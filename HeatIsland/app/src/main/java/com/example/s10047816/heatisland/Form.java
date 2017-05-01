package com.example.s10047816.heatisland;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

class SendGetTask extends AsyncTask<String, Void, Void> {
    private Exception exception;

    private void executeReq(URL urlObject) throws IOException {
        HttpURLConnection conn = null;
    }

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


    public void submit(View view){
        Log.i("submitting", "sending data to database");
        //just to clarify, temp is for temperature NOT temporary
        final TextView temp = (TextView) findViewById(R.id.temp);
        final TextView desc = (TextView) findViewById(R.id.desc);
        String descT = desc.getText().toString();
        try {
            Log.i("preencoded", ""+descT);
            descT = URLEncoder.encode(descT, "UTF-8");
            descT.replace("%20", "+");
            Log.i("postencoded", ""+descT);
            String parameters = "?location="; //
            parameters += "12.3%2C23.5";
            parameters += "&description="+descT;
            parameters += "&temperature="+temp.getText().toString();
            URL url = new URL("http://carter-lasa-guess.appspot.com/" + parameters);
            String urls = "http://carter-lasa-guess.appspot.com/" + parameters;
            Log.i("url", "sending: "+url);
            new SendGetTask().execute(urls);
        }catch(Exception e){Log.i("error", ""+e);}
    }


    public void onClickSendToDescriptions(View view){
        startActivity(new Intent(Form.this,ListViewDescription.class));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Bundle extras = getIntent().getExtras();

        if(extras!=null) {
            if (extras.containsKey("description")) {
                final EditText desc = (EditText) findViewById(R.id.desc);
                String description = "";
                for (String d : (ArrayList<String>) extras.get("description")) {
                    description += d + ", ";
                }
                desc.setText(description);
            }
        }
    }
}