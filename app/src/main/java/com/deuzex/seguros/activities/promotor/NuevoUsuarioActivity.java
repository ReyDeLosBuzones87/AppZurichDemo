package com.deuzex.seguros.activities.promotor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.deuzex.seguros.database.handlers.DBHandlerUsuario;
import com.deuzex.seguros.database.DatabaseManager;
import com.deuzex.seguros.R;
import com.deuzex.seguros.objects.Usuario;


public class NuevoUsuarioActivity extends AppCompatActivity {

    Button btnSaveUser, btnClearUser;
    EditText dni, nombre, apellido, email, direccion, direccion_nro,
            depto, telefono, localidad, provincia, cod_postal;
    Context context;
    Usuario newUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_nuevo);
        context = this;

        dni = (EditText) findViewById(R.id.etDni);
        nombre = (EditText) findViewById(R.id.etNombre);
        apellido = (EditText) findViewById(R.id.etApellido);
        email = (EditText) findViewById(R.id.etMail);
        telefono = (EditText) findViewById(R.id.etTelefono);
        direccion = (EditText) findViewById(R.id.etDireccion);
        direccion_nro = (EditText) findViewById(R.id.etDireccionNro);
        depto = (EditText) findViewById(R.id.etDepto);
        localidad = (EditText) findViewById(R.id.etLocalidad);
        provincia = (EditText) findViewById(R.id.etProvincia);
        cod_postal = (EditText) findViewById(R.id.etCodPostal);

        dni.requestFocus();

        btnSaveUser = (Button) findViewById(R.id.btnSaveUser);
        btnClearUser = (Button) findViewById(R.id.btnClearUser);

        // TODO - QUE REFRESQUE EL CAMPO DNI CADA VEZ QUE PIERDE EL FOCO

        // BOTON QUE LIMPIA LOS EDITTEXTS
        btnClearUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
                String msj = "Datos blanqueados..";
                Toast toast = Toast.makeText(getApplicationContext(), msj, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        });

        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni_user = dni.getText().toString();
                newUsuario = new Usuario();
                DatabaseManager dbm = new DatabaseManager(context);

                if (!dni_user.matches("")) {
                    newUsuario.setDni(dni_user);
                    newUsuario.setNombre(nombre.getText().toString());
                    newUsuario.setApellido(apellido.getText().toString());
                    newUsuario.setEmail(email.getText().toString());
                    newUsuario.setTelefono(telefono.getText().toString());
                    newUsuario.setDireccion(direccion.getText().toString());
                    newUsuario.setNumDireccion(direccion_nro.getText().toString());
                    newUsuario.setDepto(depto.getText().toString());
                    newUsuario.setLocalidad(localidad.getText().toString());
                    newUsuario.setProvincia(provincia.getText().toString());
                    newUsuario.setCodPostal(cod_postal.getText().toString());

                    dbm.addUsuario(newUsuario);

                    if (DBHandlerUsuario.esNuevoUser) {
                        Toast.makeText(context, "Usuario " + newUsuario.getDni() +
                                " se añadió correctamente..", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Usuario " + newUsuario.getDni() +
                                " se modificó correctamente..", Toast.LENGTH_SHORT).show();
                    }
                    clearAll();
                    finish();
                } else {
                    dni.setError("Este campo no puede estar vacío");
                    dni.requestFocus();
                }
            }
        });
    }

    // BLANQUEA LOS EDITTEXT
    public void clearAll() {
        dni.setText("");
        nombre.setText("");
        apellido.setText("");
        email.setText("");
        telefono.setText("");
        direccion.setText("");
        direccion_nro.setText("");
        depto.setText("");
        localidad.setText("");
        provincia.setText("");
        cod_postal.setText("");

        dni.requestFocus();
    }
}
