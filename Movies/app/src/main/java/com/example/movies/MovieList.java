package com.example.movies;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

public class MovieList extends MenuController {

    List<Movie> allMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
            List<Movie> movies = databaseHelper.findAllMovies();
            listViewMovies = findViewById(R.id.listViewMovie);
            MovieListAdapter movieListAdapter=new MovieListAdapter(getApplicationContext(), movies);
            listViewMovies.setAdapter(movieListAdapter);
            this.allMovies=movies;

            listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MovieList.this, MovieDetail.class);
                    String id=String.valueOf(movies.get(i).getId());
                    intent.putExtra("movie_id", id);

                    Toast.makeText(MovieList.this, "jksdl "+movies.get(i).getId(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            });
        }catch (Exception e){
            Toast.makeText(this, "No Movies Yet", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addMovie:
                Intent intent = new Intent(this, MovieAdd.class);
                startActivity(intent);
                Toast.makeText(this, "Add Movie", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void addMovie(View view) {
        Intent intent = new Intent(this, MovieAdd.class);
        startActivity(intent);
    }

    @Override
    public void updateAdapter(List<Movie> movies,String keyword) {
        if(keyword.isEmpty()){
            MovieListAdapter movieListAdapter=new MovieListAdapter(getApplicationContext(), this.allMovies);
            listViewMovies.setAdapter(movieListAdapter);
        }
        MovieListAdapter movieListAdapter=new MovieListAdapter(getApplicationContext(), movies);
        listViewMovies.setAdapter(movieListAdapter);
    }


    @Override
    public void hideMenu(Menu menu){}

}