package com.deuzex.seguros.activities.asegurado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.deuzex.seguros.R;

public class NarracionSiniestro extends AppCompatActivity {

    private TextView tv_caracteres;
    private EditText et_narracion;
    Button btn_grabar_narracion;
    public String narracion;

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Current lenght
            int restantes = 1000 - s.length();
            tv_caracteres.setText(String.valueOf(restantes));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_narracion_siniestro);

        tv_caracteres = (TextView) findViewById(R.id.txt_narracion_caracteres);
        et_narracion = (EditText) findViewById(R.id.et_narracion);
        et_narracion.addTextChangedListener(watcher);

        btn_grabar_narracion = (Button) findViewById(R.id.btn_narracion);
        btn_grabar_narracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                narracion = et_narracion.getText().toString();
                Toast.makeText(getApplicationContext(),
                        "Datos guardados",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
