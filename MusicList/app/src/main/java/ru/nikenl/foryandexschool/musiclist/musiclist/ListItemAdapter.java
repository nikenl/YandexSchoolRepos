package ru.nikenl.foryandexschool.musiclist.musiclist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by nikenl on 10.04.2016.
 */
public class ListItemAdapter extends BaseAdapter{

    Context ctx;
    LayoutInflater layoutInflater;
    ArrayList<Artist> objects;

    ListItemAdapter(Context context){                                //debug
        ctx = context;
        objects = Artist.artistsDB;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.artist, parent, false);
        }
        Artist artist = getArtist(position);

        ImageView iv = (ImageView) view.findViewById(R.id.artistImage);


        Picasso.with(ctx)                   //http://square.github.io/picasso/   - library for downloading images
                .load(artist.cover_small)
                .placeholder(R.drawable.loading_icon)
                .error(R.drawable.error)
                .into(iv);


        ((TextView) view.findViewById(R.id.artistName)).setText(artist.name);
        (view.findViewById(R.id.artistName)).setTag(artist.id);

        ((TextView) view.findViewById(R.id.artistGenresTags)).setText(artist.genres);
        if(artist.albums>=0){// -1 means error in JSON
            ((TextView) view.findViewById(R.id.artistAlb)).setText(artist.albums+" "+CorrectNumberSpelling.albSpelling(ctx, artist.albums)+", ");
        }else{
            ((TextView) view.findViewById(R.id.artistAlb)).setText("");
        }
        if(artist.songs>=0) {// -1 means error in JSON
            ((TextView) view.findViewById(R.id.artistSongs)).setText(artist.songs + " " + CorrectNumberSpelling.songSpelling(ctx, artist.songs));
        }else{
            ((TextView) view.findViewById(R.id.artistSongs)).setText("");
        }
        return view;
    }

    Artist getArtist(int position){
        return ((Artist) getItem(position));
    }
}
