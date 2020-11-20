package ca.gbc.comp3074.scavengerhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewPointActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private String name, address, task, tags;
    private int id;
    private double ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_point);

        Intent intent = getIntent();
        TextView tv = findViewById(R.id.tv_id);

        id = Integer.parseInt(intent.getStringExtra("id"));
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        task = intent.getStringExtra("task");
        tags = intent.getStringExtra("tags");
        ratings = 0.0;
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

    }

    private void openSendWithEmail(){
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing location");
        String text = "Location name: "+name+"\nAddress: "+address+"\nID: "+id+"\nTask: "+task+"\nTags: "+tags+"\nRatings: "+ratings;
        i.putExtra(Intent.EXTRA_TEXT, text);
        if(i.resolveActivity(getPackageManager())!= null){
            startActivity(i);
        }
    }

}
