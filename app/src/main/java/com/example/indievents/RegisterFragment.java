package com.example.indievents;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.indievents.db.IndiEventsOperacional;
import com.example.indievents.pojo.User;

import java.text.ParseException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText edtUsername;
    EditText edtNom;
    EditText edtEmail;
    EditText edtPass;
    CheckBox chkDev;

    IndiEventsOperacional ieo;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        ieo = IndiEventsOperacional.getInstance(this.getActivity().getApplicationContext());

        edtNom = (EditText)v.findViewById(R.id.edtName);
        edtUsername = (EditText)v.findViewById(R.id.edtUsername);
        edtEmail = (EditText)v.findViewById(R.id.edtEmail);
        edtPass = (EditText)v.findViewById(R.id.edtPassword);
        chkDev = (CheckBox)v.findViewById(R.id.chkDeveloper);

        Button btnRegister = (Button)v.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User nuevoUser = new User();

                nuevoUser.setUsername(edtUsername.getText().toString());
                nuevoUser.setNom(edtNom.getText().toString());
                nuevoUser.setPassword(edtPass.getText().toString());
                nuevoUser.setEmail(edtEmail.getText().toString());
                nuevoUser.setDev(chkDev.isChecked());

                try {
                    if (ieo.comprobarRegistro(nuevoUser) == null){
                        ieo.registrarUsuario(nuevoUser);

                        Intent intent = new Intent(getActivity(), PrincipalActivity.class);
                        intent.putExtra("user", nuevoUser);
                        startActivity(intent);
                    }else
                        Toast.makeText(getActivity().getApplicationContext(), "El usuario ya existe", Toast.LENGTH_LONG).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        return v;
    }
}