package com.example.indievents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.indievents.db.IndiEventsOperacional;
import com.example.indievents.pojo.Event;
import com.example.indievents.pojo.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CrearEventActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_event);

        user = (User)getIntent().getSerializableExtra("user");
        final IndiEventsOperacional ieo = IndiEventsOperacional.getInstance(this);

        final EditText edtTitul = (EditText)findViewById(R.id.edtEventTitul);
        final EditText edtDesc = (EditText)findViewById(R.id.edtEventDescripcion);
        final EditText edtWeb = (EditText)findViewById(R.id.edtEventWeb);
        final EditText edtFechaInici = (EditText)findViewById(R.id.edtEventFechaInicio);
        final EditText edtFechaFinal = (EditText)findViewById(R.id.edtEventFechaFinal);

        edtFechaInici.setInputType(InputType.TYPE_NULL);
        edtFechaFinal.setInputType(InputType.TYPE_NULL);

        edtFechaInici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialogInici(edtFechaInici);
            }
        });

        edtFechaFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialogFinal(edtFechaFinal);
            }
        });

        Button btnCrearEvent = (Button)findViewById(R.id.btnCrearEvent);
        btnCrearEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Event event = new Event(edtTitul.getText().toString(), edtDesc.getText().toString(), edtWeb.getText().toString(), edtFechaInici.getText().toString(), edtFechaFinal.getText().toString(), user.getId());
                    ieo.crearEvent(event);

                    Intent intent = new Intent(CrearEventActivity.this, EventsActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showDateTimeDialogInici(final EditText edtFechaInici) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        edtFechaInici.setText(dateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(CrearEventActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(CrearEventActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showDateTimeDialogFinal(final EditText edtFechaFinal) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        edtFechaFinal.setText(dateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(CrearEventActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(CrearEventActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


}