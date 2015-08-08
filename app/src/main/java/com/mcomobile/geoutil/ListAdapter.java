package com.mcomobile.geoutil;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    private LayoutInflater mInflater;
    private ArrayList<String> mData;

    public ListAdapter(Context context, ArrayList<String> mData) {
        this.context = context;
        this.mData = mData;
        mInflater = LayoutInflater.from(context);
    }

    public ListAdapter(Context context, ArrayList<String> mData, boolean isSearch) {
        this.context = context;
        this.mData = mData;
        mInflater = LayoutInflater.from(context);
    }

    public void updateResults(ArrayList<String> results) {
        this.mData = results;
        //Triggers the list update
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        // if (convertView == null) {
        convertView = mInflater.inflate(R.layout.list_item, null);
        holder = new ViewHolder();

        holder.area = (TextView) convertView.findViewById(R.id.area);
        holder.triangulo = (TextView) convertView.findViewById(R.id.idTriangulo);
        holder.iconType = (ImageView) convertView.findViewById(R.id.image);
        holder.celda = (LinearLayout)convertView.findViewById(R.id.celda);

        String area = mData.get(position).toString();

        holder.area.setText(area + " m2");
        holder.triangulo.setText("Triángulo " + (position + 1));

        String areaGood = area.replace(",",".");
        double valor = Double.parseDouble(areaGood);

        if (mData.size() > 1) {
            for (int i = 0; i < mData.size()-1; i++) {

                String celdaData = mData.get(i).toString();
                String celdaComma = celdaData.replace(",", ".");
                double valorCelda = Double.parseDouble(celdaComma);

                Log.e("","Valor: " + valor + ", valor celda: " + valorCelda);
                if (valor > valorCelda) {

                    holder.iconType.setImageResource(R.drawable.ic_add_alert_black_24dp);
                    holder.celda.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));

                } else {

                    holder.iconType.setImageResource(R.drawable.ic_signal_cellular_null_black_24dp);
                    holder.celda.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                }

            }
        }

        return convertView;
    }

    static class ViewHolder {
        TextView area;
        TextView triangulo;
        ImageView iconType;
        LinearLayout celda;
    }
}
