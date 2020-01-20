package Mapa;

import java.util.Arrays;

public class Espaco {
    private String aposento;
    private int fantasma;
    private String[] ligacoes;

    public Espaco(String aposento, int fantasma, String[] ligacoes) {
        this.aposento = aposento;
        this.fantasma = fantasma;
        this.ligacoes = ligacoes;
    }

    public String getAposento() {
        return aposento;
    }

    public void setAposento(String aposento) {
        this.aposento = aposento;
    }

    public int getFantasma() {
        return fantasma;
    }

    public void setFantasma(int fantasma) {
        this.fantasma = fantasma;
    }

    public String[] getLigacoes() {
        return ligacoes;
    }

    public void setLigacoes(String[] ligacoes) {
        this.ligacoes = ligacoes;
    }

    @Override
    public String toString() {
        return "Espaco{" +
                "aposento='" + aposento + '\'' +
                ", fantasma=" + fantasma +
                ", ligacoes=" + Arrays.toString(ligacoes) +
                '}';
    }
}
