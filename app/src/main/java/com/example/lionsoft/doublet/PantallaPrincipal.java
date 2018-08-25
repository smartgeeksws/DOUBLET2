package com.example.lionsoft.doublet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PantallaPrincipal extends AppCompatActivity {

    ImageButton btnJugar, btnConfiguracion, btnPuntajesAltos, btnLogoInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        btnConfiguracion = (ImageButton)findViewById(R.id.btn_config);
        btnJugar = (ImageButton)findViewById(R.id.btn_jugar);
        btnLogoInfo = (ImageButton)findViewById(R.id.btn_logo);
        btnPuntajesAltos = (ImageButton)findViewById(R.id.btn_puntajes);

        btnPuntajesAltos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PantallaPrincipal.this,PantallaPuntajes.class);
                startActivity(intent);
            }
        });

        btnLogoInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PantallaPrincipal.this,PantallaInfo.class);
                startActivity(intent);
            }
        });

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PantallaPrincipal.this,PantallaJuego.class);
                startActivity(intent);
            }
        });

        btnConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PantallaPrincipal.this,PantallaConfiguraciones.class);
                startActivity(intent);
            }
        });


    }
}
