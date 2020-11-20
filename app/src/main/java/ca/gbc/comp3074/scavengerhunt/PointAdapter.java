package ca.gbc.comp3074.scavengerhunt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PointAdapter extends RecyclerView.Adapter<PointAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public Button button;

        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.tv_item_text);
            button = view.findViewById(R.id.btn_view);
        }
    }

    private Context context;
    private List<Point> itemsList;
    private DatabaseHelper db;

    public PointAdapter(Context context, List<Point> items, DatabaseHelper db) {
        this.context = context;
        this.itemsList = items;
        this.db = db;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Point point = itemsList.get(position);
        holder.text.setText(point.getName());

        //set the delete action on button
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewPoint(point);
                //Toast.makeText(context, "Clicked \"" + point.getName() + "\"", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openViewPoint(Point point){
        Intent intent = new Intent(context, ViewPointActivity.class);
        intent.putExtra("id", point.getId() + "");
        intent.putExtra("name", point.getName() + "");
        intent.putExtra("address", point.getAddress() + "");
        intent.putExtra("task", point.getTask() + "");
        intent.putExtra("tags", point.getTags() + "");
        intent.putExtra("rating", point.getRating() + "");
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

}
