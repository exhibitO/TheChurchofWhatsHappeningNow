package com.androidgees.churchapp.thechurchofwhatshappeningnow;

/**
 * Created by OLAJUWON on 3/9/2015.
 */
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Intent;

public class MainActivity extends Activity {

    // Within which the entire activity is enclosed
    private DrawerLayout mDrawerLayout;

    // ListView represents Navigation Drawer
    private ListView mDrawerList;

    // ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
    private ActionBarDrawerToggle mDrawerToggle;

    // Title of the action bar
    private String mTitle = "";


    /*
    TODO: Are these fragments needed?
     */
    //fragments
    private FragmentManager webFragementManager;
    private FragmentManager rssFragementManager;

    final String initialFragment = "com.androidgees.churchapp.thechurchofwhatshappeningnow.ReaderAppActivity";


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTitle = "Welcome to Church!";
        getActionBar().setTitle(mTitle);


        // Getting reference to the DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerList = (ListView) findViewById(R.id.drawer_list);

        // create image view for first look

/*        ImageView splashImage = (ImageView)findViewById(R.id.splash_img);
        splashImage.setImag eResource(R.drawable.diaz);*/


        // Getting reference to the ActionBarDrawerToggle
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {

            /** Called when drawer is closed */
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();


            }

            /** Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle("Welcome to Church!");
                invalidateOptionsMenu();
            }

        };

        // Setting DrawerToggle on DrawerLayout
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Creating an ArrayAdapter to add items to the listview mDrawerList
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                R.layout.drawer_list_item, getResources().getStringArray(R.array.menus));

        // Setting the adapter on mDrawerList
        mDrawerList.setAdapter(adapter);

        // Enabling Home button
        getActionBar().setHomeButtonEnabled(true);

        // Enabling Up navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);


        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this, initialFragment));
        tx.commit();


        // Setting item click listener for the listview mDrawerList
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // Getting an array of rivers
                String[] menuItems = getResources().getStringArray(R.array.menus);

                // Currently selected river
                mTitle = menuItems[position];


                //Check for Active Internet Connection, if so proceed, if not throw toast and exit.
                //if (isConnectedToInternet()) {


                switch (position) {
                    //if (position == 0) {
                    case 0:
                        // Creating a fragment object
                        ReaderAppActivity linkFragment = new ReaderAppActivity();

                        // Passing selected item information to fragment
                        //Bundle data = new Bundle();
                        //data.putInt("position", position);
                        //data.putString("url", getUrl(position));


                        // Getting reference to the FragmentManager
                        FragmentManager fragmentManager1 = getFragmentManager();

                        // Creating a fragment transaction
                        FragmentTransaction fl = fragmentManager1.beginTransaction();

                        // Adding a fragment to the fragment transaction
                        fl.replace(R.id.content_frame, linkFragment);
                        // Committing the transaction
                        fl.commit();
                        break;


                    //else {
                    // Creating a fragment object
                    default:
                        WebViewFragment rFragment = new WebViewFragment();

                        // Passing selected item information to fragment
                        Bundle data = new Bundle();
                        data.putInt("position", position);
                        data.putString("url", getUrl(position));
                        rFragment.setArguments(data);


                        // Getting reference to the FragmentManager
                        FragmentManager fragmentManager2 = getFragmentManager();
                        //webFragementManager = getFragmentManager();

                        // Creating a fragment transaction
                        FragmentTransaction ft = fragmentManager2.beginTransaction();
                        // FragmentTransaction fragmentTransaction = webFragementManager.beginTransaction();

                        // Adding a fragment to the fragment transaction
                        ft.replace(R.id.content_frame, rFragment);
                        // Committing the transaction
                        ft.commit();
                        break;
                }


                // Closing the drawer
                mDrawerLayout.closeDrawer(mDrawerList);
                /*} else {

                    Toast.makeText(MainActivity.this,
                            "Please Check your Internet Connection, an Active Connection is Required to Run this Application, cocksucka!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }*/
            }


        });



    }


    protected String getUrl(int position) {
        switch (position) {
            case 0:
                return "http://thechurchofwhatshappeningnow.libsyn.com/rss";
            case 1:
                return "http://joeydiaz.net/tour/";
            case 2:
                return "http://joeydiaz.net/bio/";
            default:
                return "http://joeydiaz.net/";
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //switch (item.getItemId())

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Coming soon..",
                        Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Called whenever we call invalidateOptionsMenu()
     */

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}