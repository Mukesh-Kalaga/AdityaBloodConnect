package com.adityawebapps.adityabloodconnect;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.HashMap;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , SearchFragment.OnFragmentInteractionListener, DonorResultsFragment.OnFragmentInteractionListener{

    public String email="",avail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Button reg = (Button)findViewById(R.id.register);
        Button fin = (Button)findViewById(R.id.find);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
                RegisterFragment registerFragment = new RegisterFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_drawer, registerFragment , registerFragment.getTag()).commit();

            }
        });
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
                SearchFragment searchFragment = new SearchFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_drawer, searchFragment , searchFragment.getTag()).commit();
            }
        });



    }
    public int i = 1;
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if(i==1) {
                Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show();
                i++;
            }
            else{
                super.onBackPressed();
            }


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.person_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == R.id.personbutton)   //the androi.r.id.home have to changed
        //for the id of your button.
        {


            AlertDialog.Builder mBuilder = new AlertDialog.Builder(DrawerActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.dialogwillingness,null);
            final EditText mEmail = (EditText) mView.findViewById(R.id.email_willingness);
            final Switch mSwitch = (Switch) mView.findViewById(R.id.availability_switch);
            Button mButton = (Button) mView.findViewById(R.id.change_button);
            mButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(mEmail.getText().toString().isEmpty()){
                        Toast.makeText(DrawerActivity.this, "Please fill out your email", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        email = mEmail.getText().toString();
                        if (mSwitch.isChecked()) {
                            avail = "Yes";
                        }
                        else {
                            avail = "No";
                        }
                        class register_form extends AsyncTask<Void,Void,String> {

                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
/*                                if (s=="success") {
                                    Toast.makeText(DrawerActivity.this, "Availability sucessfully changed", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(DrawerActivity.this, "Failed to Change Availability", Toast.LENGTH_SHORT).show();
                                }*/
                                Toast.makeText(DrawerActivity.this, s, Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            protected String doInBackground(Void... v) {
                                HashMap<String,String> params = new HashMap<>();
                                params.put(Config.Title_email,email);
                                params.put(Config.Title_avail,avail);

                                RequestHandler rh = new RequestHandler();
                                String res = rh.sendPostRequest(Config.REG_CHG_URL, params);
                                return res;
                            }
                        }

                        register_form ci = new register_form();
                        ci.execute();
                    }

                    }
                });

            mBuilder.setView(mView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();

            ///here is where you have to show the alertdialog!!!!
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        i=1;

        if (id == R.id.nav_home) {

            HomeFragment homeFragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_drawer, homeFragment , homeFragment.getTag()).commit();


        } else if (id == R.id.nav_register) {

            RegisterFragment registerFragment = new RegisterFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_drawer, registerFragment , registerFragment.getTag()).commit();

        } else if (id == R.id.nav_search) {
            SearchFragment searchFragment = new SearchFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_drawer, searchFragment , searchFragment.getTag()).commit();

        } else if (id == R.id.nav_feedback) {
            FeedbackFragment feedbackFragment = new FeedbackFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_drawer, feedbackFragment, feedbackFragment.getTag()).commit();

        } else if (id == R.id.nav_about) {

            AboutFragment aboutFragment = new AboutFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_drawer, aboutFragment , aboutFragment.getTag()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
