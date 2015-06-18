package com.micros.ipartek.holamundo.bbdd;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/**
 * Gestionar las operaciones de la entidad HIPOTECA en la base de datos SQLite
 */
public class HipotecaDbAdapter {
    /**
     * Definimos constante con el nombre de la tabla
     */
    public static final String C_TABLA = "HIPOTECA" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID   = "_id";
    public static final String C_COLUMNA_NOMBRE = "hip_nombre";
    public static final String C_COLUMNA_CONDICIONES = "hip_condiciones";
    public static final String C_COLUMNA_CONTACTO = "hip_contacto";
    public static final String C_COLUMNA_EMAIL = "hip_email";
    public static final String C_COLUMNA_TELEFONO = "hip_telefono";
    public static final String C_COLUMNA_OBSERVACIONES = "hip_observaciones";

    private Context contexto;
    private HipotecaDbHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{
                                                C_COLUMNA_ID,
                                                C_COLUMNA_NOMBRE,
                                                C_COLUMNA_CONDICIONES,
                                                C_COLUMNA_CONTACTO,
                                                C_COLUMNA_EMAIL,
                                                C_COLUMNA_TELEFONO,
                                                C_COLUMNA_OBSERVACIONES
                                        } ;

    /* Constructor */
    public HipotecaDbAdapter(Context context)
    {
        this.contexto = context;
    }

    public HipotecaDbAdapter abrir() throws SQLException
    {
        dbHelper = new HipotecaDbHelper(contexto);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void cerrar()
    {
        dbHelper.close();
    }

    /**
     * Devuelve cursor con todos las columnas de la tabla
     */
    public Cursor getCursor() throws SQLException
    {
        Cursor c = db.query( true, C_TABLA, columnas, null, null, null, null, null, null);
        return c;
    }


    /**
     * Devuelve cursor con todos las columnas del registro
     */
    public Cursor getRegistro(long id) throws SQLException
    {
        Cursor c = db.query( true, C_TABLA, columnas, C_COLUMNA_ID + "=" + id, null, null, null, null, null);

        //Nos movemos al primer registro de la consulta
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


}
