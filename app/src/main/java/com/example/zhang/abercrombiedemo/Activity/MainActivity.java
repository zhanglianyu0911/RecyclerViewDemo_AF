package com.example.zhang.abercrombiedemo.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonArrayRequest;
import com.example.zhang.abercrombiedemo.AppController.AppController;
import com.example.zhang.abercrombiedemo.Items.ProductItem;
import com.example.zhang.abercrombiedemo.ProductAdapter.ProductAdapter;
import com.example.zhang.abercrombiedemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Activity for show recyclerView
 *
 * This activity is used to display items in RecyclerView by using volley library
 *
 * @author Andrew Zhang
 * @version 1.0
 * @since 2016-10-31
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String url = "https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json";
     ArrayList<ProductItem> itemList = new ArrayList<>();
    ProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // put item in recyclerView


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.itemHolder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ProductAdapter(itemList, this);
        recyclerView.setAdapter(adapter);

        makeRequest(url);


    }

    /**
     * Method make network request using volley, load data in ArrayList
     *@param url  The string for making JsonArray request
     */
    public void makeRequest(String url)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {

                try {
                    for (int i = 0; i < response.length(); i++)
                    {
                        JSONObject josonObject = response.getJSONObject(i);
                        ProductItem productItem = new ProductItem();
                        productItem.setBackgroundImage(josonObject.getString("backgroundImage"));
                        productItem.setTitle(josonObject.getString("title"));

                        //check wether element exist, if not, assign empty
                            if(josonObject.has("topDescription"))
                            {
                                productItem.setTopDescription(josonObject.getString("topDescription"));
                            }
                            else
                            {
                                productItem.setTopDescription("");
                            }

                            if(josonObject.has("promoMessage"))
                            {
                                productItem.setPromoMessage(josonObject.getString("promoMessage"));
                            }
                            else
                            {
                                productItem.setPromoMessage("");
                            }

                            if(josonObject.has("bottomDescription"))
                            {
                                productItem.setBottomDescription(josonObject.getString("bottomDescription"));
                            }
                            else
                            {
                                productItem.setBottomDescription("");
                            }


                            if (josonObject.has("content"))
                            {
                                JSONArray jsonContent = josonObject.getJSONArray("content");
                                for(int j=0; j<jsonContent.length();j++)
                                {

                                   JSONObject contentObject = jsonContent.getJSONObject(j);

                                    if(j==0)
                                    {
                                        productItem.setSelectButton_one(contentObject.getString("title"));
                                        productItem.setGetSelectButton_two("");
                                        productItem.setButton_one_link(contentObject.getString("target"));
                                    }
                                    else
                                    {
                                        productItem.setGetSelectButton_two(contentObject.getString("title"));
                                        productItem.setButton_two_link(contentObject.getString("target"));
                                    }

                                }

                            }
                            else
                            {
                                productItem.setSelectButton_one("");
                                productItem.setGetSelectButton_two("");
                            }

                        itemList.add(productItem);

                    }
                }
                catch (JSONException e) {
                        e.printStackTrace();
                   }
                adapter.notifyDataSetChanged();

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;

                //network status check
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }

            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_shop) {
            // Handle the navigation action
        } else if (id == R.id.save) {

        } else if (id == R.id.nav_massage) {

        } else if (id == R.id.nav_myAccount) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
