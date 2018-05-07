package com.adityawebapps.adityabloodconnect;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

public class find_results extends AppCompatActivity {

    public String[] namearray;
    public String[] phonearray;

    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_results);

        listview = (ListView) findViewById(R.id.donars_list);
        LinearLayout la  = (LinearLayout) findViewById(R.id.para_lay);

        Intent i = getIntent();
        String json;
        json=i.getStringExtra("Avail_donars");
        if (json.isEmpty()||json=="{\"result\":[]}") {
            Toast.makeText(this,"Sorry no Results Available",Toast.LENGTH_LONG).show();
            TextView tv = new TextView(find_results.this);
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.setText("Sorry no Results Available");
            LinearLayout dl = new LinearLayout(this);
            dl.addView(tv);
            la.addView(dl);
        }
        else {
            loadlist(json);
        }
    }
    private void loadlist(String jsonstring){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(jsonstring);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

                namearray = new String[jsonObject.getJSONArray(Config.TAG_JSON_ARRAY).length()];
                phonearray = new String[jsonObject.getJSONArray(Config.TAG_JSON_ARRAY).length()];
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String name = jo.getString(Config.Title_name);
                String mobile = jo.getString(Config.Title_mobile);
                try {
                    namearray[i] = name;
                    phonearray[i] = mobile;
                }
                catch (Exception e) {
                    Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
                    HashMap<String, String> readmadelist = new HashMap<>();
                    readmadelist.put(Config.Title_name, name);
                    readmadelist.put(Config.Title_mobile, mobile);
                    list.add(readmadelist);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            MyAdapter adapter = new MyAdapter(find_results.this, namearray, phonearray);
            listview.setAdapter(adapter);
        }
        catch (Exception e) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        ListAdapter adapter = new SimpleAdapter(
                find_results.this, list, R.layout.read_listview,
                new String[]{Config.Title_name,Config.Title_mobile},
                new int[]{R.id.readlist_text,R.id.number_donar}
        );
        }
    }

    class MyAdapter extends ArrayAdapter {
        int[] imageArray;
        String[] titleArray;
        String[] numArray;
        public MyAdapter(Context context, String[] titles1, String[] descriptions1) {
            //Overriding Default Constructor off ArratAdapter
            super(context, R.layout.read_listview,R.id.readlist_text,titles1);
            this.titleArray=titles1;
            this.numArray=descriptions1;
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //Inflating the layout
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.read_listview,parent,false);

            //Get the reference to the view objects
            TextView myTitle = (TextView) row.findViewById(R.id.readlist_text);
            TextView myDescription = (TextView) row.findViewById(R.id.number_donar);
            ImageButton call_donor = (ImageButton) row.findViewById(R.id.call_donar);

            call_donor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(),numArray[position],Toast.LENGTH_LONG).show();
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+numArray[position]));

                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) ==
                            PackageManager.PERMISSION_GRANTED)
                    {
                        try {
                            startActivity(callIntent);
                        }
                        catch (Exception e) {
                            Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getContext(),"Please 'Allow' The Permission to Make Call",Toast.LENGTH_LONG).show();
                        if (ActivityCompat.shouldShowRequestPermissionRationale(find_results.this,
                                android.Manifest.permission.CALL_PHONE)) {
                        } else {
                            ActivityCompat.requestPermissions(find_results.this,
                                    new String[]{android.Manifest.permission.CALL_PHONE},
                                    91);
                            try {
                                startActivity(callIntent);
                            }
                            catch (Exception e) {
                                Toast.makeText(getContext(),"Permission has been Denied",Toast.LENGTH_LONG).show();
                                //android:background="@drawable/checkbox_selector"

                                //Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            });

            //Providing the element of an array by specifying its position
            //myImage.setImageResource(imageArray[position]);
            myTitle.setText(titleArray[position]);
            myDescription.setText(numArray[position]);
            return row;
        }
    }

}
