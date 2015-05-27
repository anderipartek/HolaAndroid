package com.micros.ipartek.holamundo.activity;

/*
* Ejemplo de Lista con Adapatadro customizado y patron holder para optimizar
* @see: http://www.survivingwithandroid.com/2012/10/android-listview-custom-adapter-and.html
* @tags: custom, listview, pattern, holder
*
* */

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.micros.ipartek.holamundo.R;

import java.util.ArrayList;

import adapter.RowListaCustomAdapter;
import bean.Row;


public class ListaCustomActivity extends ListActivity {

    private TextView cabecera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        cabecera = (TextView) findViewById(R.id.list_tv_titulo);

        ArrayList<Row> listValues; listValues = new ArrayList<Row>();
        listValues.add( new Row("Android", "12/03/2015") );
        listValues.add( new Row("IOS", "12/03/2015") );
        listValues.add( new Row("windows", "12/03/2015") );
        listValues.add( new Row("Safari", "12/03/2015") );
        listValues.add( new Row("Ubuntu", "12/03/2015") );
        listValues.add( new Row("CHIP", "12/03/2015") );
        listValues.add( new Row("AJOY", "12/03/2015") );

        // initiate the listadapter
        RowListaCustomAdapter customAdapter = new RowListaCustomAdapter ( ListaCustomActivity.this , listValues );

        // assign the list adapter
        setListAdapter(customAdapter);

    }

    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);
        Row fila = (Row) getListView().getItemAtPosition(position);
        cabecera.setText("You clicked " + fila.getTitulo1() + "-" + fila.getTitulo2() + " at position " + position);
    }


}
