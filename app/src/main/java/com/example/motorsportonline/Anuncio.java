package com.example.motorsportonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Anuncio extends AppCompatActivity {
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncio);
        btn1 = (Button) findViewById(R.id.boton_saltar);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clic_boton_1();
            }
        });

    }
    private void clic_boton_1()
    {
        Intent miIntent = new Intent(this,Inicio.class);
        startActivity(miIntent);
    }
}