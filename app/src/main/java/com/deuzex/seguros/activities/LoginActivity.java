package com.deuzex.seguros.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deuzex.seguros.R;
import com.deuzex.seguros.activities.asegurado.MainAseguradoActivity;
import com.deuzex.seguros.activities.promotor.MainPromotorActivity;
import com.deuzex.seguros.database.DatabaseManager;


public class LoginActivity extends AppCompatActivity {

    Button BtnIngresar;
    EditText et_login_user, et_login_pass;
    static final String promotor = "33666999";
    static final String aseguradoPass = "softtek01";
    static final String promotorPass = "softtek01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        // keep status bar
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BtnIngresar = (Button) findViewById(R.id.BtnLogin);
        et_login_user = (EditText) findViewById(R.id.DNIUser);
        et_login_pass = (EditText) findViewById(R.id.PassUser);

        et_login_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_login_pass.setText("");
            }
        });

        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // promotor hardtyped
                if (et_login_user.getText().toString().equals(promotor)) {
                    if (et_login_pass.getText().toString().equals(promotorPass)) {
                        Intent intent = new Intent(LoginActivity.this, MainPromotorActivity.class);
                        startActivity(intent);
                        et_login_pass.setText("");
                    } else {
                        String msj = "Contraseña Incorrecta";
                        Toast toast = Toast.makeText
                                (getApplicationContext(), msj, Toast.LENGTH_SHORT);
                        toast.show();
                        et_login_pass.setText("");
                    }
                } else {
                    // ASEGURADO
                    String dni_asegurado = et_login_user.getText().toString();
                    DatabaseManager dbm = new DatabaseManager(getApplicationContext());
                    if (dbm.searchUserByDni(dni_asegurado)) {
                        if (et_login_pass.getText().toString().equals(aseguradoPass)) {
                            Intent intent = new Intent
                                    (LoginActivity.this, MainAseguradoActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("dni", dni_asegurado);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            et_login_pass.setText("");
                        } else {
                            String msj = "Clave incorrecta";
                            Toast.makeText(getApplicationContext(), msj, Toast.LENGTH_SHORT).show();
                            et_login_pass.setText("");
                        }
                    } else {
                        String msj = "Usuario no registrado";
                        Toast toast = Toast.makeText(getApplicationContext(), msj, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }
}
