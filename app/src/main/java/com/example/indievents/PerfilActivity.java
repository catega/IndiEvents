package com.example.indievents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.indievents.adapters.EventsAdapterPerfils;
import com.example.indievents.adapters.EventsAdapterPerfilsActivity;
import com.example.indievents.adapters.StudiosAdapter;
import com.example.indievents.pojo.User;
import com.google.android.material.navigation.NavigationView;

public class PerfilActivity extends AppCompatActivity {
    User user;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        user = (User)getIntent().getSerializableExtra("user");

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarPrincipal);
        setSupportActionBar(toolbar);

        TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtTitle.setText("Perfil");

        nav = (NavigationView)findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View v = nav.getHeaderView(0);

        TextView txtUser = (TextView)v.findViewById(R.id.txtUserMenu);
        TextView txtStudioV = (TextView)v.findViewById(R.id.txtStudioMenu);

        txtUser.setText(user.getUsername());

        if (user.isDev() && user.getStudio() != null)
            txtStudioV.setText(user.getStudio().getNom());
        else if (user.isDev() && user.getStudio() == null)
            txtStudioV.setText("Independiente");
        else
            txtStudioV.setText("IndiEvents");

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent = null;

                switch (menuItem.getItemId()){
                    case R.id.menuEventos:
                        intent = new Intent(getBaseContext(), EventsActivity.class);
                        break;
                    case R.id.menuStudios:
                        intent = new Intent(getBaseContext(), StudiosActivity.class);
                        break;
                    case R.id.menuPerfil:
                        intent = new Intent(getBaseContext(), PerfilActivity.class);
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                if (intent != null){
                    intent.putExtra("user", user);
                    startActivity(intent);
                }

                return true;
            }
        });

        TextView txtRango = (TextView)findViewById(R.id.txtRango);
        TextView txtUsername = (TextView)findViewById(R.id.txtUsername);
        TextView txtName = (TextView)findViewById(R.id.txtNom);
        TextView txtEmail = (TextView)findViewById(R.id.txtEmail);
        TextView txtStudio = (TextView)findViewById(R.id.txtStudioNom);

        RecyclerView listaEventos = (RecyclerView)findViewById(R.id.lstEventosUser);
        listaEventos.setAdapter(new EventsAdapterPerfilsActivity(this, user.getEventosEnSolitari(), R.layout.item_events_perfils));
        listaEventos.setLayoutManager(new LinearLayoutManager(this));

        if (user.isDev()){
            txtRango.setText("Dev");
            if (user.getStudio() != null)
                txtStudio.setText(user.getStudio().getNom());
            else
                txtStudio.setText("Independiente");
        } else if(user.isOrganitzador()){
            txtRango.setText("Organizador");
            txtStudio.setText("IndiEvents");
        }

        txtUsername.setText(user.getUsername());
        txtName.setText("'" + user.getNom() + "'");
        txtEmail.setText("Contacto: " + user.getEmail());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barra_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.menuEventos:
                //intent = new Intent(PerfilActivity.this, EventosActivity.class);
                break;
            case R.id.menuStudios:
                intent = new Intent(PerfilActivity.this, StudiosActivity.class);
                break;
            case R.id.menuPerfil:
                intent = new Intent(PerfilActivity.this, PerfilActivity.class);
                break;
        }

        if(intent != null)
            startActivity(intent);

        return true;
    }
}