package br.ufc.dspm.urgent;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import br.ufc.dspm.adapter.UnidadeSaudeAdapter;
import br.ufc.dspm.interfaces.AdapterListener;
import br.ufc.dspm.interfaces.FragmentListener;

/**
 * Created by Gustavo on 01/07/2016.
 */
public class ListagemUnidadeFragment extends Fragment implements AdapterListener {

    View fragmentView;

    ListView unidadeSaudeListView;
    UnidadeSaudeAdapter adapter;

    ArrayList<PostoDeSaude> unidadeSaudeList;

    FragmentListener delegate; //delegate que vai ser setado com o contexto da activity pai
    UnidadeSaudeDAO database;
    private boolean canGetLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_listagem_unidade, null);

        database = new UnidadeSaudeDAO(fragmentView.getContext());
        unidadeSaudeList = database.listPostosDeSaude();
        

        createListView();

        return fragmentView;
    }

    void createListView(){
        unidadeSaudeListView = (ListView) fragmentView.findViewById(R.id.listagemunidadefragment_listview);
        adapter = new UnidadeSaudeAdapter(unidadeSaudeList, fragmentView.getContext());//seta a lista
        adapter.setListener(this);//seta o delegate para exte contexto, que implementa a interface AdapterListener que chama onItemAdapterClick
        unidadeSaudeListView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        delegate = (FragmentListener) activity;// converte automaticamente par acontexto

    }

    //dentro do UnidadeSaudeAdapter chamamos esse metodo, ja que o delegate foi setado com esse context
    @Override
    public void onItemAdapterClick(UnidadeSaude unidadeSaude) {
        delegate.fragmentListener(unidadeSaude);// passa a unidade de saude para a activity pai
    }



}
