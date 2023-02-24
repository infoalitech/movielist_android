package com.example.movies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Abouts extends MenuController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abouts);

        SessionManager sessionManager=new SessionManager(this);
        int user_id=sessionManager.getUserId();
        if(user_id != -1){
            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
            List<User> user = databaseHelper.getUserById(user_id);
            if(user != null){
                if(user.size() > 0){
                    TextView name= (TextView) findViewById(R.id.usernameTextViewAbout);
                    name.setText(user.get(0).getName());
                    TextView email= (TextView) findViewById(R.id.emailTextviewAbout);
                    email.setText(user.get(0).getEmail());

                    TextView actorTextviewAbout= (TextView) findViewById(R.id.actorTextviewAbout);
                    actorTextviewAbout.setText(user.get(0).getActor());


                    new doIT(user.get(0).getActor()).execute();
                }
            }

        }


    }
    @Override
    public void updateAdapter(List<Movie> movies,String keyword) {    }

    @Override
    public void hideMenu(Menu menu){}


    public class doIT extends AsyncTask<Void,Void,Void>{
        String words;
        String title;
        String actor_name="robert";
        Document document = null;

        public doIT(String actor) {
            this.actor_name = actor;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                document= Jsoup.connect("https://www.imdb.com/find?s=nm&q="+actor_name+"&ref_=nv_sr_sm").get();
                //                words = document.();
                title = "Actor Not Found";

                if(document != null){
                    Element findSection = document.select(".findResult.odd").first();
                    if(findSection != null) {
                        Elements aTag = findSection.select(".result_text  a");

                        if(aTag != null) {
                            String detailPageUrl = "";
                            detailPageUrl = aTag.attr("abs:href");

                            Document doc2 = Jsoup.connect(detailPageUrl).get();
                            Element doc2findSection = doc2.select(".name-trivia-bio-text").first();
                            if(doc2findSection != null) {
                                title = doc2findSection.text();
                            }
                        }
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView scrapingText= (TextView) findViewById(R.id.scrapingText);
            scrapingText.setText(title);
        }
    }

}