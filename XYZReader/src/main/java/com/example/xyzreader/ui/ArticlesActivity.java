package com.example.xyzreader.ui;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.xyzreader.R;

public class ArticlesActivity extends AppCompatActivity implements ArticlesActivityFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportFragmentManager().findFragmentById(R.id.fragment) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, ArticlesActivityFragment.newInstance(), "MovieGridFragmentTag")
                    .addToBackStack("MovieGridFragmentName")
                    .commit();
        }
    }

}
