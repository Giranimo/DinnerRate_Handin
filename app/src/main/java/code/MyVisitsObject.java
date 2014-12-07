package code;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 31-10-2014.
 */
public class MyVisitsObject implements Serializable {
    private String RestaurantName = "";
    private String Rating = "";
    private String Comment1 = "";
    private String Comment2 = "";
    private String Comment3 = "";
    private String SmileyURL = "";
    private String ImageUrl;
    private String URL = "";
    private double Longitude;
    private double Latitude;

    /*
        public RowObject(String restaurantName, String rating, String comment1, String comment2, String comment3, String smileyURL, int imageUrl, String url, int distance) {
            RestaurantName = restaurantName;
            Rating = rating;
            Comment1 = comment1;
            Comment2 = comment2;
            Comment3 = comment3;
            SmileyURL = smileyURL;
            ImageUrl = imageUrl;
            URL = url;
            Longitude = distance;
        }
    */
    public void setRestaurantName(String CompanyName) {
        this.RestaurantName = CompanyName;
    }

    public void setImageUrl(String Image) {
        this.ImageUrl = Image;
    }

    public void setRating(String Rating) {
        this.Rating = Rating;
    }

    public void setLongitude(double longitude) {
        this.Longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.Latitude = latitude;
    }

    public void setComment1(String comment1) {
        this.Comment1 = comment1;
    }

    public void setComment2(String comment2) {
        this.Comment2 = comment2;
    }

    public void setComment3(String comment3) {
        this.Comment3 = comment3;
    }

    public void setSmileyURL(String smileyURL) {
        this.SmileyURL = smileyURL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public String getSmileyURL() {
        return this.SmileyURL;
    }

    public String getComment1() {
        return this.Comment1;
    }

    public String getComment2() {
        return this.Comment2;
    }

    public String getComment3() {
        return this.Comment3;
    }


    public String getRestaurantName() {
        return this.RestaurantName;
    }

    public String getImageUrl() {
        return this.ImageUrl;
    }

    public String getRating() {
        return this.Rating;
    }

    public double getLongitude() {
        return this.Longitude;
    }

    public double getLatitude() {
        return this.Latitude;
    }

}

