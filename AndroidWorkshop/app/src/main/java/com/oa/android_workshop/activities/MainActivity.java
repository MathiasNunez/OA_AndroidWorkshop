package com.oa.android_workshop.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.oa.android_workshop.R;
import com.oa.android_workshop.adapters.CompaniesAdapter;
import com.oa.android_workshop.application.WorkshopApplication;
import com.oa.android_workshop.models.Company;

import java.util.List;

/**
 * Created by mnunez on 8/5/17.
 */

public class MainActivity extends Activity {

    private List<Company> mCompanies;
    private ListView vCompaniesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCompanies = WorkshopApplication.getmCompanies();
        vCompaniesList = (ListView) findViewById(R.id.companies_list);
        CompaniesAdapter adapter = new CompaniesAdapter(this, 0, mCompanies);
        vCompaniesList.setAdapter(adapter);
    }

}

