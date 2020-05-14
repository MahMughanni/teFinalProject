package com.mahm.finalproject.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.mahm.finalproject.Model.BooleanRequest;
import com.mahm.finalproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class UpdatePhoneNumberActivity extends AppCompatActivity {

    private EditText mEtPhoneNumber;
    private Button mBtnOkNewPhoneNumber;
    private String url = "http://mohamedd8f8w-001-site1.dtempurl.com/api/student/";
    private EditText mEtPasswordPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phone_number);

        mEtPhoneNumber = findViewById(R.id.etPhoneNumber);
        mEtPasswordPhoneNumber = findViewById(R.id.etPasswordPhoneNumber);
        mBtnOkNewPhoneNumber = findViewById(R.id.btnOkNewPhoneNumber);

        SharedPreferences sp = getSharedPreferences(LoginActivity.USERS_SHARED, Context.MODE_PRIVATE);

        int studentId = sp.getInt(LoginActivity.STUDENT_ID, 0);

        mBtnOkNewPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = mEtPhoneNumber.getText().toString().trim();
                String password = mEtPasswordPhoneNumber.getText().toString().trim();
                updatePhoneNumber(studentId, Integer.parseInt(number), password);
            }
        });

    }

    private void updatePhoneNumber(int studentId, int studentPhoneNumber, String studentPassword) {
        try {
            JSONObject jsonBody;
            jsonBody = new JSONObject();
            jsonBody.put("studenttelephonemumber", studentPhoneNumber);
            jsonBody.put("studentpassword", studentPassword);
            String requestBody = jsonBody.toString();
            BooleanRequest booleanRequest = new BooleanRequest(Request.Method.PUT,
                    url + studentId, requestBody, new Response.Listener<Boolean>() {
                @Override
                public void onResponse(Boolean response) {
                    Toast.makeText(UpdatePhoneNumberActivity.this, "تم تغيير رقم الهاتف", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("UpdatePhoneActivity", error.getMessage() + "");
                }
            });
            // Add the request to the RequestQueue.
            Volley.newRequestQueue(this).add(booleanRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
