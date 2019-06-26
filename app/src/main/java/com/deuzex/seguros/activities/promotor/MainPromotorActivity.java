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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.deuzex.seguros.R;
import com.deuzex.seguros.activities.asegurado.MainAseguradoActivity;
import com.deuzex.seguros.database.DatabaseManager;

public class MainPromotorActivity extends AppCompatActivity {

    Button btnNewUser, btnNewSecure, btnDataUser, btnDataSecure, btnSearch,
            btnDelUser, btnDelSecure, btn_menu_asegurado;
    Spinner sp_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///////////////// LISTENER QUE BUSCA USER POR DNI Y POLIZA POR NUMERO //////////////////////
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = MainPromotorActivity.this;

                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View getUserDni = inflater.inflate(R.layout.get_usuario_dni, null);
                final View getPolicyNum = inflater.inflate(R.layout.get_poliza_num, null);

                sp_search = (Spinner) findViewById(R.id.sp_search);
                if (sp_search.getSelectedItemPosition() == 0) {
                    new AlertDialog.Builder(context).setView(getUserDni)
                            .setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final EditText etDni = (EditText)
                                            getUserDni.findViewById(R.id.etGetDniUser);
                                    String dni = etDni.getText().toString();
                                    if (!dni.matches("")) {
                                        DatabaseManager dbm = new DatabaseManager(getApplicationContext());
                                        if (dbm.searchUserByDni(dni)) {
                                            Intent intent = new Intent(MainPromotorActivity.this, DatosUsuarioActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("dni", dni);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(getApplicationContext(),
                                                    "No se encuentra ese DNI en la DB",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Complete el campo",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();
                } else if (sp_search.getSelectedItemPosition() == 1) {
                    new AlertDialog.Builder(context).setView(getPolicyNum)
                            .setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final EditText etNroPoliza = (EditText)
                                            getPolicyNum.findViewById(R.id.etGetNumPoliza);
                                    String nro_poliza = etNroPoliza.getText().toString();
                                    if (!nro_poliza.matches("")) {
                                        int nro_poliza1 = Integer.parseInt(etNroPoliza.getText().toString());
                                        DatabaseManager dbm = new DatabaseManager(getApplicationContext());
                                        if (dbm.searchPolizaByNum(nro_poliza1)) {
                                            Intent intent = new Intent(MainPromotorActivity.this, DatosPolizaActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("numero", nro_poliza);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(getApplicationContext(),
                                                    "No existe el número de póliza",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Complete el campo",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Seleccione una opción",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        /////////////////////////////*** CREATE ***//////////////////////////////////
        btnNewUser = (Button) findViewById(R.id.btnNewUser);
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainPromotorActivity.this, NuevoUsuarioActivity.class);
                startActivity(intent);
            }
        });
        btnNewSecure = (Button) findViewById(R.id.btnNewSecure);
        btnNewSecure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPromotorActivity.this, NuevaPolizaActivity.class);
                startActivity(intent);
            }
        });


        btnDelSecure = (Button) findViewById(R.id.btnDeleteSecure);
        /////////////////////////////*** LIST ***///////////////////////////////////
        btnDataUser = (Button) findViewById(R.id.btnUserData);
        btnDataUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPromotorActivity.this, ListadoUsuariosActivity.class);
                startActivity(intent);
            }
        });

        btnDataSecure = (Button) findViewById(R.id.btnSecureData);
        btnDataSecure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPromotorActivity.this, ListadoPolizasActivity.class);
                startActivity(intent);
            }
        });

        btnDelUser = (Button) findViewById(R.id.btnDeleteUser);
        btnDelUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DELETE USUARIO
                Context context = MainPromotorActivity.this;

                final LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View getUserDni = inflater.inflate(R.layout.get_usuario_dni, null);

                new AlertDialog.Builder(context).setView(getUserDni)
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final EditText etDni = (EditText)
                                        getUserDni.findViewById(R.id.etGetDniUser);
                                final String dni = etDni.getText().toString();
                                if (!dni.matches("")) {
                                    DatabaseManager dbm = new DatabaseManager(getApplicationContext());
                                    if (dbm.searchUserByDni(dni)) {
                                        // Codear para buscar código
                                        final View validateUserDelete = inflater.inflate(R.layout.valida_elimina_usuario, null);
                                        new AlertDialog.Builder(getUserDni.getContext())
                                                .setView(validateUserDelete)
                                                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        DatabaseManager dbm = new DatabaseManager(getApplicationContext());
                                                        if (dbm.deleteUser(dni)) {
                                                            Toast.makeText(getApplicationContext(),
                                                                    "Eliminación correcta",
                                                                    Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            Toast.makeText(getApplicationContext(),
                                                                    "Error: No se pudo eliminar el usuario",
                                                                    Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        }).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "No se encuentra ese DNI",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Complete el campo DNI",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Volver",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();
            }
        });

        btnDelSecure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DELETE SECURE
                final Context context = MainPromotorActivity.this;
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View getPolizaNum = inflater.inflate(R.layout.get_poliza_num, null);

                new AlertDialog.Builder(context).setView(getPolizaNum)
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final EditText etNumPoliza = (EditText)
                                        getPolizaNum.findViewById(R.id.etGetNumPoliza);
                                String numPoliza = etNumPoliza.getText().toString();
                                if (!numPoliza.matches("")) {
                                    int nro_poliza1 = Integer.parseInt(etNumPoliza.getText().toString());
                                    DatabaseManager dbm = new DatabaseManager(getApplicationContext());
                                    if (dbm.searchPolizaByNum(nro_poliza1)) {
                                        dbm.deletePoliza(String.valueOf(numPoliza));
                                        Toast.makeText(getApplicationContext(),
                                                "Eliminación de póliza correcta",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "No se encontró la póliza ingresada",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Complete número de póliza",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("Volver",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });

        btn_menu_asegurado = (Button) findViewById(R.id.btn_menu_asegurado);
        btn_menu_asegurado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPromotorActivity.this, MainAseguradoActivity.class);
                startActivity(intent);
            }
        });
    }
}
