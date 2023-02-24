package com.example.movies;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MovieListAdapter extends BaseAdapter {

    Context context;
    int id [];
    String filmTitleColumn [];
    String filmReleaseYearColumn [];
    String filmDurationColumn [];

    public MovieListAdapter (Context ctx, List<Movie> Movies){

        this.context=ctx;
        String[] tmpfilmTitleColumn =new String[Movies.size()];
        String[] tmpfilmReleaseYearColumn =new String[Movies.size()];
        String[] tmpfilmDurationColumn =new String[Movies.size()];
        for (int i = 0; i < Movies.size(); i++) {
//            tmpId[i]=Movies.get(i).getId();
            tmpfilmTitleColumn[i]=Movies.get(i).getFilmTitleColumn();
            tmpfilmReleaseYearColumn[i]=Movies.get(i).getFilmReleaseYearColumn();
            tmpfilmDurationColumn[i]=Movies.get(i).getFilmDurationColumn();
        }
//        this.id=tmpId;
        this.filmTitleColumn =tmpfilmTitleColumn;
        this.filmReleaseYearColumn =tmpfilmReleaseYearColumn;
        this.filmDurationColumn =tmpfilmDurationColumn;
    }
    @Override
    public int getCount() {
        return  this.filmTitleColumn.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater lInflater = (LayoutInflater)context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE);
            view = lInflater.inflate(R.layout.activity_movie_list_view, null);
        }
        TextView filmTitleField= (TextView) view.findViewById(R.id.filmTitleField);
        TextView filmReleaseYearField= (TextView) view.findViewById(R.id.filmReleaseYearField);
        TextView filmDurationField= (TextView) view.findViewById(R.id.filmDurationField);
        filmTitleField.setText(this.filmTitleColumn[i]);
        filmReleaseYearField.setText(this.filmReleaseYearColumn[i]);
        filmDurationField.setText(this.filmDurationColumn[i]);
        return view;
    }
}
