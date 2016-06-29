package br.ufc.dspm.urgent;

import android.content.ContentValues;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;

public class Localizador {

    private UnidadeSaudeDAO unidadeSaudeDAO;

    public Localizador(Context context) {

        unidadeSaudeDAO = new UnidadeSaudeDAO(context);

        unidadeSaudeDAO.adicionarUnidadeSaude(new UPA(-3.755920, -38.593743));
        unidadeSaudeDAO.adicionarUnidadeSaude(new UPA(-3.708786, -38.568718));

    }

    public List<UPA> localizacaoUpas() {

        return unidadeSaudeDAO.listUpas();

    }

}
