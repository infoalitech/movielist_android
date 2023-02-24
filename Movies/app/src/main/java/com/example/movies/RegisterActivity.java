package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends MenuController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void saveUsers(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        EditText username =findViewById(R.id.inputusernameReg);
        EditText email =findViewById(R.id.inputemailReg);
        EditText password =findViewById(R.id.inputpasswordReg);
        EditText actor =findViewById(R.id.inputActor);

        boolean iserror=true;
        if(emailValidator(email.getText().toString()) == false){
            TextView errorView=findViewById(R.id.registererror);
            errorView.setText("Please Enter a Valid Email Address");
            errorView.setVisibility(View.VISIBLE);
            errorView.postDelayed(new Runnable() {
                public void run() {
                    errorView.setVisibility(View.INVISIBLE);
                }
            }, 3000);
            iserror=false;
        }
        if(password.getText().length() < 8){
            TextView errorView=findViewById(R.id.registererror);
            errorView.setText("Minimum Password Length should be 8");
            errorView.setVisibility(View.VISIBLE);
            errorView.postDelayed(new Runnable() {
                public void run() {
                    errorView.setVisibility(View.INVISIBLE);
                }
            }, 3000);
            iserror=false;
        }
        if(iserror){
            databaseHelper.saveUser(username.getText().toString(),email.getText().toString(),password.getText().toString(),actor.getText().toString());
            SessionManager sessionManager=new SessionManager(this);
            Boolean loginState= sessionManager.saveSession(email.getText().toString(),password.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Message", "Registered Successfully");
            startActivity(intent);
        }
    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @Override
    public void updateAdapter(List<Movie> movies, String keyword) {    }


    @Override
    public void hideMenu(Menu menu){}
}