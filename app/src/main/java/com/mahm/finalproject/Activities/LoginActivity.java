package com.mahm.finalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mahm.finalproject.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mLoginActEdIdNum;
    private EditText mLoginActEdPassword;
    private Button mLoginActBtnLogin;
    private TextView mLoginActTvForgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvivty);

        init();

        mLoginActBtnLogin.setOnClickListener(this);
        mLoginActTvForgetPass.setOnClickListener(this);


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
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "ID Number or Password Is Empty", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.login_act_tv_forget_pass:
                Toast.makeText(this, "Forget Password .", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
