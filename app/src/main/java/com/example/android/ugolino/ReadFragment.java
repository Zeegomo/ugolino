package com.example.android.ugolino;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadFragment extends Fragment {

    public ReadFragment() {
        // Required empty public constructor
    }

    public static ArrayList<Device> read_devices = MainActivity.read_devices;

    //Device visualizer
    public static ReadAdapter adapter;
    public static ListView listView;
    public static TextView instructionsTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        //View initialization
        adapter = new ReadAdapter(getActivity(), read_devices);
        listView = (ListView) rootView.findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        instructionsTextView = (TextView) rootView.findViewById(R.id.instructions_textView);

        return rootView;
    }



    public static void dataNotify(ArrayList<Device> devices) {
        read_devices = devices;
        if(adapter != null){
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        Log.e("notify", "ReadFrament");
        Log.e("read_device" + read_devices, "ReadFrament");
        Log.e("read_devices size: " + read_devices.size(), "ReadFrament");
    }

    @Override
    public void onResume() {
        super.onResume();
        //dataNotify(MainActivity.read_devices);
        read_devices = MainActivity.read_devices;
        adapter = new ReadAdapter(getActivity(), read_devices);
        listView.setClickable(true);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        listView.requestFocus();
        if (read_devices.size() > 0) {
            listView.setVisibility(View.VISIBLE);
            instructionsTextView.setVisibility(View.GONE);
        } else {
            listView.setVisibility(View.GONE);
            instructionsTextView.setVisibility(View.VISIBLE);
        }

    }

}
