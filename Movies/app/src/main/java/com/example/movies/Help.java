package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

import java.util.List;

public class Help extends MenuController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    @Override
    public void updateAdapter(List<Movie> movies,String keyword) {    }


    @Override
    public void hideMenu(Menu menu){}
}