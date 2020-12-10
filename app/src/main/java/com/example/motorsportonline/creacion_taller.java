package com.example.motorsportonline;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class creacion_taller extends AppCompatActivity {
    EditText txtNom, txtUbi, txtHor, txtCon;
    Button btnIn, btnElim, btnBusc, btnAct;

    FirebaseDatabase FBData;
    DatabaseReference DBReference,DBMostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_taller);
        txtNom = (EditText) findViewById(R.id.txtNom);
        txtUbi = (EditText) findViewById(R.id.txtUbi);
        txtHor = (EditText) findViewById(R.id.txtHor);
        txtCon = (EditText) findViewById(R.id.txtCon);

        btnIn = (Button) findViewById(R.id.button_ING);
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NOMBRE = txtNom.getText().toString().trim().toUpperCase();
                String UBICACION = txtUbi.getText().toString().trim().toUpperCase();
                String HORARIO = txtHor.getText().toString().trim().toUpperCase();
                String CONTACTO = txtCon.getText().toString().trim().toUpperCase();
                if(NOMBRE.equals("")||UBICACION.equals("")||HORARIO.equals("")||HORARIO.equals(""))
                {
                    validar();
                }
                else {
                    Taller T = new Taller();
                    T.setNombre(NOMBRE);
                    T.setUbicacion(UBICACION);
                    T.setHorario(HORARIO);
                    T.setContacto(CONTACTO);
                    DBReference.child("Taller").child(T.getNombre()).setValue(T);
                    Toast.makeText(creacion_taller.this, "GUARDANDO !!!!!", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });


        btnElim = (Button) findViewById(R.id.button_ELIM);
        btnElim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nom = txtNom.getText().toString().trim().toUpperCase();
                if(Nom.equals(""))
                {
                    validar();
                }
                else {
                    DBReference.child("Taller").child(Nom).removeValue();
                    Toast.makeText(creacion_taller.this, "ELIMINANDO !!!!!", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });

        btnBusc = (Button) findViewById(R.id.button_Cons);
        btnBusc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int[] cont = {0};
                DatabaseReference bbdd = FirebaseDatabase.getInstance().getReference("Taller");
                /*DBMostrar = FirebaseDatabase.getInstance().getReference().child("Persona");
                DBMostrar.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            Toast.makeText(MainActivity.this, "ACA", Toast.LENGTH_LONG).show();
                            String RUN = txtRun.getText().toString().trim().toUpperCase();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Persona miP = snapshot.getValue(Persona.class);
                                if (miP.getRun().equals(RUN)) {
                                    txtNom.setText(miP.getNombre());
                                    txtCiudad.setText(miP.getCiudad());
                                    txtNum.setText(miP.getNUmero());
                                    break;
                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                */
                String Nom = txtNom.getText().toString().trim().toUpperCase();
                Query q=bbdd.orderByChild("nom").equalTo(Nom);

                /*
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int cont=0;
                        for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                            cont++;
                            Toast.makeText(MainActivity.this, "He encontrado "+cont, Toast.LENGTH_LONG).show();

                            Persona td =dataSnapshot.getValue(Persona.class);


                            System.out.println(td.getRun());



                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                */
                ((Query) q).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

                        for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                            cont[0]++;
                            System.out.println(dataSnapshot.getValue());

                            Taller miT = dataSnapshot.getValue(Taller.class);
                            txtNom.setText(miT.getUbicacion().toString());
                            txtUbi.setText(miT.getHorario().toString());
                            txtCon.setText(miT.getContacto().toString());
                            Toast.makeText(creacion_taller.this, "ENCONTRADO !!! ", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                if(cont[0] ==0)
                {
                    Toast.makeText(creacion_taller.this, "NO EXISTE ESA PERSONA!!! ", Toast.LENGTH_LONG).show();
                }
            }




        });


        btnAct = (Button) findViewById(R.id.button_UPD);
        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(creacion_taller.this, "ACTUALIZANDO !!!!!", Toast.LENGTH_LONG).show();
            }
        });

        iniciar_firebase();

    } // FIN ONCREATE

    private void iniciar_firebase() {
        FirebaseApp.initializeApp(this);
        this.FBData = FirebaseDatabase.getInstance();
        this.DBReference = this.FBData.getReference();
    }

    private void limpiar() {
        this.txtNom.setText("");
        this.txtUbi.setText("");
        this.txtHor.setText("");
        this.txtCon.setText("");
    }

    private void validar() {
        if(txtNom.getText().toString().equals(""))
        {
            txtNom.setError("Escriba Nombre");
        }
        if(txtUbi.getText().toString().equals(""))
        {
            txtUbi.setError("Escriba Ubicacion");
        }
        if(txtHor.getText().toString().equals(""))
        {
            txtHor.setError("Escriba Horario");
        }
        if(txtCon.getText().toString().equals(""))
        {
            txtCon.setError("Escriba Contacto");
        }

    }
}