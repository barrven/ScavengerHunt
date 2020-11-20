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

        int id = Integer.parseInt(intent.getStringExtra("id"));
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String task = intent.getStringExtra("task");
        String tags = intent.getStringExtra("tags");
        double ratings = 0.0;
        if(intent.getStringExtra("ratings") != null && intent.getStringExtra("ratings").length() > 0){ ratings = Double.parseDouble(intent.getStringExtra("ratings")); }
        Point point = new Point(id,name,address,task,tags,ratings);
        tv.setText(point.getAddress() + "");




    }
}
