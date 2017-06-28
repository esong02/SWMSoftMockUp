package com.example.esong02.swmsoftlofi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

/**
 * Created by esong02 on 2017-06-27.
 */

public class MyTasksActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inspection_list);

        Intent i= getIntent();
        Bundle b = i.getExtras();
        String title = "";

        if (b != null)
        {
            title = (String) b.get("Activity");
        }

        // Set the support action bar
        mToolbar = (Toolbar) findViewById(R.id.myTasksBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

        MyTasks myDataArray[] = new MyTasks[]{
                new MyTasks("Pondview2","Complete", "Northeast corner\nof Hwy 400", 5),
                new MyTasks("Lakeview\nEstates", "Pending", "Btwn Dundurn Cr &\nRosedale Heights", 15),
                new MyTasks("Oakbank\nThornbank", "Pending", "N side of Centre St btwn\nErica & Oakbank ", 30),
                new MyTasks("Trullwrook\nInvestment", "Complete", "Part Lot 7\nConcession", 50)
        };

        MyTasksAdapter myAdapter = new MyTasksAdapter(this, R.layout.item_task, myDataArray);
        ListView myList = (ListView) findViewById(R.id.inspections_todo);
        myList.setAdapter(myAdapter);

    }
}
