package com.ucas.graduationproject.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ucas.graduationproject.Activities.LoginActivity;
import com.ucas.graduationproject.Adapters.FinancialAdapter;
import com.ucas.graduationproject.Model.FinancialData;
import com.ucas.graduationproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinancialFragment extends Fragment {

    private RecyclerView rv;
    private ProgressBar progressBarFinancialF;
    private ArrayList<FinancialData> data = new ArrayList<>();
    private FinancialAdapter adapter;
    private String url = "http://siteproject-001-site1.btempurl.com/api/financialrecords";
    private int currentStudentId;

    public FinancialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_financial, container, false);
        rv = v.findViewById(R.id.rvFinancialFragment);
        progressBarFinancialF = v.findViewById(R.id.progressBarFinancialF);
        progressBarFinancialF.setVisibility(View.VISIBLE);

        SharedPreferences sp = Objects.requireNonNull(getActivity()).getSharedPreferences(LoginActivity.USERS_SHARED, Context.MODE_PRIVATE);

        currentStudentId = sp.getInt(LoginActivity.STUDENT_ID, 0);

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

                        int studentId = object1.getInt("studentId");
                        int financialId = object1.getInt("financialRecordId");
                        int payNum = object1.getInt("payNum");
                        String dateOfPay = object1.getString("dateFoPay");
                        String dateAfterCut = dateOfPay.substring(0, 10);

                        JSONObject object2 = object1.getJSONObject("semester");
                        String semesterName = object2.getString("season");

                        if (studentId == currentStudentId) {
                            data.add(new FinancialData(payNum, dateAfterCut, semesterName, financialId));
                        }
                    }

                    progressBarFinancialF.setVisibility(View.GONE);
                    adapter = new FinancialAdapter(data, getActivity());
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setHasFixedSize(true);

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
