package com.deuzex.seguros.activities.asegurado;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.deuzex.seguros.R;

public class DocumentacionActivity extends AppCompatActivity {

    Bitmap docBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentacion);
    }

    public void openFoto() {
        Intent intent = new Intent(DocumentacionActivity.this, DocumentoFotoActivity.class);

    }
}
