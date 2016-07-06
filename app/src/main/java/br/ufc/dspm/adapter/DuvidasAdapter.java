package br.ufc.dspm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufc.dspm.urgent.corporativo.Duvidas;
import br.ufc.dspm.urgent.R;

/**
 * Created by Islane on 05/07/2016.
 */
public class DuvidasAdapter extends ArrayAdapter<Duvidas>{

    private Context context;
    private List<Duvidas> duvidas = null;

    public DuvidasAdapter(Context context,  List<Duvidas> duvidas) {
        super(context,0, duvidas);
        this.duvidas = duvidas;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Duvidas duv = duvidas.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.layout_duvidas, null);

        TextView textViewNomeEstabelecimento = (TextView) view.findViewById(R.id.textPergunta);
        textViewNomeEstabelecimento.setText(duv.getPergunta());

        TextView textViewEndereco = (TextView) view.findViewById(R.id.textResposta);
        textViewEndereco.setText(duv.getResposta());

        return view;
    }

}
