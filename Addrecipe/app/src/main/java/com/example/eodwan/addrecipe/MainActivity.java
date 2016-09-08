package com.example.eodwan.addrecipe;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.widget.ArrayAdapter;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import android.provider.BaseColumns;
import android.app.SearchManager;
import android.app.DownloadManager.Request;
import android.net.Uri;

import okhttp3.OkHttpClient;

import android.os.Bundle;
import android.content.ComponentName;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AutoCompleteTextView;


import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.*;

import android.content.Intent;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.database.Cursor;
import android.content.ContentProvider;
import android.database.MatrixCursor;

import android.widget.SearchView;

import java.lang.reflect.*;
//import android.asynctask.library.JSONParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONArray;

import com.google.ads.AdRequest;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.os.StrictMode;
import android.database.sqlite.SQLiteDatabase;
//import android.support.v4.view.MenuItemCompat;
//import android.support.v7.widget.SearchView;
import android.text.TextWatcher;
import android.text.Editable;


import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner1;
    private Button Getall;

    ArrayList<Integer> ids = new ArrayList<Integer>();
    ArrayList<String> names = new ArrayList<String>();


    // adapter for auto-complete

    private TextView Text;

    private String url1 = "http://api.nal.usda.gov/ndb/reports/?ndbno=";
    private String url2 = "&type=f&format=json&api_key=lhfJq898Ab0nNzZR9XTN3kviNgMMKEAb92Q8Dfcr";
    protected DatabaseHelper db;
    protected DatabaseHelper database;

    public AutoCompleteTextView autoCom;
    private Button BUtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Text = (TextView) findViewById(R.id.text);

        db = new DatabaseHelper(this);
        db.getWritableDatabase();


        if (!db.isTableExists("food")) {
            jso();

        }

        database = new DatabaseHelper(this);


        autoCom = (AutoCompleteTextView) findViewById(R.id.auto);
        final DatabaseHelper myDb = new DatabaseHelper(MainActivity.this);
        autoCom.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String s1 = s.toString();
                try {
                    final String[] myData = myDb.SelectAllData(s1);
                    //   final AutoCompleteTextView autoCom = (AutoCompleteTextView)findViewById(R.id.auto);


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,

                            android.R.layout.simple_dropdown_item_1line, myData);
                    autoCom.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });


        Getall = (Button) findViewById(R.id.getall);

        Getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String food = autoCom.getText().toString().trim();
                Integer numbe = myDb.findid(food);
                String numberAsString = String.format("%05d", numbe);

                Toast.makeText(getApplicationContext(), numberAsString, Toast.LENGTH_LONG).show();


                jso1(numberAsString);


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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
    }

    private void jso1(String url) {


        class RegisterUser extends AsyncTask<String, Void, String> {
            private ListView listView;
            public String namo = "";
            public String valuo = "";
            public String unit = "";
            Float value;
            String stringFloat;
            Float value1;
            String stringFloat1;
            Float value2;
            String stringFloat2;
            Float value3;
            String stringFloat3;
            String food;
            Cursor cursor;
            ListDataAdpter listDataAdpter;
            SQLiteDatabase sqLiteDatabase;
            recipeDbHelper userDbHelper;
            recipeDbHelper userDbHelper2;


            Integer r = 0;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);


                Context context=MainActivity.this;
                spinner1 = (Spinner) findViewById(R.id.spinner1);
                String quintity= String.valueOf(spinner1.getSelectedItem());
                listView=(ListView)findViewById(R.id.list_view);
                userDbHelper=new  recipeDbHelper(getApplicationContext());
                sqLiteDatabase=userDbHelper.getWritableDatabase();
                Float sum=0.0f;
               String calory;


                try {
                    JSONObject jObj = new JSONObject(s);
                    String j = jObj.toString();

                    JSONObject list = jObj.getJSONObject("report");
                    String item = list.toString();
                    JSONObject jl = list.getJSONObject("food");
                    String jll = jl.toString();

                    JSONArray itemm = jl.getJSONArray("nutrients");
                    Integer mn = itemm.length();

                    String kl = itemm.toString();


                    for (int i = 0; i < itemm.length(); i++) {
                        JSONObject c = itemm.getJSONObject(i);
                        namo = c.getString("name");
                        unit = c.getString("unit");

                        if (namo.equalsIgnoreCase("Energy") && unit.equalsIgnoreCase("kcal")) {
                            valuo = c.getString("value");}}
                         //   Text.setText(valuo);
                         value = Float.parseFloat(valuo);
                            value= value/6.666666666666667f;
                            stringFloat= Float.toString(value);
                           value1 = Float.parseFloat(valuo);
                            value1= value1*1.1f;
                         stringFloat1= Float.toString(value1);
                           value2 = Float.parseFloat(valuo);
                            value2= value2*.6f;
                          stringFloat2= Float.toString(value2);
                           value3 = Float.parseFloat(valuo);
                            value3= value3*.85f;
                          stringFloat3= Float.toString(value3);
                            food = autoCom.getText().toString().trim();
                            userDbHelper.addinnformation(value,food,sqLiteDatabase);

                            listDataAdpter=new ListDataAdpter(getApplicationContext(),R.layout.row_layout);
                            listView.setAdapter(listDataAdpter);
                            userDbHelper2=new recipeDbHelper(getApplicationContext());
                            sqLiteDatabase=userDbHelper2.getReadableDatabase();
                            cursor=userDbHelper2.getinformation(sqLiteDatabase);


                       switch(quintity){

                            case "Spoon":
                              //  Text.setText(stringFloat);


                                if(cursor.moveToFirst())
                                {
                                    do {

                                        calory=cursor.getString(0);
                                        sum=userDbHelper2.getTotal(sqLiteDatabase);
                                        Text.setText(String.valueOf(sum));
                                        DataProvider dataProvider=new DataProvider(calory);
                                        listDataAdpter.add(dataProvider);

                                    }while (cursor.moveToNext());

                                }
                              //  userDbHelper2.close();
                                break;
                            case "Cup":

                                Text.setText(stringFloat1);
                                break;
                            case "1/2 Cup":

                                Text.setText(stringFloat2);
                                break;
                            case "3/4 Cup":

                                Text.setText(stringFloat3);
                                break;

                        }





                } catch (Exception e) {
                    e.printStackTrace();

                }


            }

            @Override
            protected String doInBackground(String... params) {
                Text = (TextView) findViewById(R.id.text);

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("food", params[0]);

                String all = url1 + params[0] + url2;

                try {

                    String line, newjson = "";
                    URL urls = new URL(all);
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(urls.openStream(), "UTF-8"))) {
                        while ((line = reader.readLine()) != null) {
                            newjson += line;

                        }

                        String json = newjson.toString();
                        JSONObject jObj = new JSONObject(json);

                        return json;


                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    return "true";
                }


            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(url);


    }

    public void jso() {


        class RegisterUser extends AsyncTask<String, Void, String> {
            public String name = "";
            public Integer id;
            public String name1 = "";
            private TextView Text;


            @Override
            public void onPreExecute() {
                //   new  as.execute();
                super.onPreExecute();


            }

            @Override
            public void onPostExecute(String s) {
                super.onPostExecute(s);

                if (!db.isTableExists("food")) {
                    try {

                        JSONObject jObj = new JSONObject(s);
                        String j = jObj.toString();
                        JSONObject list = jObj.getJSONObject("list");
                        String item = list.toString();
                        JSONArray itemm = list.getJSONArray("item");
                        for (int i = 0; i < itemm.length(); i++) {
                            JSONObject c = itemm.getJSONObject(i);
                            id = c.getInt("ndbno");

                            name = c.getString("name");


                            name1 = name + System.lineSeparator() + name1;
                            names.add(name1);

                            ids.add(id);
                            if (name.length() < 25) {
                                Record record = new Record(name, id);
                                db.addContact(record);
                            }

                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }
            }

            @Override
            public String doInBackground(String... params) {
                Text = (TextView) findViewById(R.id.text);

                try {
                    String line, newjson = "";
                    URL urls = new URL("http://api.nal.usda.gov/ndb/search/?format=json&r&sort=n&max=1500&offset=0&api_key=lhfJq898Ab0nNzZR9XTN3kviNgMMKEAb92Q8Dfcr");
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(urls.openStream(), "UTF-8"))) {
                        while ((line = reader.readLine()) != null) {
                            newjson += line;

                        }

                        String json = newjson.toString();
                        JSONObject jObj = new JSONObject(json);

                        return json;


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                    return "false";
                }

            }
        }

        class as extends AsyncTask<String, Void, String> {
            public String name = "";
            public Integer id;
            public String name1 = "";
            private TextView Text;


            @Override
            public void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            public void onPostExecute(String s) {
                super.onPostExecute(s);

                if (!db.isTableExists("food")) {
                    try {

                        JSONObject jObj = new JSONObject(s);
                        String j = jObj.toString();
                        JSONObject list = jObj.getJSONObject("list");
                        String item = list.toString();
                        JSONArray itemm = list.getJSONArray("item");
                        for (int i = 0; i < itemm.length(); i++) {
                            JSONObject c = itemm.getJSONObject(i);
                            id = c.getInt("ndbno");

                            name = c.getString("name");


                            name1 = name + System.lineSeparator() + name1;
                            names.add(name1);

                            ids.add(id);
                            if (name.length() < 25) {
                                Record record = new Record(name, id);
                                db.addContact(record);
                            }

                        }




                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }
            }

            @Override
            public String doInBackground(String... params) {
                Text = (TextView) findViewById(R.id.text);

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("food", params[0]);

                data.put("url", params[1]);
                String all = params[1] + params[0] + url2;

                try {
                    String line, newjson = "";
                    URL urls = new URL("http://api.nal.usda.gov/ndb/search/?format=json&r&sort=n&max=1500&offset=2000&api_key=lhfJq898Ab0nNzZR9XTN3kviNgMMKEAb92Q8Dfcr");
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(urls.openStream(), "UTF-8"))) {
                        while ((line = reader.readLine()) != null) {
                            newjson += line;

                        }

                        String json = newjson.toString();
                        JSONObject jObj = new JSONObject(json);

                        return json;


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                    return "false";
                }


            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute();


    }

}
