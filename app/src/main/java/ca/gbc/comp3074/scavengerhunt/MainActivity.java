package ca.gbc.comp3074.scavengerhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_point:
                openAddPoint();
                return true;
            case R.id.menu_search:
                openSearch();
                return true;
            case R.id.menu_about:
                openAboutUs();

            default:return super.onOptionsItemSelected(item);
        }
    }

    private void openAddPoint(){
        Intent start = new Intent(getApplicationContext(), AddPointActivity.class);
        startActivity(start);
    }

    private void openSearch(){
        Intent start = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(start);
    }

    private void openAboutUs(){
        Intent start = new Intent(getApplicationContext(), AboutUsActivity.class);
        startActivity(start);
    }

}
