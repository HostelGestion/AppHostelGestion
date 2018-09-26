package com.ifes.isis.tesis;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.ifes.isis.tesis.applib.org.ifes.isis.tesis.applib.request.AsyncLogin;
import org.ifes.isis.tesis.applib.org.ifes.isis.tesis.applib.request.AuthSingleton;
import org.ifes.isis.tesis.applib.org.ifes.isis.tesis.applib.request.HttpRequest;
import org.ifes.isis.tesis.applib.org.ifes.isis.tesis.applib.request.Metodo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApacheISIS extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apache_isis);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);
        TextView correo = (TextView) hView.findViewById(R.id.userName);
        correo.setText(AuthSingleton.getInstace().getUser());
        menu = navigationView.getMenu();

        new MenuLoad().execute();
        /*AuthSingleton.getInstace().getUser());*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.apache_isi, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        /*if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_close) {
            super.onBackPressed();
        }*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addAButton(String name)
    {
        TextView menuTexto = new TextView(this.getApplicationContext());
        menuTexto.setText(name);
        menuTexto.setTextColor(Color.BLACK);
        navigationView.addView(menuTexto);
    }


    public class SubMenuLoad extends AsyncTask<HttpRequest,Void,JSONObject> {
        SubMenu subMenu;
        private SubMenuLoad (){
            super();
        }

        public SubMenuLoad(SubMenu subMenu) {
            super();
            this.subMenu=subMenu;
        }
        @Override
        protected void onPreExecute()
        {

        }
        @Override
        protected JSONObject doInBackground(HttpRequest... voids) {
            try {
                System.out.println("envio de "+voids[0].getUrl());
                AsyncLogin sinc = new AsyncLogin();
                JSONObject objeto =sinc.handler(voids[0]);
                System.out.println("objeto: "+objeto.get("members").toString());
                return objeto;
            } catch (Exception e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(JSONObject result)
        {
            try {
                for (int i=0;i<result.getJSONObject("members").names().length();i++)
                {
                    final JSONObject resultado= result.getJSONObject("members");
                    final int id= i;
                    subMenu.add(resultado.names().get(i).toString()).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            CharSequence text = "";
                            try {
                                text = resultado.getJSONObject(resultado.names().get(id).toString()).getJSONArray("links").getJSONObject(0).getString("href").toString();
                            } catch (Exception e) {
                            }
                            text=text+"/invoke";
                            new ListLoad().execute(text.toString());
                            //codigo para mostrar lista
                            return false;
                        }
                    });

                }
            } catch (Exception e) {
            }

        }

    }
    private class MenuLoad extends AsyncTask<String,Void,JSONObject> {
        @Override
        protected void onPreExecute()
        {

        }

        @Override
        protected JSONObject doInBackground(String... voids) {
            try {

                return new AsyncLogin().handler(new HttpRequest());
            } catch (Exception e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(JSONObject result)
        {
            if(result!=null && !result.has("error")) {
                try {
                    JSONArray JSONList = (JSONArray) result.get("value");
                    for(int i=0;i<JSONList.length();i++) {
                        JSONObject tmpObj = (JSONObject) JSONList.get(i);
                        if(!tmpObj.get("href").toString().contains("isisApplib")) {

                            SubMenu submenu= menu.addSubMenu(tmpObj.get("title").toString());
                            SubMenuLoad objSubmenu=new SubMenuLoad(submenu);
                            HttpRequest httpRequest = new HttpRequest(tmpObj.get("href").toString(), Metodo.GET);
                            objSubmenu.execute(httpRequest);
                        }
                    }
                } catch (Exception e) {

                }
            }
            else{

            }

        }

    }
    private class ListLoad extends AsyncTask<String,Void,JSONObject> {
        @Override
        protected void onPreExecute()
        {
        }
        @Override
        protected JSONObject doInBackground(String... voids) {
            System.out.println(voids[0]);
            /*Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, voids[0], duration);
            toast.show();*/
            try {
                return new AsyncLogin().handler(new HttpRequest(voids[0],Metodo.GET));
            } catch (Exception e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(JSONObject result)
        {
            System.out.println("abcdefg56 "+result.toString());
            if(result!=null && !result.has("error")) {
                try {
                    JSONArray JSONList = result.getJSONObject("result").getJSONArray("value");
                    ArrayList<String> stringLista=new ArrayList<String>();
                    for (int i=0;i<JSONList.length();i++) {
                        stringLista.add(JSONList.getJSONObject(i).getString("title").replace("Object: ",""));

                    }
                    ListView mainList = (ListView) findViewById(R.id.listaPrincipal);
                    ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item_category,stringLista);
                    mainList.setAdapter(listAdapter);
                } catch (Exception e) {

                }
            }
            else{

            }

            }

    }


    private class ObjectLoad extends AsyncTask<String,Void,JSONObject> {
        @Override
        protected void onPreExecute()
        {
        }
        @Override
        protected JSONObject doInBackground(String... voids) {
            try {
                return new AsyncLogin().handler(new HttpRequest(voids[0],Metodo.GET));
            } catch (Exception e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(JSONObject result)
        {
            if(result!=null && !result.has("error")) {
                try {
                    JSONObject JSONList = result.getJSONObject("members");
                    System.out.println(JSONList.names().toString());

                } catch (Exception e) {

                }
            }
            else{

            }

        }

    }





}
