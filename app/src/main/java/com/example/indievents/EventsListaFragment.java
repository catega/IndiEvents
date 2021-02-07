package com.example.indievents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.indievents.db.IndiEventsOperacional;
import com.example.indievents.pojo.Event;
import com.example.indievents.pojo.User;
import com.example.indievents.adapters.EventsAdapter;

import java.text.ParseException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventsListaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsListaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    User user;
    IndiEventsOperacional ieo;

    public EventsListaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsListaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsListaFragment newInstance(String param1, String param2) {
        EventsListaFragment fragment = new EventsListaFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_events_lista, container, false);

        ieo = IndiEventsOperacional.getInstance(getActivity().getApplicationContext());

        final Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);


        ListView listaEvents = (ListView)v.findViewById(R.id.lstEvents);

        try {
            listaEvents.setAdapter(new EventsAdapter(this, ieo.getEvents(), R.layout.item_events));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        listaEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bundle.putSerializable("event", (Event)parent.getItemAtPosition(position));
                Fragment fragment = new EventsPerfilFragment();
                fragment.setArguments(bundle);

                ((EventsActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.events_container, fragment).commit();
            }
        });

        return v;
    }
}