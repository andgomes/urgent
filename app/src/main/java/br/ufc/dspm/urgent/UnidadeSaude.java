package br.ufc.dspm.urgent;

public abstract class UnidadeSaude {

    private double latitude;
    private double longitude;

    public UnidadeSaude() {
    }

    public UnidadeSaude(double latitude, double longitude) {

        this.latitude = latitude;
        this.longitude = longitude;

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public abstract String getTipo();

}
