package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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



}