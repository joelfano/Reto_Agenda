package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    private TextView tv_nombreDescription, tv_desDescription, tv_fechaDescription, tv_statusDescription, tv_costeDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        getSupportActionBar().hide();

        ListElement element = (ListElement) getIntent().getSerializableExtra("ListItem");

        tv_nombreDescription = findViewById(R.id.tv_titleDescription);
        tv_desDescription = findViewById(R.id.tv_desDescription);
        tv_fechaDescription = findViewById(R.id.tv_fechaDescription);
        tv_statusDescription = findViewById(R.id.tv_statusDescription);
        tv_costeDescription = findViewById(R.id.tv_costeDescription);

        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //Consultar el dato con rawQuery
        Cursor fila = bd.rawQuery("select nombre,descripcion,fec,prio,coste from articulos where nombre = '" + element.getName() +"'", null);
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
}