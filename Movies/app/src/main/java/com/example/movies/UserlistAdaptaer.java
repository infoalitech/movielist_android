package com.example.movies;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class UserlistAdaptaer extends BaseAdapter {

    Context context;
    String emails [];
    String names [];
    List<User> users;
    LayoutInflater inflater;

    public UserlistAdaptaer (Context ctx, List<User> users){
        this.context=ctx;
//        this.fruitList=fruitList;
//        this.images=images;
        this.users=users;
//        Log.i("userss", String.valueOf(users.size()));
//        users.
        String[] tmpEmails =new String[users.size()];
        String[] tmpNames =new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
        Log.i("email:", users.get(i).getEmail());
            tmpEmails[i]=users.get(i).getEmail();
            tmpNames[i]=users.get(i).getName();
        }
        this.names=tmpNames;
        this.emails=tmpEmails;

        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return  this.names.length;
//        return 0;
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

            view = lInflater.inflate(R.layout.activity_user_list_view, null);
        }
        TextView textView= (TextView) view.findViewById(R.id.emailField);
        TextView nameView= (TextView) view.findViewById(R.id.nameField);
        textView.setText(this.names[i]);
        nameView.setText(this.emails[i]);
        return view;
    }
}
