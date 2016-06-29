package br.ufc.dspm.urgent;

/**
 * Created by Gustavo on 29/06/2016.
 */
public class PostoDeSaude extends UnidadeSaude {

    String endereco;

    public PostoDeSaude() {
    }

    public PostoDeSaude(double latitude, double longitude) {
        super(latitude, longitude);
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String getTipo() {
        return "posto de saude";
    }
}
