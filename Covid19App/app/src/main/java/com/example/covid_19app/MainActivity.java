package com.example.covid_19app;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    public static List<Module> modelList = new ArrayList<>();
    Module model;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        fetchData();

    }

    private void fetchData() {

        String url = "https://data.covid19india.org/state_district_wise.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {



                    JSONObject object = new JSONObject(response);
                    Iterator <String> keys = object.keys();
                    while (keys.hasNext()) {

                        String key = keys.next();
                        JSONObject object1 = object.getJSONObject(key);
                        JSONObject object2 = object1.getJSONObject("districtData");
                        Iterator<String> skeys = object2.keys();

                        while (skeys.hasNext()){
                            String skey = skeys.next();
                            JSONObject object3 = object2.getJSONObject(skey);
                            JSONObject object4 = object3.getJSONObject("delta");
                            String active = object3.getString("active");
                            String confirmed = object3.getString("confirmed");
                            String deceased = object3.getString("deceased");
                            String recovered = object3.getString("recovered");

                            String confInc = object4.getString("confirmed");
                            String confDec = object4.getString("deceased");
                            String confRec = object4.getString("recovered");

                            model = new Module(skey, confirmed, deceased, recovered, active,
                                    confInc, confDec, confRec);

                            modelList.add(model);

                        }
                    }



                    adapter = new Adapter(MainActivity.this, modelList);
                    listView.setAdapter(adapter);

                    // In case of error it will run
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // In case of error it will run
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}