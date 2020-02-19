package Estruturas;

import Interfaces.NetworkADT;

public class Network<T> extends Graph<T> implements NetworkADT<T> {

    public Network() {
        super();
    }


    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getVertexIndex(vertex1), getVertexIndex(vertex2), weight);
    }

    /**
     * Cria a matriz de adjacencia
     * @param vertex1 vertice1
     * @param vertex2 vertice2
     * @param weight peso da aresta
     */
    public void addEdge(int vertex1, int vertex2, double weight) {
        if (vertex1 == -1 || vertex2 == -1) {
            System.out.println("vértices invalidos");
        } else {
            adjMatrix[vertex1][vertex2] = weight;
        }
    }

    /**
     * calcula os pesos das ligaçoes do grafo
     * Cria a matriz de adjacencia
     */
    public void calculateWeigth() {
        int i = 0, j = 0;
        Espaco[] espacos = new Espaco[vertices.length];
        ArrayUnorderedList<String> ligacoes;

        while(i < vertices.length){
            espacos[i] = (Espaco) vertices[i];
            i++;
        }

        i = 0;

        while (i < numVertices) {

            ligacoes = espacos[i].getLigacoes();
            ArrayIterator it=(ArrayIterator)ligacoes.iterator();
            while (it.hasNext()) {
                String lig=(String) it.next();
                addEdge( (T) espacos[i], (T) getVertex(lig), (getVertex(lig).getFantasma()+1));
                j++;
            }
            j = 0;
            i++;
        }
    }

    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        ArrayIterator it=(ArrayIterator) iteratorShortestPath(vertex1, vertex2);
        if (it!=null){
            double vida=0;
            while (it.hasNext()){
                Espaco esp=(Espaco) it.next();
                vida=vida+esp.getFantasma();
            }
            return vida;
        }
        return Double.POSITIVE_INFINITY;
    }

}
