package com.oa.android_workshop.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oa.android_workshop.R;

/**
 * Created by mnunez on 8/1/17.
 */

public class LoginActivity extends Activity {

    private Button vLoginBtn;
    private EditText vUsername, vPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vLoginBtn = (Button) findViewById(R.id.login_btn);
        vUsername = (EditText) findViewById(R.id.username);
        vPassword = (EditText) findViewById(R.id.password);


        vLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vUsername.getText().toString().length() == 0 ||
                        vPassword.toString().length() == 0) {
                    Toast.makeText(LoginActivity.this, "Username and Password are required", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
