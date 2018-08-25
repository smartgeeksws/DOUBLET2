package com.example.lionsoft.doublet;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class PantallaJuego extends AppCompatActivity implements View.OnClickListener {

    String jug1, jug2;
    TextView tvJugador1, tvJugador2;
    ArrayList<Integer> arregloImagenes = new ArrayList<Integer>();
    ImageView iv00, iv01,iv10,iv11,iv20,iv21,iv30,iv31, imgTemp1, imgTemp2;
    int parejasCorrectas =0;
    int puntaje = 0;
    View imgSeleccionada1 = null, imgSeleccionada2 = null;
    Chronometer cronometroJ1, cronometroJ2;
    Boolean turno = false;
    int ramdon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_juego);

        tvJugador1 = (TextView)findViewById(R.id.tvJugador1);
        tvJugador2 = (TextView)findViewById(R.id.tvJugador2);
        iv00 = (ImageView)findViewById(R.id.iv00);
        iv01 = (ImageView)findViewById(R.id.iv01);
        iv10 = (ImageView)findViewById(R.id.iv10);
        iv11 = (ImageView)findViewById(R.id.iv11);
        iv20 = (ImageView)findViewById(R.id.iv20);
        iv21 = (ImageView)findViewById(R.id.iv21);
        iv30 = (ImageView)findViewById(R.id.iv30);
        iv31= (ImageView)findViewById(R.id.iv31);
        cronometroJ1 = (Chronometer)findViewById(R.id.cronometroJ1);
        cronometroJ2 = (Chronometer)findViewById(R.id.cronometroJ2);

        SharedPreferences sharpref = getSharedPreferences("Jugadores", Context.MODE_PRIVATE);
        jug1 = sharpref.getString("Jugador1","NoHay");
        jug2 = sharpref.getString("Jugador2","NoHay");

        tvJugador1.setText(""+jug1);
        tvJugador2.setText(""+jug2);
        cronometroJ1.setBase(SystemClock.elapsedRealtime());
        cronometroJ2.setBase(SystemClock.elapsedRealtime());
        sortearPrimerTurno();
        arregloImagenes();
        asignarImagenes();

        iv00.setOnClickListener(this);

    }

    private void asignarImagenes() {
        iv00.setTag(asignarImagenAleatoria());
        iv01.setTag(asignarImagenAleatoria());
        iv10.setTag(asignarImagenAleatoria());
        iv11.setTag(asignarImagenAleatoria());
        iv20.setTag(asignarImagenAleatoria());
        iv21.setTag(asignarImagenAleatoria());
        iv30.setTag(asignarImagenAleatoria());
        iv31.setTag(asignarImagenAleatoria());
    }

    private Object asignarImagenAleatoria() {
        int imagenSeleccionada = 0;

        Collections.shuffle(arregloImagenes);
        int indiceAleatorio = (int) (Math.random()*arregloImagenes.size());
        imagenSeleccionada = arregloImagenes.get(indiceAleatorio);
        arregloImagenes.remove(indiceAleatorio);
        return imagenSeleccionada;

    }

    private void arregloImagenes() {
        arregloImagenes.add(R.drawable.imagen1);
        arregloImagenes.add(R.drawable.imagen2);
        arregloImagenes.add(R.drawable.imagen3);
        arregloImagenes.add(R.drawable.imagen4);
        arregloImagenes.add(R.drawable.imagen1);
        arregloImagenes.add(R.drawable.imagen2);
        arregloImagenes.add(R.drawable.imagen3);
        arregloImagenes.add(R.drawable.imagen4);
    }

    private void sortearTurno() {
        if(turno == true){
            if(ramdon == 0){
                cronometroJ1.stop();
                tvJugador1.setTextColor(Color.GRAY);
                cronometroJ2.start();
                tvJugador2.setTextColor(Color.BLACK);
                turno = false;
                ramdon = 1;

            }else if(ramdon == 1){
                cronometroJ2.stop();
                tvJugador2.setTextColor(Color.GRAY);
                cronometroJ1.start();
                tvJugador1.setTextColor(Color.BLACK);
                turno = false;
                ramdon = 0;

            }
        }else if(turno == false){
            if(ramdon == 0){
                cronometroJ1.stop();
                tvJugador1.setTextColor(Color.GRAY);
                cronometroJ2.start();
                tvJugador2.setTextColor(Color.BLACK);
                turno = true;
                ramdon = 1;

            }else if(ramdon == 1){
                cronometroJ2.stop();
                tvJugador2.setTextColor(Color.GRAY);
                cronometroJ1.start();
                tvJugador1.setTextColor(Color.BLACK);
                turno = true;
                ramdon = 0;

            }
        }
    }

    private void sortearPrimerTurno() {
        final ArrayList<String> jugadores = new ArrayList<String>();
        jugadores.add(jug1.toString());
        jugadores.add(jug2.toString());
        ramdon = (int) (Math.random()*jugadores.size());
        
        if(ramdon == 0){
            tvJugador1.setTextColor(Color.BLACK);
            cronometroJ1.start();
            tvJugador2.setTextColor(Color.GRAY);
            cronometroJ2.stop();
            turno = true;
            
        }else if(ramdon == 1){
            tvJugador2.setTextColor(Color.BLACK);
            cronometroJ2.start();
            tvJugador1.setTextColor(Color.GRAY);
            cronometroJ1.stop();
            turno = true;
            
        }

    }

    @Override
    public void onClick(View view) {

        if(imgSeleccionada1 != null && imgSeleccionada2 != null){
            return;
        }

        final ImageView imgTempo1 = (ImageView)findViewById(view.getId());
        int recursoImagenTempo1 = (int) view.getTag();
        imgTempo1.setImageResource(recursoImagenTempo1);

        if(imgSeleccionada1 == null){
            imgSeleccionada1 = view;
            imgTempo1.setEnabled(true);
        }else{
            imgSeleccionada2 = view;

            imgTemp1 = (ImageView)findViewById(imgSeleccionada1.getId());
            imgTemp2 = (ImageView)findViewById(imgSeleccionada2.getId());
            imgTemp1.setEnabled(true);
            int rscImg1 = (int) imgSeleccionada1.getTag();
            int rscImg2 = (int) imgSeleccionada2.getTag();

            if(rscImg1 == rscImg2){
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.bien);
                mediaPlayer.start();
                Toast.makeText(getApplicationContext(),"Excelente",Toast.LENGTH_SHORT).show();
                CountDownTimer countDownTimer = new CountDownTimer(1000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {

                       imgTemp1.setVisibility(View.INVISIBLE);
                       imgTemp2.setVisibility(View.INVISIBLE);
                       cronometroJ1.stop();
                    }
                }.start();

                cronometroJ1.start();
                imgTemp1 = null;
                imgTemp2 = null;
                puntaje += 100;
                parejasCorrectas++;
                if(parejasCorrectas == 4){
                    cronometroJ1.stop();
                    cronometroJ2.stop();
                }
            }else{
                CountDownTimer countDownTimer2 = new CountDownTimer(1000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.mal);
                        mediaPlayer.start();
                        Toast.makeText(getApplication(),"Perdiste tu turno",Toast.LENGTH_SHORT).show();
                        imgTemp1.setImageResource(R.drawable.btn_menu);
                        imgTemp2.setImageResource(R.drawable.btn_menu);
                    }
                }.start();

                if(puntaje == 0){
                    puntaje = 0;
                    sortearTurno();
                }else if(puntaje != 0){
                    puntaje -= 2;
                    sortearTurno();
                }


            }
        }





    }
}
