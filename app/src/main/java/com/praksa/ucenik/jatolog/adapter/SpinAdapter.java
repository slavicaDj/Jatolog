package com.praksa.ucenik.jatolog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.model.VrstaRijeci;
import com.praksa.ucenik.jatolog.util.Util;

import java.util.ArrayList;

/**
 * Created by ucenik on 5/14/2018.
 */

public class SpinAdapter extends ArrayAdapter<VrstaRijeci> {

    private Context context;
    private ArrayList<VrstaRijeci> values;

    public SpinAdapter(Context context, int textViewResourceId, ArrayList<VrstaRijeci> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public VrstaRijeci getItem(int position){
        return values.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.vrsta_rijeci_spinner_item,parent,false);
        TextView textViewVrstaRijec = view.findViewById(R.id.textViewVrstaRijeci);

        if (Util.getCurrentLocale(context).getLanguage().equals("sr")) {
            textViewVrstaRijec.setText(values.get(position).getNazivCirilica());
        }
        else {
            textViewVrstaRijec.setText(values.get(position).getNazivLatinica());
        }

        // And finally return your dynamic (or custom) view for each spinner item
        return textViewVrstaRijec;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textViewVrstaRijec = (TextView) super.getDropDownView(position, convertView, parent);

        if (Util.getCurrentLocale(context).getLanguage().equals("sr")) {
            textViewVrstaRijec.setText(values.get(position).getNazivCirilica());
        }
        else {
            textViewVrstaRijec.setText(values.get(position).getNazivLatinica());
        }

        return textViewVrstaRijec;
    }

}
