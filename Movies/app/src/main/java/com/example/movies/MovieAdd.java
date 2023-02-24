package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MovieAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_add);
    }

    public void saveMovie(View view){
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        EditText filmTitleColumn=findViewById(R.id.filmTitleColumnId);
        EditText filmReleaseYearColumn=findViewById(R.id.filmReleaseYearColumnId);
        EditText filmDurationColumn=findViewById(R.id.filmDurationColumnId);
        EditText filmDirectorColumn=findViewById(R.id.filmDirectorColumnId);
        EditText filmDistributedByColumn=findViewById(R.id.filmDistributedByColumnId);
        EditText filmImageColumn=findViewById(R.id.filmImageColumnId);
        databaseHelper.saveMovie(filmTitleColumn.getText().toString(),filmReleaseYearColumn.getText().toString(),filmDurationColumn.getText().toString(),filmDirectorColumn.getText().toString(),filmDistributedByColumn.getText().toString(),filmImageColumn.getText().toString() );
        Intent intent = new Intent(this, MovieList.class);
        startActivity(intent);
    }

}