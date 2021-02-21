package com.example.indievents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.indievents.pojo.Studio;
import com.example.indievents.pojo.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class StudiosActivity extends AppCompatActivity {

    User user;
    int frg;
    boolean cambiarBoto = true;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studios);

        user = (User)getIntent().getSerializableExtra("user");
        frg = getIntent().getIntExtra("fragment", 0);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarPrincipal);
        setSupportActionBar(toolbar);

        TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtTitle.setText("Studios");

        nav = (NavigationView)findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View v = nav.getHeaderView(0);

        TextView txtUser = (TextView)v.findViewById(R.id.txtUserMenu);
        TextView txtStudio = (TextView)v.findViewById(R.id.txtStudioMenu);

        txtUser.setText(user.getUsername());

        if (user.isDev() && user.getStudio() != null)
            txtStudio.setText(user.getStudio().getNom());
        else if (user.isDev() && user.getStudio() == null)
            txtStudio.setText("Independiente");
        else
            txtStudio.setText("IndiEvents");

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
                    case R.id.menuNews:
                        intent = new Intent(getBaseContext(), NoticiasActivity.class);
                        break;
                    case R.id.menuMaps:
                        startActivity(new Intent(StudiosActivity.this, MapsActivity.class));
                        break;
                    case R.id.menuLogOut:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(StudiosActivity.this, MainActivity.class));
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


        final Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);

        Fragment fragment;

        if (frg == 0)
            fragment = new StudiosListaFragment();
        else{
            bundle.putSerializable("studio", (Studio)getIntent().getSerializableExtra("studio"));
            fragment = new StudioPerfilFragment();
        }

        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.studios_container, fragment).commit();

        final Button btnCrearStudio = (Button)findViewById(R.id.btnCrearStudio);
        btnCrearStudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cambiarBoto){
                    btnCrearStudio.setText("Cancelar");
                    Fragment fragment = new CrearStudioFragment();
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.studios_container, fragment).commit();
                }else{
                    btnCrearStudio.setText("Crear studio");
                    Fragment fragment = new StudiosListaFragment();
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.studios_container, fragment).commit();
                }

                cambiarBoto = !cambiarBoto;
            }
        });
        
        if(user.isDev() && user.getStudio() == null)
            btnCrearStudio.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.menuSettings:
                //intent = new Intent(PerfilActivity.this, EventosActivity.class);
                break;
        }

        if(intent != null){
            intent.putExtra("user", user);
            startActivity(intent);
        }

        return true;
    }
}