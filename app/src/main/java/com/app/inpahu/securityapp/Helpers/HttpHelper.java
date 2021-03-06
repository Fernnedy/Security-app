package com.app.inpahu.securityapp.Helpers;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {

    private static final String HOST_URL = "https://security-server-inpahu.herokuapp.com";

    public static String executePost(String urlr, JSONObject data) {

        try {

            URL url = new URL(HOST_URL + urlr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:221.0) Gecko/20100101 Firefox/31.0");

            // is output buffer writter
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");

            //set headers and method
            Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
            writer.write(data.toString());

            // json data
            writer.close();
            InputStream inputStream = urlConnection.getInputStream();

            //input stream
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = reader.readLine()) != null)
                buffer.append(inputLine + "\n");

            if (buffer.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            String JsonResponse = buffer.toString();

            //response data
            Log.i("Response",JsonResponse);

            return JsonResponse;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String executeGet(String urlr) {

        try {

            URL url = new URL(HOST_URL + urlr);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");

            int responseCode = connection.getResponseCode();

            final StringBuilder output = new StringBuilder("Request URL " + url);
            output.append(System.getProperty("line.separator") + "Response Code " + responseCode);
            output.append(System.getProperty("line.separator") + "Type " + "GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder responseOutput = new StringBuilder();
            System.out.println("output===============" + br);
            while ((line = br.readLine()) != null) {
                responseOutput.append(line);
            }
            br.close();

            return responseOutput.toString();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String executeGeoPost(String lat, String lng) {

        try {

            String urlr = "https://nominatim.openstreetmap.org/reverse.php?format=json&lat="+lat+"&lon="+lng;

            URL url = new URL(urlr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:221.0) Gecko/20100101 Firefox/31.0");

            // is output buffer writter
            urlConnection.setRequestMethod("GET");

            InputStream inputStream = urlConnection.getInputStream();

            //input stream
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = reader.readLine()) != null)
                buffer.append(inputLine + "\n");

            if (buffer.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            String JsonResponse = buffer.toString();

            //response data
            Log.i("Response",JsonResponse);

            return JsonResponse;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String executeGeoGet(String place) {

        try {

            String urlr = "https://nominatim.openstreetmap.org/?format=json&addressdetails=1&q=" + place + "&format=json&limit=1";

            URL url = new URL(urlr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:221.0) Gecko/20100101 Firefox/31.0");

            // is output buffer writter
            urlConnection.setRequestMethod("GET");

            InputStream inputStream = urlConnection.getInputStream();

            //input stream
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = reader.readLine()) != null)
                buffer.append(inputLine + "\n");

            if (buffer.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            String JsonResponse = buffer.toString();

            //response data
            Log.i("Response",JsonResponse);

            return JsonResponse;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
