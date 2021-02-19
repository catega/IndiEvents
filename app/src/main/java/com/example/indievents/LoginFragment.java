package com.example.indievents;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.indievents.db.IndiEventsOperacional;
import com.example.indievents.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        mAuth = FirebaseAuth.getInstance();

        final EditText edtUsername = (EditText)v.findViewById(R.id.edtUsername);
        final EditText edtPass = (EditText)v.findViewById(R.id.edtPassword);
        final ProgressBar progressBar = (ProgressBar)v.findViewById(R.id.progressbarLogin);

        final IndiEventsOperacional ieo = IndiEventsOperacional.getInstance(this.getActivity().getApplicationContext());

        Button btnLogin = (Button)v.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUsername.getText().toString().isEmpty() || edtPass.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(edtUsername.getText().toString(), edtPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                User u = new User();
                                u.setEmail(edtUsername.getText().toString());
                                u.setPassword(edtPass.getText().toString());
                                try {
                                    u = ieo.login(u);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if(u == null){
                                    Toast.makeText(getActivity(), "Los datos no coinciden", Toast.LENGTH_LONG).show();
                                }else{
                                    Intent intent = new Intent(getActivity(), PrincipalActivity.class);
                                    intent.putExtra("user", u);
                                    startActivity(intent);
                                }
                            }else
                                Toast.makeText(getActivity(), "No se ha podido logear", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        return v;
    }
}