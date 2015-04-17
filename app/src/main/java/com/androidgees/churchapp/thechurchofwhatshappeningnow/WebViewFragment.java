package com.androidgees.churchapp.thechurchofwhatshappeningnow;

/**
 * Created by OLAJUWON on 7/10/2014.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Retrieving the currently selected item number
        int position = getArguments().getInt("position");

        String url = getArguments().getString("url");

        // List of rivers
        String[] menus = getResources().getStringArray(R.array.menus);

        // Creating view corresponding to the fragment
        View v = inflater.inflate(R.layout.fragment_layout, container, false);

        // Updating the action bar title
        getActivity().getActionBar().setTitle(menus[position]);

        //Initializing and loading url in webview
        WebView webView = (WebView) v.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        //
        // webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setMinimumFontSize(20);




        //true makes the Webview have a normal viewport such as a normal desktop browser
        //when false the webview will have a viewport constrained to it's own dimensions
        webView.getSettings().setUseWideViewPort(true);

       //webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return v;
    }
}
