package ca.gbc.comp3074.scavengerhunt;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewTeamMemberActivity extends AppCompatActivity {

    private int id;
    private String name, email, phone, sms;
    private double ratings;

    private TeamMember teammember;
    private TeamMemberAdapter adapter;
    private TextView[] outputs;

    //for deleting teammember and refreshing list
    private List<TeamMember> teammembers = new ArrayList<>();
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teammember);

        // Receive info about object from previous activity
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");
        sms = intent.getStringExtra("sms");

        teammember = new TeamMember(id,name,email,phone,sms);

        //initialize these for use with clearing list of items on delete
        dbHelper = new DatabaseHelper(this);
        adapter = new TeamMemberAdapter(this, teammembers, dbHelper);

        //Get the text views for displaying teammember details
        outputs = new TextView[]{
                findViewById(R.id.tv_name),
                findViewById(R.id.tv_email),
                findViewById(R.id.tv_phone),
                findViewById(R.id.tv_sms)
        };

        outputs[0].setText(name);
        outputs[1].setText(email);
        outputs[2].setText(phone);
        outputs[3].setText(sms);
    }

    //?
    private void openDelete(){
        DialogInterface.OnClickListener diOnClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(getApplicationContext(), "Team member deleted", Toast.LENGTH_SHORT).show();
                        dbHelper.deleteItem(teammember);
                        //finish();
                        clearList();
                        backToView();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        //Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this team member?").setPositiveButton("Delete teammember", diOnClick).setNegativeButton("No", diOnClick).show();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete:
                openDelete();
                return true;

            default:return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.view_teammember_menu, menu);
        return true;
    }

    private void clearList(){
        teammembers.clear();
        teammembers.addAll(dbHelper.getAllTeamMembers());
        adapter.notifyDataSetChanged();
    }

    private void backToView(){
        Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(backToMain);
    }

}
