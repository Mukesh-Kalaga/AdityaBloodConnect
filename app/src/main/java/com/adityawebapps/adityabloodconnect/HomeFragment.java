package com.adityawebapps.adityabloodconnect;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView reg,find;

    public HomeFragment() {
        // Required empty public constructor1
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        reg = (TextView) view.findViewById(R.id.register);
        find= (TextView) view.findViewById(R.id.find);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment registerFragment= new RegisterFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.content_drawer, registerFragment , registerFragment.getTag()).commit();
            }
        });
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment searchFragment= new SearchFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.content_drawer, searchFragment, searchFragment.getTag()).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
