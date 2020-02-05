package Estruturas;

import java.util.Arrays;

public class Mapa {
    private String nome;
    private int pontos;
    private Espaco[] mapa;

    public Mapa(String nome, int pontos, Espaco[] mapa) {
        this.nome = nome;
        this.pontos = pontos;
        this.mapa = mapa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Espaco[] getMapa() {
        return mapa;
    }

    public void setMapa(Espaco[] mapa) {
        this.mapa = mapa;
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Mapa or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Mapa)) {
            return false;
        }

        // typecast o to Mapa so that we can compare data members
        Mapa c = (Mapa) o;

        // Compare the data members and return accordingly
        return this.getNome().equals(c.getNome());
    }

    @Override
    public String toString() {
        return "Mapa{" +
                "nome='" + nome + '\'' +
                ", pontos=" + pontos +
                ", mapa=" + Arrays.toString(mapa) +
                '}';
    }
}
