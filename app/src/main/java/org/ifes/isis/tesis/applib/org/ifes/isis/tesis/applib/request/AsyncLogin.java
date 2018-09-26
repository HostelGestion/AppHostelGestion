package org.ifes.isis.tesis.applib.org.ifes.isis.tesis.applib.request;

import android.os.AsyncTask;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by router on 05/11/17.
 */

public class AsyncLogin  {
    final JSONObject responseJSON = null;
    //final static String  urlAPI="http://192.168.0.74:8080/restful";
    //final String url="http://www.ifes.edu.ar";
    public JSONObject handler(HttpRequest params) throws JSONException {
        //BufferedReader br=null;
        System.out.println("ejecutando consulta");
        JSONObject retorno= new JSONObject("{\"error\":\"notNull\"}");;
        BufferedReader bufferedReader;
        HttpURLConnection urlConnection;
        try {
            System.out.println("la direccion c es "+params.getUrl());
            URL url = new URL(params.getUrl());
            urlConnection = (HttpURLConnection) url.openConnection();
            System.out.println("entro a ver los parametros"+params.getHeadersList().isEmpty());
            ArrayList<HttpHeaders> listHeaders = params.getHeadersList();
            System.out.println("lista de parametros"+listHeaders.size());
            for (int i=0;i<listHeaders.size();i++)
            {
                System.out.println(listHeaders.get(i).getProperty()+" "+listHeaders.get(i).getValue());
                if (listHeaders.get(i).getProperty().contains("Auth")) {
                    byte[] loginBytes = (AuthSingleton.getInstace().getUser() + ":" + AuthSingleton.getInstace().getPassword()).getBytes();
                    StringBuilder loginBuilder = new StringBuilder()
                            .append("Basic ")
                            .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
                    urlConnection.addRequestProperty("Authorization", loginBuilder.toString());
                }
                else{
                urlConnection.addRequestProperty(listHeaders.get(i).getProperty(),listHeaders.get(i).getValue());
                }
            }
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line=bufferedReader.readLine())!=null) {
                sb.append(line+"\n");
            }
            System.out.println("el retorno es:"+sb.toString());
            retorno= new JSONObject(sb.toString());
        }
        catch (IOException e) {
            try {
                System.out.println("error en :"+e.getMessage());
                retorno = new JSONObject("{\"error\":\"" + e.getLocalizedMessage()+"\"}");
            } catch (Exception exep) {
                System.out.println("error en 3:"+exep.getLocalizedMessage());
            }
        }
        catch (Exception e) {
            try {
                System.out.println("error en 1:"+e.getMessage());
                retorno = new JSONObject("{\"error\":\"" + e.getLocalizedMessage()+"\"}");
            } catch (Exception exep) {
                System.out.println("error en 2:"+exep.getLocalizedMessage());
            }
        }
        return retorno;
    }
}
