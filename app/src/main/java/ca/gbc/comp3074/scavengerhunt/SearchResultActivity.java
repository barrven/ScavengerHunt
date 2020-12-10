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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        recyclerView = findViewById(R.id.recycler_view);
        String search = this.getIntent().getStringExtra("search");
        System.out.println("here:");
        System.out.println(search);

        dbHelper = new DatabaseHelper(this);
        points.addAll(dbHelper.getByTagOrName(search));
        adapter = new PointAdapter(this, points, dbHelper); //pass db helper here so items can be deleted with the button

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }
}