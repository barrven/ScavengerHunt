package ca.gbc.comp3074.scavengerhunt;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.gbc.comp3074.scavengerhunt.DatabaseHelper;
import ca.gbc.comp3074.scavengerhunt.Point;
import ca.gbc.comp3074.scavengerhunt.PointAdapter;
import ca.gbc.comp3074.scavengerhunt.R;

public class SearchResultActivity extends AppCompatActivity {
    //basically a copy/paste of mainactivity
    //in another life they'd use the same class but yolo

    private DatabaseHelper dbHelper;
    private List<Point> points = new ArrayList<>();
    private PointAdapter adapter;
    private RecyclerView recyclerView;
    public static final int SEARCH_TYPE_NAME = 0;
    public static final int SEARCH_TYPE_TAGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        recyclerView = findViewById(R.id.recycler_view);
        int type = Integer.parseInt(this.getIntent().getStringExtra("type"));
        String search = this.getIntent().getStringExtra("search");

        dbHelper = new DatabaseHelper(this);

        //could easily add new searches this way
        switch (type){
            case SEARCH_TYPE_NAME:
                points.addAll(dbHelper.getByName(search));
            case SEARCH_TYPE_TAGS:
                points.addAll(dbHelper.getByTags(search));
        }
        System.out.println(points.size());
        adapter = new PointAdapter(this, points, dbHelper); //pass db helper here so items can be deleted with the button

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }
}