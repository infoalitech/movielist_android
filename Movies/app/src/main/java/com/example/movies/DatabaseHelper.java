package com.example.movies;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends  SQLiteOpenHelper {


    private static String dbName = "MoveDatabase";
    private static int dbVersion = 1;


    private static String UserTable = "User";

    private static String userIdColumn = "id";
    private static String userNameColumn = "name";
    private static String userPasswordColumn = "password";
    private static String userEmailColumn = "email";
    private static String userActorColumn = "actor";



    private static String FilmsTable = "Films";
    private static String filmIdColumn = "id";
    private static String filmTitleColumn = "title";
    private static String filmReleaseYearColumn = "releaseYear";
    private static String filmDurationColumn = "duration";
    private static String filmDirectorColumn = "director";
    private static String filmDistributedByColumn = "distributedBy";
    private static String filmImageColumn = "image";


    public DatabaseHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                " create table " + UserTable + " ( " +
                        userIdColumn + " integer primary key autoincrement, " +
                        userNameColumn + "  text, " +
                        userPasswordColumn + "  text, " +
                        userActorColumn + "  text, " +
                        userEmailColumn + "  text " + " ) "
        );
        sqLiteDatabase.execSQL("create table " + FilmsTable + "(" +
                filmIdColumn +" integer primary key autoincrement, " +
                filmTitleColumn  +"  text, " +
                filmReleaseYearColumn  +"  text ," +
                filmDurationColumn  +"  text, " +
                filmDirectorColumn  +"  text, " +
                filmDistributedByColumn  +"  text, " +
                filmImageColumn  +"  text " +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void saveUser(String username, String email, String password,String actor){

        String query ="Insert into "+ UserTable +"("+ userNameColumn +","+ userEmailColumn +","+
                userPasswordColumn+","+ userActorColumn +") values('"+ username +"','"+ email +"','"+ password +"','"+ actor +"')";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(query);
    }

    public void saveMovie(String filmTitleColumnVal ,String filmReleaseYearColumnVal ,String filmDurationColumnVal ,String filmDirectorColumnVal ,String filmDistributedByColumnVal ,String filmImageColumnVal){

        String query ="Insert into "+ FilmsTable +"("+  filmTitleColumn +","+  filmReleaseYearColumn +","+  filmDurationColumn +","+  filmDirectorColumn +","+  filmDistributedByColumn +","+  filmImageColumn +") " +
                                            "values('"+  filmTitleColumnVal +"','"+  filmReleaseYearColumnVal +"','"+  filmDurationColumnVal +"','"+  filmDirectorColumnVal +"','"+  filmDistributedByColumnVal +"','"+  filmImageColumnVal +"')";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(query);
    }


    public int checkUser(String email, String password){
        System.out.println("Error3 query check  fompo" );
        String query ="SELECT * FROM  "+ UserTable +" where "+
                userEmailColumn +" = '" +email+"' and "+
                userPasswordColumn +" = '"+ password +"'";
        System.out.println("Error3 query check  " + query);

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  " + UserTable + " WHERE " + userEmailColumn + " = '" + email +"' and "+ userPasswordColumn  + " = '" + password + "'",null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            int id=cursor.getInt(0);
            return id;
        }
        return 0;
    }

    public List<User> findAllUsers() {
        List<User> users = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + UserTable, null);
            if (cursor.moveToFirst()) {
                users = new ArrayList<>();
                do {
                    Log.i("cursoe","cursoe"+cursor);
                    User user = new User();
                    user.setId(cursor.getInt(0));
                    user.setName(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    user.setEmail(cursor.getString(3));
                    user.setActor(cursor.getString(4));
                    users.add(user);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            users = null;
        }
        return users;
    }

    public List<User>  getUserById(int id) {
        List<User> users = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + UserTable +" where id = "+ id + "", null);
            if (cursor.moveToFirst()) {
                users = new ArrayList<>();
                do {
                    User user = new User();
                    user.setId(cursor.getInt(0));
                    user.setName(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    user.setEmail(cursor.getString(4));
                    user.setActor(cursor.getString(3));
                    users.add(user);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            users = null;
        }
        return users;
    }

    public List<Movie> getMovieById(int id) {
        List<Movie> movies = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + FilmsTable +" where id = "+ id + "", null);
            if (cursor.moveToFirst()) {
                movies = new ArrayList<>();
                do {
                    Movie movie = new Movie();
                    movie.setId(cursor.getInt(0));
                    movie.setFilmTitleColumn(cursor.getString(1));
                    movie.setFilmReleaseYearColumn(cursor.getString(2));
                    movie.setFilmDurationColumn(cursor.getString(3));
                    movie.setFilmDistributedByColumn(cursor.getString(4));
                    movie.setFilmImageColumn(cursor.getString(5));
                    movies.add(movie);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            movies = null;
        }
        return movies;
    }



    public List<Movie> findAllMovies() {
        List<Movie> movies = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + FilmsTable, null);
            if (cursor.moveToFirst()) {
                movies = new ArrayList<>();
                do {
                    Movie movie = new Movie();
                    movie.setId(cursor.getInt(0));
                    movie.setFilmTitleColumn(cursor.getString(1));
                    movie.setFilmReleaseYearColumn(cursor.getString(2));
                    movie.setFilmDurationColumn(cursor.getString(3));
                    movie.setFilmDistributedByColumn(cursor.getString(4));
                    movie.setFilmImageColumn(cursor.getString(5));
                    movies.add(movie);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            movies = null;
        }
        return movies;
    }

    public List<Movie> searchMovie(String keyword) {
        List<Movie> movies = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String Query="select * from " + FilmsTable  + " where " + filmTitleColumn + " like '%" + keyword + "%'";
            Cursor cursor = sqLiteDatabase.rawQuery(Query, null);
            if (cursor.moveToFirst()) {
                movies = new ArrayList<>();
                do {
                    Movie movie = new Movie();
                    movie.setId(cursor.getInt(0));
                    movie.setFilmTitleColumn(cursor.getString(1));
                    movie.setFilmReleaseYearColumn(cursor.getString(2));
                    movie.setFilmDurationColumn(cursor.getString(3));
                    movie.setFilmDistributedByColumn(cursor.getString(4));
                    movie.setFilmImageColumn(cursor.getString(5));
                    movies.add(movie);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            movies = null;
        }
        return movies;
    }
}
