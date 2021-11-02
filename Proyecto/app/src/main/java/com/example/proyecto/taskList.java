package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class taskList extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        tv = findViewById(R.id.tv);
    }

    //Consultar en la BD
    public void consultaporcodigo(View v) {
        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //Obtener el dato que utilizas en la consulta
        //Consultar el dato con rawQuery
        Cursor fila = bd.rawQuery("select nombre from listas", null);
        if (fila.moveToFirst()) {
            tv.setText(fila.getString(0));
        } else
            Toast.makeText(this, "No existe un artículo con dicho código", Toast.LENGTH_SHORT).show();
        bd.close();
    }
}