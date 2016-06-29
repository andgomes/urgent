package br.ufc.dspm.urgent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Animation fade_in;
    private Animation fade_out;

    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campanhas();

    }

    public void campanhas(){
        viewFlipper = (ViewFlipper) findViewById(R.id.vfCampanhas);

        fade_in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        viewFlipper.setInAnimation(fade_in);
        viewFlipper.setOutAnimation(fade_out);

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.startFlipping();

    }

    public void visualizarUpas(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            mapsUpa();
        }

    }

    private void mapsUpa() {

        List<UPA> upas = Localizador.localizacaoUpas();

        double[] latlngs = new double[upas.size() * 2];
        Bundle bundle = new Bundle();

        for (int i = 0, j = 0; i < latlngs.length; i += 2, j++) {

            latlngs[i] = upas.get(j).getLatitude();
            latlngs[i + 1] = upas.get(j).getLongitude();

        }

        bundle.putDoubleArray("Localizations", latlngs);

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);

    }

    public void chamarSamu(View view){

        String telefone = "192";
        Uri uri = Uri.parse("tel:" + telefone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);

        startActivity(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                          int[] grantResults) {

        switch (requestCode) {

            case 1:

                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mapsUpa();
                }

        }

    }

}