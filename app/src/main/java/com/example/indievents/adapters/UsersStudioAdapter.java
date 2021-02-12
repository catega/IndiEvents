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

public class UsersStudioAdapter extends RecyclerView.Adapter<UsersStudioAdapter.ViewHolder> {
    private int layout;
    private Context context;
    List<User> users;

    public UsersStudioAdapter(Fragment context, List<User> objects, @LayoutRes int layout) {
        this.context = context.getActivity();
        this.layout = layout;
        this.users = objects;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtUser.setText(users.get(position).getUsername());
        holder.txtUserName.setText(users.get(position).getNom());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtUser, txtUserName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUser = itemView.findViewById(R.id.txtUserStudio);
            txtUserName = itemView.findViewById(R.id.txtUserNomStudio);
        }
    }
}
