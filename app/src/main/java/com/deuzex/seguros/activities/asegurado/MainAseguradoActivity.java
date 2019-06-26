package com.deuzex.seguros.activities.asegurado;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deuzex.seguros.R;
import com.deuzex.seguros.activities.promotor.ListadoPolizasActivity;
import com.deuzex.seguros.activities.promotor.NuevaPolizaActivity;
import com.deuzex.seguros.database.DatabaseManager;
import com.deuzex.seguros.objects.Usuario;

public class MainAseguradoActivity extends AppCompatActivity {

    Button btn_datos, btn_reportar_siniestro, btn_contacto,
            btn_documentos, btn_nueva_poliza, btn_cotizar;
    TextView tv_datos_asegurado;
    String dni_asegurado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_asegurado);

        // USUARIO LOGUEADO
        Bundle bundle = getIntent().getExtras();
        DatabaseManager dbm = new DatabaseManager(getApplicationContext());

        dni_asegurado = bundle.getString("dni");
        Usuario usuario = dbm.getAllDataUser(dni_asegurado);

        // CARTEL BIENVENIDA
        if (usuario != null) {
            tv_datos_asegurado = (TextView) findViewById(R.id.tv_datos_asegurado);
            String bienvenida = "Bienvenido " + usuario.getNombre();
            tv_datos_asegurado.setText(bienvenida);
        }

        btn_datos = (Button) findViewById(R.id.btn_asegurado_datos);
        btn_datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Context context = getApplicationContext();

                DatabaseManager dbm = new DatabaseManager(context);

                bundle.putString("dni", dni_asegurado);

                Intent intent = new Intent(MainAseguradoActivity.this,
                        DatosAseguradoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_cotizar = (Button) findViewById(R.id.btn_asegurado_cotizar);
        btn_cotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAseguradoActivity.this, CotizadorActivity.class);
                startActivity(intent);
            }
        });

        btn_nueva_poliza = (Button) findViewById(R.id.btn_asegurado_nueva_poliza);
        btn_nueva_poliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAseguradoActivity.this,
                        NuevaPolizaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("dni", dni_asegurado);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_documentos = (Button) findViewById(R.id.btn_asegurado_documentos);
        btn_documentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAseguradoActivity.this, DocumentacionActivity.class);
                startActivity(intent);
            }
        });

        btn_reportar_siniestro = (Button) findViewById(R.id.btn_asegurado_reportar_siniestro);
        btn_reportar_siniestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainAseguradoActivity.this,
                        ReportarSiniestroActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("dni", dni_asegurado);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_contacto = (Button) findViewById(R.id.btn_asegurado_contacto);
        btn_contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAseguradoActivity.this, ContactoActivity.class);
                startActivity(intent);
            }
        });
    }
}
