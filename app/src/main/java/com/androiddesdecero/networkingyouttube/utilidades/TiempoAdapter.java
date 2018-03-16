package com.androiddesdecero.networkingyouttube.utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androiddesdecero.networkingyouttube.R;

import java.util.List;

/**
 * Created by albertopalomarrobledo on 16/3/18.
 */

public class TiempoAdapter extends ArrayAdapter<Tiempo> {
    public TiempoAdapter(Context context, List<Tiempo> tiempos){
        super(context,0,tiempos);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent, false);
        }
        Tiempo tiempo = getItem(position);
        TextView itemTemperatura = (TextView) listItemView.findViewById(R.id.item_temperatura);
        TextView itemFecha = (TextView) listItemView.findViewById(R.id.item_fecha);
        String temperatura = tiempo.temperatura;
        String fecha =  tiempo.fecha;
        itemTemperatura.setText(temperatura);
        itemFecha.setText(fecha);
        return listItemView;
    }
}
