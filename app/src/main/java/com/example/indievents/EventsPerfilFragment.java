package com.example.indievents;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.indievents.adapters.StudiosAdapter;
import com.example.indievents.adapters.UsersEventAdapter;
import com.example.indievents.db.IndiEventsOperacional;
import com.example.indievents.pojo.Event;
import com.example.indievents.pojo.User;

import java.text.ParseException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventsPerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsPerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    User user;
    Event event;
    IndiEventsOperacional ieo;

    public EventsPerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsPerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsPerfilFragment newInstance(String param1, String param2) {
        EventsPerfilFragment fragment = new EventsPerfilFragment();
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
        event = (Event)this.getArguments().getSerializable("event");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_events_perfil, container, false);

        ieo = IndiEventsOperacional.getInstance(getActivity().getApplicationContext());

        TextView txtNom = (TextView)v.findViewById(R.id.txtEventNom);
        txtNom.setText(event.getNom());
        TextView txtDescripcio = (TextView)v.findViewById(R.id.txtEventDescripcio);
        txtDescripcio.setText(event.getDescripcio());
        TextView txtFechaInici = (TextView)v.findViewById(R.id.txtEventFechaInicio);
        TextView txtFechaFinal = (TextView)v.findViewById(R.id.txtEventFechaFinal);
        txtFechaInici.setText(event.getFechaIniciString());
        txtFechaFinal.setText(event.getFechaFinalString());

        Button btnUnirse = (Button)v.findViewById(R.id.btnUnirseUser);
        btnUnirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ieo.apuntarUserEvent(user, event);

                Intent intent = new Intent(getActivity().getApplicationContext(), PerfilActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        try {
            if (ieo.comprobarRegistroUserEvento(user, event))
                btnUnirse.setVisibility(View.GONE);
            else if (!user.isDev())
                btnUnirse.setVisibility(View.GONE);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Button btnUnirStudio = (Button)v.findViewById(R.id.btnUnirseStudio);
        btnUnirStudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ieo.apuntarStudioEvent(user, event);

                Intent intent = new Intent(getActivity().getApplicationContext(), PerfilActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        try {
            if (user.isDev()) {
                if (ieo.comprobarRegistroStudioEvento(user, event) || (user.getStudio().getStudioAdmin() != user.getId()))
                    btnUnirStudio.setVisibility(View.GONE);
            }else
                btnUnirStudio.setVisibility(View.GONE);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*RecyclerView lstStudios = (RecyclerView)v.findViewById(R.id.lstEventStudios);
        lstStudios.setAdapter(new StudiosAdapter(this, event.getStudiosParticipants(), R.layout.item_studio));
        lstStudios.setLayoutManager(new LinearLayoutManager(this.getActivity()));*/

        RecyclerView lstUsers = (RecyclerView)v.findViewById(R.id.lstEventDevs);
        lstUsers.setAdapter(new UsersEventAdapter(this, event.getDevelopersParticipants(), R.layout.item_user_event));
        lstUsers.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return v;
    }
}