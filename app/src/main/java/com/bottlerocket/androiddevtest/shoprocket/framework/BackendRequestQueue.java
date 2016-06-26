package com.bottlerocket.androiddevtest.shoprocket.framework;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class BackendRequestQueue {


    private static BackendRequestQueue mRequestQueueInstance;
    private        RequestQueue        mRequestQueue;
    private static Context             mContext;
    private        ImageLoader         mImageLoader;

    /**
     * @param context
     */
    private BackendRequestQueue(Context context) {

        mContext = context;
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(this.getRequestQueue(), new ImageLoader.ImageCache() {

            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

            @Override
            public Bitmap getBitmap(String s) {
                return mCache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

                mCache.put(s,bitmap);
            }
        });
    }

    /**
     * @param context
     * @return
     */
    public static synchronized BackendRequestQueue getInstance(Context context) {
        if (mRequestQueueInstance == null) {
            mRequestQueueInstance = new BackendRequestQueue(context);
        }
        return mRequestQueueInstance;
    }

    /**
     * @return
     */
    public RequestQueue getRequestQueue() {

        if (null == mRequestQueue) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }


    /**
     * @param req
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {

        getRequestQueue().add(req);

    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void setImageLoader(ImageLoader mImageLoader) {
        this.mImageLoader = mImageLoader;
    }
}
