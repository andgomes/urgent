package br.ufc.dspm.urgent;

/**
 * Created by Gustavo on 29/06/2016.
 */
public class PostoDeSaude extends UnidadeSaude {

    public PostoDeSaude() {
    }

    public PostoDeSaude(double latitude, double longitude) {
        super(latitude, longitude);
    }

    @Override
    public String getTipo() {
        return "posto de saude";
    }
}
