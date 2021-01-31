package com.example.indievents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    boolean cambiar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.container_login, new LoginFragment()).commit();

        final Button btnRegister = (Button)findViewById(R.id.btnRegisterLogin);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cambiar) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_login, new RegisterFragment()).commit();
                    btnRegister.setText("Cancel");
                }
                else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_login, new LoginFragment()).commit();
                    btnRegister.setText("Register");
                }

                cambiar = !cambiar;
            }
        });
    }



}