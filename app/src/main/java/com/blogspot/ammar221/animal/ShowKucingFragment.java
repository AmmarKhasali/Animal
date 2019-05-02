package com.blogspot.ammar221.animal;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowKucingFragment extends Fragment {


    public ShowKucingFragment() {
        // Required empty public constructor
    }

    private ArrayList<HashMap<String,String>> Kucing;

    RecyclerView lv_kucing;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_kucing, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv_kucing = view.findViewById(R.id.rv_kucing);
        lv_kucing.setLayoutManager(new LinearLayoutManager(getActivity()));

        Kucing = new ArrayList<>();
        showData();
    }

    private void showData() {
        final ProgressDialog progressDialaog = new ProgressDialog(getActivity());
        progressDialaog.setTitle("Loading");
        progressDialaog.setMessage("Please Wait..");
        progressDialaog.show();

        String URL  = UrlConstants.BASEURL +"/v1/breeds";

        JsonArrayRequest myRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialaog.dismiss();

                if (response != null)
                Log.d("log", "onResponse" + response.toString());


                for (int a =0; a < response.length(); a++) {

                    try {

                        JSONObject jsonObject = response.getJSONObject(a);
                        HashMap<String, String> rowData = new HashMap<>();
                        rowData.put("name", jsonObject.getString("name"));
                        rowData.put("origin", jsonObject.getString("origin"));
                        Kucing.add(rowData);


                        KucingAdapter kucingAdapter = new KucingAdapter(getActivity(), Kucing);
                        lv_kucing.setAdapter(kucingAdapter);
                    } catch (JSONException e) {
                        Log.d("log", "JSONExcaption: " + e.getMessage());
                    }

                }
            }
        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialaog.dismiss();
                Log.d("log", "onErrorResponse: " + error.getMessage());
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        RequestQueue myrequestqueue = Volley.newRequestQueue(getActivity());
        myrequestqueue.add(myRequest);
    }


}
