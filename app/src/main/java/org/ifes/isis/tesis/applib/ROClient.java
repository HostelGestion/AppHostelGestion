package org.ifes.isis.tesis.applib;

import android.os.AsyncTask;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.net.Authenticator;
import org.ifes.isis.tesis.applib.constants.Resource;
import org.ifes.isis.tesis.applib.org.ifes.isis.tesis.applib.request.AuthSingleton;

public class ROClient extends AsyncTask<String, Void, String> {
    //private final DefaultHttpClient client;
    private String host = "http://"+ AuthSingleton.getInstace().getIp()+":8080/restful/";

    private ROClient() {
        //client = new DefaultHttpClient();
        //setCredential("sven", "pass");
    }

    @Override
    protected String doInBackground(String... strings) {
        RORequest request = RORequest.To(host, Resource.HomePage, strings);
        System.out.println(request.toString());
        return "okey";
    }

    private static ROClient roClient = null;


/*
    public void setCredential(String username, String password) {
        try {
            // Sets the authenticator that will be used by the networking code
            // when a proxy or an HTTP server asks for authentication.
            Authenticator.setDefault(new AsyncLogin.CustomAuthenticator());
            URL url = new URL("http://www.secure-site-example.com:80/");
            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }

    }
*/
    public void setHost(String host){
        this.host = host;
    }

    public static ROClient getInstance() {
        if (roClient == null) {
            roClient = new ROClient();
        }
        return roClient;
    }

    public RORequest RORequestTo(String path) {
        return RORequest.To(path);
    }

    /*public HttpResponse execute(String httpMethod, RORequest roRequest, Map<String, Object> args) throws ConnectionException, InvalidCredentialException, UnknownErrorException {
        HttpResponse response = null;
        if (httpMethod.equals("GET")) {
            HttpGet get;
            try {
                get = new HttpGet(roRequest.asUriStr());*/
                //get.setHeader("Accept", "*/*");
                /*response = client.execute(get);

            } catch (Exception e) {
                e.printStackTrace();
                throw new ConnectionException();
            }
        } else if (httpMethod.equals("POST")) {
            HttpPost post;
            try {
                post = new HttpPost(roRequest.asUriStr());*/
                //post.setHeader("Accept", "*/*");
                //post.setHeader("Content-Type", "*/*");
                /*if (args != null) {
                    Map argmap = new HashMap<String, Map<String, Object>>();
                    String[] params = {};
                    params = args.keySet().toArray(params);
                    for (String param : params) {
                        Map value = new HashMap<String, Object>();
                        value.put("value", args.get(param));
                        argmap.put(param, value);
                    }
                    ObjectMapper objectMapper = new ObjectMapper();
                    String data = objectMapper.writeValueAsString(argmap);

                    System.out.println(data);
                    post.setEntity(new StringEntity(data));
                }
                response = client.execute(post);
                System.out.println("Status code " + response.getStatusLine().getStatusCode());
            } catch (Exception e) {
                e.printStackTrace();
                throw new ConnectionException();
            }

        } else if (httpMethod.equals("PUT")) {
            HttpPut put;

            if (args != null) {

                try {
                    put = new HttpPut(roRequest.asUriStr());*/
                    //put.setHeader("Accept", "*/*");
         //           put.setHeader("Content-Type", "*/*");
       /*             ObjectMapper objectMapper = new ObjectMapper();
                    String data = objectMapper.writeValueAsString(args);

                    System.out.println(data);
                    put.setEntity(new StringEntity(data));
                    response = client.execute(put);
                    System.out.println("Status code " + response.getStatusLine().getStatusCode());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new ConnectionException();
                }
            }

        }

        if (response != null) {
            int statusCode = response.getStatusLine().getStatusCode();
            switch (statusCode) {
                case 200:
                    break;
                case 401:
                    throw new InvalidCredentialException();

                default:
                    System.out.println("Error " + statusCode);
                    try {
                        String json = EntityUtils.toString(response.getEntity());
                        System.out.println(json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    throw new UnknownErrorException();

            }
        }

        return response;
    }*/

    /*public <T extends JsonRepr> T executeT(Class<T> t, String httpMethod, RORequest roRequest, Map<String, Object> args) throws org.ifes.isis.tesis.applib.exceptions.JsonParseException, ConnectionException, InvalidCredentialException, UnknownErrorException {
        HttpResponse response = execute(httpMethod, roRequest, args);

        try {
            String json = EntityUtils.toString(response.getEntity());
            JsonParser jp = new JsonFactory().createJsonParser(json);
            ObjectMapper objectMapper = new ObjectMapper();
            T representation = objectMapper.readValue(jp, t);
            return representation;
        } catch (Exception e) {
            e.printStackTrace();
            throw new org.ifes.isis.tesis.applib.exceptions.JsonParseException();
        }
    }*/

    /*public <T extends JsonRepr> T get(Class<T> t, String path, Map<String, Object> args) throws org.ifes.isis.tesis.applib.exceptions.JsonParseException, ConnectionException, InvalidCredentialException, UnknownErrorException {
        return executeT(t, "GET", RORequestTo(path), args);

    }*/

    /*public <T extends JsonRepr> T post(Class<T> t, String uri, Map<String, Object> args) throws org.ifes.isis.tesis.applib.exceptions.JsonParseException, ConnectionException, InvalidCredentialException, UnknownErrorException {
        return executeT(t, "POST", RORequestTo(uri), args);
    }

    public <T extends JsonRepr> T put(Class<T> t, String uri, Map<String, Object> args) throws org.ifes.isis.tesis.applib.exceptions.JsonParseException, ConnectionException, InvalidCredentialException, UnknownErrorException {
        return executeT(t, "PUT", RORequestTo(uri), args);
    }

    public <T extends JsonRepr> T delete(Class<T> t, String uri, Map<String, Object> args) throws org.ifes.isis.tesis.applib.exceptions.JsonParseException, ConnectionException, InvalidCredentialException, UnknownErrorException {
        return executeT(t, "DELETE", RORequestTo(uri), args);
    }*/

    /*public Homepage homePage()  {
        String params[] = {};
        RORequest request = RORequest.To(host, Resource.HomePage, params);
        Homepage homepageRepresentation =executeT(Homepage.class, "GET", request, null);
        return null;
    }*/
}
