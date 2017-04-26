package com.example.s10047816.heatisland;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by s10047816 on 4/19/2017.
 */
public class Form extends AppCompatActivity{


    public String descCat = "";
    HashMap<String,Boolean> descValues = new HashMap<>();


    private void executeReq(URL urlObject) throws IOException {
        HttpURLConnection conn = null;

        conn = (HttpURLConnection) urlObject.openConnection();
        conn.setReadTimeout(100000); //Milliseconds
        conn.setConnectTimeout(150000); //Milliseconds
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
    }

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
        final TextView temp = (TextView) findViewById(R.id.temp);
        final TextView desc = (TextView) findViewById(R.id.desc);
        try {
            String parameters = "?location="; //
            parameters += "12.3%2C23.5";
            parameters += "&description="+desc.getText().toString();
            URL url = new URL("http://carter-lasa-guess.appspot.com/" + parameters);
            executeReq(url);
        }
        catch(Exception e){Log.i("error", "error");}
    }


    public void onClickSendToDescriptions(View view){
        startActivity(new Intent(Form.this,ListViewDescription.class));
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

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
    }
}
