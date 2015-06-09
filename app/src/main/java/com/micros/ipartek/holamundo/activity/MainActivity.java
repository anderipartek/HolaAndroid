package com.micros.ipartek.holamundo.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.micros.ipartek.holamundo.R;
import com.micros.ipartek.holamundo.preferencias.PreferenciasActivity;

import java.io.File;
import java.io.FileOutputStream;


public class MainActivity extends ActionBarActivity {

    public final static String PARAMETRO = "p1";
    private static final String TAG = "MainActivity";


    Button boton;
    Button botonActividad;
    Button botonPref;
    TextView tvSaludo;
    TextView etSaludo;

    //contexto de la Actividad
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //llamar al padre
        super.onCreate(savedInstanceState);

        //cargar la vista o layout
        setContentView(R.layout.activity_main);

        context = getApplicationContext();




        boton = (Button) findViewById(R.id.main_btn_pulsar);
        botonActividad = (Button) findViewById(R.id.main_btn_actividad);
        botonPref = (Button) findViewById(R.id.main_btn_show_preferences);

        tvSaludo = (TextView) findViewById(R.id.main_tv_saludo);
        etSaludo = (EditText) findViewById(R.id.main_et_saludo);


        // Mostrar preferencias del usuario en Toas
        botonPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtener preferencias
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

                String mensaje="";
                mensaje = "Texo: " +   sharedPref.getString("edittext_preference","") + " \n";
                mensaje += "Check: " +   sharedPref.getBoolean("checkbox_preference", false) + " \n";

                Toast toast = Toast.makeText(context, mensaje , Toast.LENGTH_LONG );
                toast.show();
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvSaludo.setText("Ongi Etorri " + etSaludo.getText());
                //crear un fichero y guardar en la memoria interna
                saveFile();

            }
        });


        botonActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OtraActivity.class);
                intent.putExtra(PARAMETRO, "pamaetroMETRO");
                intent.putExtra("p2", 2);
                startActivity(intent);
            }
        });





    }

    /**
     * Guarda un fichero de texto en la memoria interna del telefono
     * Usa el campo de texto del saludo como contenido
     * nombre del fichero 'myfile.txt'
     */
    private void saveFile() {

        Log.v(TAG, "saveFile inicio");
        String filename = "myfile.txt";
        String contenido = "vacio";
        FileOutputStream outputStream;

        try {

            contenido = tvSaludo.getText().toString();

            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(contenido.getBytes());
            outputStream.close();
            Log.i(TAG, "Fichero creado " + context.getFilesDir() + " " + filename);
            Toast toast = Toast.makeText(context, "Fichero guardado Memoria interna" , Toast.LENGTH_LONG );
            toast.show();
        } catch (Exception e) {
            Toast toast = Toast.makeText(context, "ERROR guardando fichero" , Toast.LENGTH_LONG );
            toast.show();
            Log.e(TAG, "Excepcion");
            e.printStackTrace();
        }
        Log.v(TAG, "saveFile fin");

    }


    @Override
    protected void onStart() {
        super.onStart();
       // getactivit
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

            Intent intent = new Intent(context , PreferenciasActivity.class);
            startActivity(intent);

        }

        if (id == R.id.action_search) {

            Toast toast = Toast.makeText(context, "Has pulsado la lupa" , Toast.LENGTH_LONG );
            toast.show();

        }

        if ( id == R.id.action_dialog){
            mostrarDialogo();
        }


        if ( id == R.id.action_lista_custom){
            Intent intent = new Intent(context , ListaCustomActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * Ejmplo para un dialogo con dos botones Aceptar y rechazar
     */
    private void mostrarDialogo( ){

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setMessage( "Mensaje del Dialog ")
                .setPositiveButton( R.string.action_aceptar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton( R.string.action_rechazar , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.show();
    }


    public void llamarActivityListaCustom(View view) {
        Intent intent = new Intent(this, ListaPersonalizadaActivity.class);
        startActivity(intent);
        Toast toast = Toast.makeText(context, "Llamar Lista Customizada" , Toast.LENGTH_LONG );
        toast.show();
    }


}
