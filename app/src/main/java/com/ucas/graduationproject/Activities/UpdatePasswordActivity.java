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

public class UpdatePasswordActivity extends AppCompatActivity {

    private EditText mEtOldPassword;
    private EditText mEtNewPassword;
    private EditText mEtConfirmNewPassword;
    private Button mBtnOkNewPassword;
    private String url = "http://siteproject-001-site1.btempurl.com/api/student";
    private Toolbar mToolbarUpdatePassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        mToolbarUpdatePassword = findViewById(R.id.toolbarUpdatePassword);
        mEtOldPassword = findViewById(R.id.etOldPassword);
        mEtNewPassword = findViewById(R.id.etNewPassword);
        mEtConfirmNewPassword = findViewById(R.id.etConfirmNewPassword);
        mBtnOkNewPassword = findViewById(R.id.btnOkNewPassword);

        setSupportActionBar(mToolbarUpdatePassword);
        progressDialog = new ProgressDialog(this);

        SharedPreferences sp = getSharedPreferences(LoginActivity.USERS_SHARED, Context.MODE_PRIVATE);

        int studentId = sp.getInt(LoginActivity.STUDENT_ID, 0);

        mBtnOkNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = mEtOldPassword.getText().toString().trim();
                String newPass = mEtNewPassword.getText().toString().trim();
                String confirmNewPass = mEtConfirmNewPassword.getText().toString().trim();

                if (!checkInternetConnected()) {
                    progressDialog.dismiss();
                    Toast.makeText(UpdatePasswordActivity.this, "عذرا لا يوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();
                }

                if (!oldPass.isEmpty() || !newPass.isEmpty() || !confirmNewPass.isEmpty()) {
                    if (confirmNewPass.equals(newPass) && newPass.length() >= 10) {

                        changePassword(studentId, oldPass, newPass, confirmNewPass);

                    } else if (!confirmNewPass.equals(newPass)) {
                        Toast.makeText(UpdatePasswordActivity.this, "تاكيد كلمة المرور غير متطابقة", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(UpdatePasswordActivity.this, "تاكيد كلمة المرور اقل من 10 احرف", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UpdatePasswordActivity.this, "الرجاء ملئ الحقول الفارغة", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mToolbarUpdatePassword.setNavigationIcon(R.drawable.ic_back);

        mToolbarUpdatePassword.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void changePassword(int studentId, String oldPass, String newPass, String confirmNewPass) {
        progressDialog.setMessage("جاري تغيير كلمة المرور...");
        progressDialog.show();

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
                    progressDialog.dismiss();
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("UpdatePasswordActivity", error.getMessage() + "");
                    if (error.getMessage() == null) {
                        Toast.makeText(UpdatePasswordActivity.this, "كلمة المرور القديمة خاطئة", Toast.LENGTH_LONG).show();
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
