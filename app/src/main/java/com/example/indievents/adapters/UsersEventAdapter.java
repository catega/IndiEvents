package com.example.indievents.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indievents.pojo.User;
import com.example.indievents.R;

import java.util.List;

public class UsersEventAdapter extends RecyclerView.Adapter<UsersEventAdapter.ViewHolder> {
    private int layout;
    private Context context;
    List<User> users;

    public UsersEventAdapter(Fragment context, List<User> objects, @LayoutRes int layout) {
        this.context = context.getActivity();
        this.layout = layout;
        this.users = objects;
    }


    @NonNull
    @Override
    public UsersEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);
        return new UsersEventAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersEventAdapter.ViewHolder holder, int position) {
        holder.txtUser.setText(users.get(position).getUsername());
        holder.txtUserName.setText(users.get(position).getNom());
        holder.txtUserStudio.setText(users.get(position).getStudio().getNom());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtUser, txtUserName, txtUserStudio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUser = itemView.findViewById(R.id.txtUserEvent);
            txtUserName = itemView.findViewById(R.id.txtUserNomEvent);
            txtUserStudio = itemView.findViewById(R.id.txtUserStudioEvent);
        }
    }
}
