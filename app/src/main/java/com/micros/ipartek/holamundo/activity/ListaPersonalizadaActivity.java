package com.micros.ipartek.holamundo.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.micros.ipartek.holamundo.R;

import java.util.ArrayList;
import java.util.Date;

import adapter.ListaPersonalizadaAdaptador;
import bean.RowBean;

public class ListaPersonalizadaActivity extends Activity {


    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personalizada);

        //obtener la lista
        lista = (ListView)findViewById(R.id.listaPersonalizada);

        //juego de datos para la lista: hardkodeados
        ArrayList<RowBean> datos = new ArrayList<RowBean>();
        for (int i=0; i < 20; i++) {
            datos.add(new RowBean("Titulo" + i , (new Date()).toString() ));
        }

        //crear un adaptador personalizado
        ListaPersonalizadaAdaptador adaptador = new ListaPersonalizadaAdaptador( ListaPersonalizadaActivity.this , datos);

        //insertar adapatador en lista
        lista.setAdapter(adaptador);

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
