package ca.gbc.comp3074.scavengerhunt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.MyViewHolder> {

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
    private List<TeamMember> teamsList;
    private DatabaseHelper db;

    public TeamMemberAdapter(Context context, List<TeamMember> teams, DatabaseHelper db) {
        this.context = context;
        this.teamsList = teams;
        this.db = db;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View teamView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(teamView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final TeamMember teammember = teamsList.get(position);
        holder.text.setText(teammember.getName());

        //set the delete action on button
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewTeamMember(teammember);
            }
        });
    }

    private void openViewTeamMember(TeamMember teammember){
        Intent intent = new Intent(context, ViewTeamMemberActivity.class);
        intent.putExtra("id", teammember.getId() + "");
        intent.putExtra("name", teammember.getName() + "");
        intent.putExtra("email", teammember.getEmail() + "");
        intent.putExtra("phone", teammember.getPhone() + "");
        intent.putExtra("sms", teammember.getSms() + "");
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

}
