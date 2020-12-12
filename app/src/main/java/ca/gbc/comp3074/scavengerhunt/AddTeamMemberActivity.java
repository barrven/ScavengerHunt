package ca.gbc.comp3074.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddTeamMemberActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper = new DatabaseHelper(this);
    private List<TeamMember> teammembers = new ArrayList<>();
    private TeamMemberAdapter adapter = new TeamMemberAdapter(this, teammembers, dbHelper);
    private Context context;

    private EditText etName;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etSms;
    private EditText etRating;
    private Button btnAddTeammember;

    private String name;
    private String email;
    private String phone;
    private String sms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teammember);

        etName = findViewById(R.id.etAddTeammemberName);
        etEmail = findViewById(R.id.etAddTeammemberEmail);
        etPhone = findViewById(R.id.etAddTeammemberPhone);
        etSms = findViewById(R.id.etAddTeammemberSms);
        btnAddTeammember = findViewById(R.id.btnAddTeammember);

        btnAddTeammember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                phone = etPhone.getText().toString();
                sms = etSms.getText().toString();

                addTeammember(name, email, phone, sms);

                Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backToMain);
    }
        });
    }

    public void addTeammember(String name, String email, String phone, String sms){
        dbHelper.insertItem(name, email, phone, sms);
        teammembers.clear();
        teammembers.addAll(dbHelper.getAllTeamMembers());
        adapter.notifyDataSetChanged();
    }
}