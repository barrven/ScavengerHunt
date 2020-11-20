package ca.gbc.comp3074.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPointActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAddress;
    private EditText etTask;
    private EditText etTags;
    private EditText etRating;
    private Button btnAddPoint;

    private String name;
    private String address;
    private String task;
    private String tags;
    private String tempRating;
    private double rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point);

        etName = findViewById(R.id.etAddPointName);
        etAddress = findViewById(R.id.etAddPointAddress);
        etTask = findViewById(R.id.etAddPointTask);
        etTags = findViewById(R.id.etAddPointTags);
        etRating = findViewById(R.id.etAddPointRating);
        btnAddPoint = findViewById(R.id.btnAddPoint);

        btnAddPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                address = etAddress.getText().toString();
                task = etTask.getText().toString();
                tags = etTags.getText().toString();
                tempRating = etRating.getText().toString();
                rating = Double.parseDouble(tempRating);
            }
        });
    }
}