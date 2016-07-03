package br.ufc.dspm.urgent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Animation fade_in;
    private Animation fade_out;

    private Localizador localizador;

    ViewFlipper viewFlipper;

    UnidadeSaudeDAO dataBase;

    ArrayList<UnidadeSaude> postos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localizador = new Localizador(this);

        dataBase = new UnidadeSaudeDAO(this);
        postos = dataBase.listPostosDeSaude();
        if(postos.isEmpty()){
            ArrayList<UnidadeSaude> enderecoList = Util.getEnderecoList(this);
            postos = Util.setCoordinatesByAddress(enderecoList, this);
            for (int i=0; i<postos.size(); i++){
                dataBase.adicionarUnidadeSaude(postos.get(i));
            }

        }
        if(postos.isEmpty()){
            Toast.makeText(this, "Não foi possivel carregar os postos de saúde", Toast.LENGTH_LONG).show();
        }

        campanhas();

    }

    public void campanhas() {

        viewFlipper = (ViewFlipper) findViewById(R.id.vfCampanhas);

        fade_in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        viewFlipper.setInAnimation(fade_in);
        viewFlipper.setOutAnimation(fade_out);

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.startFlipping();

    }

    public void listagem(View view){

        Intent intent = new Intent(this, ListagemUnidadeActivity.class);
        startActivity(intent);
    }

    public void visualizarHospitaisClick(View view){

        visualizarHospitais();

    }

    public void visualizarHospitais() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            mapsHospital();
        }

    }

    public void visualizarPostosClick(View view){

        visualizarPostos();

    }

    public void visualizarPostos() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            mapsPostoDeSaude();
        }

    }

    public void visualizarUpasClick(View view) {

        visualizarUpas();

    }

    public void visualizarUpas() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            mapsUpa();
        }

    }

    private void mapsHospital() {

        //List<UPA> hospitais = localizador.localizacaoUpas();

        List<Hospital> hospitais = Util.getHospitalList(this);

        double[] latlngs = new double[hospitais.size() * 2];
        Bundle bundle = new Bundle();

        for (int i = 0, j = 0; i < latlngs.length; i += 2, j++) {

            latlngs[i] = hospitais.get(j).getLatitude();
            latlngs[i + 1] = hospitais.get(j).getLongitude();

        }

        bundle.putDoubleArray("Localizations", latlngs);

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);

    }

    private void mapsPostoDeSaude(){

        double[] latlngs = new double[postos.size() * 2];
        Bundle bundle = new Bundle();

        for (int i = 0, j = 0; i < latlngs.length; i += 2, j++) {

            latlngs[i] = postos.get(j).getLatitude();
            latlngs[i + 1] = postos.get(j).getLongitude();

        }

        bundle.putDoubleArray("Localizations", latlngs);

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);


    }

    private void mapsUpa() {

        //List<UPA> upas = localizador.localizacaoUpas();

        List<UPA> upas = Util.getUpasList(this);

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