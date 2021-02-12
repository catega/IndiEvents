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

import com.example.indievents.R;
import com.example.indievents.pojo.Event;
import com.example.indievents.pojo.User;

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
    public void onBindViewHolder(@NonNull EventsAdapterPerfils.ViewHolder holder, int position) {
        holder.txtNom.setText(events.get(position).getNom());
        holder.txtFechaInici.setText(events.get(position).getFechaIniciString());
        holder.txtFechaFinal.setText(events.get(position).getFechaFinalString());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNom, txtFechaInici, txtFechaFinal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNom = itemView.findViewById(R.id.txtEventNomPerfils);
            txtFechaInici = itemView.findViewById(R.id.txtEventFechaIniciPerfils);
            txtFechaFinal = itemView.findViewById(R.id.txtEventFechaFinalPerfils);
        }
    }
}
