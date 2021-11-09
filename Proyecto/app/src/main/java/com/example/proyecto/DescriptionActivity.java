package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    TextView tv_titleDescription;
    TextView tv_cityDescription;
    TextView tv_statusDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ListElement element = (ListElement) getIntent().getSerializableExtra("ListElement");
        tv_titleDescription = findViewById(R.id.tv_titleDescription);
        tv_cityDescription = findViewById(R.id.tv_cityDescription);
        tv_statusDescription = findViewById(R.id.tv_statusDescription);

    }
}