package ca.gbc.comp3074.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewPointActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_point);

        Intent intent = getIntent();
        TextView tv = findViewById(R.id.tv_id);

        //this is currently null because of the way i'm launching this activity (from the button in the PointAdapter class)
        String id = "String Extra: " + intent.getStringExtra("id");
        tv.setText(id);




    }
}
