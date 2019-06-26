package com.deuzex.seguros.activities.asegurado;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.deuzex.seguros.R;
import com.deuzex.seguros.database.DatabaseManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReportarSiniestroActivity extends AppCompatActivity {

    Button btn_naraccion, btn_saca_foto, btn_muestra_foto, btn_ubicacion;
    Calendar calendario;
    CheckBox chk_pais;
    Spinner sp_provincia, sp_pais, sp_nro_poliza;
    EditText et_fecha_siniestro, et_codigopostal, et_localidad;
    int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap fotoBitmap;
    public String dni_asegurado = "";

    // GPS
    LocationManager locationManager;
    Double latitud, longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_siniestro);

        et_fecha_siniestro = (EditText) findViewById(R.id.et_reportar_fecha);
        et_codigopostal = (EditText) findViewById(R.id.et_reportar_cp);
        et_localidad = (EditText) findViewById(R.id.et_reportar_localidad);
        sp_provincia = (Spinner) findViewById(R.id.sp_provincia);
        sp_pais = (Spinner) findViewById(R.id.sp_pais_extranjero);
        sp_pais.setEnabled(false);

        resetFecha();
        setSpinnerPolizas();
        setSpinnerPaises();
        getFecha();

        btn_naraccion = (Button) findViewById(R.id.btn_reporte_narracion);
        btn_naraccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportarSiniestroActivity.this,
                        NarracionSiniestro.class);
                startActivity(intent);
            }
        });

        btn_saca_foto = (Button) findViewById(R.id.btn_reporte_foto);
        btn_saca_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levantaCamara();
            }
        });

        btn_muestra_foto = (Button) findViewById(R.id.btn_reporte_mostrar);
        btn_muestra_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFoto();
            }
        });

        btn_ubicacion = (Button) findViewById(R.id.btn_reporte_gps);
        btn_ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO - Codear el GPS

            }
        });

        chk_pais = (CheckBox) findViewById(R.id.chk_pais);
        chk_pais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    sp_pais.setEnabled(true);
                    et_codigopostal.setEnabled(false);
                    et_localidad.setEnabled(false);
                    sp_provincia.setEnabled(false);
                } else {
                    sp_pais.setEnabled(false);
                    et_codigopostal.setEnabled(true);
                    et_localidad.setEnabled(true);
                    sp_provincia.setEnabled(true);
                }
            }
        });


    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // acá va la secuencia de código
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            fotoBitmap = (Bitmap) extras.get("data");
        }
    }

    private void getFecha() {
        calendario = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendario.set(Calendar.YEAR, year);
                calendario.set(Calendar.MONTH, month);
                calendario.set(Calendar.DAY_OF_MONTH, day);
                updateFecha();
            }
        };

        et_fecha_siniestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ReportarSiniestroActivity.this, date,
                        calendario.get(Calendar.YEAR),
                        calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateFecha() {
        String formato = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.US);
        et_fecha_siniestro.setText(sdf.format(calendario.getTime()));
    }

    private void resetFecha() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        et_fecha_siniestro.setText(dateFormat.format(new Date()));
    }

    private void setSpinnerPaises() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource
                (getApplicationContext(), R.array.paises_array, R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        sp_pais.setAdapter(arrayAdapter);
    }

    private void setSpinnerPolizas() {
        DatabaseManager dbm = new DatabaseManager(getApplicationContext());
        getBundle();
        List<String> polizas = dbm.getNumPolizas(dni_asegurado);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this, R.layout.simple_spinner_item, polizas);
        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        sp_nro_poliza = (Spinner) findViewById(R.id.sp_nro_poliza);
        sp_nro_poliza.setAdapter(dataAdapter);
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            dni_asegurado = bundle.getString("dni");
        }
    }

    private void levantaCamara() {
        Intent tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (tomarFotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(tomarFotoIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void openFoto() {
        Intent intent = new Intent(ReportarSiniestroActivity.this, SiniestroFotoActivity.class);
        if (fotoBitmap != null) {
            intent.putExtra("bitmapFoto", fotoBitmap);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),
                    "No hay foto para mostrar", Toast.LENGTH_SHORT).show();
        }
    }
}
