package ca.gbc.comp3074.scavengerhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private List<Point> points = new ArrayList<>();
    private PointAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        dbHelper = new DatabaseHelper(this);
        points.addAll(dbHelper.getAllItems());
        adapter = new PointAdapter(this, points, dbHelper); //pass db helper here so items can be deleted with the button

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }







    //*****menu methods*****
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_point:
                openAddPoint();
                return true;
            case R.id.menu_search:
                openSearch();
                return true;
            case R.id.menu_about:
                openAboutUs();

            default:return super.onOptionsItemSelected(item);
        }
    }

    private void openAddPoint(){
        Intent start = new Intent(getApplicationContext(), AddPointActivity.class);
        startActivity(start);
    }

    private void openSearch(){
        Intent start = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(start);
    }

    private void openAboutUs(){
        Intent start = new Intent(getApplicationContext(), AboutUsActivity.class);
        startActivity(start);
    }

}
