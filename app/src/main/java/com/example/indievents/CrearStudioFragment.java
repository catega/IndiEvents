package com.example.indievents;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.indievents.db.IndiEventsOperacional;
import com.example.indievents.pojo.Studio;
import com.example.indievents.pojo.User;

import java.text.ParseException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrearStudioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearStudioFragment extends Fragment {

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

    public CrearStudioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrearStudioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CrearStudioFragment newInstance(String param1, String param2) {
        CrearStudioFragment fragment = new CrearStudioFragment();
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
        View v = inflater.inflate(R.layout.fragment_crear_studio, container, false);
        ieo = IndiEventsOperacional.getInstance(getActivity().getApplicationContext());

        final Bundle bundle = new Bundle();

        final EditText edtNom = (EditText)v.findViewById(R.id.edtStudioNom);
        final EditText edtEmail = (EditText)v.findViewById(R.id.edtStudioEmail);
        final EditText edtWeb = (EditText)v.findViewById(R.id.edtStudioWeb);
        Button btnRegistro = (Button)v.findViewById(R.id.btnRegistrarStudio);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studio = new Studio();
                studio.setNom(edtNom.getText().toString());
                studio.setEmail(edtEmail.getText().toString());
                studio.setWeb(edtWeb.getText().toString());

                try {
                    ieo.registrarStudio(studio, user);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    bundle.putSerializable("user", ieo.userUpdated(user));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Fragment fragment = new StudiosListaFragment();
                fragment.setArguments(bundle);

                ((StudiosActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.studios_container, fragment).commit();
            }
        });


        return v;
    }
}