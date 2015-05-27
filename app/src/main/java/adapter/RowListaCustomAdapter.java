package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.micros.ipartek.holamundo.R;

import java.util.ArrayList;

import bean.Row;

/**
 * Created by ur00 on 26/05/2015.
 */
public class RowListaCustomAdapter extends BaseAdapter {


    private static final String TAG = "RowListaCustomAdapter";
    private static int convertViewCounter = 0;

    private ArrayList<Row> data;
    private LayoutInflater inflater = null;


    //Patron ViewHolder
    static class ViewHolder {
        TextView texto1;
        TextView texto2;
    }

    /**
     * Constructor para el Adapatador, obtiene los datos y crea un inflater
     * @param c contexto de la App
     * @param data datos para rellenar la lista
     */
    public RowListaCustomAdapter ( Context c, ArrayList<Row>data){
        Log.v(TAG, "Construir Adaptador");
        this.data = data;
        inflater = LayoutInflater.from(c);

    }


    @Override
    public int getCount() {
        Log.v(TAG, "getCount() ");
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        Log.v(TAG, "in getItem() for position " + position);
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.v(TAG, "in getItemId() for position " + position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;

        // si no existe crear la vista
        if (rowView == null) {

            //obtener el layout
            rowView = inflater.inflate( R.layout.row_custom_lista, null);

            //inicializar holder con las vistas por su id
            holder = new ViewHolder();
            holder.texto1 = (TextView)rowView.findViewById(R.id.lista_custom_tv_1);
            holder.texto2 = (TextView)rowView.findViewById(R.id.lista_custom_tv_2);

            //guardar holder en view
            rowView.setTag(holder);
        }

        holder = (ViewHolder) rowView.getTag();
        //rellenar vistas de la fila
        holder.texto1.setText( data.get(position).getTitulo1() );
        holder.texto2.setText( data.get(position).getTitulo2() );

        return rowView;
    }
}
