package com.example.indievents.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indievents.R;
import com.example.indievents.db.IndiEventsOperacional;
import com.example.indievents.pojo.Studio;

import java.text.ParseException;
import java.util.List;

public class StudiosPerfilAdapter extends RecyclerView.Adapter<StudiosPerfilAdapter.ViewHolder>{
    private int layout;
    private Context context;
    List<Studio> studios;

    public StudiosPerfilAdapter(Fragment context, List<Studio> objects, @LayoutRes int layout) {
        this.context = context.getActivity();
        this.layout = layout;
        this.studios = objects;
    }


    @NonNull
    @Override
    public StudiosPerfilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);
        return new StudiosPerfilAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudiosPerfilAdapter.ViewHolder holder, final int position) {
        holder.txtNom.setText(studios.get(position).getNom());
        holder.txtDevsNum.setText(String.valueOf(studios.get(position).getDevelopers().size()));
        holder.txtGamesNum.setText(String.valueOf(studios.get(position).getJocs().size()));
        holder.txtLabelDevs.setText("Devs: ");
        holder.txtLabelGames.setText("Games: ");

        final IndiEventsOperacional ieo = IndiEventsOperacional.getInstance(context);

        holder.studioLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.studio_dialog, null);

                TextView txtStudioDialogNom = (TextView)dialogView.findViewById(R.id.txtStudioDialogNom);
                TextView txtStudioDialogWeb = (TextView)dialogView.findViewById(R.id.txtStudioDialogWeb);
                TextView txtStudioDialogEmail = (TextView)dialogView.findViewById(R.id.txtStudioDialogEmail);
                TextView txtOwnerLabel = (TextView)dialogView.findViewById(R.id.txtOwnerLabel);
                TextView txtUserOwner = (TextView)dialogView.findViewById(R.id.txtUserOwner);
                TextView txtDevsLabel = (TextView)dialogView.findViewById(R.id.txtDevsLabel);
                TextView txtStudioDialogDevsNum = (TextView)dialogView.findViewById(R.id.txtStudioDialogDevsNum);
                TextView txtGamesLabel = (TextView)dialogView.findViewById(R.id.txtGamesLabel);
                TextView txtStudioDialogGamesNum = (TextView)dialogView.findViewById(R.id.txtStudioDialogGamesNum);
                TextView txtStudioDialogOwnerNom = (TextView)dialogView.findViewById(R.id.txtStudioDialogOwnerNom);

                txtStudioDialogNom.setText(studios.get(position).getNom());
                txtStudioDialogWeb.setText(studios.get(position).getWeb());
                txtStudioDialogEmail.setText(studios.get(position).getEmail());
                txtOwnerLabel.setText("Creador del studio:");
                try {
                    txtUserOwner.setText(ieo.buscarUser(studios.get(position).getStudioAdmin()).getUsername());
                    txtStudioDialogOwnerNom.setText(ieo.buscarUser(studios.get(position).getStudioAdmin()).getNom());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                txtDevsLabel.setText("Miembros:");
                txtStudioDialogDevsNum.setText(String.valueOf(studios.get(position).getDevelopers().size()));
                txtGamesLabel.setText("Juegos:");
                txtStudioDialogGamesNum.setText(String.valueOf(studios.get(position).getJocs().size()));


                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNom, txtDevsNum, txtGamesNum, txtLabelDevs, txtLabelGames;
        ConstraintLayout studioLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNom = itemView.findViewById(R.id.txtStudioNom);
            txtDevsNum = itemView.findViewById(R.id.txtStudioDevsNum);
            txtGamesNum = itemView.findViewById(R.id.txtStudioGamesNum);
            txtLabelDevs = itemView.findViewById(R.id.txtStudioDevs);
            txtLabelGames = itemView.findViewById(R.id.txtStudioGames);

            studioLayout = itemView.findViewById(R.id.studioPerfilLayout);
        }
    }
}
