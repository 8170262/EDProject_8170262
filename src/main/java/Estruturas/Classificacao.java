package Estruturas;

public class Classificacao {
    private Mapa mapa;
    private ArrayOrderedList<Double> vida;

    public Classificacao(Mapa mapa, ArrayOrderedList<Double> vida) {
        this.mapa = mapa;
        this.vida = vida;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public ArrayOrderedList<Double> getVida() {
        return vida;
    }

    public void setVida(ArrayOrderedList<Double> vida) {
        this.vida = vida;
    }
}
