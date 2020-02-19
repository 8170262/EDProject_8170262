package Estruturas;

public class Classificacao {
    private String mapa;
    private String utiliador;
    private Double vida;

    public Classificacao(String mapa, String utiliador, Double vida) {
        this.mapa = mapa;
        this.utiliador = utiliador;
        this.vida = vida;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public String getUtiliador() {
        return utiliador;
    }

    public void setUtiliador(String utiliador) {
        this.utiliador = utiliador;
    }

    public Double getVida() {
        return vida;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }

    @Override
    public String toString() {
        return "Mapa: "+ mapa +"; " +"Utilizador: "+ utiliador+ ";" +"Vida:" + vida +";" + "\n";
    }
}
