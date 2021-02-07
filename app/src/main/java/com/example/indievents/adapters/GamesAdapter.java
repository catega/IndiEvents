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
import com.example.indievents.pojo.Game;
import com.example.indievents.pojo.Studio;

import java.util.List;

public class GamesAdapter<T> extends ArrayAdapter<T> {
    private int layout;
    public GamesAdapter(Fragment context, List<T> objects, @LayoutRes int layout) {
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

        TextView nomGame = (TextView) gridView.findViewById(R.id.txtGameNom);
        TextView descGame = (TextView) gridView.findViewById(R.id.txtGameDescripcio);
        TextView generes = (TextView) gridView.findViewById(R.id.txtGamesGeneres);


        Game item = (Game) getItem(position);


        nomGame.setText(item.getTitul());
        descGame.setText(item.getDescripcio());
        generes.setText(item.getGeneres());

        return gridView;
    }
}
