package com.example.nishtha.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nishtha.telljokes.TellJoke;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements AsyncResponse {

    EndpointsAsyncTask endpoint;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button b = (Button) root.findViewById(R.id.jokeButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endpoint = new EndpointsAsyncTask();
                endpoint.delegate = MainFragment.this;
                endpoint.execute();
            }
        });
     return root;
    }



    @Override
    public void processFinish(String joke) {
//        JokeProvider jokeProvider = new JokeProvider();
        Intent i = new Intent(getContext(), TellJoke.class);
        i.putExtra("joke", joke);
        startActivity(i);
    }
}
