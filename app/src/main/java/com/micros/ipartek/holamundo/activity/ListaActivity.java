package com.micros.ipartek.holamundo.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.micros.ipartek.holamundo.R;

import java.util.ArrayList;
import java.util.List;


public class ListaActivity extends ListActivity {

    private TextView cabecera;
    private List<String> listValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        cabecera = (TextView) findViewById(R.id.list_tv_titulo);

        listValues = new ArrayList<String>();
        listValues.add("Android");
        listValues.add("IOS");
        listValues.add("Symbian");
        listValues.add("Windows");
        listValues.add("Ubuntu");

        // initiate the listadapter
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(
                    this,                   //contexto
                    R.layout.row_layout,    //layout de la fila
                    R.id.row_tv,            //elemento de la fila para rellenar
                    listValues              //listado datos para relleno
        );

        // assign the list adapter
        setListAdapter(myAdapter);

    }

    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);
        String selectedItem = (String) getListView().getItemAtPosition(position);
        cabecera.setText("You clicked " + selectedItem + " at position " + position);
    }



}
