package com.example.s10047816.heatisland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;

public class ListViewDescription extends AppCompatActivity {

    String[] descriptiveWords = {"dirty", "grassy", "watery", "firey", "gina", "chen"};
    boolean[] descriptiveBools = new boolean[descriptiveWords.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_description);

        ArrayAdapter<String> descriptions = new ArrayAdapter<String>(this, R.layout.list_view, descriptiveWords);
        ListView list = (ListView) findViewById(R.id.list);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(descriptions);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                descriptiveBools[position] = !descriptiveBools[position];
            }
        });
    }


    public void onClickSubmit(View view){
        Button submit = (Button)view;
        Intent intent = new Intent(ListViewDescription.this,Form.class);
        ArrayList<String> description = new ArrayList<>();
        for(int i=0;i<descriptiveBools.length;i++){
            if(descriptiveBools[i]){
                description.add(descriptiveWords[i]);
            }
        }
        intent.putExtra("description", description);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), Arrays.toString(descriptiveBools), Toast.LENGTH_SHORT).show();

    }
}
