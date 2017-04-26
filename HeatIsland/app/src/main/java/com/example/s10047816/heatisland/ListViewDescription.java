package com.example.s10047816.heatisland;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewDescription extends AppCompatActivity {

    String[] descriptiveWords = {"dirty", "grassy", "watery", "firey", "gina", "chen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_description);

        ArrayAdapter<String> descriptions = new ArrayAdapter<String>(this, R.layout.list_view, descriptiveWords);
        ListView list = (ListView) findViewById(R.id.list);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(descriptions);
    }
}
