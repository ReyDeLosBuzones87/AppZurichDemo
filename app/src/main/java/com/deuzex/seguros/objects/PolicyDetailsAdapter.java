package com.deuzex.seguros.objects;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.deuzex.seguros.R;

import java.util.Vector;

public class PolicyDetailsAdapter extends ArrayAdapter<Poliza> {
    private Activity myContext;
    private Vector<Poliza> data;

    public PolicyDetailsAdapter(Context context, int resourceId, Vector<Poliza> polizaVector) {
        super(context, resourceId, polizaVector);
        myContext = (Activity) context;
        data = polizaVector;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = myContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.row_poliza_list, null);

        TextView nro_poliza = (TextView) rowView.findViewById(R.id.policyListNumero);
        nro_poliza.setText(String.valueOf(data.elementAt(position).getNumPoliza()));

        TextView dni = (TextView) rowView.findViewById(R.id.policyListDniUser);
        dni.setText(data.elementAt(position).getDniUsuario());

        return rowView;
    }
    public void updateData(Vector<Poliza> listData) {
        this.data = listData;
    }
}
