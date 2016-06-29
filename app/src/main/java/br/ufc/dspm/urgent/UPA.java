package br.ufc.dspm.urgent;

public class UPA extends UnidadeSaude {

    public UPA(double lat, double lng) {
        super(lat, lng);
    }

    public String getTipo() {
        return "upa";
    }

}
