package ca.gbc.comp3074.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ca.gbc.comp3074.scavengerhunt.adapters.PointAdapter;
import ca.gbc.comp3074.scavengerhunt.adapters.TeamMemberAdapter;
import ca.gbc.comp3074.scavengerhunt.entities.Point;
import ca.gbc.comp3074.scavengerhunt.entities.TeamMember;
import ca.gbc.comp3074.scavengerhunt.helpers.DatabaseHelper;

public class UpdatePointActivity extends AppCompatActivity {

    private final DatabaseHelper dbHelper = new DatabaseHelper(this);
    private final List<Point> points = new ArrayList<>();
    private final PointAdapter adapter = new PointAdapter(this, points, dbHelper);

    private EditText[] inputs;
    Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point);

        //initialize inputs
        inputs = new EditText[]{
                findViewById(R.id.etAddPointName),
                findViewById(R.id.etAddPointAddress),
                findViewById(R.id.etAddPointTask),
                findViewById(R.id.etAddPointTags),
                findViewById(R.id.etAddPointRating)
        };

        //initialize teammember object
        point = getPointFromIntent(getIntent());
        inputs[0].setText(point.getName());
        inputs[1].setText(point.getAddress());
        inputs[2].setText(point.getTask());
        inputs[3].setText(point.getTags());
        inputs[4].setText(Double.toString(point.getRating()));

        //change page title (reusing layout file for add team member)
        TextView title = findViewById(R.id.tvAddPointTitle);
        title.setText("Update Point");

        //make the button
        Button btnAddPoint = findViewById(R.id.btnAddPoint);
        btnAddPoint.setText("Update");
        btnAddPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTeammember();
            }

        });
    }

    private Point getPointFromIntent(Intent intent){
        return new Point(
                Integer.parseInt(intent.getStringExtra("id")),
                intent.getStringExtra("name"),
                intent.getStringExtra("address"),
                intent.getStringExtra("task"),
                intent.getStringExtra("tags"),
                Double.parseDouble(intent.getStringExtra("rating"))
        );
    }

    public void updateTeammember(){

        //update the object
        point.setName(inputs[0].getText().toString());
        point.setAddress(inputs[1].getText().toString());
        point.setTask(inputs[2].getText().toString());
        point.setTags(inputs[3].getText().toString());
        point.setRating(Double.parseDouble(inputs[4].getText().toString()));

        //pass the object to the database
        dbHelper.updateItem(point);
        Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();

        adapter.notifyDataSetChanged();
        finish(); //go back to previous activity
    }
}