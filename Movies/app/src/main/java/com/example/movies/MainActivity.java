package com.example.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends MenuController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        try {
            SessionManager sessionManager=new SessionManager(this);
            Boolean isSession=sessionManager.isSession();
            System.out.println("Error is session s " + isSession);
            if(isSession == true){
                Button loginButton=findViewById(R.id.loginHomeMenu);
                loginButton.setVisibility(View.GONE);

                Button registerButton=findViewById(R.id.registerHomeMenu);
                registerButton.setVisibility(View.GONE);

            }else{
                Button logoutButton=findViewById(R.id.logoutHomeMenu);
                logoutButton.setVisibility(View.GONE);
            }
        }catch (Exception e){
            // This will catch any exception, because they are all descended from Exception
            System.out.println("Error2 " + e.getMessage());
        }



        try {
            getMessage();
        }catch(Exception e){
            System.out.println("Error3 " + e.getMessage());
        }

    }


    public void logoutHome(View view) {
        try {
            SessionManager sessionManager=new SessionManager(this);
            sessionManager.removeSession();
            home(null);
        }catch (Exception e){
            System.out.println("Error home logout " + e.getMessage());
        }
    }

    public void loginHome(View view) {
        Login(null);
    }

    @Override
    public void updateAdapter(List<Movie> movies,String keyword) {    }


    @Override
    public void hideMenu(Menu menu){}


    public void registerHome(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}