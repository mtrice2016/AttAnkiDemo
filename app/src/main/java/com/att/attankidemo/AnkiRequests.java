package com.att.attankidemo;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class AnkiRequests {

    private static final int PORT = 7801;
    private static final String ADDRESS = "192.168.11.109";

    interface StringFunc {
        public void func(String str);
    }

    interface VolleyErrorFunc {
        public void func(VolleyError error);
    }


    //Default request which does not process a response TODO rename to include no response?
    private static StringRequest generateRequest(String query) {
        return generateRequest(query, Request.Method.GET);
    }

    //Default request which does not process a response TODO rename to include no response?
    private static StringRequest generateRequest(String query, int method) {
        String url = generateUrlFromQuery(query);
        return new StringRequest(method, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });
    }

    //TODO make private once hack is fixed
    public static StringRequest generateRequest(String query, int method,
                       Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = generateUrlFromQuery(query);
        return new StringRequest(method, url, listener, errorListener);
    }

    private static String generateUrlFromQuery(String query) {
        String unencoded = "http://" + ADDRESS + ":" + PORT + query;
        return unencoded.replaceAll(" ", "%20");
    }


    public static StringRequest setSpeed(int speed) {
        return setSpeed("all", speed);
    }

    public static StringRequest setSpeed(String name, int speed) {
        String query = "/setSpeed/" + name + "/" + Integer.toString(speed);
        return generateRequest(query, Request.Method.POST);
    }

    public static StringRequest setLights(int red, int green, int blue) {
        return setLights("all", red, green, blue);
    }

    public static StringRequest setLights(String name, int red, int green, int blue) {
        String query = "/setEngineLight/" + name + "/" + Integer.toString(red) + "/" + Integer.toString(green) + "/" + Integer.toString(blue);
        return generateRequest(query, Request.Method.POST);
    }

//    public static StringRequest mapTrack(String name) {
//        String query = "/mapTrack/" + name + "/";
//        return generateRequest(query, Request.Method.POST);
//    }
//
//    public static StringRequest saveMap() {
//        return generateRequest("/mapSave", Request.Method.POST);
//    }
//
//    public static StringRequest loadMap() {
//        return generateRequest("/mapLoad", Request.Method.POST);
//    }
//
//    public static StringRequest getMap() {
//        return generateRequest("/getTrackMapData");
//    }


}
