package com.example.s10047816.heatisland;

<<<<<<< HEAD
import android.os.AsyncTask;
=======
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
>>>>>>> 1612af4f66ead038e6dd60325a45d44cee7da10e
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

<<<<<<< HEAD
    private Exception exception;
=======

    public String descCat = "";
    HashMap<String,Boolean> descValues = new HashMap<>();


    private void executeReq(URL urlObject) throws IOException {
        HttpURLConnection conn = null;
>>>>>>> 1612af4f66ead038e6dd60325a45d44cee7da10e

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

<<<<<<< HEAD
public class Form extends AppCompatActivity{

    HashMap<String,Boolean> descValues = new HashMap<>();
=======
    /*
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        Log.i("info", "you got here! 3");
        getMenuInflater().inflate(R.menu.description_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextMenuSelected(MenuItem item) {
        //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.i("info", "you got here! 2");
        return true;
    }
>>>>>>> 1612af4f66ead038e6dd60325a45d44cee7da10e

    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("info", "you got here!");
        if (!item.isChecked()){
            descValues.put(item.getTitle().toString(), true);
        } else{
            descValues.put(item.getTitle().toString(),false);
        }
        return true;
    }
    */
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


    public void onClickSendToDescriptions(View view){
        startActivity(new Intent(Form.this,ListViewDescription.class));
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
<<<<<<< HEAD
        Button button = (Button) findViewById(R.id.menuButton);
        registerForContextMenu(button);
=======

        /*
        Drawable drawable = getResources().getDrawable(R.drawable.logo);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        ScaleDrawable scaleDrawable = new ScaleDrawable(drawable,0,0.2f,0.2f);
        TextView title = (TextView) findViewById(R.id.title);
        title.setCompoundDrawables(scaleDrawable.getDrawable(),null,null,null);
        */
        //Button button = (Button) findViewById(R.id.menuButton);
        //registerForContextMenu(button);

        /*
                try {
            String parameters = ""; //
            URL url = new URL("http://carter.com" + parameters);
            executeReq(url);
        }
        catch(Exception e){Log.i("error", "error")}
         */
>>>>>>> 1612af4f66ead038e6dd60325a45d44cee7da10e
    }
}