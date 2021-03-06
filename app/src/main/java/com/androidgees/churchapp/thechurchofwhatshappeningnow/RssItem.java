package com.androidgees.churchapp.thechurchofwhatshappeningnow;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by OLAJUWON on 7/19/2014.
 */
public class RssItem {


/*
    //item title
    private String title;
    //item link
    private String link;
    private String description;
    private String pubdate;
    private String url;
*/


    static SimpleDateFormat FORMATTER =
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    private String title;

    private String link;

    private String url;

    private String description;


    private Date date;



    public String getTitle() {

        return title;
    }



    public void setTitle(String title) {
        //this.title = title.trim();
        this.title = title;

    }

    public String getLink(){
        return link;

    }




/*    public void setLink(String link){
        try {
            this.link = new URL(link);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }*/



    public void setLink(String link){



        this.link = link;
    }




    // end old code


    public String getDescription(String description){
        return description;
    }




    public void setDescription(String description) {
        //this.description = description.trim();
        this.description = description;
    }




    public String getDate() {
        return FORMATTER.format(this.date);
    }





    public void setDate(String date) {
        // pad the date if necessary
        while (!date.endsWith("00")){

           date += "0";
        }
        try {
            this.date = FORMATTER.parse(date.trim());
        } catch (ParseException e) {
            //throw new RuntimeException(e);
        }
    }


    public RssItem copy(){
        RssItem copy = new RssItem();
        copy.title = title;
        copy.link = link;
        copy.description = description;
        copy.date = date;
        return copy;
    }




    @Override
    public String toString() {
/*        StringBuilder sb = new StringBuilder();
        sb.append("Title: ");
        sb.append(title);
        sb.append('\n');
        sb.append("Date: ");
        sb.append(this.getDate());
        sb.append('\n');
        sb.append("Link: ");
        sb.append(link);
        sb.append('\n');
        sb.append("Description: ");
        sb.append(description);
        return sb.toString();*/

        return title;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((link == null) ? 0 : link.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RssItem other = (RssItem) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (link == null) {
            if (other.link != null)
                return false;
        } else if (!link.equals(other.link))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }



    public int compareTo(RssItem another) {
        if (another == null) return 1;
        // sort descending, most recent first
        return another.date.compareTo(date);
    }
}
