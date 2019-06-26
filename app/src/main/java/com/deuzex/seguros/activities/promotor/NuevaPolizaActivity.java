package com.deuzex.seguros.activities.promotor;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.deuzex.seguros.R;
import com.deuzex.seguros.database.DatabaseManager;
import com.deuzex.seguros.objects.Poliza;
import com.deuzex.seguros.objects.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NuevaPolizaActivity extends AppCompatActivity {

    Button btn_guardar, btn_limpiar;
    EditText et_dni_usuario, et_fecha_vig, et_duracion;
    Calendar calendario;
    TextView tv_apellido_usuario, tv_nombre_usuario;
    Spinner sp_tipo_poliza, sp_tipo_pago;
    DatabaseManager databaseManager;
    Poliza newPoliza;
    Context context;
    boolean existeUsuario;
    public String dni_asegurado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poliza_nueva);
        et_dni_usuario = (EditText) findViewById(R.id.etDniUsuario);
        et_fecha_vig = (EditText) findViewById(R.id.etVigencia);
        et_duracion = (EditText) findViewById(R.id.etDuracion);
        sp_tipo_poliza = (Spinner) findViewById(R.id.sp_poliza);
        sp_tipo_pago = (Spinner) findViewById(R.id.sp_pago);
        tv_apellido_usuario = (TextView) findViewById(R.id.txtApellidoUsuario);
        tv_nombre_usuario = (TextView) findViewById(R.id.txtNombreUsuario);
        context = getApplicationContext();
        databaseManager = new DatabaseManager(context);
        et_dni_usuario.requestFocus();
        existeUsuario = false;
        calendario = Calendar.getInstance();

        // TODO - CAMBIAR CANTIDAD DE DIAS POR FECHA FIN
        setBundle();
        getFecha();
        resetFecha();

        et_dni_usuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !existeUsuario) {
                    String dni_usuario = et_dni_usuario.getText().toString();
                    if (!dni_usuario.matches("")) {
                        DatabaseManager dbm = new DatabaseManager(context);
                        if (dbm.searchUserByDni(dni_usuario)) {
                            setDatosUsuario();
                            existeUsuario = true;
                        } else {
                            et_dni_usuario.setError("El usuario no existe en la base de datos");
                            existeUsuario = false;
                        }
                    } else {
                        et_dni_usuario.setError("Ingrese DNI del usuario");
                    }
                }
            }
        });


        // BOTON GUARDAR DATOS
        btn_guardar = (Button) findViewById(R.id.btnSaveSecure);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (existeUsuario) {
                    // Validar que no exista la póliza en la DB
                    newPoliza = new Poliza();
                    newPoliza.setDniUsuario(et_dni_usuario.getText().toString());
                    newPoliza.setVigencia(et_duracion.getText().toString());
                    newPoliza.setFechaVig(et_fecha_vig.getText().toString());
                    newPoliza.setTipoPago(sp_tipo_pago.getSelectedItem().toString());
                    newPoliza.setTipoPoliza(sp_tipo_poliza.getSelectedItem().toString());

                    databaseManager.addPoliza(newPoliza);
                    String msj = "Póliza para el usuario " +
                            et_dni_usuario.getText().toString() + " añadida correctamente";
                    Toast.makeText(context, msj, Toast.LENGTH_LONG).show();
                    existeUsuario = false;
                    clearAll();
                    finish();
                } else {
                    if (et_dni_usuario.getText().toString().matches("")) {
                        et_dni_usuario.setError("Ingrese DNI a buscar");
                    }
                }
            }
        });

        // BOTON LIMPIAR CAMPOS
        btn_limpiar = (Button) findViewById(R.id.btnClearSecure);
        btn_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
                String msj = "Datos blanqueados..";
                Toast toast = Toast.makeText(getApplicationContext(), msj, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        });
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

        et_fecha_vig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(NuevaPolizaActivity.this, date,
                        calendario.get(Calendar.YEAR),
                        calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateFecha() {
        String formato = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.US);
        et_fecha_vig.setText(sdf.format(calendario.getTime()));
    }

    private void resetFecha() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        et_fecha_vig.setText(dateFormat.format(new Date()));
    }

    public void clearAll() {
        et_dni_usuario.setText("");
        et_fecha_vig.setText("");
        et_duracion.setText("");
        tv_apellido_usuario.setText("");
        tv_nombre_usuario.setText("");
        et_dni_usuario.requestFocus();
    }

    // metodo para traer los datos del usuario y setearlos en los textview
    public void setDatosUsuario() {

        String dni_usuario = et_dni_usuario.getText().toString();
        DatabaseManager dbm = new DatabaseManager(context);
        Usuario dataUsuario = dbm.setDatosUsuario(dni_usuario);

        tv_nombre_usuario.setText(dataUsuario.getNombre());
        tv_apellido_usuario.setText(dataUsuario.getApellido());
    }

    public void setBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            dni_asegurado = bundle.getString("dni");
            et_dni_usuario.setText(dni_asegurado);
            DatabaseManager dbm = new DatabaseManager(context);
            if (dbm.searchUserByDni(dni_asegurado)) {
                setDatosUsuario();
                existeUsuario = true;
            } else {
                Toast.makeText(getApplicationContext(),
                        "Hubo un error al traer la petición",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
