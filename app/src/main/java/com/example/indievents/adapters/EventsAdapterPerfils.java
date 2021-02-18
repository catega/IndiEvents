package com.example.indievents.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indievents.R;
import com.example.indievents.pojo.Event;
import com.example.indievents.pojo.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class EventsAdapterPerfils extends RecyclerView.Adapter<EventsAdapterPerfils.ViewHolder> {
    private int layout;
    private Context context;
    List<Event> events;

    public EventsAdapterPerfils(Fragment context, List<Event> objects, @LayoutRes int layout) {
        this.context = context.getActivity();
        this.layout = layout;
        this.events = objects;
    }


    @NonNull
    @Override
    public EventsAdapterPerfils.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);
        return new EventsAdapterPerfils.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapterPerfils.ViewHolder holder, final int position) {
        holder.txtNom.setText(events.get(position).getNom());
        holder.txtFechaInici.setText(events.get(position).getFechaIniciString());
        holder.txtFechaFinal.setText(events.get(position).getFechaFinalString());

        holder.eventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.event_dialog, null);

                TextView txtEventDialogNom = (TextView)dialogView.findViewById(R.id.txtEventDialogNom);
                TextView txtEventDialogDesc = (TextView)dialogView.findViewById(R.id.txtEventDialogDesc);
                TextView txtEventDialogWeb = (TextView)dialogView.findViewById(R.id.txtEventDialogWeb);
                TextView txtEventDialogFechaIniciLabel = (TextView)dialogView.findViewById(R.id.txtEventDialogFechaIniciLabel);
                TextView txtEventDialogFechaInici = (TextView)dialogView.findViewById(R.id.txtEventDialogFechaInici);
                TextView txtEventDialogFechaFinalLabel = (TextView)dialogView.findViewById(R.id.txtEventDialogFechaFinalLabel);
                TextView txtEventDialogFechaFinal = (TextView)dialogView.findViewById(R.id.txtEventDialogFechaFinal);

                txtEventDialogNom.setText(events.get(position).getNom());
                txtEventDialogDesc.setText(events.get(position).getDescripcio());
                txtEventDialogWeb.setText(events.get(position).getWeb());
                txtEventDialogFechaIniciLabel.setText("Fecha inicio:");
                txtEventDialogFechaInici.setText(events.get(position).getFechaIniciString());
                txtEventDialogFechaFinalLabel.setText("Fecha final:");
                txtEventDialogFechaFinal.setText(events.get(position).getFechaFinalString());


                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNom, txtFechaInici, txtFechaFinal;
        ConstraintLayout eventLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNom = itemView.findViewById(R.id.txtEventNomPerfils);
            txtFechaInici = itemView.findViewById(R.id.txtEventFechaIniciPerfils);
            txtFechaFinal = itemView.findViewById(R.id.txtEventFechaFinalPerfils);
            eventLayout = itemView.findViewById(R.id.eventPerfilLayout);
        }
    }
}
