package com.example.indievents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.indievents.adapters.GamesAdapter;
import com.example.indievents.adapters.StudiosAdapter;
import com.example.indievents.adapters.UsersEventAdapter;
import com.example.indievents.adapters.UsersStudioAdapter;
import com.example.indievents.db.IndiEventsOperacional;
import com.example.indievents.pojo.Event;
import com.example.indievents.pojo.Studio;
import com.example.indievents.pojo.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudioPerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudioPerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    User user;
    Studio studio;
    IndiEventsOperacional ieo;

    public StudioPerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudioPerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudioPerfilFragment newInstance(String param1, String param2) {
        StudioPerfilFragment fragment = new StudioPerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        user = (User)this.getArguments().getSerializable("user");
        studio = (Studio)this.getArguments().getSerializable("studio");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_studio_perfil, container, false);

        ieo = IndiEventsOperacional.getInstance(getActivity().getApplicationContext());

        TextView txtNom = (TextView)v.findViewById(R.id.txtStudioNom);
        txtNom.setText(studio.getNom());
        TextView txtWeb = (TextView)v.findViewById(R.id.txtStudioWeb);
        TextView txtEmail = (TextView)v.findViewById(R.id.txtStudioEmail);
        txtWeb.setText(studio.getWeb());
        txtEmail.setText(studio.getEmail());

        ListView lstDevs = (ListView)v.findViewById(R.id.lstStudioDevs);
        lstDevs.setAdapter(new UsersStudioAdapter<>(this, studio.getDevelopers(), R.layout.item_user_studio));
        ListView lstGames = (ListView)v.findViewById(R.id.lstStudioGames);
        lstGames.setAdapter(new GamesAdapter<>(this, studio.getJocs(), R.layout.item_games));
        return v;
    }
}