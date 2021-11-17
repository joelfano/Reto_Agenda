package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptionActivity extends AppCompatActivity {

    private TextView tv_nombreDescription, tv_desDescription, tv_fechaDescription, tv_statusDescription, tv_costeDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        getSupportActionBar().hide();

        tv_nombreDescription = findViewById(R.id.tv_titleDescription);
        tv_desDescription = findViewById(R.id.tv_desDescription);
        tv_fechaDescription = findViewById(R.id.tv_fechaDescription);
        tv_statusDescription = findViewById(R.id.tv_statusDescription);
        tv_costeDescription = findViewById(R.id.tv_costeDescription);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("taskName");

        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Consultar el dato con rawQuery
        Cursor fila = bd.rawQuery("select nombre,descripcion,fec,prio,coste,pendiente from articulos where nombre = '" + name +"'", null);
        String color = "";
        if (fila.moveToFirst()) {
            if(fila.getString(3).equals("Urgente")){
                tv_statusDescription.setTextColor(Color.parseColor("#5B0000"));
            }else if(fila.getString(3).equals("Alta")){
                tv_statusDescription.setTextColor(Color.parseColor("#F35721"));
            }else if(fila.getString(3).equals("Media")){
                tv_statusDescription.setTextColor(Color.parseColor("#F3F021"));
            }else{
                tv_statusDescription.setTextColor(Color.parseColor("#21F34D"));
            }

            tv_nombreDescription.setText(fila.getString(0));
            tv_desDescription.setText(fila.getString(1));
            tv_fechaDescription.setText(fila.getString(2));
            tv_statusDescription.setText(fila.getString(3));
            tv_costeDescription.setText(fila.getString(4));
        }
        bd.close();
    }

    public  void hecho(View view){

        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Obtener los datos que quires agregar
        String nom = tv_nombreDescription.getText().toString();

        //Crear un registro / Las columnas
        ContentValues registro = new ContentValues();
        registro.put("pendiente", true);

        //Hacer un update en la BD
        int cant = bd.update("articulos", registro, "nombre='" + nom + "'" , null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "no existe un artículo con el código ingresado", Toast.LENGTH_SHORT).show();
    }

    public void borrar(View view) {

        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Obtener dato para utilizar en en delete
        String nom = tv_nombreDescription.getText().toString();

        //Hacer el delete
        int cant = bd.delete("articulos", "nombre='" + nom + "'", null);
        bd.close();

        if (cant == 1){
            Toast.makeText(this, "Se borró el artículo con dicho código", Toast.LENGTH_SHORT).show();
             Intent i = new Intent(this, taskList.class);
            startActivity(i);
        }else {
            Toast.makeText(this, "No existe un artículo con dicho código", Toast.LENGTH_SHORT).show();
        }
    }
}