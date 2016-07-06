package br.ufc.dspm.urgent.corporativo;

public class Sobre {

    private String nomeIntegrante;
    private String funcaoIntegrante;
    private int foto;

    public Sobre(String nomeIntegrante, String funcaoIntegrante, int foto){

        this.nomeIntegrante = nomeIntegrante;
        this.funcaoIntegrante = funcaoIntegrante;
        this.foto = foto;

    }

    public String getNomeIntegrante(){

        return nomeIntegrante;

    }

    public void setNomeIntegrante(String nomeIntegrante){

        this.nomeIntegrante = nomeIntegrante;

    }

    public String getFuncaoIntegrante(){

        return funcaoIntegrante;

    }

    public void setFuncaoIntegrante (String funcaoIntegrante){

        this.funcaoIntegrante = funcaoIntegrante;

    }

    public int getFoto(){

        return foto;

    }

    public void setFoto(int foto){

        this.foto = foto;

    }
}




