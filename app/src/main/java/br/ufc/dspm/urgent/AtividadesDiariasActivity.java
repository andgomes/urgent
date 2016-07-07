package br.ufc.dspm.urgent;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AtividadesDiariasActivity extends AppCompatActivity {

    private TextView tvListaAtividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades_diarias);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Hábitos Saudáveis - URGENT");

        tvListaAtividades = (TextView) findViewById(R.id.tvAtividades);

        StringBuilder sb = new StringBuilder();
        sb.append("1 - Alimente-se bem").append("\n\n");
        sb.append("2 - Mexa-se").append("\n\n");
        sb.append("3 - Beba água").append("\n\n");
        sb.append("4 - Evite o estresse").append("\n\n");
        sb.append("5 - Divirta-se").append("\n\n");
        sb.append("6 - Descanse quando necessário").append("\n\n");
        sb.append("7 - Cuide da sua postura").append("\n\n");
        sb.append("8 - Use protetor solar").append("\n\n");
        sb.append("9 - Esqueça o cigarro e o alcóol").append("\n\n");
        sb.append("10 - Levante sua auto-estima");

        tvListaAtividades.setText(sb.toString());

    }

    @Override
    protected void onStop() {

        super.onStop();
        finish();

    }

}
