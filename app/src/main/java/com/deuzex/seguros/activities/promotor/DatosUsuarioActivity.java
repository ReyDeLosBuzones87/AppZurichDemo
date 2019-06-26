package com.deuzex.seguros.activities.promotor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.deuzex.seguros.R;
import com.deuzex.seguros.database.DatabaseManager;
import com.deuzex.seguros.objects.Usuario;

public class DatosUsuarioActivity extends AppCompatActivity {

    TextView dni, nombre, apellido, email, telefono, direccion, direccion_nro,
            depto, localidad, provincia, cod_postal;
    Button btnNuevaPoliza, btnVerPolizas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_datos);

        dni = (TextView) findViewById(R.id.viewUserDni);
        nombre = (TextView) findViewById(R.id.viewUserNombre);
        apellido = (TextView) findViewById(R.id.viewUserApellido);
        email = (TextView) findViewById(R.id.viewUserEmail);
        telefono = (TextView) findViewById(R.id.viewUserTelefono);
        direccion = (TextView) findViewById(R.id.viewUserDireccion);
        direccion_nro = (TextView) findViewById(R.id.viewUserDireccionNro);
        depto = (TextView) findViewById(R.id.viewUserDepto);
        localidad = (TextView) findViewById(R.id.viewUserLocalidad);
        provincia = (TextView) findViewById(R.id.viewUserProvincia);
        cod_postal = (TextView) findViewById(R.id.viewUserCodPostal);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String dni_user = bundle.getString("dni");

            DatabaseManager dbm = new DatabaseManager(getApplicationContext());
            Usuario usuario = dbm.getAllDataUser(dni_user);

            if (usuario != null) {
                dni.setText(usuario.getDni());
                nombre.setText(usuario.getNombre());
                apellido.setText(usuario.getApellido());
                email.setText(usuario.getEmail());
                telefono.setText(usuario.getTelefono());
                direccion.setText(usuario.getDireccion());
                direccion_nro.setText(usuario.getNumDireccion());
                depto.setText(usuario.getDepto());
                localidad.setText(usuario.getLocalidad());
                provincia.setText(usuario.getProvincia());
                cod_postal.setText(usuario.getCodPostal());
            }
        }

        // LLAMA AL NUEVA POLIZA CON LOS DATOS DEL CLIENTE
        btnNuevaPoliza = (Button) findViewById(R.id.btn_asegurado_nueva_poliza);
        btnNuevaPoliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosUsuarioActivity.this, NuevaPolizaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("dni", dni.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        // MUESTRA LAS POLIZAS DEL USUARIO
        btnVerPolizas = (Button) findViewById(R.id.btn_verpolizas);
        btnVerPolizas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosUsuarioActivity.this, ListadoPolizasActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("dni", dni.getText().toString());
                bundle.putString("nombre", nombre.getText().toString());
                bundle.putString("apellido", apellido.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}
