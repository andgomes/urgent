package br.ufc.dspm.urgent;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.ufc.dspm.interfaces.FragmentListener;

public class ListagemUnidadeActivity extends AppCompatActivity implements FragmentListener{

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    ListagemUnidadeFragment listagemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_unidade);
        callListagemUnidadeFragment();
    }

    void callListagemUnidadeFragment(){
        fm = getFragmentManager();

        listagemFragment = new ListagemUnidadeFragment();

        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.add(R.id.listagemunidadeactivity_fragment_framelayout, listagemFragment);
        fragmentTransaction.commit();
    }

    //implements FragmentListener implementa esse metodo, dentro do fragmento, pegamos esse contexto dentro do OnAttach()
    @Override
    public void fragmentListener(UnidadeSaude unidadeSaude) {

    }
}
