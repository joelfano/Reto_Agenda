package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class newTask extends AppCompatActivity {
    private EditText etNombre,etDescripcion,etFecha,etPrioridad,etCoste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);

        etNombre=findViewById(R.id.etNombre);
        etDescripcion=findViewById(R.id.etDescripcion);
        etFecha=findViewById(R.id.etFecha);
        etPrioridad=findViewById(R.id.etPrioridad);
        etCoste=findViewById(R.id.etCoste);
        getSupportActionBar().hide();
    }



    public void addTask(View v) {
        //crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Obtener los datos del EditText
        String cod = etNombre.getText().toString();
        String descri = etDescripcion.getText().toString();
        String fecha = etFecha.getText().toString();
        String prio = etPrioridad.getText().toString();
        String coste = etCoste.getText().toString();

        //Poner los datos en la tabla registro
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("fec", fecha);
        registro.put("prio", prio);
        registro.put("coste", coste);

        //Insertarlo en la tabla
        bd.insert("articulos", null, registro);
        bd.close();
        etNombre.setText("");
        etDescripcion.setText("");
        etFecha.setText("");
        etPrioridad.setText("");
        etCoste.setText("");

        Toast.makeText(this, "Se cargaron los datos del artículo", Toast.LENGTH_SHORT).show();
    }

    public void goBack(View v) {
        Intent i = new Intent(this, mainMenu.class);
        startActivity(i);
    }
}