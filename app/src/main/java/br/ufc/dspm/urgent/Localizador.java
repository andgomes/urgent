package br.ufc.dspm.urgent;

import java.util.LinkedList;
import java.util.List;

public class Localizador {

    public static List<UPA> localizacaoUpas() {

        List<UPA> upas = new LinkedList<>();

        upas.add(new UPA(-3.755920, -38.593743));
        upas.add(new UPA(-3.708786, -38.568718));

        return upas;

    }

}
