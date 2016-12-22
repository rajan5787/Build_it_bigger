package com.example.nishtha.builditbigger;

import android.os.AsyncTask;

import com.example.nishtha.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String>{
    private static MyApi myApiService = null;
    public AsyncResponse delegate=null;
    String joke;
    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {
      MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-140313.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getMyJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        //Log.d("hello",joke);
        this.joke=joke;
        delegate.processFinish(joke);
    }
}
