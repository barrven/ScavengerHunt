package ca.gbc.comp3074.scavengerhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewPointActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_point);

        Intent intent = getIntent();
        TextView tv = findViewById(R.id.tv_id);

        // Getting Object from Point Adapter
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




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.view_point_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_edit:
                openMenuEdit();
                return true;
            case R.id.menu_delete:
                openDelete();
                return true;
            case R.id.menu_show_location:
                openShowLocation();
            case R.id.menu_share_w_email:
                openSendWithEmail();

            default:return super.onOptionsItemSelected(item);
        }
    }

    private void openMenuEdit(){

    }

    private void openDelete(){

    }

    private void openShowLocation(){
        Intent start = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(start);
    }

    private void openSendWithEmail(){

    }

}
