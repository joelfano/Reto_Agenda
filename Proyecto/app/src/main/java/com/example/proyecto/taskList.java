package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class taskList extends AppCompatActivity {

    public static List<ListElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        getSupportActionBar().hide();
        init();
    }

    public void init() {
        elements = new ArrayList<>();

        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Consultar el dato con rawQuery
        Cursor fila = bd.rawQuery("select nombre,descripcion,prio from articulos", null);
        showRows(fila);
        bd.close();

        ListAdapter listAdapter = new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToDescription(item);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
        registerForContextMenu(recyclerView);
    }

    public void listahecho(View view) {

        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Consultar el dato con rawQuery
        elements.clear();
        Cursor fila = bd.rawQuery("select nombre,descripcion,prio,pendiente from articulos where pendiente=true", null);
        showRows(fila);
        bd.close();

        ListAdapter listAdapter = new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToDescription(item);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
        registerForContextMenu(recyclerView);
    }

    public void listapendiente(View view) {

        //Crear BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Consultar el dato con rawQuery
        elements.clear();
        Cursor fila = bd.rawQuery("select nombre,descripcion,prio,pendiente from articulos where pendiente=false", null);
        showRows(fila);
        bd.close();

        ListAdapter listAdapter = new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToDescription(item);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
        registerForContextMenu(recyclerView);
    }

    public void showRows(Cursor fila) {
        String color = "";
        while (fila.moveToNext()) {
            if (fila.getString(2).equals("Urgente")) {
                color = "#5B0000";
            } else if (fila.getString(2).equals("Alta")) {
                color = "#F35721";
            } else if (fila.getString(2).equals("Media")) {
                color = "#F3F021";
            } else {
                color = "#21F34D";
            }
            elements.add(new ListElement(color, fila.getString(0), fila.getString(1)));
        }
    }

    public void moveToDescription(ListElement item) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("ListItem", item);
        startActivity(intent);
    }
}
