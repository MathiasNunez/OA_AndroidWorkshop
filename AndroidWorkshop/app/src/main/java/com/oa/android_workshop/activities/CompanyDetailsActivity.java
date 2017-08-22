package com.oa.android_workshop.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oa.android_workshop.R;
import com.oa.android_workshop.models.Company;
import com.squareup.picasso.Picasso;

public class CompanyDetailsActivity extends Activity implements View.OnClickListener {

    private ImageView vLogo;
    private TextView vName, vPhone, vAddress, vWebSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        Company company = (Company) getIntent().getExtras().getSerializable("company");
        vLogo = (ImageView) findViewById(R.id.logo);
        vName = (TextView) findViewById(R.id.name);
        vAddress = (TextView) findViewById(R.id.address);
        vPhone = (TextView) findViewById(R.id.phone);
        vWebSite = (TextView) findViewById(R.id.website);
        if (company != null) {
            Picasso.with(this)
                    .load(company.getLogoUrl())
                    .placeholder(getResources().getDrawable(R.mipmap.ic_launcher))
                    .into(vLogo);

            vName.setText(company.getName());
            vAddress.setText(company.getAddress());
            vAddress.setOnClickListener(this);
            vPhone.setText(company.getPhone());
            vPhone.setOnClickListener(this);
            vWebSite.setText(company.getWebSiteUrl());
            vWebSite.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.address:
                break;
            case R.id.phone:
                break;
            case R.id.website:
                break;
        }
    }
}
