package org.ifes.isis.tesis.applib.org.ifes.isis.tesis.applib.request;

import android.util.Base64;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by router on 20/11/17.
 */

public class HttpRequest {
    private String url="/";
    private Metodo method;//GET PUT POST OPTIONS
    private boolean login;
    private ArrayList<HttpParameters> parametersList;
    private ArrayList<HttpHeaders> headersList;

    public String getUrl() {
        return url;
    }

    public Metodo getMethod() {
        return method;
    }

    public boolean isLogin() {
        return login;
    }

    public ArrayList<HttpParameters> getParametersList() {
        return parametersList;
    }

    public ArrayList<HttpHeaders> getHeadersList() {
        return headersList;
    }

    public HttpRequest() {
        this.url = AuthSingleton.getInstace().getIp()+"restful/services";
        this.method = Metodo.GET;
        this.login = false;
        this.headersList = new ArrayList<HttpHeaders>();
        this.parametersList = new ArrayList<HttpParameters>();
        this.headersList = new ArrayList<HttpHeaders>();
        byte[]loginBytes=(AuthSingleton.getInstace().getUser()+":"+AuthSingleton.getInstace().getPassword()).getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes,Base64.DEFAULT));
        this.headersList.add(new HttpHeaders("Authentication",loginBuilder.toString()));
        this.headersList.addAll(headersList);
    }
    public HttpRequest(String url, Metodo method) {
        this.url = url;
        this.method = method;
        this.login = false;
        this.parametersList = null;
        this.headersList = new ArrayList<HttpHeaders>();
        System.out.println("la direccion es " +this.url);
        this.headersList = new ArrayList<HttpHeaders>();
        byte[]loginBytes=(AuthSingleton.getInstace().getUser()+":"+AuthSingleton.getInstace().getPassword()).getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes,Base64.DEFAULT));
        this.headersList.add(new HttpHeaders("Authentication",loginBuilder.toString()));
        this.headersList.addAll(headersList);
    }
    public HttpRequest(String url, Metodo method, ArrayList<HttpParameters> parametersList, List<HttpHeaders> headersList) {
        this.url = url;
        this.method = method;
        this.login = false;
        this.parametersList = parametersList;
        this.headersList = new ArrayList<HttpHeaders>();
        byte[]loginBytes=(AuthSingleton.getInstace().getUser()+":"+AuthSingleton.getInstace().getPassword()).getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes,Base64.DEFAULT));
        this.headersList.add(new HttpHeaders("Authentication",loginBuilder.toString()));
        this.headersList.addAll(headersList);
    }
    public HttpRequest(String url, Metodo method, boolean login, ArrayList<HttpParameters> parametersList, ArrayList<HttpHeaders> headersList) {
        this.url = url;
        this.method = method;
        this.login = login;
        this.parametersList = parametersList;
        this.headersList = headersList;
    }
}
