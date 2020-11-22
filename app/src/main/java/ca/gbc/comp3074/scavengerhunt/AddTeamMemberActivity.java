package ca.gbc.comp3074.scavengerhunt;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddTeamMemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team_member);
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

            default:return super.onOptionsItemSelected(item);
        }
    }

    private void openAddTeamMember(){
        Intent start = new Intent(getApplicationContext(), AddPointActivity.class);
        startActivity(start);
    }

    
}
