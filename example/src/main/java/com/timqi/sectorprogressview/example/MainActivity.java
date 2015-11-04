package com.timqi.sectorprogressview.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView list;

    String[] atys = new String[]{"SectorProgressView", "ColorfulRingProgressView"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("MainActivity");

        list = (ListView) findViewById(R.id.list);
        ListAdapter a = new ArrayAdapter(this, R.layout.item_activity_main,atys);
        list.setAdapter(a);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent intent = new Intent(MainActivity.this,
                            Class.forName("com.timqi.sectorprogressview.example."
                            + atys[position]
                            + "Aty"));
                    intent.putExtra("TITLE", atys[position]);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private int dp2px(float dp) {
        return (int) (getResources().getDisplayMetrics().density*dp+0.5f);
    }
}
