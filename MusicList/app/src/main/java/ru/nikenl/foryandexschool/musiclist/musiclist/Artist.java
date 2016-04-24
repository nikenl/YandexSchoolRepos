package ru.nikenl.foryandexschool.musiclist.musiclist;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nikenl on 10.04.2016.
 */
public class Artist {

    public static ArrayList<Artist> artistsDB = new ArrayList<>();      //list of all artists, getting from JSON

    int id;
    String name;
    String genres;
    int songs;
    int albums;
    String link;
    String description;
    String cover_small;
    String cover_big;

    public Artist(int id, String name, String genres, int songs, int albums, String link, String description, String cover_small, String cover_big) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.songs = songs;
        this.albums = albums;
        this.link = link;
        this.description = description;
        this.cover_small = cover_small;
        this.cover_big = cover_big;
    }

    public static Artist getArtist(int id){
        for(Artist a : artistsDB){
            if (a.id == id){
                return a;
            }
        }
        return null;
    }
}
