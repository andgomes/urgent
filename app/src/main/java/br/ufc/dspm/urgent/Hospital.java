package br.ufc.dspm.urgent;

/**
 * Created by Gustavo on 29/06/2016.
 */
public class Hospital extends UnidadeSaude {

    String endereco;

    public Hospital(double latitude, double longitude) {
        super(latitude, longitude);
    }

    @Override
    public String getTipo() {
        return "Hospital";
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
