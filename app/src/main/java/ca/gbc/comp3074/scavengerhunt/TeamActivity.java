package ca.gbc.comp3074.scavengerhunt;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.gbc.comp3074.scavengerhunt.adapters.TeamMemberAdapter;
import ca.gbc.comp3074.scavengerhunt.entities.TeamMember;
import ca.gbc.comp3074.scavengerhunt.helpers.DatabaseHelper;

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
        setContentView(R.layout.activity_team);
        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        recyclerView = findViewById(R.id.recycler_view2);

        dbHelper = new DatabaseHelper(this);
        teammembers.addAll(dbHelper.getAllTeamMembers());
        adapter = new TeamMemberAdapter(this, teammembers, dbHelper); //pass db helper here so items can be deleted with the button
        System.out.println("s:"+teammembers.size());

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.team_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_team_member:
                openAddTeamMember();
                return true;

            case android.R.id.home:
                finish();
                return true;

            default:return super.onOptionsItemSelected(item);
        }
    }

    private void openAddTeamMember(){
        Intent start = new Intent(getApplicationContext(), AddTeamMemberActivity.class);
        startActivity(start);
    }
}