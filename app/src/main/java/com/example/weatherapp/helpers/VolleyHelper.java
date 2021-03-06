package com.example.weatherapp.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleyHelper {
    private static VolleyHelper instance = null;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public static VolleyHelper getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyHelper(context);
        }
        return instance;
    }

    private VolleyHelper(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(this.requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }
        });
    }


    public RequestQueue getRequestQueue() {
        return this.requestQueue;
    }

    public ImageLoader getImageLoader() {
        return this.imageLoader;
    }
}
