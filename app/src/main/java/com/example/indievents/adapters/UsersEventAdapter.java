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

import com.example.indievents.pojo.User;
import com.example.indievents.R;

import java.util.List;

public class UsersEventAdapter<T> extends ArrayAdapter<T> {
    private int layout;
    public UsersEventAdapter(Fragment context, List<T> objects, @LayoutRes int layout) {
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

        TextView txtUser = (TextView) gridView.findViewById(R.id.txtUserEvent);
        TextView txtUserNom = (TextView) gridView.findViewById(R.id.txtUserNomEvent);
        TextView txtUserStudio = (TextView) gridView.findViewById(R.id.txtUserStudioEvent);

        User item = (User) getItem(position);


        txtUser.setText(item.getUsername());
        txtUserNom.setText("'" + item.getNom() + "'");
        txtUserStudio.setText(item.getStudio().getNom());

        return gridView;
    }
}
