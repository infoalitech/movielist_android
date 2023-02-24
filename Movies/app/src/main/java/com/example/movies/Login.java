package com.example.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Login extends MenuController {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void loginClicked(View view){

        System.out.println("Error fompo test login");
        EditText email =findViewById(R.id.editTextEmailAddressid);
        EditText password = findViewById(R.id.editTextEmailAddressid);

//        if(email.getText().length() < 8){
//            TextView errorView=findViewById(R.id.loginerror);
//            errorView.setText("Minimum Password Length should be 8");
//            errorView.setVisibility(View.VISIBLE);
//            errorView.postDelayed(new Runnable() {
//                public void run() {
//                    errorView.setVisibility(View.INVISIBLE);
//                }
//            }, 3000);
//        }

        SessionManager sessionManager=new SessionManager(Login.this);
        Boolean loginState= sessionManager.saveSession(email.getText().toString(),password.getText().toString());

        System.out.println("Error loginState" + loginState);
        Toast.makeText(this, "Login start", Toast.LENGTH_SHORT).show();


        if(loginState == false){
            TextView errorView=findViewById(R.id.loginerror);
            errorView.setText("Invalid Credentials");
            errorView.setVisibility(View.VISIBLE);
            errorView.postDelayed(new Runnable() {
                public void run() {
                    errorView.setVisibility(View.INVISIBLE);
                }
            }, 3000);
        }else{
            Toast.makeText(sessionManager, "Login Successfull", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Message", "Login Successfully");
            startActivity(intent);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

//        SessionManager sessionManager=new SessionManager(Login.this);
//        Boolean isSession=sessionManager.isSession();
//        if(isSession){
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra("Message", "Login Successfully");
//            startActivity(intent);
//        }

    }

    @Override
    public void updateAdapter(List<Movie> movies,String keyword) {    }

    @Override
    public void hideMenu(Menu menu){}
}