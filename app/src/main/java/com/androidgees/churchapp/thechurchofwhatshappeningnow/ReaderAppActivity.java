package com.androidgees.churchapp.thechurchofwhatshappeningnow;

/**
 * Created by OLAJUWON on 7/19/2014.
 */

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;





public class ReaderAppActivity extends Fragment {

    private ReaderAppActivity local;
    private ListView mList;
    /**
     * This method creates main application view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //super.onCreate(savedInstanceState);
        // Set view
        //setContentView(R.layout.fragment_rss);





        local = this;
        //int position = getArguments().getInt("position");

       // String url = getArguments().getString("url");

        // List of rivers
        String[] menus = getResources().getStringArray(R.array.menus);

        // Creating view corresponding to the fragment
        View v = inflater.inflate(R.layout.fragment_rss, container, false);
        // Set reference to this activity
        //local = this;

        GetRSSDataTask task = new GetRSSDataTask();

        // Start download RSS task
        task.execute("http://thechurchofwhatshappeningnow.libsyn.com/rss");
        //task.execute(url);

        // Debug the thread name
        Log.d("ITCRssReader", Thread.currentThread().getName());

        //mList = (ListView) findViewById(R.id.rssListMainView);

        return v;
    }

    private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem> > {
        @Override
        protected List<RssItem> doInBackground(String... urls) {

            // Debug the task thread name
            Log.d("ITCRssReader", Thread.currentThread().getName());

            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);

                // Parse RSS, get items
                return rssReader.getItems();

            } catch (Exception e) {
                Log.e("ITCRssReader", e.getMessage());
            }


            return null;
        }

        @Override
        protected void onPostExecute(List<RssItem> result) {

            // Get a ListView from main view
            ListView mList = (ListView) getView().findViewById(R.id.rssListMainView);

            // Create a list adapter
            ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(getActivity(),R.layout.rss_text, result);
            //ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(getActivity(),R.layout.fragment_rss, result);
            // Set list adapter for the ListView
            mList.setAdapter(adapter);

            // Set list view item click listener
            mList.setOnItemClickListener(new ListListener(result, getActivity()));




        }
    }
}


