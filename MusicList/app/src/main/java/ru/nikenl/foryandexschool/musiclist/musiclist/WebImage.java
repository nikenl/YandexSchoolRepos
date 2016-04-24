package ru.nikenl.foryandexschool.musiclist.musiclist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebImage extends AsyncTask<String, Void, Bitmap> {

    ImageView iv;

    public WebImage(ImageView imageView){
        iv = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Bitmap doInBackground(String... src) {
        Bitmap bitmap = null;
        URL url;
        try {
            url = new URL(src[0]);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    protected void onPostExecute(Bitmap result) {
        iv.setImageBitmap(result);
    }
}
