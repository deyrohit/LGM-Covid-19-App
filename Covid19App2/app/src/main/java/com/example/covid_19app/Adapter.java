package com.example.covid_19app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<Module> {
    private Context context;
    private List<Module> modelList;

    public Adapter(Context context, List<Module> modelList) {
        super(context, R.layout.testing, modelList);

        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testing, null, true);


        TextView state = view.findViewById(R.id.state);
        TextView active = view.findViewById(R.id.active);
        TextView cured = view.findViewById(R.id.cured);
        TextView death = view.findViewById(R.id.death);
        TextView total = view.findViewById(R.id.total);
        TextView incactive = view.findViewById(R.id.incactive);
        TextView inccured = view.findViewById(R.id.inccured);
        TextView incdeath = view.findViewById(R.id.incdeath);


        state.setText(modelList.get(position).getName());
        active.setText(modelList.get(position).getActive());
        cured.setText(modelList.get(position).getCured());
        death.setText(modelList.get(position).getDeath());
        total.setText(modelList.get(position).getTotal());
        incactive.setText(modelList.get(position).getIncAct());
        inccured.setText(modelList.get(position).getIncRec());
        incdeath.setText(modelList.get(position).getIncDec());

        return view;
    }
}