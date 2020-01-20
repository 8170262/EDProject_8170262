package Estruturas;

import Interfaces.NetworkADT;
import Mapa.Espaco;
import Mapa.Mapa;

public class Network<T> extends Graph<T> implements NetworkADT<T> {

    public Network() {
        super();
    }

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getVertexIndex(vertex1), getVertexIndex(vertex2), weight);
    }

    public void addEdge(int vertex1, int vertex2, double weight) {
        if (vertex1 == -1 || vertex2 == -1) {
            System.out.println("vértices invalidos");
        } else {

            adjMatrix[vertex1][vertex2] = weight;

        }

    }

    public void calculateWeigth() {
        int i = 0, j = 0;
        Espaco[] espacos = new Espaco[vertices.length];
        String[] ligacoes;

        while(i < vertices.length){
            espacos[i] = (Espaco) vertices[i];
            i++;
        }

        i = 0;

        while (i < numVertices) {

            ligacoes = espacos[i].getLigacoes();

            while (j < ligacoes.length) {
                addEdge( (T) espacos[i], (T) getVertex(ligacoes[j]), (getVertex(ligacoes[j]).getFantasma()+1));
                j++;
            }
            j = 0;
            i++;
        }

    }

    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
