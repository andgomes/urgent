package br.ufc.dspm.urgent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import br.ufc.dspm.urgent.corporativo.DuvidasActivity;
import br.ufc.dspm.urgent.corporativo.SobreActivity;
import br.ufc.dspm.urgent.listagempostos.ListagemUnidadeActivity;
import br.ufc.dspm.urgent.unidades.Hospital;
import br.ufc.dspm.urgent.unidades.PostoDeSaude;
import br.ufc.dspm.urgent.unidades.UPA;
import br.ufc.dspm.urgent.unidades.UnidadeSaudeDAO;
import br.ufc.dspm.urgent.unidades.Util;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Animation fade_in;
    private Animation fade_out;

    private ViewFlipper viewFlipper;

    private UnidadeSaudeDAO dataBase;

    private ArrayList<PostoDeSaude> postos;
    private ArrayList<Hospital> hospitais;
    private List<UPA> upas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dataBase = new UnidadeSaudeDAO(this);
        postos = dataBase.listPostosDeSaude();

        if (postos.isEmpty()) {

            ArrayList<PostoDeSaude> enderecoPostosList = Util.getEnderecoPostosList(this);
            postos = Util.setCoordinatesByAddress(enderecoPostosList, this);

            for (int i=0; i<postos.size(); i++){
                dataBase.adicionarUnidadeSaude(postos.get(i));
            }

        }

        if (postos.isEmpty()) {
            Toast.makeText(this, "Não foi possivel carregar os postos de saúde", Toast.LENGTH_LONG).show();
        }

        hospitais = dataBase.listHospitais();

        if (hospitais.isEmpty()) {

            hospitais = Util.getHospitalList(this);

            for (int i = 0; i < hospitais.size(); i++) {
                dataBase.adicionarUnidadeSaude(hospitais.get(i));
            }

        }

        if (hospitais.isEmpty()) {
            Toast.makeText(this, "Não foi possivel carregar os hospitais", Toast.LENGTH_LONG).show();
        }

        upas = dataBase.listUpas();

        if (upas.isEmpty()) {

            upas = Util.getUpasList(this);

            for (int i = 0; i < upas.size(); i++) {
                dataBase.adicionarUnidadeSaude(upas.get(i));
            }

        }

        if (upas.isEmpty()) {
            Toast.makeText(this, "Não foi possivel carregar as UPAs", Toast.LENGTH_LONG).show();
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

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            mapsHospital();
        }

    }

    public void visualizarPostosClick(View view){

        visualizarPostos();

    }

    public void visualizarPostos() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 2);

        } else {
            mapsPostoDeSaude();
        }

    }

    public void visualizarUpasClick(View view) {

        visualizarUpas();

    }

    public void visualizarUpas() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 3);

        } else {
            mapsUpa();
        }

    }

    private void mapsHospital() {

        List<Hospital> hospitais = Util.getHospitalList(this);

        double[] latlngs = new double[hospitais.size() * 2];
        Bundle bundle = new Bundle();

        for (int i = 0, j = 0; i < latlngs.length; i += 2, j++) {

            latlngs[i] = hospitais.get(j).getLatitude();
            latlngs[i + 1] = hospitais.get(j).getLongitude();

        }

        bundle.putDoubleArray("Localizations", latlngs);
        bundle.putString("TipoUnidade", "Hospitais");

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
        bundle.putString("TipoUnidade", "Postos de Saúde");

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);


    }

    private void mapsUpa() {

        List<UPA> upas = Util.getUpasList(this);

        double[] latlngs = new double[upas.size() * 2];
        Bundle bundle = new Bundle();

        for (int i = 0, j = 0; i < latlngs.length; i += 2, j++) {

            latlngs[i] = upas.get(j).getLatitude();
            latlngs[i + 1] = upas.get(j).getLongitude();

        }

        bundle.putDoubleArray("Localizations", latlngs);
        bundle.putString("TipoUnidade", "UPAs");

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
                    mapsHospital();
                }

                break;

            case 2:

                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mapsPostoDeSaude();
                }

                break;

            case 3:

                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mapsUpa();
                }

                break;

        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Hopital) {

            visualizarHospitais();

        } else if (id == R.id.nav_PostoDeSaude) {

            visualizarPostos();

        } else if (id == R.id.nav_Upas) {

            visualizarUpas();

        } else if (id == R.id.nav_ListaDePostos) {

            Intent intent = new Intent(this, ListagemUnidadeActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Samu) {

            String telefone = "192";
            Uri uri = Uri.parse("tel:" + telefone);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);

        } else if (id == R.id.nav_Duvidas) {

            Intent intent = new Intent(this, DuvidasActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Sobre) {

            Intent intent = new Intent(this, SobreActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
