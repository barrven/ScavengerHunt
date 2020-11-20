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

public class AddPointActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper = new DatabaseHelper(this);
    private List<Point> points = new ArrayList<>();
    private PointAdapter adapter = new PointAdapter(this, points, dbHelper);
    private Context context;

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

                addPoint(name, address, task, tags, rating);

                Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backToMain);

        //Something else I was trying --Kevin
//
//                Intent addPointData = new Intent(context, MainActivity.class);
//                addPointData.putExtra("name", name);
//                addPointData.putExtra("address", address);
//                addPointData.putExtra("task", task);
//                addPointData.putExtra("tags", tags);
//                addPointData.putExtra("rating", rating);
//                setResult(RESULT_OK, addPointData);
//                finish();
//                context.startActivity(addPointData);

//                MainActivity.addPoint(name, address, task, tags, rating);
            }
        });
    }

    public void addPoint(String name, String address, String task, String tags, double rating){
        dbHelper.insertItem(name, address, task, tags, rating);
//        Toast.makeText(this, "Inserted \"" + dbHelper.getItem(itemDbId).getText() +"\"", Toast.LENGTH_LONG).show();
        points.clear();
        points.addAll(dbHelper.getAllItems());
        adapter.notifyDataSetChanged();
    }
}