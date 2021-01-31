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

import com.example.indievents.pojo.Studio;
import com.example.indievents.R;

import java.util.List;

public class StudiosAdapter<T> extends ArrayAdapter<T> {
    private int layout;
    public StudiosAdapter(Fragment context, List<T> objects, @LayoutRes int layout) {
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

        TextView nomStudio = (TextView) gridView.findViewById(R.id.txtStudioNom);
        TextView txtDevs = (TextView) gridView.findViewById(R.id.txtStudioDevs);
        TextView numDevs = (TextView) gridView.findViewById(R.id.txtStudioDevsNum);
        TextView txtGames = (TextView) gridView.findViewById(R.id.txtStudioGames);
        TextView numGames = (TextView) gridView.findViewById(R.id.txtStudioGamesNum);

        Studio item = (Studio) getItem(position);


        nomStudio.setText(item.getNom());
        txtDevs.setText("Devs:");
        numDevs.setText(String.valueOf(item.getDevelopers().size()));
        txtGames.setText("Games:");
        numGames.setText(String.valueOf(item.getJocs().size()));

        return gridView;
    }
}
