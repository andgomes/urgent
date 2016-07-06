package br.ufc.dspm.urgent;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.ufc.dspm.adapter.SobreAdapter;

public class SobreActivity extends AppCompatActivity {

    ListView listaDeSobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Sobre");

        listaDeSobre = (ListView) findViewById(R.id.lista_Sobre);
        List<Sobre> sobre = novoSobre();

        SobreAdapter sobreAdapter = new SobreAdapter
                (this, sobre);
        listaDeSobre.setAdapter(sobreAdapter);

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

    private List<Sobre> novoSobre(){
        List<Sobre> sobre = new ArrayList<Sobre>();
        sobre.add(novoSobre("Aline Alexandre", "Desenvolvedor", R.drawable.aline));
        sobre.add(novoSobre("Anderson Gomes", "Desenvolvedor", R.drawable.anderson));
        sobre.add(novoSobre("Antonio Islane", "Desenvolvedor", R.drawable.islane));
        sobre.add(novoSobre("Gustavo Brilhante", "Desenvolvedor", R.drawable.gustavo));
        return sobre;
    }

    private Sobre novoSobre(String nomeIntegrante, String funcaoIntegrante, int foto){
        Sobre sobre = new Sobre(nomeIntegrante, funcaoIntegrante, foto);
        return sobre;

    }

}
