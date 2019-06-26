package com.deuzex.seguros.activities.asegurado;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.deuzex.seguros.R;

public class DocumentoFotoActivity extends AppCompatActivity {

    ImageView img_fullsize;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documento_foto);

        bitmap = getIntent().getExtras().getParcelable("bitmap");
        img_fullsize = (ImageView) findViewById(R.id.doc_fullsize);
        img_fullsize.setImageBitmap(bitmap);
        img_fullsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
