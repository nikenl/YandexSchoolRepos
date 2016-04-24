package ru.nikenl.foryandexschool.musiclist.musiclist;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by nikenl on 20.04.2016.
 */
public class GetArtistsFromJSON extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... v) {
        InputStream is;
        URL url;
        String jsonData;
        try {

            url = new URL(MainActivity.jsonHTTP);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(100000);
            connection.setConnectTimeout(100000);
            connection.setRequestMethod("GET");
            connection.setUseCaches(true);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                is = connection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                sb.append(bufferedReader.readLine());
                jsonData = sb.toString();

                Artist.artistsDB = getDataFromJSON(jsonData);

                if(Artist.artistsDB==null) return MainActivity.context.getString(R.string.JSONElementError);    //checking error of JSON parsing
                return "";  //no errors
            }else {
                return connection.getResponseMessage() + " . Error Code : " + responseCode; //404
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String error) {
        if(error.equals("")) { //no errors
            ListItemAdapter listItemAdapter = new ListItemAdapter(MainActivity.context);
            MainActivity.lvMain.setAdapter(listItemAdapter);
        }else{  //show error message
            Toast toast =Toast.makeText(MainActivity.context,error, Toast.LENGTH_LONG);
            toast.show();
        }
        super.onPostExecute(error);
    }

    public ArrayList<Artist> getDataFromJSON(String JSON_string){

        try{
            ArrayList<Artist> artists = new ArrayList<>();

            JSONArray data = new JSONArray(JSON_string);

            for(int i = 0; i<data.length(); i++){
                if (isCancelled())
                    return null;

                ArrayList<String> arGenres = new ArrayList<>();
                JSONObject c = data.getJSONObject(i);


        //getting id, name and images of artist. If error - skip this artist
                int id;
                String name;
                JSONObject jsonCover;
                String imgSmall;
                String imgBig;
                try {
                    id = c.getInt("id");
                    name = c.getString("name");
                    jsonCover = c.getJSONObject("cover");
                    imgSmall = jsonCover.getString("small");
                    imgBig = jsonCover.getString("big");
                }catch (JSONException e){
                    continue;
                }

        //getting genres as String. If error - empty string
                String strGenres="";
                try {
                    JSONArray jsonGenres = c.getJSONArray("genres");
                    for(int j = 0; j<jsonGenres.length(); j++) {
                        arGenres.add(jsonGenres.getString(j));
                    }
                    strGenres = arGenres.toString().replace("[","").replace("]","");
                }catch (JSONException e){
                    e.printStackTrace();
                }

        //getting number of songs(tracks). If error: songs = -1
                int tracks = -1;
                try {
                    tracks = c.getInt("tracks");
                }catch (JSONException e){
                }
        //getting number of albums. If error: albums = -1
                int albums = -1;
                try {
                    albums = c.getInt("albums");
                }catch (JSONException e){
                }

        //getting description. If error - empty string
                String description="";
                try {
                    description = c.getString("description");
                }catch (JSONException e){
                }

        //getting link. If error - empty string
                String strLink = "";
                try {
                    strLink = c.getString("link");
                }catch (JSONException e){}

        //making Artist object with all collecting data
                artists.add(new Artist(
                        id,
                        name,
                        strGenres,
                        tracks,
                        albums,
                        strLink,
                        description,
                        imgSmall,
                        imgBig
                ));
            }
            return artists;
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
