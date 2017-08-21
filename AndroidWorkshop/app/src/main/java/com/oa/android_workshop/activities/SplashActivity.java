package com.oa.android_workshop.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.oa.android_workshop.R;
import com.oa.android_workshop.application.WorkshopApplication;
import com.oa.android_workshop.models.Company;
import com.oa.android_workshop.network.ServiceHandler;
import com.overactiveinc.oarestapi.network.RestApiCallback;

import java.util.List;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ServiceHandler serviceHandler = new ServiceHandler(this);
        serviceHandler.doGetCompanies(new RestApiCallback<List<Company>>() {
            @Override
            public void onSuccess(List<Company> object) {
                WorkshopApplication.setmCompanies(object);
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(SplashActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
