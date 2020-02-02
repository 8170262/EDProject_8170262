package Mapa;

import Estruturas.ArrayUnorderedList;

import java.util.Arrays;

public class Espaco {
    private String aposento;
    private int fantasma;
    private ArrayUnorderedList<String> ligacoes;

    public Espaco(String aposento, int fantasma, ArrayUnorderedList<String> ligacoes) {
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

    public ArrayUnorderedList<String> getLigacoes() {
        return ligacoes;
    }

    public void setLigacoes(ArrayUnorderedList<String> ligacoes) {
        this.ligacoes = ligacoes;
    }

    @Override
    public String toString() {
        return "Espaco{" +
                "aposento='" + aposento + '\'' +
                ", fantasma=" + fantasma +
                ", ligacoes=" + ligacoes.toString() +
                '}';
    }
}
