package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class mainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void ejecutar1(View v) {
        Toast.makeText(this, "No existe dicho contraseña", Toast.LENGTH_LONG).show();
    }

    public void go_task_list(View v) {
        Intent i = new Intent(this, taskList.class);
        startActivity(i);
    }

}