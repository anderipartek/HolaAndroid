package com.micros.ipartek.holamundo.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * clase controladora de la base de datos
 */
public class HipotecaDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "HipotecaDbHelper";

    private static int version = 2;
    private static String name = "hipoteca.sqlite" ;
    private static CursorFactory factory = null;

    public HipotecaDbHelper(Context context)
    {
        super(context, name, factory, version);
    }

    //Se ejecuta la primera vez para crear la bbdd
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(TAG, "Creando base de datos");

        db.execSQL( "CREATE TABLE HIPOTECA(" +
                " _id INTEGER PRIMARY KEY," +
                " hip_nombre TEXT NOT NULL, " +
                " hip_condiciones TEXT, " +
                " hip_contacto TEXT," +
                " hip_email TEXT," +
                " hip_telefono TEXT," +
                " hip_observaciones TEXT)" );

        db.execSQL( "CREATE UNIQUE INDEX hip_nombre ON HIPOTECA(hip_nombre ASC)" );

        Log.i(TAG, "Tabla HIPOTECA creada");

       /*
        * Insertamos datos iniciales
        */
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(1,'Santander')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(2,'BBVA')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(3,'La Caixa')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(4,'Cajamar')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(5,'Bankia')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(6,'Banco Sabadell')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(7,'Banco Popular')");

        Log.i(TAG, "Datos iniciales HIPOTECA insertados");

        Log.i(TAG, "Fin onCreate");
    }

    //se ejecuta si incrementamos la version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (newVersion == 2)
        {
            db.execSQL("UPDATE HIPOTECA SET hip_contacto = 'Julián Gómez Martínez'," +
                    "              hip_email = 'jgmartinez@gmail.com'," +
                    "              hip_observaciones = 'Tiene toda la documentación y está estudiando la solicitud. En breve llamará para informar de las condiciones'" +
                    " WHERE _id = 1");
        }
    }
}
