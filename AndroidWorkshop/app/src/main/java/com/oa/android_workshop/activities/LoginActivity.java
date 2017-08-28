package com.oa.android_workshop.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.oa.android_workshop.R;
import com.oa.android_workshop.network.ServiceHandler;
import com.overactiveinc.oarestapi.network.RestApiCallback;

/**
 * Created by mnunez on 8/1/17.
 */

public class LoginActivity extends Activity {

    private Button vLoginBtn;
    private EditText vUsername, vPassword;
    private RelativeLayout vProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vLoginBtn = (Button) findViewById(R.id.login_btn);
        vUsername = (EditText) findViewById(R.id.username);
        vPassword = (EditText) findViewById(R.id.password);
        vProgressBar = (RelativeLayout) findViewById(R.id.progress_bar);

        vLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vUsername.getText().toString().length() == 0 ||
                        vPassword.getText().toString().length() == 0) {
                    Toast.makeText(LoginActivity.this, "Username and Password are required", Toast.LENGTH_SHORT).show();
                } else {
                    vProgressBar.setVisibility(View.VISIBLE);
                    ServiceHandler serviceHandler = new ServiceHandler(LoginActivity.this);
                    serviceHandler.doPostLogin(vUsername.getText().toString(), vPassword.getText().toString(),
                            new RestApiCallback<String>() {
                                @Override
                                public void onSuccess(String object) {
                                    goToHome();
                                    vProgressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onError(String message) {
                                    vProgressBar.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

    }

    private void goToHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
