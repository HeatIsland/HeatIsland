package com.example.s2034257.heatisland;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myOnClickMethod(View view) {


        LinearLayout gameOver = (LinearLayout) findViewById(R.id.pa);
        TextView Submit = (TextView) findViewById(R.id.button);
        gameOver.setVisibility(View.VISIBLE);
    }


    public void playAgain(View view) {
        TextView overMessage = (TextView) findViewById(R.id.editText);
        TextView overM = (TextView) findViewById(R.id.editText2);
        TextView over = (TextView) findViewById(R.id.editText4);
        TextView o = (TextView) findViewById(R.id.editText5);
        LinearLayout gameOver = (LinearLayout) findViewById(R.id.pa);
        gameOver.setVisibility(View.INVISIBLE);
        overMessage.setText("");
        overM.setText("");
        over.setText("");
        o.setText("");
    }
}