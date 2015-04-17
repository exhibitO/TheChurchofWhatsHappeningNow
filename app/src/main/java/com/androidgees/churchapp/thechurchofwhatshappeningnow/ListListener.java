package com.androidgees.churchapp.thechurchofwhatshappeningnow;

/**
 * Created by OLAJUWON on 7/19/2014.
 */

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.androidgees.churchapp.thechurchofwhatshappeningnow.RssItem;
import com.androidgees.churchapp.thechurchofwhatshappeningnow.SingleMenuItem;

public class ListListener implements OnItemClickListener {
    // Our listener will contain a reference to the list of RSS Items
    // List item's reference
    List<RssItem> listItems;
    // And a reference to a calling activity
    // Calling activity reference
    Activity activity;
    /** We will set those references in our constructor.*/

    public ListListener(List<RssItem> aListItems, Activity anActivity) {
        listItems = aListItems;
        activity  = anActivity;
    }



    //ListAdapter adapter = new SimpleAdapter(this, List<RssItem> aListItems, )

    /** Start a browser with url from the rss item.*/
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        // We create an Intent which is going to display data
        Intent i = new Intent(Intent.ACTION_VIEW);



       // Intent i = new Intent(view.getContext(), SingleMenuItem.class);
//
//        i.putExtra(KEY_NAME);
//        i.putExtra(KEY_DURATION, Price);
//        i.putExtra(KEY_ARTIST, Description);
      //  activity.startActivity(i);


        //Intent i = new Intent("android.intent.action.MUSIC_PLAYER");
//        .putExtra("url", true);

        // We have to set data for our new Intent
        //OLD
        i.setData(Uri.parse(listItems.get(pos).getLink()));
        // And start activity with our Intent

        //Starts Web activity with link
        //OLD
         activity.startActivity(i);
    }
}