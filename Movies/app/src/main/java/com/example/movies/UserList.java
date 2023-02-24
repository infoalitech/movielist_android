package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class UserList extends MenuController {

    private ListView listViewUser;
    String fruits []= {"grapes","apple"};
    String images []= {"1","2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
            List<User> users = databaseHelper.findAllUsers();
//        Log.i("jdslkf","users:"+users);
            listViewUser = findViewById(R.id.listViewUser);
            UserlistAdaptaer userlistAdaptaer=new UserlistAdaptaer(getApplicationContext(), users);
            listViewUser.setAdapter(userlistAdaptaer);
        }catch (Exception e){
            Toast.makeText(this, "No Users", Toast.LENGTH_SHORT).show();
        }

        hideMenu(menu);

    }
    @Override
    public void hideMenu(Menu menu){
//        MenuItem searchView = menu.findItem(R.id.searchView);
//        searchView.setVisible(false);
//        MenuItem addMovie = menu.findItem(R.id.addMovie);
//        addMovie.setVisible(false);
    }
    public void addUsers(View view) {
        Intent intent = new Intent(this, AddUser.class);
        startActivity(intent);
    }

    @Override
    public void updateAdapter(List<Movie> movies,String keyword) {    }



}