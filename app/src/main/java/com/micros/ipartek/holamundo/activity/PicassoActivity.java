package com.micros.ipartek.holamundo.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import com.squareup.picasso.Picasso;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.micros.ipartek.holamundo.R;

public class PicassoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        ImageView imageView = (ImageView) findViewById(R.id.imageView_picasso);
/*
        Picasso.with(this)
                .load("https://lh3.googleusercontent.com/gjO-p8gTNMrCup5OfRBG7FFEYTlD7yAGadwza3rp1Pw=w640-h222-no")
                .into(imageView);
            */
    }


}
