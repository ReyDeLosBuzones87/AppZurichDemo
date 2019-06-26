package com.deuzex.seguros.activities.asegurado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.deuzex.seguros.R;

public class CotizadorActivity extends AppCompatActivity {

    Spinner sp_cotizacion_tipo;
    LinearLayout cotizador_datos, cotizador_auto, cotizador_hogar, cotizador_vida;
    Button btn_cotizador_siguiente, btn_cotizador_limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizador);

        sp_cotizacion_tipo = (Spinner) findViewById(R.id.sp_cotizacion_tipo);
        cotizador_datos = (LinearLayout) findViewById(R.id.layout_cotizador_datos);
        cotizador_auto = (LinearLayout) findViewById(R.id.layout_cotizador_auto);
        cotizador_hogar = (LinearLayout) findViewById(R.id.layout_cotizador_hogar);
        cotizador_vida = (LinearLayout) findViewById(R.id.layout_cotizador_vida);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.poliza_array, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        sp_cotizacion_tipo.setAdapter(adapter);
        sp_cotizacion_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // AUTOMOTOR
                        cotizador_auto.setVisibility(View.VISIBLE);
                        cotizador_hogar.setVisibility(View.GONE);
                        cotizador_vida.setVisibility(View.GONE);
                        break;

                    case 1: // VIDA
                        cotizador_vida.setVisibility(View.VISIBLE);
                        cotizador_auto.setVisibility(View.GONE);
                        cotizador_hogar.setVisibility(View.GONE);
                        break;

                    case 2: // PROPIEDAD
                        cotizador_hogar.setVisibility(View.VISIBLE);
                        cotizador_auto.setVisibility(View.GONE);
                        cotizador_vida.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btn_cotizador_limpiar = (Button) findViewById(R.id.btn_cotizador_limpiar);
        btn_cotizador_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Función en implementación",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btn_cotizador_siguiente = (Button) findViewById(R.id.btn_cotizador_siguiente);
        btn_cotizador_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Función en implementación",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
