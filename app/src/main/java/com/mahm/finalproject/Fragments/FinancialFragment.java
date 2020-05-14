package com.mahm.finalproject.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mahm.finalproject.Adapters.FinancialAdapter;
import com.mahm.finalproject.Model.FinancialData;
import com.mahm.finalproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinancialFragment extends Fragment {

    private RecyclerView rv;
    private ArrayList<FinancialData> data = new ArrayList<>();
    private FinancialAdapter adapter;
    private String url = "http://mohamedd8f8w-001-site1.dtempurl.com/api/financialrecords";

    public FinancialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_financial, container, false);
        rv = v.findViewById(R.id.rvFinancialFragment);

        if (!checkInternetConnected()) {
            Toast.makeText(getActivity(), "عذرا لا يوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();

        } else {
            loadFinancialRecords();
        }

        return v;
    }

    private void loadFinancialRecords() {

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object1 = array.getJSONObject(i);

                        int financialId = object1.getInt("financialRecordId");
                        int payNum = object1.getInt("payNum");
                        String dateOfPay = object1.getString("dateFoPay");

                        JSONObject object2 = object1.getJSONObject("semester");

                        String dateAfterCut = dateOfPay.substring(0, 10);

                        data.add(new FinancialData(payNum, 0, dateAfterCut, financialId));
                        adapter = new FinancialAdapter(data, getActivity());
                        rv.setAdapter(adapter);
                        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rv.setHasFixedSize(true);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("FinancialFragment", error.getMessage() + "");
            }
        });

        Volley.newRequestQueue(getActivity()).add(request);
    }

    private boolean checkInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }


}
