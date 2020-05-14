package com.mahm.finalproject.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.mahm.finalproject.Model.BooleanRequest;
import com.mahm.finalproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class UpdatePasswordActivity extends AppCompatActivity {

    private EditText mEtOldPassword;
    private EditText mEtNewPassword;
    private EditText mEtConfirmNewPassword;
    private Button mBtnOkNewPassword;
    private String url = "http://mohamedd8f8w-001-site1.dtempurl.com/api/student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        mEtOldPassword = findViewById(R.id.etOldPassword);
        mEtNewPassword = findViewById(R.id.etNewPassword);
        mEtConfirmNewPassword = findViewById(R.id.etConfirmNewPassword);
        mBtnOkNewPassword = findViewById(R.id.btnOkNewPassword);

        SharedPreferences sp = getSharedPreferences(LoginActivity.USERS_SHARED, Context.MODE_PRIVATE);

        int studentId = sp.getInt(LoginActivity.STUDENT_ID, 0);

        mBtnOkNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = mEtOldPassword.getText().toString().trim();
                String newPass = mEtNewPassword.getText().toString().trim();
                String confirmNewPass = mEtConfirmNewPassword.getText().toString().trim();

                if (!checkInternetConnected()) {
                    Toast.makeText(UpdatePasswordActivity.this, "عذرا لا يوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();
                }

                if (confirmNewPass.equals(newPass) && !oldPass.isEmpty()) {

                    changePassword(studentId, oldPass, newPass, confirmNewPass);
                }

            }
        });


    }

    private void changePassword(int studentId, String oldPass, String newPass, String confirmNewPass) {

        try {
            JSONObject jsonBody;
            jsonBody = new JSONObject();
            jsonBody.put("studentid", studentId);
            jsonBody.put("studentpassword", oldPass);
            jsonBody.put("newpassword", confirmNewPass);
            String requestBody = jsonBody.toString();
            BooleanRequest booleanRequest = new BooleanRequest(Request.Method.PUT,
                    url, requestBody, new Response.Listener<Boolean>() {
                @Override
                public void onResponse(Boolean response) {
                    Toast.makeText(UpdatePasswordActivity.this, "تم تغيير كلمة المرور", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("UpdatePasswordActivity", error.getMessage() + "");
                }
            });
            // Add the request to the RequestQueue.
            Volley.newRequestQueue(this).add(booleanRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean checkInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }


}
