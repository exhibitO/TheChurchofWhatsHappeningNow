package com.androidgees.churchapp.thechurchofwhatshappeningnow;

/**
 * Created by OLAJUWON on 7/19/2014.
 */

import android.os.Message;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/*
public class RssParseHandler extends DefaultHandler {

    // List of items parsed
    private List<RssItem> rssItems;
    // We have a local reference to an object which is constructed while parser is working on an item tag
    // Used to reference item while parsing
    private RssItem currentItem;
    // We have two indicators which are used to differentiate whether a tag title or link is being processed by the parser
    // Parsing title indicator
    private boolean parsingTitle;
    // Parsing link indicator
    private boolean parsingLink;

    public RssParseHandler() {
        rssItems = new ArrayList();
    }
    // We have an access method which returns a list of items that are read from the RSS feed. This method will be called when parsing is done.
    public List<RssItem> getItems() {
        return rssItems;
    }
    // The StartElement method creates an empty RssItem object when an item start tag is being processed. When a title or link tag are being processed appropriate indicators are set to true.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            currentItem = new RssItem();
        } else if ("title".equals(qName)) {
            parsingTitle = true;

        }


       else if ("url".equals(qName)) {
            parsingTitle = true;
   
        }


        else if ("link".equals(qName)) {
            parsingLink = true;
        }

        //parsing enclosure tag
        else if ("enclosure".equals(qName)) {
            // Get tags attributes number
            int attrsLength = attributes.getLength();
            for (int i = 0; i < attrsLength; i++) {
                String attrName = attributes.getQName(i);  // attribute name
                if ("url".equals(attrName))   // This tag has only one attribute but it is better to check it name is correct
                    currentItem.setAudioUrl(attributes.getValue(i));

            }


        }

    }

    //The EndElement method adds the  current RssItem to the list when a closing item tag is processed. It sets appropriate indicators to false -  when title and link closing tags are processed

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {
            rssItems.add(currentItem);
            currentItem = null;
        } else if ("title".equals(qName)) {
            parsingTitle = false;

        } else if ("url".equals(qName)) {
            parsingTitle = false;


        } else if ("link".equals(qName)) {
            parsingLink = false;
        }
    }
    // Characters method fills current RssItem object with data when title and link tag content is being processed
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (parsingTitle) {
            if (currentItem != null)
                currentItem.setTitle(new String(ch, start, length));
        } else if (parsingLink) {
            if (currentItem != null) {
                currentItem.setLink(new String(ch, start, length));
                parsingLink = false;
            }
        }
    }
}
*/

public class RssParseHandler extends DefaultHandler {
    private List<RssItem> rssItems;
    private RssItem currentMessage;
    //private StringBuilder builder;
    private boolean parseLink;
    private boolean parseTitle;
    private boolean parseDate;
    private boolean parseDes;

    public RssParseHandler() {
        rssItems = new ArrayList();
    }

    public List<RssItem> getItems() {


        return this.rssItems;
    }





   /* @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        rssItems = new ArrayList<RssItem>();
        builder = new StringBuilder();
    }*/

    @Override
    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, name, attributes);
        if (localName.equalsIgnoreCase("item")) {
            this.currentMessage = new RssItem();
        } else if (localName.equalsIgnoreCase("title")) {
            //currentMessage.setTitle(builder.toString());
            parseTitle = true;
        } else if (localName.equalsIgnoreCase("link")) {
            //currentMessage.setLink(builder.toString());
            parseLink = true;
        } else if (localName.equalsIgnoreCase("description")) {
            //currentMessage.setDescription(builder.toString());
           parseDes = true;
            } else if (localName.equalsIgnoreCase("pubDate")) {
            //currentMessage.setDate(builder.toString());

            parseDate = true;
        }

        //parsing enclosure tag
        else if ("enclosure".equals(localName)) {
            // Get tags attributes number
            int attrsLength = attributes.getLength();
            for (int i = 0; i < attrsLength; i++) {
                String attrName = attributes.getQName(i);  // attribute name
                if ("url".equals(attrName))   // This tag has only one attribute but it is better to check it name is correct
                    currentMessage.getLink();

            }

        }
    }


    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {


        super.endElement(uri, localName, name);
        if (this.currentMessage != null) {
            if (localName.equalsIgnoreCase("item")) {
                rssItems.add(currentMessage);
                //currentMessage = null;
            } else if (localName.equalsIgnoreCase("link")) {
                //currentMessage.setLink(builder.toString());
                //parseLink = false;
           } else if (localName.equalsIgnoreCase("description")) {
                //currentMessage.setDescription(builder.toString());
              //parseDes = false;
            } else if (localName.equalsIgnoreCase("pubDate")){

                //currentMessage.setDate(builder.toString());
                parseDate = false;
            } else if (localName.equalsIgnoreCase("title")) {
                //currentMessage.setTitle(builder.toString());
                parseTitle = false;
            }
            //builder.setLength(0);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        //builder.append(ch, start, length);
        if (parseTitle) {
            if (currentMessage != null)
                currentMessage.setTitle(new String(ch, start, length));

        } else if (parseLink) {
            if (currentMessage != null) {
                currentMessage.setLink(new String(ch, start, length));
                //parseLink = false;
            }
        } else if (parseDes) {
            if (currentMessage != null)
                currentMessage.setDescription(new String(ch, start, length));
                //parseLink = false;


        } else if (parseDate) {
            if (currentMessage != null) {
                currentMessage.setDate(new String(ch, start, length));
                //currentMessage.setDate(new String(ch, start, length));
                //parseDesc = false;
            }
        }
    }

}

