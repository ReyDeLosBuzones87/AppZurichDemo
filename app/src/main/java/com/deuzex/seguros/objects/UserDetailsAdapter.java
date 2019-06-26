package com.deuzex.seguros.objects;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.deuzex.seguros.R;

import java.util.Vector;

public class UserDetailsAdapter extends ArrayAdapter<Usuario> {
    private Activity myContext;
    private Vector<Usuario> data;

    public UserDetailsAdapter(Context context, int resourceId, Vector<Usuario> usuarioVector) {
        super(context, resourceId, usuarioVector);
        myContext = (Activity) context;
        data = usuarioVector;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = myContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.row_usuario_list, null);

        TextView dni = (TextView) rowView.findViewById(R.id.userListDni);
        dni.setText(data.elementAt(position).getDni());

        TextView apellido = (TextView) rowView.findViewById(R.id.userListApellido);
        apellido.setText(data.elementAt(position).getApellido());

        TextView nombre = (TextView) rowView.findViewById(R.id.userListNombre);
        nombre.setText(data.elementAt(position).getNombre());

        return rowView;
    }

    public void updateData(Vector<Usuario> listData) {
        this.data = listData;
    }
}
