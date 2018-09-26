package org.ifes.isis.tesis.applib.org.ifes.isis.tesis.applib.request;

/**
 * Created by router on 20/11/17.
 */

public class AuthSingleton {
    private String user;
    private String password;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = "http://"+ip+":8080/";
    }
    private static AuthSingleton myObject;

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    private AuthSingleton()
    {
    }
    public void setCredetials(String user,String password){
        this.myObject.user=user;
        this.myObject.password=password;
    }

    public static AuthSingleton getInstace()
    {
        if(myObject ==null)
        {
            myObject = new AuthSingleton();
            return myObject;
        }
        else
        {
            return myObject;
        }
    }


}
