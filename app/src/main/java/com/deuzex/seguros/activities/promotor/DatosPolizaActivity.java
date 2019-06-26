package com.deuzex.seguros.activities.promotor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.deuzex.seguros.R;
import com.deuzex.seguros.database.DatabaseManager;
import com.deuzex.seguros.objects.Poliza;

public class DatosPolizaActivity extends AppCompatActivity {

    TextView nro_poliza, dni_usuario, fecha_vig, dias_vig, tipo_poliza, tipo_pago;
    Button btnEliminaPoliza;
    public Boolean seElimino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poliza_datos);

        nro_poliza = (TextView) findViewById(R.id.viewPolizaNumber);
        dni_usuario = (TextView) findViewById(R.id.viewPolizaDniUser);
        fecha_vig = (TextView) findViewById(R.id.viewPolizaFechaVig);
        dias_vig = (TextView) findViewById(R.id.viewPolizaDiasVig);
        tipo_poliza = (TextView) findViewById(R.id.viewPolizaTipoPoliza);
        tipo_pago = (TextView) findViewById(R.id.viewPolizaTipoPago);
        seElimino = false;

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String numero_poliza = bundle.getString("numero");
            int numero = Integer.parseInt(numero_poliza);
            DatabaseManager dbm = new DatabaseManager(getApplicationContext());
            final Poliza poliza = dbm.getAllDataPoliza(numero);

            nro_poliza.setText(String.valueOf(poliza.getNumPoliza()));
            dni_usuario.setText(poliza.getDniUsuario());
            fecha_vig.setText(poliza.getFechaVig());
            dias_vig.setText(poliza.getVigencia());
            tipo_poliza.setText(poliza.getTipoPoliza());
            tipo_pago.setText(poliza.getTipoPago());

            final Context context = DatosPolizaActivity.this;
            final LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // ELIMINA POLIZA
            btnEliminaPoliza = (Button) findViewById(R.id.btn_eliminaPoliza);
            btnEliminaPoliza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String nro_poliza = String.valueOf(poliza.getNumPoliza());
                    final View validatePolicyDelete = inflater
                            .inflate(R.layout.valida_elimina_poliza, null);
                    new AlertDialog.Builder(context).setView(validatePolicyDelete)
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatabaseManager dbm = new DatabaseManager(getApplicationContext());
                                    if (dbm.deletePoliza(nro_poliza)) {
                                        Toast.makeText(getApplicationContext(), "Eliminación correcta",
                                                Toast.LENGTH_SHORT).show();
                                        seElimino = true;
                                        finish();

                                        /*
                                        Intent intent = new Intent(DatosPolizaActivity.this, MainPromotorActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);*/
                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "Error: No se pudo eliminar la póliza",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(),
                    "Hubo un error al traer los datos, intente más tarde.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
