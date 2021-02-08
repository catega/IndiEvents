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
import com.example.indievents.pojo.Studio;
import com.example.indievents.pojo.User;
import com.example.indievents.adapters.StudiosAdapter;

import java.text.ParseException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudiosListaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudiosListaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    User user;
    IndiEventsOperacional ieo;

    public StudiosListaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudiosListaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudiosListaFragment newInstance(String param1, String param2) {
        StudiosListaFragment fragment = new StudiosListaFragment();
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
        View v = inflater.inflate(R.layout.fragment_studios_lista, container, false);

        ieo = IndiEventsOperacional.getInstance(getActivity().getApplicationContext());

        final Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);

        ListView listaStudios = (ListView)v.findViewById(R.id.lstStudios);

        try {
            listaStudios.setAdapter(new StudiosAdapter(this, ieo.getStudios(), R.layout.item_studio));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        listaStudios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bundle.putSerializable("studio", (Studio)parent.getItemAtPosition(position));
                Fragment fragment = new StudioPerfilFragment();
                fragment.setArguments(bundle);

                ((StudiosActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.studios_container, fragment).commit();
            }
        });

        return v;
    }
}