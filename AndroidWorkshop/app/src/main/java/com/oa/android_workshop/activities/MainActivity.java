package com.oa.android_workshop.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        vCompaniesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Company company = mCompanies.get(i);
                Intent intent = new Intent(MainActivity.this, CompanyDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("company", company);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}

