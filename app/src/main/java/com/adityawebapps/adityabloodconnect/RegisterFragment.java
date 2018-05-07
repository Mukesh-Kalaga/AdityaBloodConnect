package com.adityawebapps.adityabloodconnect;


import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.HashMap;

import static android.R.attr.fingerprintAuthDrawable;
import static android.R.attr.fragment;
import static android.R.color.white;
import static com.adityawebapps.adityabloodconnect.R.color.colorAccent;
import static com.adityawebapps.adityabloodconnect.R.color.foreground_material_dark;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private String name,phone,email,city,address,dob,gender,blood,avail;
    private Switch savail;
    private EditText ename,ephone,eemail,ecity,eaddress,edob;
    private Button aplus,aminus,bplus,bminus,abplus,abminus,oplus,ominus,male,female,submit;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        final View view = inflater.inflate(R.layout.fragment_register, container, false);
        /*b1=(Button)view.findViewById(R.id.a_plus);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackground(getResources().getDrawable(R.drawable.bg_red));
            }
        });*/

        ename = (EditText) view.findViewById(R.id.name_text);
        ephone = (EditText) view.findViewById(R.id.phone_text);
        eemail = (EditText) view.findViewById(R.id.email_text);
        ecity = (EditText) view.findViewById(R.id.city_text);
        eaddress = (EditText) view.findViewById(R.id.address_text);
        edob = (EditText) view.findViewById(R.id.date_text);

        aplus = (Button) view.findViewById(R.id.a_plus);
        aminus = (Button) view.findViewById(R.id.a_minus);
        bplus = (Button) view.findViewById(R.id.b_plus);
        bminus = (Button) view.findViewById(R.id.b_minus);
        abplus = (Button) view.findViewById(R.id.ab_plus);
        abminus = (Button) view.findViewById(R.id.ab_minus);
        oplus = (Button) view.findViewById(R.id.o_plus);
        ominus = (Button) view.findViewById(R.id.o_minus);
        submit = (Button) view.findViewById(R.id.submit_but);

        male = (Button) view.findViewById(R.id.male);
        female = (Button) view.findViewById(R.id.female);

        savail = (Switch) view.findViewById(R.id.avail_switch);
        avail = "No";
