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

import com.example.indievents.R;
import com.example.indievents.pojo.Event;

import java.text.SimpleDateFormat;
import java.util.List;

public class EventsAdapterPerfils<T> extends ArrayAdapter<T> {
    private int layout;
    public EventsAdapterPerfils(Fragment context, List<T> objects, @LayoutRes int layout) {
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

        TextView nomEvent = (TextView) gridView.findViewById(R.id.txtEventNomPerfils);
        TextView txtFechaInici = (TextView) gridView.findViewById(R.id.txtEventFechaIniciPerfils);
        TextView txtFechaFinal = (TextView) gridView.findViewById(R.id.txtEventFechaFinalPerfils);

        Event item = (Event) getItem(position);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        nomEvent.setText(item.getNom());
        txtFechaInici.setText(String.valueOf(format.format(item.getFechaInici())));
        txtFechaFinal.setText(String.valueOf(format.format(item.getFechaFinal())));

        return gridView;
    }
}
