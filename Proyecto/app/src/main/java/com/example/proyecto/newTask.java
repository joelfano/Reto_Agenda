package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

import java.util.Calendar;

public class newTask extends AppCompatActivity {

    private EditText etNombre,etDescripcion,etCoste;
    private TextView tvFecha;
    private Button btnFecha;
    private Spinner sp_Prio;
    private Calendar c;
    private DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);

        etNombre=findViewById(R.id.etNombre);
        etDescripcion=findViewById(R.id.etDescripcion);
        etCoste=findViewById(R.id.etCoste);

        tvFecha=findViewById(R.id.tvFecha);

        btnFecha=findViewById(R.id.btnFecha);

        sp_Prio =findViewById(R.id.spPrio);
        String []opciones={"Urgente","Alta","Media","Baja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        sp_Prio.setAdapter(adapter);

        getSupportActionBar().hide();
    }

    public void addTask(View v) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String nombre = etNombre.getText().toString();
        String descri = etDescripcion.getText().toString();
        String fecha = tvFecha.getText().toString();
        String prio = sp_Prio.getSelectedItem().toString();
        String coste = etCoste.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("descripcion", descri);
        registro.put("fec", fecha);
        registro.put("prio", prio);
        registro.put("coste", coste);

        bd.insert("articulos", null, registro);
        bd.close();
        etNombre.setText("");
        etDescripcion.setText("");
        tvFecha.setText("");
        etCoste.setText("");

        Toast.makeText(this, "Se ha añadido una nueva tarea", Toast.LENGTH_SHORT).show();
    }

    public void elegirFecha(View v) {
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                tvFecha.setText(day + "/" + month +"/"+ year);
            }
        }, day, month, year);
        dpd.show();
    }

    public void goBack(View v) {
        Intent i = new Intent(this, mainMenu.class);
        startActivity(i);
    }
}