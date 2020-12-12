package ca.gbc.comp3074.scavengerhunt;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity {
    //basically a copy/paste of mainactivity
    //in another life they'd use the same class but yolo

    private DatabaseHelper dbHelper;
    private List<TeamMember> teammembers = new ArrayList<>();
    private TeamMemberAdapter adapter;
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
        int type = Integer.parseInt(this.getIntent().getStringExtra("type"));
        String search = this.getIntent().getStringExtra("search");

        dbHelper = new DatabaseHelper(this);

        System.out.println(teammembers.size());
        adapter = new TeamMemberAdapter(this, teammembers, dbHelper); //pass db helper here so items can be deleted with the button

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }
}