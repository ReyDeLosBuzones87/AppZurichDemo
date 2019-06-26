package com.deuzex.seguros.activities.promotor;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.deuzex.seguros.R;
import com.deuzex.seguros.database.DatabaseManager;
import com.deuzex.seguros.objects.UserDetailsAdapter;
import com.deuzex.seguros.objects.Usuario;

import java.util.Vector;

////////////////////  LISTVIEW DE TODOS LOS USUARIOS ///////////////////////////////////
public class ListadoUsuariosActivity extends ListActivity {

    Vector<Usuario> usuarioList;
    ImageButton btnBack;
    UserDetailsAdapter userAdapter;
    Context context;
    DatabaseManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_listado);

        // GET ALL USERS
        final ListView listUsuarios = (ListView) findViewById(android.R.id.list);
        context = this;
        dbm = new DatabaseManager(context);
        usuarioList = dbm.listAllUsuarios();
        userAdapter = new UserDetailsAdapter(context, R.layout.row_usuario_list, usuarioList);
        listUsuarios.setAdapter(userAdapter);
        listUsuarios.setSelection(userAdapter.getCount() - 1);

        // ON CLICK DE ITEM
        listUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListadoUsuariosActivity.this,
                        DatosUsuarioActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("dni", usuarioList.get(position).getDni());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
