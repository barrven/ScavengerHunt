package ca.gbc.comp3074.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UpdateTeamMemberActivity extends AppCompatActivity {

    private final DatabaseHelper dbHelper = new DatabaseHelper(this);
    private final List<TeamMember> teammembers = new ArrayList<>();
    private final TeamMemberAdapter adapter = new TeamMemberAdapter(this, teammembers, dbHelper);

    private EditText[] inputs;
    TeamMember teamMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teammember);

        //initialize inputs
        inputs = new EditText[]{
            findViewById(R.id.etAddTeammemberName),
            findViewById(R.id.etAddTeammemberEmail),
            findViewById(R.id.etAddTeammemberPhone),
            findViewById(R.id.etAddTeammemberSms)
        };

        //initialize teammember object
        teamMember = getTeamMemberFromIntent(getIntent());
        inputs[0].setText(teamMember.getName());
        inputs[1].setText(teamMember.getEmail());
        inputs[2].setText(teamMember.getPhone());
        inputs[3].setText(teamMember.getSms());

        //change page title (reusing layout file for add team member)
        TextView title = findViewById(R.id.tvAddTeammemberTitle);
        title.setText("Update Team Member");

        //make the button
        Button btnAddTeammember = findViewById(R.id.btnAddTeammember);
        btnAddTeammember.setText("Update");
        btnAddTeammember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTeammember();
            }

        });
    }

    private TeamMember getTeamMemberFromIntent(Intent intent){
        return new TeamMember(
            Integer.parseInt(intent.getStringExtra("id")),
            intent.getStringExtra("name"),
            intent.getStringExtra("email"),
            intent.getStringExtra("phone"),
            intent.getStringExtra("sms")
        );
    }

    public void updateTeammember(){

        //update the object
        teamMember.setName(inputs[0].getText().toString());
        teamMember.setEmail(inputs[1].getText().toString());
        teamMember.setPhone(inputs[2].getText().toString());
        teamMember.setSms(inputs[3].getText().toString());

        //pass the object to the database
        dbHelper.updateItem(teamMember);
        Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();


        adapter.notifyDataSetChanged();
        finish(); //go back to previous activity
    }
}