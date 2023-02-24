package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getSupportActionBar().hide();

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String movie_ids =extras.getString("movie_id");
            Toast.makeText(this, "movie_id : " + movie_ids, Toast.LENGTH_SHORT).show();

            int movie_id=Integer.parseInt(movie_ids);
            //The key argument here must match that used in the other activity
            if(movie_id != -1){
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                List<Movie>  movies= databaseHelper.getMovieById(movie_id);

                    TextView filmTitleFieldDetail= (TextView) findViewById(R.id.filmTitleFieldDetail);
                    TextView filmReleaseYearFieldDetail= (TextView) findViewById(R.id.filmReleaseYearFieldDetail);
                    TextView filmDurationFieldDetail= (TextView) findViewById(R.id.filmDurationFieldDetail);
                    TextView filmDistributedByColumnDetail= (TextView) findViewById(R.id.filmDistributedByColumnDetail);
                    TextView filmImageColumnDetail= (TextView) findViewById(R.id.filmImageColumnDetail);

                    filmTitleFieldDetail.setText(movies.get(0).getFilmTitleColumn());
                    filmReleaseYearFieldDetail.setText(movies.get(0).getFilmReleaseYearColumn());
                    filmDurationFieldDetail.setText(movies.get(0).getFilmDurationColumn());
                    filmDistributedByColumnDetail.setText(movies.get(0).getFilmDistributedByColumn());
                    filmImageColumnDetail.setText(movies.get(0).getFilmImageColumn());
            }
        }
    }
}