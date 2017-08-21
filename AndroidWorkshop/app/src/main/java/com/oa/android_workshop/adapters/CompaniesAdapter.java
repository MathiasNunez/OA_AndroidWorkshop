package com.oa.android_workshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oa.android_workshop.R;
import com.oa.android_workshop.models.Company;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by mnunez on 8/10/17.
 */

public class CompaniesAdapter extends ArrayAdapter<Company> {

    private List<Company> mCompanies;
    private Context mContext;

    public CompaniesAdapter(Context context, int resource, List<Company> companies) {
        super(context, resource);
        this.mCompanies = companies;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.company_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.vName.setText(mCompanies.get(position).getName());
        viewHolder.vAddress.setText(mCompanies.get(position).getAddress());
        viewHolder.vPhone.setText(mCompanies.get(position).getPhone());
        Picasso.with(mContext).load(mCompanies.get(position)
                .getLogoUrl()).placeholder(mContext.getResources().getDrawable(R.mipmap.ic_launcher))
                .into(viewHolder.vLogo);
        return convertView;
    }

    @Override
    public int getCount() {
        return mCompanies.size();
    }

    @Override
    public Company getItem(int position) {
        return mCompanies.get(position);
    }

    private static class ViewHolder {
        TextView vName, vAddress, vPhone;
        ImageView vLogo;

        ViewHolder(View view) {
            super();
            vName = (TextView) view.findViewById(R.id.company_name);
            vAddress = (TextView) view.findViewById(R.id.company_address);
            vPhone = (TextView) view.findViewById(R.id.company_phone);
            vLogo = (ImageView) view.findViewById(R.id.company_logo);
        }

    }
}
