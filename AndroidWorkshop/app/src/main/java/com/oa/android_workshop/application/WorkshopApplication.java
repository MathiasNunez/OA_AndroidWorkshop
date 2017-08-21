package com.oa.android_workshop.application;

import android.app.Application;

import com.oa.android_workshop.models.Company;

import java.util.List;

/**
 * Created by mnunez on 8/21/17.
 */

public class WorkshopApplication extends Application {

    private static List<Company> mCompanies;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static List<Company> getmCompanies() {
        return mCompanies;
    }

    public static void setmCompanies(List<Company> mCompanies) {
        WorkshopApplication.mCompanies = mCompanies;
    }
}
