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

import com.example.indievents.pojo.Event;
import com.example.indievents.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class EventsAdapter<T> extends ArrayAdapter<T> {
    private int layout;
    public EventsAdapter(Fragment context, List<T> objects, @LayoutRes int layout) {
        super(context.getActivity(), 0, objects);
        this.layout = layout;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView = convertView;

        if (null == convertView) {
            gridView = inflater.inflate(layout, parent, false);
        }

        TextView nomEvent = (TextView) gridView.findViewById(R.id.txtEventNom);
        TextView txtPreu = (TextView) gridView.findViewById(R.id.txtEventPrecio);
        TextView txtPreuNum = (TextView) gridView.findViewById(R.id.txtEventPrecioNum);
        TextView txtFecha = (TextView) gridView.findViewById(R.id.txtEventFecha);
        TextView txtStudios = (TextView) gridView.findViewById(R.id.txtEventStudios);
        TextView txtStudiosNum = (TextView) gridView.findViewById(R.id.txtEventStudiosNum);
        TextView txtDevs = (TextView) gridView.findViewById(R.id.txtEventDevs);
        TextView txtDevsNum = (TextView) gridView.findViewById(R.id.txtEventDevsNum);

        Event item = (Event) getItem(position);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");



        nomEvent.setText(item.getNom());
        txtPreu.setText("Entrada:");
        txtPreuNum.setText(String.valueOf(item.getPreu()) + "€");
        txtFecha.setText(String.valueOf(format.format(item.getFechaInici())) + " - " + String.valueOf(format.format(item.getFechaFinal())));
        txtStudios.setText("Studios:");
        txtStudiosNum.setText(String.valueOf(item.getStudiosParticipants().size()));
        txtDevs.setText("Devs:");
        txtDevsNum.setText(String.valueOf(item.getDevelopersParticipants().size()));

        return gridView;
    }
}
