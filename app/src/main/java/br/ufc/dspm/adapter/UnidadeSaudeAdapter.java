package br.ufc.dspm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufc.dspm.interfaces.AdapterListener;
import br.ufc.dspm.urgent.R;
import br.ufc.dspm.urgent.UnidadeSaude;

/**
 * Created by Gustavo on 01/07/2016.
 */
public class UnidadeSaudeAdapter extends BaseAdapter {
    AdapterListener listener;
    private ArrayList data;
    private Context context;

    public UnidadeSaudeAdapter(ArrayList data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void setListener(AdapterListener listener) {
        this.listener = listener;
    }

    public class ViewHolder {
        TextView textView01, textView02, textView03;
        LinearLayout listViewLayout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return (UnidadeSaude) data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.layout_listview_cell_unidadesaude, null);
            viewHolder = new ViewHolder();
            viewHolder.textView01 = (TextView) convertView
                    .findViewById(R.id.unidadesaude_cell_nome);
            viewHolder.textView02 = (TextView) convertView
                    .findViewById(R.id.unidadesaude_cell_endereco);
            viewHolder.textView03 = (TextView) convertView
                    .findViewById(R.id.unidadesaude_cell_telefone);
            viewHolder.listViewLayout = (LinearLayout) convertView
                    .findViewById(R.id.unidadesaude_cell_linearlayout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final UnidadeSaude unidadeSaude = (UnidadeSaude)getItem(position);

        viewHolder.listViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemAdapterClick(unidadeSaude);
            }
        });
        if(unidadeSaude.getNome().equals("null") || unidadeSaude.getNome().equals("")){
            viewHolder.textView01.setVisibility(View.GONE);
        }else {
            viewHolder.textView01.setText(unidadeSaude.getNome());
        }
        viewHolder.textView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemAdapterClick(unidadeSaude);
            }
        });


        if(unidadeSaude.getEndereco().equals("null") || unidadeSaude.getEndereco().equals("")){
            viewHolder.textView02.setVisibility(View.GONE);
        }else{
            viewHolder.textView02.setText(unidadeSaude.getEndereco());
        }
        viewHolder.textView02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemAdapterClick(unidadeSaude);
            }
        });


        if(unidadeSaude.getTelefone().equals("null") || unidadeSaude.getTelefone().equals("")){
            viewHolder.textView03.setVisibility(View.GONE);
        }else{
            viewHolder.textView03.setText(unidadeSaude.getTelefone());
        }
        viewHolder.textView03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemAdapterClick(unidadeSaude);
            }
        });

        return convertView;
    }
}
