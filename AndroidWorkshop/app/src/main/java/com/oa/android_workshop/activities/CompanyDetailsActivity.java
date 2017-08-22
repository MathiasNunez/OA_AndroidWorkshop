package com.oa.android_workshop.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
    private Company mCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        mCompany = (Company) getIntent().getExtras().getSerializable("company");
        vLogo = (ImageView) findViewById(R.id.logo);
        vName = (TextView) findViewById(R.id.name);
        vAddress = (TextView) findViewById(R.id.address);
        vPhone = (TextView) findViewById(R.id.phone);
        vWebSite = (TextView) findViewById(R.id.website);
        if (mCompany != null) {
            Picasso.with(this)
                    .load(mCompany.getLogoUrl())
                    .placeholder(getResources().getDrawable(R.mipmap.ic_launcher))
                    .into(vLogo);

            vName.setText(mCompany.getName());
            vAddress.setText(mCompany.getAddress());
            vAddress.setOnClickListener(this);
            vPhone.setText(mCompany.getPhone());
            vPhone.setOnClickListener(this);
            vWebSite.setText(mCompany.getWebSiteUrl());
            vWebSite.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.address:
                String addressUri = "http://maps.google.com/maps?q=loc:" + mCompany.getLatitude() + ","
                        + mCompany.getLongitude() + " (" + mCompany.getName() + ")";
                Intent addressIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(addressUri));
                startActivity(addressIntent);
                break;
            case R.id.phone:
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:" + mCompany.getPhone()));
                startActivity(phoneIntent);
                break;
            case R.id.website:
                Intent websiteIntent = new Intent(this, WebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", mCompany.getWebSiteUrl());
                websiteIntent.putExtras(bundle);
                startActivity(websiteIntent);
                break;
        }
    }
}
