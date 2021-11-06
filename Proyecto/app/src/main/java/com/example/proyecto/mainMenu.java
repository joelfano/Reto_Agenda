package com.example.proyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class mainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.op1) {
            mostrarDialoo();
            Toast.makeText(this,"Se seleccionó la primer opción",Toast.LENGTH_LONG).show();
        }
        if (id==R.id.op2) {
            Toast.makeText(this,"Se seleccionó la segunda opción",Toast.LENGTH_LONG).show();
        }
        if (id==R.id.op3) {
            Intent i = new Intent(this, newTask.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void go_new_task(View v) {
        Intent i = new Intent(this, newTask.class);
        startActivity(i);
    }

    public void go_task_list(View v) {
        Intent i = new Intent(this, taskList.class);
        startActivity(i);
    }

    public void mostrarDialoo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainMenu.this);

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_dialogo,null);

        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        Button aceptar = view.findViewById(R.id.btnaceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText viejo = findViewById(R.id.etviejo);
                String contraviejo = viejo.getText().toString();
                SharedPreferences prefe=getSharedPreferences("login", Context.MODE_PRIVATE);
                String c=prefe.getString(contraviejo, "");

                if (c.length()==0){
                    Toast.makeText(getApplicationContext(),"No existe dicho nombre", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });

    }
}