package com.example.s10047816.heatisland;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.security.KeyStore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.change_page_menu, menu);
        return true;
    }

    public void clicker(View view){
        startActivity(new Intent(MainActivity.this,Form.class));
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case(R.id.home):
                return true;
            case(R.id.form):
                startActivity(new Intent(MainActivity.this,Form.class));
                return true;
            default:
                super.onOptionsItemSelected(item);

        }
        return false;
    }
}
