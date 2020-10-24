package com.example.motorsportonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    RecyclerView MiRecycler;
    String nombre[], ubicacion[], horario[], tipodereparacion[], contacto[];
    int imagenes[] = {R.drawable.autoplanet, R.drawable.frenoscontinental, R.drawable.lubricar, R.drawable.lubricentro, R.drawable.neumaticos, R.drawable.parabrisas};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MiRecycler = (RecyclerView) findViewById(R.id.MIRecicler);
        nombre = getResources().getStringArray(R.array.taller);
        ubicacion = getResources().getStringArray(R.array.ubicacion);
        horario = getResources().getStringArray(R.array.horario);
        tipodereparacion = getResources().getStringArray(R.array.tipodereparacion);
        contacto = getResources().getStringArray(R.array.contacto);

        MiAdapter MA = new MiAdapter(this, this.nombre, this.ubicacion, this.horario, this.tipodereparacion, this.contacto, this.imagenes);
        MiRecycler.setAdapter(MA);
        MiRecycler.setLayoutManager(new LinearLayoutManager(this));


    }
}