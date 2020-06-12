package com.ucas.graduationproject.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ucas.graduationproject.Activities.LoginActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.ucas.graduationproject.Adapters.Custom_RvAdapter_NoteFg;
import com.ucas.graduationproject.Model.Item_NoteFg;
import com.ucas.graduationproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment {

    private View view;
    private RecyclerView mNotefgRecView;
    private List<Item_NoteFg> mData;
    private TextView tvStudentName;
    private ProgressBar progressBarNotesF;
    private Custom_RvAdapter_NoteFg recViwe_Adapter;
    private LinearLayout notesLayout;

    private String url = "http://siteproject-001-site1.btempurl.com/api/notes";

    public NoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_note, container, false);

        init();
        progressBarNotesF.setVisibility(View.VISIBLE);
        
        if (!checkInternetConnected()) {
            Toast.makeText(getActivity(), "عذرا لا يوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();

        } else {
            SharedPreferences sp = getActivity().getSharedPreferences(LoginActivity.USERS_SHARED, Context.MODE_PRIVATE);

            String name = sp.getString(LoginActivity.STUDENT_NAME, "");

            tvStudentName.setText(name);

            mData = new ArrayList<>();
            loadNots();
        }


        return view;


    }

    void init() {

        mNotefgRecView = view.findViewById(R.id.notefg_RecView);
        tvStudentName = view.findViewById(R.id.tvStudentName);
        progressBarNotesF = view.findViewById(R.id.progressBarNotesF);
        notesLayout = view.findViewById(R.id.notesLayout);

    }

    private void loadNots() {

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        int notId = object.getInt("noteId");
                        String message = object.getString("massage");
                        String title = object.getString("title");
                        int teacherId = object.getInt("teacherId");

                        JSONObject object2 = object.getJSONObject("teacher");
                        String teacherName = object2.getString("name");

                        JSONObject object3 = object2.getJSONObject("subject");
                        int subjectId = object3.getInt("subjectId");
                        String subjectName = object3.getString("subjectName");

                        mData.add(new Item_NoteFg(subjectName
                                , teacherName
                                , title
                                , message));

                        progressBarNotesF.setVisibility(View.GONE);
                        notesLayout.setVisibility(View.VISIBLE);
                        recViwe_Adapter = new Custom_RvAdapter_NoteFg(getContext(), mData);
                        mNotefgRecView.setLayoutManager(new LinearLayoutManager(getContext()));
                        mNotefgRecView.setHasFixedSize(true);
                        mNotefgRecView.setAdapter(recViwe_Adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("notsFragment", error.getMessage() + "");
            }
        });

        Volley.newRequestQueue(getActivity()).add(request);

    }

    private boolean checkInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
