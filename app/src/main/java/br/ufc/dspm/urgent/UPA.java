package br.ufc.dspm.urgent;

public class UPA {

    private double latitude;
    private double longitude;

    public UPA(double lat, double lng) {

        latitude = lat;
        longitude = lng;

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
