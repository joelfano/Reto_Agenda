package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_Usuario;
    private EditText et_Contraseña;
    private CheckBox cb_Recuerda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        et_Usuario = findViewById(R.id.et_Usuario);
        et_Contraseña = findViewById(R.id.et_Contraseña);
        cb_Recuerda= findViewById(R.id.cb_Recuerda);
        getSupportActionBar().hide();

        String contra="12345";
        String usu="asier";

        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(usu, contra);
        editor.commit();
    }

    public void ejecutar(View v) {
        String usu=et_Usuario.getText().toString();
        String contra=et_Usuario.getText().toString();
        SharedPreferences prefe=getSharedPreferences("login", Context.MODE_PRIVATE);
        String d=prefe.getString(usu, "");
        String c=prefe.getString(contra, "");

        if (d.length()==0){
            Toast.makeText(this,"No existe dicho nombre", Toast.LENGTH_LONG).show();
        }else if (c.length()==0) {
            Toast.makeText(this, "No existe dicho contraseña", Toast.LENGTH_LONG).show();
        }else{
            Intent i = new Intent(this, mainMenu.class);
            startActivity(i);
        }
    }
}
