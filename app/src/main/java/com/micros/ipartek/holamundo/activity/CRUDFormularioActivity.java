package com.micros.ipartek.holamundo.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.micros.ipartek.holamundo.R;
import com.micros.ipartek.holamundo.bbdd.HipotecaDbAdapter;

import java.io.File;
import java.util.Date;

public class CRUDFormularioActivity extends ActionBarActivity {


    private static final String TAG ="CRUDFormularioActivity";
    private HipotecaDbAdapter dbAdapter;
    private Cursor cursor;

    //
    // Modo del formulario
    //
    private int modo;

    //
    // Identificador del registro que se edita cuando la opción es MODIFICAR
    //
    private long id;

    //
    // Elementos de la vista
    //
    private EditText nombre;
    private EditText condiciones;
    private EditText contacto;
    private EditText telefono;
    private EditText email;
    private EditText observaciones;
    //botonera
    private Button boton_guardar;
    private Button boton_cancelar;

    //camara fotos
    private Button boton_camara;
    private ImageView imagen_camara;
    private String nombreFoto;
    private static final String SD_PATH_FOTO = "HolaAndroid";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hipoteca_formulario);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if (extra == null) return;

        //
        // Obtenemos los elementos de la vista
        //
        nombre = (EditText) findViewById(R.id.nombre);
        condiciones = (EditText) findViewById(R.id.condiciones);
        contacto = (EditText) findViewById(R.id.contacto);
        telefono = (EditText) findViewById(R.id.telefono);
        email = (EditText) findViewById(R.id.email);
        observaciones = (EditText) findViewById(R.id.observaciones);

        boton_guardar = (Button) findViewById(R.id.boton_guardar);
        boton_cancelar = (Button) findViewById(R.id.boton_cancelar);
        boton_camara = (Button) findViewById(R.id.boton_camara);
        imagen_camara = (ImageView) findViewById(R.id.imagen_camara);

        //
        // Creamos el adaptador
        //
        dbAdapter = new HipotecaDbAdapter(this);
        dbAdapter.abrir();

        //
        // Obtenemos el identificador del registro si viene indicado
        //
        if (extra.containsKey(HipotecaDbAdapter.C_COLUMNA_ID)) {
            id = extra.getLong(HipotecaDbAdapter.C_COLUMNA_ID);
            consultar(id);
        }

        //
        // Establecemos el modo del formulario
        //
        establecerModo(extra.getInt(CRUDActivity.C_MODO));

        //
        // Definimos las acciones para los dos botones
        //
        boton_guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        boton_cancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

        boton_camara.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                camaraFotos();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crud, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.menu_eliminar) {
            eliminar();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void establecerModo(int m) {
        this.modo = m;

        if (modo == CRUDActivity.C_VISUALIZAR) {
            this.setTitle(nombre.getText().toString());
            this.setEdicion(false);

        } else if (modo == CRUDActivity.C_CREAR) {

            this.setTitle("Nuevo Registro");
            this.setEdicion(true);

        } else if (modo == CRUDActivity.C_EDITAR) {
            this.setTitle("Editando.....");
            this.setEdicion(true);
        }
    }

    private void consultar(long id) {
        //
        // Consultamos el centro por el identificador
        //
        cursor = dbAdapter.getRegistro(id);

        nombre.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_NOMBRE)));
        condiciones.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_CONDICIONES)));
        contacto.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_CONTACTO)));
        telefono.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_TELEFONO)));
        email.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_EMAIL)));
        observaciones.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_OBSERVACIONES)));
    }

    private void setEdicion(boolean opcion) {
        nombre.setEnabled(opcion);
        condiciones.setEnabled(opcion);
        contacto.setEnabled(opcion);
        telefono.setEnabled(opcion);
        email.setEnabled(opcion);
        observaciones.setEnabled(opcion);
    }


    private void guardar() {

        //
        // Obtenemos los datos del formulario
        //
        ContentValues reg = new ContentValues();

        if (modo == CRUDActivity.C_EDITAR) {
            reg.put(HipotecaDbAdapter.C_COLUMNA_ID, id);
        }
        reg.put(HipotecaDbAdapter.C_COLUMNA_NOMBRE, nombre.getText().toString());
        reg.put(HipotecaDbAdapter.C_COLUMNA_CONDICIONES, condiciones.getText().toString());
        reg.put(HipotecaDbAdapter.C_COLUMNA_CONTACTO, contacto.getText().toString());
        reg.put(HipotecaDbAdapter.C_COLUMNA_TELEFONO, telefono.getText().toString());
        reg.put(HipotecaDbAdapter.C_COLUMNA_EMAIL, email.getText().toString());
        reg.put(HipotecaDbAdapter.C_COLUMNA_OBSERVACIONES, observaciones.getText().toString());

        if (modo == CRUDActivity.C_CREAR) {
            dbAdapter.insert(reg);
            Toast.makeText(getApplicationContext(), "Registro insertado", Toast.LENGTH_SHORT).show();
        } else if (modo == CRUDActivity.C_CREAR) {
            dbAdapter.update(reg);
            Toast.makeText(getApplicationContext(), "Registro modificado", Toast.LENGTH_SHORT).show();
        }

        //
        // Devolvemos el control
        //
        setResult(RESULT_OK);
        finish();
    }

    private void cancelar() {
        setResult(RESULT_CANCELED, null);
        finish();
    }

    private void eliminar() {
        /**
         * Borramos el registro con confirmación
         */
        AlertDialog.Builder dialogEliminar = new AlertDialog.Builder(this);

        dialogEliminar.setIcon(android.R.drawable.ic_dialog_alert);
        dialogEliminar.setTitle("¿Desea eliminar?");
        dialogEliminar.setMessage("Ten cuidado no hay vuelta atras");
        dialogEliminar.setCancelable(false);

        dialogEliminar.setPositiveButton(
                getResources().getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int boton) {
                        dbAdapter.delete(id);
                        Toast.makeText(getApplication(), "Resgistro Eliminado", Toast.LENGTH_SHORT).show();
                        /**
                         * Devolvemos el control
                         */
                        setResult(RESULT_OK);
                        finish();
                    }
                });

        dialogEliminar.setNegativeButton(android.R.string.no, null);
        dialogEliminar.show();

    }


    private void camaraFotos() {

        try {
            String stateSD = Environment.getExternalStorageState();
            if (  ! Environment.MEDIA_MOUNTED.equals(stateSD) ) {

                //Creamos el Intent para llamar a la Camara
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //Creamos una carpeta en la memoria interna del terminal
                File imagesFolder = new File(Environment.getExternalStorageDirectory(), SD_PATH_FOTO);
                if (!imagesFolder.exists()) {
                    imagesFolder.mkdirs();
                }
                //añadimos el nombre de la imagen
                nombreFoto = "foto.jpg";
                File image = new File(imagesFolder, nombreFoto);
                Uri uriSavedImage = Uri.fromFile(image);
                //Le decimos al Intent que queremos grabar la imagen
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

                Log.i(TAG, "La imagen se guardara en: " + uriSavedImage);
                // start the image capture Intent
                startActivityForResult(cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

            }else{
                Log.w(TAG, "SD NO esta insertada");
                Toast toast = Toast.makeText(getApplicationContext(), "SD NO esta insertada" , Toast.LENGTH_LONG );
                toast.show();

            }

        }catch (Exception e){
            Log.e(TAG, " **************** camaraFotos excepcion");
            e.printStackTrace();

        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult requestCode:" + requestCode + " resultCode:"+ resultCode + " data:"+ data.toString() );
        //Comprovamos que la foto se a realizado
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //Creamos un bitmap con la imagen recientemente
            //almacenada en la memoria
            Toast.makeText(this, "Image saved to:\n" +  data.getData(), Toast.LENGTH_LONG).show();
            Log.i(TAG, "Recuperando imagen de: " + Environment.getExternalStorageDirectory() + nombreFoto );
            Bitmap bMap = BitmapFactory.decodeFile( getApplicationContext().getFilesDir() + nombreFoto );
            //Añadimos el bitmap al imageView para
            //mostrarlo por pantalla
            imagen_camara.setImageBitmap(bMap);
        }

    }
}