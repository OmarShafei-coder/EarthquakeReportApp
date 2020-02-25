package com.example.earthquakereport;


import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

      /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(String sampleJsonResponse) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakesList = new ArrayList<>();

        // Try to parse the sampleJsonResponse. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the sampleJsonResponse string and
            // build up a list of Earthquake objects with the corresponding data.

            // Convert sampleJsonResponse String into a JSONObject
            JSONObject jsonRootObject = new JSONObject(sampleJsonResponse);

            //Extract “features” JSONArray
            JSONArray features = jsonRootObject.optJSONArray("features");

            //Loop through each feature in the array
            for(int i=0; i<features.length(); i++){
                //Get earthquake JSONObject at position i
                JSONObject featuresJSONObject = features.optJSONObject(i);
                //Get “properties” JSONObject
                JSONObject properties = featuresJSONObject.optJSONObject("properties");
                //Extract “mag” for magnitude
                double mag = properties.optDouble("mag");

                //Extract “place” for location
                String place = properties.optString("place");
                //splitting the location data into two strings
                String[] location = {"0", "0"};
                if(place.contains("of")){
                    location = place.split("of");
                    location[0] += "of";
                }else {
                    location[0] = "Near the";
                    location[1] = place;
                }

                //Extract “time” for time
                long timeInMilliseconds  = properties.optLong("time");
                //Date Formatting
               String dateToDisplay = dataFormatting(timeInMilliseconds);
                //getting the URL
                String url = properties.optString("url");
                //Create Earthquake java object from magnitude, location, and date
                Earthquake earthquakeObject = new Earthquake(mag, location[0], location[1], dateToDisplay, url);
                //Add earthquake to list of earthquakes
                earthquakesList.add(earthquakeObject);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakesList;
    }

    private static String dataFormatting(long timeInMilliseconds) {
        Date dateObject = new Date(timeInMilliseconds);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);
        return dateToDisplay;
    }
}