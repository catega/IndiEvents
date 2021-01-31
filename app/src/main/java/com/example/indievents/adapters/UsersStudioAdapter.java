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

public class UsersStudioAdapter<T> extends ArrayAdapter<T> {
    private int layout;
    public UsersStudioAdapter(Fragment context, List<T> objects, @LayoutRes int layout) {
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

        TextView txtUser = (TextView) gridView.findViewById(R.id.txtUserStudio);
        TextView txtUserNom = (TextView) gridView.findViewById(R.id.txtUserNomStudio);

        User item = (User) getItem(position);


        txtUser.setText(item.getUsername());
        txtUserNom.setText("'" + item.getNom() + "'");

        return gridView;
    }
}
