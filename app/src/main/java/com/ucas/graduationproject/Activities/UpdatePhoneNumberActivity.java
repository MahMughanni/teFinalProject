package com.ucas.graduationproject.Activities;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.android.volley.toolbox.Volley;
import com.ucas.graduationproject.Model.BooleanRequest;
import com.ucas.graduationproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UpdatePhoneNumberActivity extends AppCompatActivity {

    private EditText mEtPhoneNumber;
    private Button mBtnOkNewPhoneNumber;
    private String url = "http://siteproject-001-site1.btempurl.com/api/student/";
    private EditText mEtPasswordPhoneNumber;
    private Toolbar mToolbarUpdatePhone;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phone_number);

        mToolbarUpdatePhone = findViewById(R.id.toolbarUpdatePhone);
        mEtPhoneNumber = findViewById(R.id.etPhoneNumber);
        mEtPasswordPhoneNumber = findViewById(R.id.etPasswordPhoneNumber);
        mBtnOkNewPhoneNumber = findViewById(R.id.btnOkNewPhoneNumber);

        setSupportActionBar(mToolbarUpdatePhone);
        progressDialog = new ProgressDialog(this);

        SharedPreferences sp = getSharedPreferences(LoginActivity.USERS_SHARED, Context.MODE_PRIVATE);

        int studentId = sp.getInt(LoginActivity.STUDENT_ID, 0);

        mBtnOkNewPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = mEtPhoneNumber.getText().toString().trim();
                String password = mEtPasswordPhoneNumber.getText().toString().trim();

                if (!checkInternetConnected()) {
                    progressDialog.dismiss();
                    Toast.makeText(UpdatePhoneNumberActivity.this, "عذرا لا يوجد اتصال بالانترنت", Toast.LENGTH_SHORT).show();
                } else {
                    if (!number.isEmpty() || !password.isEmpty()) {
                        updatePhoneNumber(studentId, Integer.parseInt(number), password);

                    } else {
                        Toast.makeText(UpdatePhoneNumberActivity.this, "الرجاء ملئ الحقول الفارغة", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mToolbarUpdatePhone.setNavigationIcon(R.drawable.ic_back);

        mToolbarUpdatePhone.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void updatePhoneNumber(int studentId, int studentPhoneNumber, String studentPassword) {
        progressDialog.setMessage("جاري تغيير رقم الهاتف...");
        progressDialog.show();

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
                    progressDialog.dismiss();
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("UpdatePhoneActivity", error.getMessage() + "");
                    if (error.getMessage() == null) {
                        Toast.makeText(UpdatePhoneNumberActivity.this, "كلمة المرور خاطئة", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
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
