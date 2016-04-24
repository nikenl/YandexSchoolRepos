package ru.nikenl.foryandexschool.musiclist.musiclist;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    static String jsonHTTP = "http://cache-default04g.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.json";
    static ListView lvMain;
    static Context context;

    //AsyncTask object for downloading data
    GetArtistsFromJSON getArtistsFromJsonAsync = new GetArtistsFromJSON();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMain = (ListView)findViewById(R.id.lvArtistsList);

    //download data from JSON to ArrayList<Artist> in AsyncTask
        getArtistsFromJsonAsync.execute();


        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //Making animation of appearing for second activity
                ActivityOptions animOptions = ActivityOptions.makeCustomAnimation(
                        MainActivity.this, R.anim.slide_in_left, R.anim.slide_out_left);

                TextView tv = (TextView) view.findViewById(R.id.artistName);
                Intent intent = new Intent(MainActivity.this, ArtistActivity.class);

                intent.putExtra("artist_id",tv.getTag().toString());

                startActivity(intent,animOptions.toBundle());
            }
        });

    }

    @Override
    protected void onDestroy() {
        getArtistsFromJsonAsync.cancel(true);
        super.onDestroy();
    }
}
