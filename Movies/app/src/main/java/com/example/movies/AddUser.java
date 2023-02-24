package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.List;

public class AddUser extends MenuController {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
    }

    public void saveUsers(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        EditText username =findViewById(R.id.inputusername);
        EditText email =findViewById(R.id.inputemail);
        EditText password =findViewById(R.id.inputpassword);
//        databaseHelper.saveUser(username.getText().toString(),email.getText().toString(),password.getText().toString());
        Intent intent = new Intent(this, UserList.class);
        startActivity(intent);
    }

    @Override
    public void updateAdapter(List<Movie> movies,String keyword) {    }


    @Override
    public void hideMenu(Menu menu){}
}