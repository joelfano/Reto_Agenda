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
    }

    public void ejecutar(View v) {
        String contra=et_Contraseña.getText().toString();

        SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
        if (preferences.contains("contraseña")){
            String c=preferences.getString("contraseña", "");
            if (contra.equals(c)){
                Intent i = new Intent(this, mainMenu.class);
                startActivity(i);
            }else{
                Toast.makeText(this, "La contraseña es incorrecta", Toast.LENGTH_LONG).show();
            }
        }else{
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("contraseña",contra);
            editor.commit();
        }
    }
}
