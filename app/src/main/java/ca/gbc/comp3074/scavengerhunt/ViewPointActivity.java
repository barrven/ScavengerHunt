package ca.gbc.comp3074.scavengerhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewPointActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private String name, address, task, tags;
    private int id;
    private double ratings;
    private Point point;
    private PointAdapter adapter;

    //for refreshing list when deleting
    private List<Point> points = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_point);

        Intent intent = getIntent();
        TextView tv = findViewById(R.id.tv_id);

        // Getting Object from Point Adapter
        id = Integer.parseInt(intent.getStringExtra("id"));
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        task = intent.getStringExtra("task");
        tags = intent.getStringExtra("tags");
        ratings = 0.0;
        if(intent.getStringExtra("ratings") != null && intent.getStringExtra("ratings").length() > 0){
            ratings = Double.parseDouble(intent.getStringExtra("ratings"));
        }
        point = new Point(id,name,address,task,tags,ratings);
        tv.setText(point.getAddress() + "");

        //initialize these for use with clearing list of items on delete
        dbHelper = new DatabaseHelper(this);
        adapter = new PointAdapter(this, points, dbHelper);
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
                return true;
            case R.id.menu_share_w_email:
                openSendWithEmail();
                return true;

            default:return super.onOptionsItemSelected(item);
        }
    }

    private void openMenuEdit(){

    }

    private void openDelete(){
        DialogInterface.OnClickListener diOnClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();
                        dbHelper.deleteItem(point);
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
        builder.setMessage("Are you sure you want to delete this point?").setPositiveButton("Delete point", diOnClick).setNegativeButton("No", diOnClick).show();
    }

    private void clearList(){
        points.clear();
        points.addAll(dbHelper.getAllItems());
        adapter.notifyDataSetChanged();
    }

    private void backToView(){
        Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(backToMain);
    }

    private void openShowLocation(){
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("id", point.getId() + "");
        intent.putExtra("name", point.getName() + "");
        intent.putExtra("address", point.getAddress() + "");
        intent.putExtra("task", point.getTask() + "");
        intent.putExtra("tags", point.getTags() + "");
        intent.putExtra("rating", point.getRating() + "");
        startActivity(intent);
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
