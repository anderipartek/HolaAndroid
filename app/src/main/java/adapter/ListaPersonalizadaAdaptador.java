package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.micros.ipartek.holamundo.R;

import java.util.ArrayList;

import bean.RowBean;

/**
 * Created by ur00 on 28/05/2015.
 */
public class ListaPersonalizadaAdaptador extends BaseAdapter {

    private static final String TAG = "ListaPersonAdaptador";

    private ArrayList<RowBean> data;
    private LayoutInflater inflater = null;

    //Patron ViewHolder para optimizar findByID
    static class ViewHolder {
        TextView tvCabecera;
        TextView tvFecha;
    }

    /**
     * Constructor para el Adapatador
     * @param c contexto de la Activity que llama al Adaptador
     * @param data juego de datos para cargar o inflar
     */
    public ListaPersonalizadaAdaptador ( Context c, ArrayList<RowBean> data){

        Log.i(TAG, "Construir Adaptador");
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
        Log.v(TAG, "getItem() ");
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.v(TAG, "getItemId() ");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //si la vista no esta creada la iniflamos
        if ( convertView == null ){
            Log.i(TAG, "Inflamos la convertView ");
            //inflamos el layout de  la fila
            convertView = inflater.inflate( R.layout.fila_personalizada, null);
            //inicializar holder con las vistas por su id
            viewHolder = new ViewHolder();
            viewHolder.tvCabecera = (TextView)convertView.findViewById(R.id.fila_per_tv_titulo);
            viewHolder.tvFecha    = (TextView)convertView.findViewById(R.id.fila_per_tv_fecha);
            //guardar el la view el viewholder
            convertView.setTag(viewHolder);
        }else{
            Log.i(TAG, "usarmos ViewHolder, NO Inflamos la convertView ");
            viewHolder = (ViewHolder)convertView.getTag();
        }

        //rellenar vistas de la fila
        viewHolder.tvCabecera.setText( data.get(position).getTitulo() );
        viewHolder.tvFecha.setText( data.get(position).getFecha() );

        //retornar la vista inflada con sus valores
        return convertView;
    }
}
