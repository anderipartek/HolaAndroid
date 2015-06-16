package com.micros.ipartek.holamundo.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

//@see: http://developer.android.com/guide/components/services.html
public class MyService extends Service {

    private static final String TAG = "MyService";


    //creamos un nuevo timer que sera el encargado de realizar acciones cada X milisegundos
    private Timer timer = new Timer();
    //Iniciamos la constante del intervalo para ser usada en el timer.
    private static final long INTERVAL_IN_MS = 1000; // Milisegundo
    //inicializamos los segundos que el servicio esta arrancado a 0
    private int seconds = 0;

    public MyService() {
        //siempre hay que definir aunque este vacio
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Servicio creado");
        //cuando el servicio se crea reiniciamos el contador de segundos.
        seconds = 0;
        //indicamos al timer la tarea que tiene que realizar cada X milisegundos
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                //Incrementamos el contador.
                seconds += 1;
                //Mostramos el tiempo que lleva inicializado por pantalla.
                Log.i(TAG, "Segundos desde el inicio -> " + seconds);
            }
        }, 0, INTERVAL_IN_MS);
    }



    @Override
    public void onDestroy() {
        //Cuando se destruya el servicio cancelamos el scheduler que se crea al inicio del servicio
        Log.i(TAG, "Servicio destruido");
        if (timer != null)
            timer.cancel();
        super.onDestroy();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
