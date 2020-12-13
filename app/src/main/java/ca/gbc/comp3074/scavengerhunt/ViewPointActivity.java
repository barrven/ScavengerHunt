package ca.gbc.comp3074.scavengerhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewPointActivity extends AppCompatActivity {

    private int id;
    private String name, address, task, tags;
    private double rating;

    private Point point;
    private PointAdapter adapter;
    private TextView[] outputs;

    //for deleting point and refreshing list
    private List<Point> points = new ArrayList<>();
    private DatabaseHelper dbHelper;

    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_point);

        // Receive info about object from previous activity
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        task = intent.getStringExtra("task");
        tags = intent.getStringExtra("tags");
        rating = 0;
        if(intent.getStringExtra("rating") != null
                && intent.getStringExtra("rating").length() > 0){
            rating = Double.parseDouble(intent.getStringExtra("rating"));
        }

        point = new Point(id,name,address,task,tags, rating);

        //initialize these for use with clearing list of items on delete
        dbHelper = new DatabaseHelper(this);
        adapter = new PointAdapter(this, points, dbHelper);

        //Get the text views for displaying point details
        outputs = new TextView[]{
                findViewById(R.id.tv_point_title),
                findViewById(R.id.tv_address),
                findViewById(R.id.tv_task),
                findViewById(R.id.tv_tags),
                findViewById(R.id.tv_current_rating)
        };

        outputs[0].setText(name + " Details");
        outputs[1].setText(address);
        outputs[2].setText(task);
        outputs[3].setText(tags);
        outputs[4].setText(Double.toString(rating));

        //manipulate the rating bar
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating((float) rating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                point.setRating(rating);
                dbHelper.updateItem(point);
                outputs[4].setText(Double.toString(point.getRating()));
            }
        });
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
            case R.id.menu_share_w_twitter:
                openSendWithTwitter();
                return true;

            default:return super.onOptionsItemSelected(item);
        }
    }

    private void openSendWithFacebook() {

    }

    private void openSendWithTwitter() {
        String twuri = "http://twitter.com/intent/tweet?text=Scavenger hunt point: "+ Uri.encode(name +", "+address+", "+task, "utf-8");
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(twuri));
        startActivity(i);
    }

    private void openMenuEdit(){
        // todo: add an edit point activity
    }

    private void openDelete(){
        DialogInterface.OnClickListener diOnClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();
                        dbHelper.deleteItem(point);
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
        String text = "Location name: "+name+"\nAddress: "+address+"\nID: "+id+"\nTask: "+task+"\nTags: "+tags+"\nRatings: "+ rating;
        i.putExtra(Intent.EXTRA_TEXT, text);
        if(i.resolveActivity(getPackageManager())!= null){
            startActivity(i);
        }
    }

}
