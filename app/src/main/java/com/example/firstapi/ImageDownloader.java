package com.example.firstapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

class ImageDownloader extends AsyncTask<String, Void, Bitmap>
// первый параметр строка содержит url картинки, второй void – не будем
//показывать процесс прогресса, третий Bitmap – картинка, которую
//загрузим в ImageView
        {
        ImageView bmImage;
public ImageDownloader(ImageView bmImage) {
        this.bmImage = bmImage;
        }

protected Bitmap doInBackground(String... urls) {//фоновый поток
        String url = urls[0];
        Bitmap mIcon = null;
        try {
        InputStream in = new java.net.URL(url).openStream();
        mIcon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
        Log.e("Error", e.getMessage());
        }
        return mIcon;
        }
//после выполнения фоновой операции doInBackground результат попадает в
//onPostExecute, который выполняется в главном потоке и результат
//можно отобразить в интерфейсе
protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
        }
        }