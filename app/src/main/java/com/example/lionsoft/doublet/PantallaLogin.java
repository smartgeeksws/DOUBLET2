package com.example.lionsoft.doublet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PantallaLogin extends AppCompatActivity {

    EditText etJugador1, etJugador2;
    Button btnJugar;
    String jugador1, jugador2;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_login);

        btnJugar = (Button)findViewById(R.id.btnJugar);
        etJugador1 = (EditText)findViewById(R.id.etJugador1);
        etJugador2 = (EditText)findViewById(R.id.etJugador2);

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jugador1 = etJugador1.getText().toString();
                jugador2 = etJugador2.getText().toString();

                if(jugador1.equals("")){
                    Toast.makeText(getApplicationContext(),"EL campo Jugador 1 esta vacio",Toast.LENGTH_SHORT).show();
                }else {
                    if(jugador2.equals("")){
                        Toast.makeText(getApplicationContext(),"El campo Jugador 2 esta vacion",Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(PantallaLogin.this,PantallaPrincipal.class);
                        startActivity(intent);
                        SharedPreferences sharpref = getSharedPreferences("Jugadores", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharpref.edit();
                        editor.putString("Jugador1",""+jugador1);
                        editor.putString("Jugador2",""+jugador2);
                        editor.commit();
                    }

                }

            }
        });




    }
}