/*        AutoCompleteTextView districtText = (AutoCompleteTextView) view.findViewById(R.id.district);
        String[]  district_names;
        district_names = getResources().getStringArray(R.array.districts);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,district_names);
        districtText.setAdapter(adapter)*/;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = ename.getText().toString();
                phone = ephone.getText().toString();
                email = eemail.getText().toString();
                city = ecity.getText().toString();
                address = eaddress.getText().toString();
                dob = edob.getText().toString();

                if(savail.isChecked()) {
                    avail="Yes";
                }
                else {
                    avail="No";
                }

                if (!name.isEmpty()&&!phone.isEmpty()&&!email.isEmpty()&&!city.isEmpty()&&!address.isEmpty()&&!dob.isEmpty()&&blood!=null&&gender!=null&&avail!=null) {
                    Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                    Register();
                }
                else if (name.isEmpty()) {
                    Toast.makeText(getActivity(),"Please fill Name",Toast.LENGTH_LONG).show();

                }
                else if (phone.isEmpty()) {
                    Toast.makeText(getActivity(),"Please fill Phone",Toast.LENGTH_LONG).show();
                }
                else if (email.isEmpty()) {
                    Toast.makeText(getActivity(),"Please fill Email",Toast.LENGTH_LONG).show();
                }
                else if (city.isEmpty()) {
                    Toast.makeText(getActivity(),"Please fill City",Toast.LENGTH_LONG).show();
                }
                else if (address.isEmpty()) {
                    Toast.makeText(getActivity(),"Please fill Address",Toast.LENGTH_LONG).show();
                }
                else if (dob.isEmpty()) {
                    Toast.makeText(getActivity(),"Please fill dob",Toast.LENGTH_LONG).show();
                }
                else if (blood.isEmpty()) {
                    Toast.makeText(getActivity(),"Please select bloodgroup",Toast.LENGTH_LONG).show();
                }
                else if (gender.isEmpty()) {
                    Toast.makeText(getActivity(),"Please select Gender",Toast.LENGTH_LONG).show();
                }
                else if (avail.isEmpty()) {
                    Toast.makeText(getActivity(),"",Toast.LENGTH_LONG).show();
                }

            }
        });

        aplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blood = "A+";
                aplus.setBackgroundResource(R.drawable.bg_red);
                aplus.setTextColor(Color.parseColor("#ffffff"));

                aminus.setBackgroundResource(R.drawable.bg_white);
                aminus.setTextColor(Color.parseColor("#000000"));
                bplus.setBackgroundResource(R.drawable.bg_white);
                bplus.setTextColor(Color.parseColor("#000000"));
                bminus.setBackgroundResource(R.drawable.bg_white);
                bminus.setTextColor(Color.parseColor("#000000"));
                abplus.setBackgroundResource(R.drawable.bg_white);
                abplus.setTextColor(Color.parseColor("#000000"));
                abminus.setBackgroundResource(R.drawable.bg_white);
                abminus.setTextColor(Color.parseColor("#000000"));
                oplus.setBackgroundResource(R.drawable.bg_white);
                oplus.setTextColor(Color.parseColor("#000000"));
                ominus.setBackgroundResource(R.drawable.bg_white);
                ominus.setTextColor(Color.parseColor("#000000"));

            }
        });
        aminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blood = "A-";
                aminus.setBackgroundResource(R.drawable.bg_red);
                aminus.setTextColor(Color.parseColor("#ffffff"));

                aplus.setBackgroundResource(R.drawable.bg_white);
                aplus.setTextColor(Color.parseColor("#000000"));
                bplus.setBackgroundResource(R.drawable.bg_white);
                bplus.setTextColor(Color.parseColor("#000000"));
                bminus.setBackgroundResource(R.drawable.bg_white);
                bminus.setTextColor(Color.parseColor("#000000"));
                abplus.setBackgroundResource(R.drawable.bg_white);
                abplus.setTextColor(Color.parseColor("#000000"));
                abminus.setBackgroundResource(R.drawable.bg_white);
                abminus.setTextColor(Color.parseColor("#000000"));
                oplus.setBackgroundResource(R.drawable.bg_white);
                oplus.setTextColor(Color.parseColor("#000000"));
                ominus.setBackgroundResource(R.drawable.bg_white);
                ominus.setTextColor(Color.parseColor("#000000"));
            }
        });
        bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blood = "B+";
                bplus.setBackgroundResource(R.drawable.bg_red);
                bplus.setTextColor(Color.parseColor("#ffffff"));

                aminus.setBackgroundResource(R.drawable.bg_white);
                aminus.setTextColor(Color.parseColor("#000000"));
                aplus.setBackgroundResource(R.drawable.bg_white);
                aplus.setTextColor(Color.parseColor("#000000"));
                bminus.setBackgroundResource(R.drawable.bg_white);
                bminus.setTextColor(Color.parseColor("#000000"));
                abplus.setBackgroundResource(R.drawable.bg_white);
                abplus.setTextColor(Color.parseColor("#000000"));
                abminus.setBackgroundResource(R.drawable.bg_white);
                abminus.setTextColor(Color.parseColor("#000000"));
                oplus.setBackgroundResource(R.drawable.bg_white);
                oplus.setTextColor(Color.parseColor("#000000"));
                ominus.setBackgroundResource(R.drawable.bg_white);
                ominus.setTextColor(Color.parseColor("#000000"));
            }
        });
        bminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blood = "B-";
                bminus.setBackgroundResource(R.drawable.bg_red);
                bminus.setTextColor(Color.parseColor("#ffffff"));

                aminus.setBackgroundResource(R.drawable.bg_white);
                aminus.setTextColor(Color.parseColor("#000000"));
                aplus.setBackgroundResource(R.drawable.bg_white);
                aplus.setTextColor(Color.parseColor("#000000"));
                bplus.setBackgroundResource(R.drawable.bg_white);
                bplus.setTextColor(Color.parseColor("#000000"));
                abplus.setBackgroundResource(R.drawable.bg_white);
                abplus.setTextColor(Color.parseColor("#000000"));
                abminus.setBackgroundResource(R.drawable.bg_white);
                abminus.setTextColor(Color.parseColor("#000000"));
                oplus.setBackgroundResource(R.drawable.bg_white);
                oplus.setTextColor(Color.parseColor("#000000"));
                ominus.setBackgroundResource(R.drawable.bg_white);
                ominus.setTextColor(Color.parseColor("#000000"));
            }
        });
        abplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blood = "AB+";
                abplus.setBackgroundResource(R.drawable.bg_red);
                abplus.setTextColor(Color.parseColor("#ffffff"));

                aminus.setBackgroundResource(R.drawable.bg_white);
                aminus.setTextColor(Color.parseColor("#000000"));
                aplus.setBackgroundResource(R.drawable.bg_white);
                aplus.setTextColor(Color.parseColor("#000000"));
                bplus.setBackgroundResource(R.drawable.bg_white);
                bplus.setTextColor(Color.parseColor("#000000"));
                bminus.setBackgroundResource(R.drawable.bg_white);
                bminus.setTextColor(Color.parseColor("#000000"));
                abminus.setBackgroundResource(R.drawable.bg_white);
                abminus.setTextColor(Color.parseColor("#000000"));
                oplus.setBackgroundResource(R.drawable.bg_white);
                oplus.setTextColor(Color.parseColor("#000000"));
                ominus.setBackgroundResource(R.drawable.bg_white);
                ominus.setTextColor(Color.parseColor("#000000"));
            }
        });
        abminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blood = "AB-";
                abminus.setBackgroundResource(R.drawable.bg_red);
               abminus.setTextColor(Color.parseColor("#ffffff"));

                aminus.setBackgroundResource(R.drawable.bg_white);
                aminus.setTextColor(Color.parseColor("#000000"));
                aplus.setBackgroundResource(R.drawable.bg_white);
                aplus.setTextColor(Color.parseColor("#000000"));
                bplus.setBackgroundResource(R.drawable.bg_white);
                bplus.setTextColor(Color.parseColor("#000000"));
                bminus.setBackgroundResource(R.drawable.bg_white);
                bminus.setTextColor(Color.parseColor("#000000"));
                abplus.setBackgroundResource(R.drawable.bg_white);
                abplus.setTextColor(Color.parseColor("#000000"));;
                oplus.setBackgroundResource(R.drawable.bg_white);
                oplus.setTextColor(Color.parseColor("#000000"));
                ominus.setBackgroundResource(R.drawable.bg_white);
                ominus.setTextColor(Color.parseColor("#000000"));
            }
        });
        oplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blood = "O+";
                oplus.setBackgroundResource(R.drawable.bg_red);
                oplus.setTextColor(Color.parseColor("#ffffff"));

                aminus.setBackgroundResource(R.drawable.bg_white);
                aminus.setTextColor(Color.parseColor("#000000"));
                aplus.setBackgroundResource(R.drawable.bg_white);
                aplus.setTextColor(Color.parseColor("#000000"));
                bplus.setBackgroundResource(R.drawable.bg_white);
                bplus.setTextColor(Color.parseColor("#000000"));
                bminus.setBackgroundResource(R.drawable.bg_white);
                bminus.setTextColor(Color.parseColor("#000000"));
                abplus.setBackgroundResource(R.drawable.bg_white);
                abplus.setTextColor(Color.parseColor("#000000"));
                abminus.setBackgroundResource(R.drawable.bg_white);
                abminus.setTextColor(Color.parseColor("#000000"));
                ominus.setBackgroundResource(R.drawable.bg_white);
                ominus.setTextColor(Color.parseColor("#000000"));
            }
        });
        ominus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blood = "O-";
                ominus.setBackgroundResource(R.drawable.bg_red);
                ominus.setTextColor(Color.parseColor("#ffffff"));

                aminus.setBackgroundResource(R.drawable.bg_white);
                aminus.setTextColor(Color.parseColor("#000000"));
                aplus.setBackgroundResource(R.drawable.bg_white);
                aplus.setTextColor(Color.parseColor("#000000"));
                bplus.setBackgroundResource(R.drawable.bg_white);
                bplus.setTextColor(Color.parseColor("#000000"));
                bminus.setBackgroundResource(R.drawable.bg_white);
                bminus.setTextColor(Color.parseColor("#000000"));
                abplus.setBackgroundResource(R.drawable.bg_white);
                abplus.setTextColor(Color.parseColor("#000000"));
                abminus.setBackgroundResource(R.drawable.bg_white);
                abminus.setTextColor(Color.parseColor("#000000"));
                oplus.setBackgroundResource(R.drawable.bg_white);
                oplus.setTextColor(Color.parseColor("#000000"));
            }
        });


        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "M";
                male.setBackgroundResource(R.drawable.bg_red);
                male.setTextColor(Color.parseColor("#ffffff"));

                female.setBackgroundResource(R.drawable.bg_white);
                female.setTextColor(Color.parseColor("#000000"));
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "F";
                female.setBackgroundResource(R.drawable.bg_red);
                female.setTextColor(Color.parseColor("#ffffff"));

                male.setBackgroundResource(R.drawable.bg_white);
                male.setTextColor(Color.parseColor("#000000"));
            }
        });



        return view;
    }

    private void Register() {
        class register_form extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Please wait...","Registering...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.Title_name,name);
                params.put(Config.Title_bloodgroup,blood);
                params.put(Config.Title_gender,gender);
                params.put(Config.Title_dob,dob);
                params.put(Config.Title_mobile,phone);
                params.put(Config.Title_city,city);
                params.put(Config.Title_email,email);
                params.put(Config.Title_address,address);
                params.put(Config.Title_avail,avail);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.REGISTER_URL, params);
                return res;
            }
        }

        register_form ci = new register_form();
        ci.execute();
    }


}

