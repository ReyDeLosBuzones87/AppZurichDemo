package com.deuzex.seguros.activities.asegurado;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.deuzex.seguros.R;

public class SiniestroFotoActivity extends AppCompatActivity {

    ImageView img_fullsize;
    Bitmap bitmapFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siniestro_foto);

        bitmapFoto = getIntent().getExtras().getParcelable("bitmapFoto");
        img_fullsize = (ImageView) findViewById(R.id.img_fullsize);
        img_fullsize.setImageBitmap(bitmapFoto);
        img_fullsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
