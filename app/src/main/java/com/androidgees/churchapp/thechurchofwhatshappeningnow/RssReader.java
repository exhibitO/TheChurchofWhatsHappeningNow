package com.androidgees.churchapp.thechurchofwhatshappeningnow;

/**
 * Created by OLAJUWON on 7/19/2014.
 */

import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;



public class RssReader {
    // Our class has an attribute which represents RSS Feed URL
    private String rssUrl;



    public RssReader(String rssUrl) {



        this.rssUrl = rssUrl;
    }


/**
     * We set this URL with the constructor
     *


/**
     * Get RSS items. This method will be called to get the parsing process result.
     * @return
     */

    public List<RssItem> getItems() throws Exception {
        // At first we need to get an SAX Parser Factory object
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            // Using factory we create a new SAX Parser instance
            SAXParser saxParser = factory.newSAXParser();
            // We need the SAX parser handler object
            RssParseHandler handler = new RssParseHandler();
            // We call the method parsing our RSS Feed
            saxParser.parse(rssUrl, handler);
            // The result of the parsing process is being stored in the handler object
            return handler.getItems();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

/*
public class RssReader extends BaseFeedParser {


    protected RssReader(String feedUrl){
        super(feedUrl);
    }

    public List<Message> parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            RssParseHandler handler = new RssParseHandler();
            parser.parse(this.getInputStream(), handler);
            return handler.getMessages();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}*/
