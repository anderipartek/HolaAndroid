package com.micros.ipartek.holamundo.preferencias;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.micros.ipartek.holamundo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

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


            final EditTextPreference et = (EditTextPreference) findPreference("ultima_actualizacion_preference");
            et.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String formatted = format.format( new Date());
                    et.setSummary(formatted);
                    return true;
                }
            });


            final DatePreference dp= (DatePreference) findPreference("fecha_actualizacion_preference");
            dp.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    //your code to change values.
                    //dp.setText(new Date().toString() );
                    dp.setSummary("actulizado");
                    return true;
                }
            });


        }




    }
}
