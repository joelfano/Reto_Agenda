package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
    }

    //crear BD
    public void alta(View v) {
        //crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //Obtener los datos del EditText
        String nombre = etNombre.getText().toString();
        String descri = etDescripcion.getText().toString();
        String fecha = etFecha.getText().toString();
        String pri = etPrioridad.getText().toString();
        String coste = etCoste.getText().toString();
        //Poner los datos en cada columna
        ContentValues lista = new ContentValues();
        lista.put("nombre", nombre);
        lista.put("descripcion", descri);
        lista.put("fecha", fecha);
        lista.put("prioridad", pri);
        lista.put("coste", coste);
        //Insertarlo en BD
        bd.insert("articulos", null, lista);
        bd.close();
        etNombre.setText("");
        etDescripcion.setText("");
        etFecha.setText("");
        etPrioridad.setText("");
        etCoste.setText("");
        Toast.makeText(this, "Se cargaron los datos del artículo", Toast.LENGTH_SHORT).show();
    }
}