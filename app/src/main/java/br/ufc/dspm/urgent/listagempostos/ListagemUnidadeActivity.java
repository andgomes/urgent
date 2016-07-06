package br.ufc.dspm.urgent.listagempostos;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import br.ufc.dspm.interfaces.FragmentListener;
import br.ufc.dspm.urgent.R;
import br.ufc.dspm.urgent.unidades.UnidadeSaude;

public class ListagemUnidadeActivity extends AppCompatActivity implements FragmentListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    ListagemUnidadeFragment listagemFragment;
    private GoogleApiClient mGoogleApiClient;

    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_unidade);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Listagem de postos");


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void callListagemUnidadeFragment() {
        fm = getFragmentManager();

        listagemFragment = new ListagemUnidadeFragment();
        if (mLastLocation != null) {

            double[] latlngs = new double[2];
            Bundle bundle = new Bundle();


            latlngs[0] = mLastLocation.getLatitude();
            latlngs[1] = mLastLocation.getLongitude();


            bundle.putDoubleArray("Localizations", latlngs);
            listagemFragment.setArguments(bundle);
        }

        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.add(R.id.listagemunidadeactivity_fragment_framelayout, listagemFragment);
        fragmentTransaction.commit();
    }

    //implements FragmentListener implementa esse metodo, dentro do fragmento, pegamos esse contexto dentro do OnAttach()
    @Override
    public void fragmentListener(UnidadeSaude unidadeSaude) {

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            callListagemUnidadeFragment();

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
