package com.example.indievents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.indievents.db.IndiEventsOperacional;
import com.example.indievents.pojo.Game;
import com.example.indievents.pojo.Studio;
import com.example.indievents.pojo.User;

import java.text.ParseException;

public class GameActivity extends AppCompatActivity {
    User user;
    Studio studio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        user = (User)getIntent().getSerializableExtra("user");
        studio = (Studio)getIntent().getSerializableExtra("studio");
        final IndiEventsOperacional ieo = IndiEventsOperacional.getInstance(this.getApplicationContext());

        final EditText edtTitul = (EditText)findViewById(R.id.edtGameTitle);
        final EditText edtDesc = (EditText)findViewById(R.id.edtGameDescripcion);

        final CheckBox chkAccion = (CheckBox)findViewById(R.id.chkAccion);
        final CheckBox chkArcade = (CheckBox)findViewById(R.id.chkArcade);
        final CheckBox chkAventura = (CheckBox)findViewById(R.id.chkAventura);
        final CheckBox chkEstrategia = (CheckBox)findViewById(R.id.chkEstrategia);
        final CheckBox chkFPS = (CheckBox)findViewById(R.id.chkFps);
        final CheckBox chkGestion = (CheckBox)findViewById(R.id.chkGestion);
        final CheckBox chkJRPG = (CheckBox)findViewById(R.id.chkJrpg);
        final CheckBox chkLucha = (CheckBox)findViewById(R.id.chkLucha);
        final CheckBox chkMetroidvania = (CheckBox)findViewById(R.id.chkMetroidvania);
        final CheckBox chkNovela = (CheckBox)findViewById(R.id.chkNovelaVisual);
        final CheckBox chkPlataformas = (CheckBox)findViewById(R.id.chkPlataformas);
        final CheckBox chkPuzzles = (CheckBox)findViewById(R.id.chkPuzzles);
        final CheckBox chkRPG = (CheckBox)findViewById(R.id.chkRPG);
        final CheckBox chkShooter = (CheckBox)findViewById(R.id.chkShooter);
        final CheckBox chkSurvival = (CheckBox)findViewById(R.id.chkSurvival);

        Button btnRegistrar = (Button)findViewById(R.id.btnRegistrarJoc);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game game = new Game();
                game.setTitul(edtTitul.getText().toString());
                game.setDescripcio(edtDesc.getText().toString());

                String generes = "";

                generes += chkAccion.isChecked() ? chkAccion.getText().toString() + ", " : "";
                generes += chkArcade.isChecked() ? chkArcade.getText().toString() + ", " : "";
                generes += chkAventura.isChecked() ? chkAventura.getText().toString() + ", " : "";
                generes += chkEstrategia.isChecked() ? chkEstrategia.getText().toString() + ", " : "";
                generes += chkFPS.isChecked() ? chkFPS.getText().toString() + ", " : "";
                generes += chkGestion.isChecked() ? chkGestion.getText().toString() + ", " : "";
                generes += chkJRPG.isChecked() ? chkJRPG.getText().toString() + ", " : "";
                generes += chkLucha.isChecked() ? chkLucha.getText().toString() + ", " : "";
                generes += chkMetroidvania.isChecked() ? chkMetroidvania.getText().toString() + ", " : "";
                generes += chkNovela.isChecked() ? chkNovela.getText().toString() + ", " : "";
                generes += chkPlataformas.isChecked() ? chkPlataformas.getText().toString() + ", " : "";
                generes += chkPuzzles.isChecked() ? chkPuzzles.getText().toString() + ", " : "";
                generes += chkRPG.isChecked() ? chkRPG.getText().toString() + ", " : "";
                generes += chkShooter.isChecked() ? chkShooter.getText().toString() + ", " : "";
                generes += chkSurvival.isChecked() ? chkSurvival.getText().toString() + ", " : "";

                generes = generes.substring(0, generes.length() - 2);

                game.setGeneres(generes);

                try {
                    ieo.registrarJoc(game, studio);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                bundle.putSerializable("studio", studio);
                bundle.putInt("fragment", 1);

                Intent intent = new Intent(GameActivity.this, StudiosActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}