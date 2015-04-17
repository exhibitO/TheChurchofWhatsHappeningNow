package com.androidgees.churchapp.thechurchofwhatshappeningnow;

/**
 * Created by OLAJUWON on 9/20/2014.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleMenuItem  extends Activity {

    // XML node keys
    static final String KEY_NAME = "name";
    static final String KEY_DATE = "pubdate";
    static final String KEY_DESC = "description";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);

        // getting intent data
        Intent in = getIntent();

        // Get XML values from previous intent
        String name = in.getStringExtra(KEY_NAME);
        String date = in.getStringExtra(KEY_DATE);
        String description = in.getStringExtra(KEY_DESC);

        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblDate = (TextView) findViewById(R.id.date_label);
        TextView lblDesc = (TextView) findViewById(R.id.description_label);

        lblName.setText(name);
        lblDate.setText(date);
        lblDesc.setText(description);
    }
}