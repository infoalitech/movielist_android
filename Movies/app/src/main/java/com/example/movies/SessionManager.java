package com.example.movies;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;

import java.util.List;

public class SessionManager extends  MenuController {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME="session";
    String SESSION_KEY="session_user";

    Context context;
    public SessionManager(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
    }
    public boolean saveSession(String email, String password){
        DatabaseHelper databaseHelper = new DatabaseHelper(this.context);
        int isUserExist = databaseHelper.checkUser(email,password);
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt("SESSION_KEY",isUserExist);
        editor.commit();
        if(isUserExist == 0){
            return false;
        }
        return true;
    }
    public boolean isSession(){
        int user_id=sharedPreferences.getInt("SESSION_KEY",-1);
        if(user_id == -1){
            return false;
        }
        return true;
    }

    public int getUserId(){
        int user_id=sharedPreferences.getInt("SESSION_KEY",-1);
        return  user_id;
    }
    public boolean removeSession(){
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt(SESSION_KEY,-1);
        editor.clear();
        editor.commit();
        return true;
    }

    @Override
    public void updateAdapter(List<Movie> movies,String keyword) {    }

    @Override
    public void hideMenu(Menu menu){}
}
