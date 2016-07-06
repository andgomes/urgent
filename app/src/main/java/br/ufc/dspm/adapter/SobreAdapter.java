package br.ufc.dspm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.ufc.dspm.urgent.R;
import br.ufc.dspm.urgent.corporativo.Sobre;


public class SobreAdapter extends ArrayAdapter<Sobre> {

    private Context context;
    private List<Sobre> sobre = null;

    public SobreAdapter(Context context,  List<Sobre> sobre) {
        super(context,0, sobre);
        this.sobre = sobre;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Sobre integrantes = sobre.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.layout_sobre, null);

        ImageView imageViewImagem = (ImageView) view.findViewById(R.id.imageFoto);
        imageViewImagem.setImageResource(integrantes.getFoto());

        TextView textViewNomeEstabelecimento = (TextView) view.findViewById(R.id.textNomeIntegrante);
        textViewNomeEstabelecimento.setText(integrantes.getNomeIntegrante());

        TextView textViewEndereco = (TextView) view.findViewById(R.id.textFuncaoIntegrante);
        textViewEndereco.setText(integrantes.getFuncaoIntegrante());

        return view;
    }

}