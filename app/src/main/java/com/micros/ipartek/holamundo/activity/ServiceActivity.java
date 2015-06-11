package com.micros.ipartek.holamundo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.micros.ipartek.holamundo.servicios.MyIntentService;
import com.micros.ipartek.holamundo.servicios.MyService;
import com.micros.ipartek.holamundo.R;

public class ServiceActivity extends ActionBarActivity {


    Button startButton = null;
    Button stopButton = null;

    Button btn_service_intent = null;

    TextView status = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        //obtenemos el Label de status del layout mediante su ID
        status = (TextView) findViewById(R.id.textView_status);
        //obtenemos el Boton de inicio del layout mediante su ID
        startButton = (Button) findViewById(R.id.button_startService);

        //obtenemos el Boton de inicio del layout mediante su ID
        btn_service_intent = (Button) findViewById(R.id.button_service_intent);

        //Añadimos un listener para el evento click del boton de inicio.
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {//Cuando se pulse el boton de inicio
                //Cambiamos el texto del Label status a "Activado"
                status.setText("ACTIVADO");
                //Cambiamos el color del texto a verde.
                status.setTextColor(Color.GREEN);
                //LLamamos a la función que inicia el servicio.
                startService();
            }
        });

        //obtenemos el Boton de detener del layout mediante su ID
        stopButton = (Button) findViewById(R.id.button_stopService);
        //Añadimos un listener para el evento click del boton de detencion.
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Cambiamos el texto del Label status a "Desactivado"
                status.setText("DESACTIVADO");
                //Cambiamos el color del texto a rojo.
                status.setTextColor(Color.RED);
                //LLamamos a la funcion que detiene el servicio.
                stopService();
            }
        });


        //Añadimos un listener para el evento click del boton de inicio.
        btn_service_intent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {//Cuando se pulse el boton de inicio
                //Cambiamos el texto del Label status a "Activado"
                status.setText("INTENT SERVICE");
                //Cambiamos el color del texto a verde.
                status.setTextColor(Color.BLUE);
                //LLamamos a la función que inicia el servicio.
                startIntentService();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Cuando la activity es destruida, detenemos el servicio
        stopService();
    }

    /**
     * Inicia el servicio
     */
    private void startService() {
        //Creamos un nuevo intent al servicio.
        Intent service = new Intent(this, MyService.class);
        //iniciamos el servicio mediante el intent creado.
        startService(service);
    }

    private void startIntentService() {
        //Creamos un nuevo intent al servicio.
        Intent service = new Intent(this, MyIntentService.class);
        //iniciamos el servicio mediante el intent creado.
        startService(service);
    }

    /**
     * Finaliza el servicio
     */
    private void stopService() {
        //Creamos un nuevo intent al servicio
        Intent service = new Intent(this, MyService.class);
        //detenemos el servicio mediante el intent creado.
        stopService(service);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
