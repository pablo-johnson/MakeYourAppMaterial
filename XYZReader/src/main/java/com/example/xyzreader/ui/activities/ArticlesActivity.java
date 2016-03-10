package com.example.xyzreader.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.xyzreader.R;
import com.example.xyzreader.ui.fragments.ArticlesFragment;

public class ArticlesActivity extends AppCompatActivity implements ArticlesFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportFragmentManager().findFragmentById(R.id.fragment) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, ArticlesFragment.newInstance(), "MovieGridFragmentTag")
                    .addToBackStack("MovieGridFragmentName")
                    .commit();
        }
    }

}
