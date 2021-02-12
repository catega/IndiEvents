package com.example.indievents.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indievents.R;
import com.example.indievents.pojo.Game;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {
    private int layout;
    private Context context;
    List<Game> games;

    public GamesAdapter(Fragment context, List<Game> objects, @LayoutRes int layout) {
        this.context = context.getActivity();
        this.layout = layout;
        this.games = objects;
    }


    @NonNull
    @Override
    public GamesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);
        return new GamesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesAdapter.ViewHolder holder, int position) {
        holder.txtTitul.setText(games.get(position).getTitul());
        holder.txtDescripcio.setText(games.get(position).getDescripcio());
        holder.txtGeneres.setText(games.get(position).getGeneres());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitul, txtDescripcio, txtGeneres;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitul = itemView.findViewById(R.id.txtGameNom);
            txtDescripcio = itemView.findViewById(R.id.txtGameDescripcio);
            txtGeneres = itemView.findViewById(R.id.txtGamesGeneres);
        }
    }
}
