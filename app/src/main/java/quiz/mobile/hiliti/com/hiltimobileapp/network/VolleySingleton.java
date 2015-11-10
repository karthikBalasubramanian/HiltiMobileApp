package quiz.mobile.hiliti.com.hiltimobileapp.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import quiz.mobile.hiliti.com.hiltimobileapp.HiltiApplication;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;

/**
 * Created by vaishu on 02-11-2015.
 */
public class VolleySingleton {
    private static VolleySingleton vSingletonInstance = null;
    private ImageLoader imageLoader;
    private RequestQueue mRequestQueue;

    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(HiltiApplication.getAppContext());
        imageLoader = new ImageLoader(mRequestQueue,new BitmapLruCache());

    }
    public static synchronized VolleySingleton getvSingletonInstance(){
        if(vSingletonInstance == null){

            vSingletonInstance = new VolleySingleton();
        }

        return vSingletonInstance;
    }

    public RequestQueue getmRequestQueue(){
        Log.m("inside request queue");
        if(mRequestQueue==null){
            Log.m("request queue null, fetching request");
            Cache cache = new DiskBasedCache(HiltiApplication.getAppContext().getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            // Don't forget to start the volley request queue
            mRequestQueue.start();
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        getmRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
