package com.ucas.graduationproject.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ucas.graduationproject.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mLoginActEdIdNum;
    private EditText mLoginActEdPassword;
    private Button mLoginActBtnLogin;
    private TextView mLoginActTvForgetPass;
    public static final String USERS_SHARED = "Users";
    public static final String STUDENT_ID = "studentId";
    public static final String STUDENT_NAME = "studentName";
    public static final String STUDENT_DOF = "studentDateOfBirth";
    public static final String STUDENT_PHONE = "studentPhone";
    private ProgressDialog progressDialog;

    private String url = "http://siteproject-001-site1.btempurl.com/api/student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvivty);

        init();

        mLoginActBtnLogin.setOnClickListener(this);
        mLoginActTvForgetPass.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

    }

    void init() {
        mLoginActEdIdNum = findViewById(R.id.login_act_ed_idNum);
        mLoginActEdPassword = findViewById(R.id.login_act_ed_password);
        mLoginActBtnLogin = findViewById(R.id.login_act_btn_login);
        mLoginActTvForgetPass = findViewById(R.id.login_act_tv_forget_pass);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_act_btn_login:

                String idNumber = mLoginActEdIdNum.getText().toString();
                String password = mLoginActEdPassword.getText().toString();

                if (!idNumber.isEmpty() && !password.isEmpty()) {

                    if (!checkInternetConnected()) {
                        Toast.makeText(LoginActivity.this, "عذرا لا يوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();
                    } else {
                        progressDialog.setMessage("جاري تسجيل الدخول...");
                        progressDialog.show();
                        loginStudent(idNumber.trim(), password.trim());
                    }

                } else {
                    Toast.makeText(this, "ID Number or Password Is Empty", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.login_act_tv_forget_pass:
                startActivity(new Intent(getBaseContext(), ResetPasswordActivity.class));
                break;

        }
    }

    private void loginStudent(String studentId, String studentPassword) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("studentid", Integer.parseInt(studentId));
            jsonObject.put("studentpassword", studentPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        try {

                            int id = response.getInt("studentId");
                            String name = response.getString("studentName");
                            String dateOfBirth = response.getString("studentDateOfBirth");
                            String phoneNumber = response.getString("studentTelephoneMumber");

                            SharedPreferences sp = getSharedPreferences(USERS_SHARED, MODE_PRIVATE);
                            SharedPreferences.Editor spEdit = sp.edit();

                            spEdit.putInt(STUDENT_ID, id);
                            spEdit.putString(STUDENT_NAME, name);
                            spEdit.putString(STUDENT_DOF, dateOfBirth);
                            spEdit.putString(STUDENT_PHONE, phoneNumber);
                            spEdit.apply();

                            Log.e("success", id + "\n" + name + "\n" + dateOfBirth + "\n" + phoneNumber + "");

                            startActivity(new Intent(getBaseContext(), HomeActivity.class));
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LoginActivity", error.getMessage() + "");
                if (error.getMessage() == null) {
                    Toast.makeText(LoginActivity.this, "الرجاء التحقق من رقم الطالب او كلمة المرور", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });

        Volley.newRequestQueue(this).add(request);
    }

    private boolean checkInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
