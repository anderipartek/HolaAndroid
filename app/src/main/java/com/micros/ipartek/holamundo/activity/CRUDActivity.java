package com.micros.ipartek.holamundo.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.micros.ipartek.holamundo.R;
import com.micros.ipartek.holamundo.bbdd.HipotecaCursorAdapter;
import com.micros.ipartek.holamundo.bbdd.HipotecaDbAdapter;
import com.micros.ipartek.holamundo.bbdd.HipotecaDbHelper;

import java.sql.SQLDataException;

public class CRUDActivity extends ActionBarActivity {

    Context context;
    private HipotecaDbAdapter dbAdapter;
    private Cursor cursor;
    private HipotecaCursorAdapter cursorAdapter ;
    private ListView lista;
    private TextView texto;

    public static final String C_MODO  = "modo" ;
    public static final int C_VISUALIZAR = 551 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        context = getApplicationContext();

        lista = (ListView) findViewById( R.id.crud_list );
        texto = (TextView) findViewById( R.id.crud_texto );

        dbAdapter = new HipotecaDbAdapter(this);
        dbAdapter.abrir();
        consultar();

        //registrar evento click sobre elementos de la lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                visualizar(id);
            }
        });


    }


    /**
     * Obtiene un cursor con todas las columnas de la tabla
     * Adapta a listView
     */
    private void consultar(){

       //TODO controlar el numero de consultas para mostrar/ocultar TextView
        cursor = dbAdapter.getCursor();
        if ( cursor != null ) {
            if (cursor.getCount() > 0) {
                startManagingCursor(cursor);
                cursorAdapter = new HipotecaCursorAdapter(this, cursor);
                lista.setAdapter(cursorAdapter);

                //ocultar TextView
                texto.setVisibility(View.INVISIBLE);
            } else {
                texto.setVisibility(View.VISIBLE);
            }
        }
    }

    private void visualizar(long id)
    {
        // Llamamos a la Actividad HipotecaFormulario indicando el modo visualización y el identificador del registro
        Intent i = new Intent( context , CRUDFormularioActivity.class);
        i.putExtra(C_MODO, C_VISUALIZAR);
        i.putExtra(HipotecaDbAdapter.C_COLUMNA_ID, id);
        startActivityForResult(i, C_VISUALIZAR);
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

        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }
}
