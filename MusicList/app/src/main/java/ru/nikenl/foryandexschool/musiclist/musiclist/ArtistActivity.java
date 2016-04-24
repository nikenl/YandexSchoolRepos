package ru.nikenl.foryandexschool.musiclist.musiclist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ArtistActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        int id = Integer.parseInt(getIntent().getExtras().getString("artist_id")); //getting an ID of selected artist

        Artist artist = Artist.getArtist(id);       //getting artist data from ArrayList

        setTitle(artist.name);      //title of activity


        ImageView iv = (ImageView)findViewById(R.id.artistImage);
        Picasso.with(this)                   //http://square.github.io/picasso/   - library for downloading images
                .load(artist.cover_big)
                .placeholder(R.drawable.loading_icon)
                .error(R.drawable.error)
                .into(iv);


        TextView tvGenreTags = (TextView)findViewById(R.id.artistGenresTags);
        tvGenreTags.setText(artist.genres);

        TextView tvAlbums = (TextView)findViewById(R.id.artistAlb);
        tvAlbums.setText(artist.albums+CorrectNumberSpelling.albSpelling(this, artist.albums)+", ");

        TextView tvSongs = (TextView)findViewById(R.id.artistSongs);
        tvSongs.setText(artist.songs+CorrectNumberSpelling.songSpelling(this, artist.songs));

        TextView tvDescr = (TextView)findViewById(R.id.artistDescr);
        tvDescr.setText(artist.description);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

}
