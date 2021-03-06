package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class taskList extends AppCompatActivity {

    ListView listViewTask;
    private final ArrayList<String> taskName = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        listViewTask = findViewById(R.id.listViewTask);

        Objects.requireNonNull(getSupportActionBar()).hide();
        init();

        listViewTask.setOnItemClickListener((parent, view, position, id) -> {
            String name = (String) listViewTask.getItemAtPosition(position);
            moveToDescription(name);
        });
    }

    public void init() {

        taskName.clear();

        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Consultar el dato con rawQuery
        Cursor fila = bd.rawQuery("select nombre,descripcion,prio from articulos", null);
        while (fila.moveToNext()) {
            taskName.add(fila.getString(0));
        }
        bd.close();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, taskName);
        listViewTask.setAdapter(adapter);
        registerForContextMenu(listViewTask);
    }

    public void listahecho(View view) {

        taskName.clear();

        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Consultar el dato con rawQuery
        Cursor fila = bd.rawQuery("select nombre,descripcion,prio,pendiente from articulos where pendiente=true", null);
        while (fila.moveToNext()) {
            taskName.add(fila.getString(0));
        }
        bd.close();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, taskName);
        listViewTask.setAdapter(adapter);
        registerForContextMenu(listViewTask);
    }

    public void listapendiente(View view) {

        taskName.clear();

        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Consultar el dato con rawQuery
        Cursor fila = bd.rawQuery("select nombre,descripcion,prio,pendiente from articulos where pendiente=false", null);
        while (fila.moveToNext()) {
            taskName.add(fila.getString(0));
        }
        bd.close();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, taskName);
        listViewTask.setAdapter(adapter);
        registerForContextMenu(listViewTask);
    }

    public void moveToDescription(String name){
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("taskName", name);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Elige una opcion");
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.opc_modify:
                String name = (String) listViewTask.getItemAtPosition(i.position);
                Intent intent = new Intent(this, modifyTask.class);
                intent.putExtra("nombre", name);
                startActivity(intent);
                return false;
            case R.id.opc_delete:
                borrar(i.position);
                init();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void borrar(int position) {

        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Obtener dato para utilizar en en delete
        String nom = (String) listViewTask.getItemAtPosition(position);

        //Hacer el delete
        int cant = bd.delete("articulos", "nombre='" + nom + "'", null);
        bd.close();

        if (cant == 1){
            Toast.makeText(this, "Se borr?? el art??culo con dicho c??digo", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No existe un art??culo con dicho c??digo", Toast.LENGTH_SHORT).show();
        }
    }
}
