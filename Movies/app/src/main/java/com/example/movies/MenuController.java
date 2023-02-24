package com.example.movies;

import android.app.SearchManager;
import android.content.Intent;
//import android.content.SharedPreferences;
import android.os.Bundle;
//import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public abstract class MenuController extends AppCompatActivity {

    protected ListView listViewMovies;
    protected  SearchView searchView;
    Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.searchView).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        Log.i("error ","mesgsearch");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMovie(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchMovie(newText);
                return false;
            }
        });

        this.searchView=searchView;
        this.menu=menu;

//        MenuItem logoutMenu = menu.findItem(R.id.logoutMenu);
//        logoutMenu.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeMenu:
                home(null);
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.usersMenu:
                userList(null);
                Toast.makeText(this, "Users", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.aboutMenu:
                aboutUs(null);
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.addMovie:
                Intent intent = new Intent(this, MovieAdd.class);
                startActivity(intent);
                Toast.makeText(this, "Add Movie", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.helpMenu:
                helpus(null);
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.loginMenu:
//                Login(null);
//                Toast.makeText(this, "Add Movie", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.logoutMenu:
//                Logout(null);
//                return true;
            case R.id.exitMenu:
                exit(null);
                Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Login(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public void Logout(View view) {
        try {
            SessionManager sessionManager=new SessionManager(this);
            sessionManager.removeSession();
        }catch (Exception e){
            System.out.println("Error3 " + e.getMessage());
        }
    }
    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void userList(View view) {
        Intent intent = new Intent(this, UserList.class);
        startActivity(intent);
    }
    public void aboutUs(View view) {
        Intent intent = new Intent(this, Abouts.class);
        startActivity(intent);
    }
    public void helpus(View view) {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }
    public void movieList(View view) {
        Intent intent = new Intent(this, MovieList.class);
        startActivity(intent);
    }
    public void exit(View view) {
        finish();
        System.exit(0);
    }
    public void getMessage(){
        try {
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                String result = extras.getString("Message");
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // This will catch any exception, because they are all descended from Exception
            System.out.println("Error " + e.getMessage());
        }
    }
    private void searchMovie(String keyword) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Movie> movies = databaseHelper.searchMovie(keyword);
        try {
            listViewMovies.setVisibility(View.VISIBLE);
            updateAdapter(movies,keyword);
        }catch (Exception e){
            listViewMovies.setVisibility(View.INVISIBLE);
        }
    }
    public abstract void updateAdapter(List<Movie> movies,String keyword);

    public abstract void hideMenu(Menu menu);


    @Override
    protected void onStart() {
        super.onStart();
    }
}
