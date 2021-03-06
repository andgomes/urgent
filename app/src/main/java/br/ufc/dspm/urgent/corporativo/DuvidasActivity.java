package br.ufc.dspm.urgent.corporativo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.ufc.dspm.adapter.DuvidasAdapter;
import br.ufc.dspm.urgent.R;
import br.ufc.dspm.urgent.corporativo.Duvidas;

public class DuvidasActivity extends AppCompatActivity {

    ListView listaDeDuvidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duvidas);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Dúvidas");

        listaDeDuvidas = (ListView) findViewById(R.id.lista_Duvidas);
        List<Duvidas> duvidas = novaDuvida();

        DuvidasAdapter duvidasAdapter = new DuvidasAdapter
                (this, duvidas);
        listaDeDuvidas.setAdapter((ListAdapter) duvidasAdapter);

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

    private List<Duvidas> novaDuvida(){
        List<Duvidas> duvidas = new ArrayList<Duvidas>();
        duvidas.add(novaDuvida("O que é o URGENT?", "Urgent é o novo aplicativo que vai " +
                "auxiliar você a encontrar o que você precisa para ser atendido na rede pública de saúde."));

        duvidas.add(novaDuvida("Posso me consultar em qualquer posto de saúde?",
                "Não, a consulta deve ser feita no posto de saúde mais próximo da sua casa, levando " +
                        "comprovante de endereço e identidade."));

        duvidas.add(novaDuvida("Posso ser atendido em qualquer UPA de Fortaleza?",
                "Sim, as Unidades de Pronto Atendimento(UPAs) atendem a todos que precisam, independente " +
                        "do bairro que a pessoa reside."));

        duvidas.add(novaDuvida("Como encontro a UPA mais perto de mim?",
                "Sempre que você selecionar Hospitais, Postos de Saúde, ou UPAS, o aplicativo vai mostrar " +
                        "no minimo as 3 localizações mais perto de você."));

        duvidas.add(novaDuvida("Em casos cosiderados leves, onde devo ir?",
                "Nesses casos, o mais recomendado é que você se dirija ao posto de saúde do seu bairro. " +
                        "Caso seu bairro não tenha posto de saúde procure a unidade mais próxima de você."));

        duvidas.add(novaDuvida("Em casos cosiderados medianos, onde devo ir?",
                "Nesses casos, o mais recomendado é que você se dirija a uma UPA mais próxima de você."));

        duvidas.add(novaDuvida("Em casos cosiderados graves, onde devo ir?",
                "Nesses casos, o mais recomendado é que você se dirija ao hospital mais próximo ou acione o SAMU."));

        return duvidas;
    }

    private Duvidas novaDuvida(String pergunta, String resposta){
        Duvidas duvidas = new Duvidas(pergunta, resposta);
        return duvidas;

    }
}
