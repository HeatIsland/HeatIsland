package com.example.s10047816.heatisland;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by s10047816 on 4/19/2017.
 */
public class Form extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Drawable drawable = getResources().getDrawable(R.drawable.logo);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        ScaleDrawable scaleDrawable = new ScaleDrawable(drawable,0,0.2f,0.2f);
        TextView title = (TextView) findViewById(R.id.title);
        title.setCompoundDrawables(scaleDrawable.getDrawable(),null,null,null);

    }
}
