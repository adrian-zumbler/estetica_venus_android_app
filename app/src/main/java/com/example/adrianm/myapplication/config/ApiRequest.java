package com.example.adrianm.myapplication.config;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by AdrianM on 15/05/2016.
 */
public class ApiRequest {

    private URL url;
    private String url_api;

    public ApiRequest(String url_api) throws MalformedURLException {
        this.url_api = url_api;
        this.url = new URL(Settings.URL_API+url_api);
    }

    public JSONObject post(String params) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) this.url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        OutputStream os = httpURLConnection.getOutputStream();
        BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
        Log.d("params",params);
        buffer.write(params);
        buffer.flush();
        buffer.close();
        os.close();
        int responseCode=httpURLConnection.getResponseCode();
        String response = "";
        // Evalua codigo 200 en la respuesta del servidor
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String line;
            BufferedReader br=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while ((line=br.readLine()) != null) {
                response+=line;
            }
            //Se captura el resultado y se crea un objeto json
            JSONObject json = new JSONObject(response);
            //Se accede a las propiedades del objeto json
            Log.d("respuesta",json.toString());
            return json;

        }
        if (responseCode == 201) {
            String line;
            BufferedReader br=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while ((line=br.readLine()) != null) {
                response+=line;
            }
            //Se captura el resultado y se crea un objeto json
            JSONObject json = new JSONObject(response);
            //Se accede a las propiedades del objeto json
            Log.d("respuesta",json.toString());
            return json;

        }
        else {
            return null;

        }


    }

    public void get() throws Exception {

    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URL getUrl() {

        return this.url;
    }

    public void setUrl_api(String url_api){
        this.url_api = url_api;
    }

    public String getUrl_api(){
        return this.url_api;
    }

}
