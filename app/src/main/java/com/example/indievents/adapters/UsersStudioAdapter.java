package com.example.indievents.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtUser.setText(users.get(position).getUsername());
        holder.txtUserName.setText(users.get(position).getNom());

        holder.userLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.user_dialog, null);

                TextView txtUserDialogUsername = (TextView)dialogView.findViewById(R.id.txtUserDialogUsername);
                TextView txtUserDialogNom = (TextView)dialogView.findViewById(R.id.txtUserDialogNom);
                TextView txtUserDialogEmail = (TextView)dialogView.findViewById(R.id.txtUserDialogEmail);
                TextView txtUserDialogStudio = (TextView)dialogView.findViewById(R.id.txtUserDialogStudio);
                ImageView imgUser = (ImageView)dialogView.findViewById(R.id.imgUserDialog);

                txtUserDialogUsername.setText(users.get(position).getUsername());
                txtUserDialogNom.setText(users.get(position).getNom());
                txtUserDialogEmail.setText(users.get(position).getEmail());
                imgUser.setImageResource(R.drawable.ic_baseline_account_circle_24);

                if (users.get(position).getStudio() == null)
                    txtUserDialogStudio.setText("Independiente");
                else
                    txtUserDialogStudio.setText(users.get(position).getStudio().getNom());

                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtUser, txtUserName;
        ConstraintLayout userLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUser = itemView.findViewById(R.id.txtUserStudio);
            txtUserName = itemView.findViewById(R.id.txtUserNomStudio);

            userLayout = itemView.findViewById(R.id.userStudioLayout);
        }
    }
}
