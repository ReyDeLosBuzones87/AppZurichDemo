package com.deuzex.seguros.activities.promotor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.deuzex.seguros.R;
import com.deuzex.seguros.database.DatabaseManager;
import com.deuzex.seguros.objects.PolicyDetailsAdapter;
import com.deuzex.seguros.objects.Poliza;

import java.util.Vector;

public class ListadoPolizasActivity extends AppCompatActivity {

    Context context;
    Vector<Poliza> polizaList;
    PolicyDetailsAdapter polizasAdapter;
    TextView tvNombreUsuario, tvApellidoUsuario;
    LinearLayout layout_nombres_listado_polizas;
    Bundle bundle;
    ListView listPolizas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poliza_listado);

        // GET POLIZAS
        listPolizas = (ListView) findViewById(android.R.id.list);
        context = this;
        bundle = getIntent().getExtras();
        layout_nombres_listado_polizas = (LinearLayout) findViewById
                (R.id.layout_nombres_listado_polizas);

        if (bundle != null) {
            setPolizaList();
        } else {
            DatabaseManager dbm = new DatabaseManager(context);
            polizaList = dbm.listAllPolizas();
            polizasAdapter = new PolicyDetailsAdapter(context,
                    R.layout.row_poliza_list, polizaList);
            listPolizas.setAdapter(polizasAdapter);
            listPolizas.setSelection(polizasAdapter.getCount() - 1);
        }

        // ON CLICK ITEM
        listPolizas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListadoPolizasActivity.this,
                        DatosPolizaActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("numero", String.valueOf(polizaList.get(position).getNumPoliza()));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        polizaList.clear();
        setPolizaList();
        polizasAdapter.notifyDataSetChanged();
    }

    public void setPolizaList() {
        if (bundle != null) {
            layout_nombres_listado_polizas.setVisibility(View.VISIBLE);
            String dni_user = bundle.getString("dni");
            String nombre, apellido;
            tvNombreUsuario = (TextView) findViewById(R.id.tv_nombre_usuario);
            tvApellidoUsuario = (TextView) findViewById(R.id.tv_apellido_usuario);

            nombre = bundle.getString("nombre");
            apellido = bundle.getString("apellido");
            tvNombreUsuario.setVisibility(View.VISIBLE);
            tvApellidoUsuario.setVisibility(View.VISIBLE);
            tvNombreUsuario.setText(nombre);
            tvApellidoUsuario.setText(apellido);

            DatabaseManager dbm = new DatabaseManager(context);
            polizaList = dbm.getPolizaByDni(dni_user);
            polizasAdapter = new PolicyDetailsAdapter(
                    context, R.layout.row_poliza_list, polizaList);
            listPolizas.setAdapter(polizasAdapter);
            listPolizas.setSelection(polizasAdapter.getCount() - 1);
        } else {
            DatabaseManager dbm = new DatabaseManager(context);
            polizaList = dbm.listAllPolizas();
            polizasAdapter = new PolicyDetailsAdapter(context,
                    R.layout.row_poliza_list, polizaList);
            listPolizas.setAdapter(polizasAdapter);
            listPolizas.setSelection(polizasAdapter.getCount() - 1);
        }

    }
}