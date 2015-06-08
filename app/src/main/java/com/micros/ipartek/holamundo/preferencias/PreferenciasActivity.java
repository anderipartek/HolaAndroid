package com.micros.ipartek.holamundo.preferencias;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.micros.ipartek.holamundo.R;

/**
 * Created by ur00 on 03/06/2015.
 */
public class PreferenciasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PreferenciasFragment()).commit();
    }


    public static class PreferenciasFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);

            // Load the preferencias from an XML resource
            addPreferencesFromResource(R.xml.preferencias);




        }




    }
}
