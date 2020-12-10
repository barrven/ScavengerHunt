package ca.gbc.comp3074.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SearchActivity extends AppCompatActivity {

    Spinner searchBy;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        searchBy = (Spinner) findViewById(R.id.sp_search_by);
        editText = (EditText) findViewById(R.id.editTextSearch);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SearchResultActivity.class);
                String search = editText.getText().toString();
                String strtype = searchBy.getSelectedItem().toString();
                int type;
                if (strtype.equals("Name")){
                    type = SearchResultActivity.SEARCH_TYPE_NAME;
                } else {
                    type = SearchResultActivity.SEARCH_TYPE_TAGS;
                }
                i.putExtra("type", type+"");
                i.putExtra("search", search);
                startActivity(i);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.search_choices, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        searchBy.setAdapter(adapter);




    }
}
