package com.androiddesdecero.networkingyouttube.utilidades;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by albertopalomarrobledo on 16/3/18.
 */

public class Utilidades {

    public static final String LOG_TAG = Utilidades.class.getSimpleName();

    public static Tiempo obtenerDatos(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        Tiempo tiempo = extractFeatureFromJson(jsonResponse);
        return tiempo;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error al crear URL", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static Tiempo extractFeatureFromJson(String tiempoJSON) {
        if (TextUtils.isEmpty(tiempoJSON)) {
            return null;
        }
        List<Tiempo> tiempos = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(tiempoJSON);
            JSONArray listArray = baseJsonResponse.getJSONArray("list");

            for (int i=0; i<listArray.length();i++){
                JSONObject firstFeature = listArray.getJSONObject(i);
                String fecha = firstFeature.getString("dt");
                JSONObject main = firstFeature.getJSONObject("main");
                String temperatura = main.getString("temp");
                //Tiempo tiempo = new Tiempo(temperatura,fecha);
                //tiempos.add(tiempo);
                return new Tiempo(temperatura, fecha);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problemas", e);
        }
        return null;
    }
}
