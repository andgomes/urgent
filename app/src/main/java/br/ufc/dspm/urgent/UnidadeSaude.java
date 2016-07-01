package br.ufc.dspm.urgent;

import br.ufc.dspm.json.ObjectJson;

public abstract class UnidadeSaude extends ObjectJson{

    private double latitude;
    private double longitude;
    private int distance;
    private String nome="";
    private String endereco="";
    private String telefone="";

    public UnidadeSaude() {
    }

    public UnidadeSaude(double latitude, double longitude) {

        this.latitude = latitude;
        this.longitude = longitude;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereço) {
        this.endereco = endereco;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public abstract String getTipo();

}
