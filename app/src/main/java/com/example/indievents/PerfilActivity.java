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
import com.example.indievents.db.IndiEventsOperacional;
import com.example.indievents.pojo.User;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;

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
        final IndiEventsOperacional ieo = IndiEventsOperacional.getInstance(this);

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
        TextView txtEvents = (TextView)findViewById(R.id.txtEventsUserLabel);


        txtStudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                bundle.putInt("fragment", 1);
                try {
                    bundle.putSerializable("studio", ieo.studioPerfil(user.getStudio()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(PerfilActivity.this, StudiosActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        RecyclerView listaEventos = (RecyclerView)findViewById(R.id.lstEventosUser);

        if (user.isDev()){
            txtRango.setText("Dev");
            listaEventos.setAdapter(new EventsAdapterPerfilsActivity(this, user.getEventosEnSolitari(), R.layout.item_events_perfils));
            if (user.getStudio() != null)
                txtStudio.setText(user.getStudio().getNom());
            else
                txtStudio.setText("Independiente");
        } else if(user.isOrganitzador()){
            try {
                listaEventos.setAdapter(new EventsAdapterPerfilsActivity(this, ieo.eventosCreats(user), R.layout.item_events_perfils));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtRango.setText("Organizador");
            txtStudio.setText("IndiEvents");
            txtEvents.setText("Eventos creados:");
        }

        listaEventos.setLayoutManager(new LinearLayoutManager(this));


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
                intent = new Intent(PerfilActivity.this, EventsActivity.class);
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