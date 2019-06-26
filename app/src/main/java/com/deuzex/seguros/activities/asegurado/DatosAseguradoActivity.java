package com.deuzex.seguros.activities.asegurado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.deuzex.seguros.R;
import com.deuzex.seguros.activities.promotor.DatosPolizaActivity;
import com.deuzex.seguros.activities.promotor.ListadoPolizasActivity;
import com.deuzex.seguros.database.DatabaseManager;
import com.deuzex.seguros.objects.PolicyDetailsAdapter;
import com.deuzex.seguros.objects.Poliza;
import com.deuzex.seguros.objects.Usuario;

import java.util.Vector;

public class DatosAseguradoActivity extends AppCompatActivity {

    TextView dni, nombre, apellido, email, direccion, num_direccion,
            depto, telefono, codpostal, localidad, provincia;
    Vector<Poliza> polizaList;
    PolicyDetailsAdapter polizasAdapter;
    String dni_user;
    Bundle bundle;
    ListView listPolizas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_asegurado);

        listPolizas = (ListView) findViewById(android.R.id.list);
        dni = (TextView) findViewById(R.id.tv_dni_asegurado);
        nombre = (TextView) findViewById(R.id.tv_nombre_asegurado);
        apellido = (TextView) findViewById(R.id.tv_apellido_asegurado);
        email = (TextView) findViewById(R.id.tv_email_asegurado);
        direccion = (TextView) findViewById(R.id.tv_direccion_asegurado);
        num_direccion = (TextView) findViewById(R.id.tv_nrodireccion_asegurado);
        depto = (TextView) findViewById(R.id.tv_depto_asegurado);
        telefono = (TextView) findViewById(R.id.tv_telefono_asegurado);
        codpostal = (TextView) findViewById(R.id.tv_codpostal_asegurado);
        localidad = (TextView) findViewById(R.id.tv_localidad_asegurado);
        provincia = (TextView) findViewById(R.id.tv_provincia_asegurado);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            setPolizaList();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Hubo un error al traer los datos",
                    Toast.LENGTH_SHORT).show();
            finish();
        }

        // ON CLICK ITEM
        listPolizas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent
                        (DatosAseguradoActivity.this, DatosPolizaActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("numero", String.valueOf(polizaList.get(position).getNumPoliza()));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    // POPULA EL LISTVIEW
    public void setPolizaList() {
        dni_user = bundle.getString("dni");
        DatabaseManager dbm = new DatabaseManager(getApplicationContext());
        Usuario usuario = dbm.getAllDataUser(dni_user);
        polizaList = dbm.getPolizaByDni(dni_user);
        polizasAdapter = new PolicyDetailsAdapter
                (this, R.layout.row_poliza_list, polizaList);
        listPolizas.setAdapter(polizasAdapter);
        listPolizas.setSelection(polizasAdapter.getCount() - 1);

        if (usuario != null) {
            dni.setText(usuario.getDni());
            nombre.setText(usuario.getNombre());
            apellido.setText(usuario.getApellido());
            email.setText(usuario.getEmail());
            direccion.setText(usuario.getDireccion());
            num_direccion.setText(usuario.getNumDireccion());
            depto.setText(usuario.getDepto());
            telefono.setText(usuario.getTelefono());
            codpostal.setText(usuario.getCodPostal());
            localidad.setText(usuario.getLocalidad());
            provincia.setText(usuario.getProvincia());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        polizaList.clear();
        setPolizaList();
        polizasAdapter.notifyDataSetChanged();
    }
}
