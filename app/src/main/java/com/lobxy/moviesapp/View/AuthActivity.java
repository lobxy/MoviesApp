package com.lobxy.moviesapp.View;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lobxy.moviesapp.R;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ACT NAME";
    private TextInputLayout inputLayout_name;

    private TextInputEditText input_edit_name;
    private TextInputEditText input_edit_username;
    private TextInputEditText input_edit_password;

    private ProgressBar progressBar;

    private Button btn_trigger_login;
    private Button btn_trigger_register;

    private String mName, mUsername, mPassword;

    boolean login_trigger = true;  // true for login & false to register

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        progressBar = findViewById(R.id.auth_progress_bar);

        inputLayout_name = findViewById(R.id.auth_text_input_name);

        input_edit_name = findViewById(R.id.auth_edit_name);
        input_edit_username = findViewById(R.id.auth_edit_username);
        input_edit_password = findViewById(R.id.auth_edit_password);

        btn_trigger_login = findViewById(R.id.auth_btn_login);
        btn_trigger_register = findViewById(R.id.auth_btn_register);

        btn_trigger_register.setOnClickListener(this);
        btn_trigger_login.setOnClickListener(this);

        Button submit = findViewById(R.id.auth_btn_submit);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.auth_btn_login:
                login_trigger = true;
                inputLayout_name.setVisibility(View.GONE);
                btn_trigger_login.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                btn_trigger_register.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                break;

            case R.id.auth_btn_register:
                login_trigger = false;
                inputLayout_name.setVisibility(View.VISIBLE);
                btn_trigger_login.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                btn_trigger_register.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                break;

            case R.id.auth_btn_submit:
                validate();
                break;

            default:
                Log.d(TAG, "onClick: no view found with listener attached.");
                break;
        }
    }

    private void validate() {

        mName = input_edit_name.getEditableText().toString().trim();
        mUsername = input_edit_name.getEditableText().toString().trim();
        mPassword = input_edit_password.getEditableText().toString().trim();

        if (mName.isEmpty() && !login_trigger) {
            input_edit_name.setError("Field empty");
        } else if (mUsername.isEmpty()) {
            input_edit_username.setError("Field empty");
        } else if (mPassword.isEmpty()) {
            input_edit_password.setError("Field empty");
        } else {
            if (login_trigger) {
                loginUser();
            } else {
                registerUser();
            }
        }
    }


    private void registerUser() {

    }

    private void loginUser() {

    }
}
