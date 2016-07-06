package br.ufc.dspm.urgent.unidades;

/**
 * Created by Gustavo on 29/06/2016.
 */
public class Hospital extends UnidadeSaude {

    public Hospital(double latitude, double longitude) {
        super(latitude, longitude);
    }

    @Override
    public String getTipo() {
        return "hospital";
    }
}
