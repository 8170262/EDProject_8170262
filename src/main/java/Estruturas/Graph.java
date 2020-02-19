package Estruturas;


import Exceptions.EmptyCollectionException;
import Interfaces.GraphADT;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;


public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 5;
    protected int numVertices; // number of vertices in the graph
    protected double[][] adjMatrix; // adjacency matrix
    protected T[] vertices; //Value of Vertices

    public Graph() {
        numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    public void setVertex(T vertex) {
        Espaco[] espacos = new Espaco[numVertices];
        Espaco espaco = (Espaco) vertex;
        int i = 0;

        while (i < numVertices) {
            espacos[i] = (Espaco) vertices[i];
            i++;
        }

        i = 0;

        while (i < numVertices) {
            if (espaco.getAposento().equals(espacos[i].getAposento())) {
                vertices[i] = (T) espaco;
            }
            i++;
        }
    }

    /**
     * Método que retorna o index de um vértice
     *
     * @param vertex vertice
     * @return index corresponde ao vertice ou -1 caso o vértice não exista na
     * nossa lista de vértices
     */
    public int getVertexIndex(T vertex) {
        int i = 0;
        while (i < numVertices) {
            if (vertex.equals(vertices[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Método que adiciona vertice
     *
     * @param vertex vertice
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacityArray();
            expandCapacityMatriz();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = 0.;
            adjMatrix[i][numVertices] = 0;
        }
        numVertices++;
    }

    @Override
    public void removeVertex(T vertex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        //Implementado em Network
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        int v1, v2;

        v1 = getVertexIndex(vertex1);
        v2 = getVertexIndex(vertex2);

        adjMatrix[v1][v2] = 0;
        adjMatrix[v2][v1] = 0;
    }

    public boolean indexIsValid(int index) {
        if (vertices[index] != null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Metodo para encontrar o vertice apartir do nome
     * @param aposento nome do aposento
     * @return Espaco com aquele nome
     */
    public Espaco getVertex(String aposento) {
        int i = 0;
        Espaco[] espacos = new Espaco[numVertices];

        while (i < numVertices) {
            espacos[i] = (Espaco) vertices[i];
            i++;
        }
        i = 0;
        while (i < espacos.length) {
            if (aposento.equals(espacos[i].getAposento())) {
                return espacos[i];
            }
            i++;
        }

        return null;
    }

    public Espaco getVertex(int i) {
        return (Espaco) vertices[i];
    }

    /**
     * Metodo para descobrir as ligaçoes deste vertice
     * @param vertex vertice
     * @return retorna o iterador de uma lista com as ligaçoes do vertice
     */
    public Iterator adjVertex(T vertex) {
        Integer x, startVertexaux, j = 0;
        startVertexaux = getVertexIndex(vertex);

        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (startVertexaux == -1) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        visited[startVertexaux] = true;

        x = startVertexaux;
        while (j < 2) {
            while (!traversalQueue.isEmpty()) {
                x = traversalQueue.dequeue();
                resultList.addToRear(vertices[x.intValue()]);
            }

            if (j < 1) {
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[x.intValue()][i] > 0 && !visited[i]) {
                        traversalQueue.enqueue(new Integer(i));
                        visited[i] = true;
                    }
                }
            }
            j++;
        }

        return resultList.iterator();

    }

    @Override
    public Iterator iteratorBFS(T startVertex) {
        Integer x, startVertexaux;
        startVertexaux = getVertexIndex(startVertex);

        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (startVertexaux == -1) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(new Integer(startVertexaux));
        visited[startVertexaux] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x.intValue()]);

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x.intValue()][i] > 0 && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator iteratorDFS(T startVertex) {
        Integer x, startVertexaux;
        boolean found;
        startVertexaux = getVertexIndex(startVertex);

        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        if (!indexIsValid(startVertexaux)) {
            return resultList.iterator();
        }
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(new Integer(startVertexaux));
        resultList.addToRear(vertices[startVertexaux]);
        visited[startVertexaux] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x.intValue()][i] > 0 && !visited[i]) {
                    traversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }


    /**
     * Método que retorna um iterador que contém o caminho mais curto entre os dois vértices.
     *
     * @param startVertex vertice incial
     * @param targetVertex vertice destino
     * @return um iterador com o caminho mais curto entre os dois vértices
     */
    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {

        Iterator iterator = null;

        // obtém os indices dos vértices
        int source = this.getIndex(startVertex);
        int destination = this.getIndex(targetVertex);

        // se os dois indices forem válidos, significa que os vértices existem
        if (this.indexIsValid(source) && this.indexIsValid(destination)) {
            try {
                //aplica o algoritmo de dijkstra que indica o caminho mais curto entre os 2 vértices
                iterator = this.dijkstra(source, destination);
            } catch (EmptyCollectionException e) {
                e.printStackTrace();
            }
        }

        // retorna o iterador
        return iterator;
    }

    /**
     * Método que retorna um iterador com o caminho mais curto.
     * Este método é calculado com a ajuda do algoritmo Dijkstra.
     *
     * @param origem
     * @param destino
     * @return um iterador com caminho mais curto entre os dois vértices
     */
    private Iterator dijkstra(int origem, int destino)  {

        // lista para a distancia de cada vertice
        double[] distancia = new double[this.numVertices];

        // lista que vai guardar o melhor caminho
        Integer[] anterior = new Integer[this.numVertices];

        // definir todos os valores da lista a null
        for (int i = 0; i < this.numVertices; i++) {
            anterior[i] = null;
        }

        // lista que marca os vertices visitados
        boolean[] vertices_visitados = new boolean[this.numVertices];

        for (int i = 0; i < this.numVertices; i++) {

            // define a distância de cada vertice com valor infinito
            distancia[i] = Double.POSITIVE_INFINITY;

            // define todos os vertices como não usados
            vertices_visitados[i] = false;
        }

        // distância do vertice de origem
        distancia[origem] = 0;

        for (int i = 0; i < this.numVertices - 1; i++) {

            // valor de menor custo
            int vertice_menorCusto = minDistance(distancia, vertices_visitados);

            // coloca o vertice de menor custo como visitado
            vertices_visitados[vertice_menorCusto] = true;

            for (int j = 0; j < this.numVertices; j++) {

                // se o vertice ainda nao tiver sido visitado
                if (!vertices_visitados[j] && this.adjMatrix[vertice_menorCusto][j] != 0.0 && distancia[vertice_menorCusto] != Double.POSITIVE_INFINITY
                        && distancia[vertice_menorCusto] + this.adjMatrix[vertice_menorCusto][j] < distancia[j]) {

                    distancia[j] = distancia[vertice_menorCusto] + this.adjMatrix[vertice_menorCusto][j];

                    anterior[j] = vertice_menorCusto;
                }
            }
        }

        // lista que irá receber o valor dos vértices
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        Integer target = destino;


        if (anterior[target] != null) {
            while (target != null){
                resultList.addToFront(this.vertices[target]);
                target = anterior[target];
            }
        }

        // retorna o iterador
        return resultList.iterator();
    }

    /**
     * Calcula o caminho de menor custo entre os vértices.
     *
     * @param distancias array com as distancias
     * @param vertices_visitados array com os status dos vertices
     * @return valor do menor custo
     */
    protected int minDistance(double distancias[], boolean vertices_visitados[]) {

        // valor do caminho mais curto
        double minDistance = Double.POSITIVE_INFINITY;

        // indice do caminho mais curto
        int minDistanceIndex = -1;

        for (int i = 0; i < this.numVertices; i++) {

            // se a distancia do vertice for menor que o valor do caminho mais curto e o vertice não tiver sido usado
            if (!vertices_visitados[i] && distancias[i] <= minDistance) {

                // a distancia passa a ser o valor do caminho mais curto
                minDistance = distancias[i];

                // guarda o valor do indice
                minDistanceIndex = i;
            }
        }

        // retorna o valor de menor custo
        return minDistanceIndex;
    }

    /**
     * Método que obtém o índice do vertice inserido. Caso o vértice não exista
     * retorna -1
     *
     * @param vertex vertice a verificar
     * @return índice do vértice
     */
    protected int getIndex(T vertex) {

        // se o vértice for nulo
        if (vertex == null) {
            return -1;
        }

        // Percorrer o array de vértices
        for (int i = 0; i < vertices.length; i++) {

            // Se o vertice introduzido for igual ao vertice da posição atual
            if (vertex.equals(vertices[i])) {
                return i; // Retornar o index da posição atual
            }
        }

        // se o vértice não for encontrado, retorna -1
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return (vertices.length == 0);
    }

    /**
     *
     * @return true or false
     */
    @Override
    public boolean isConnected() {
        ArrayIterator itr;
        int contador = 0, i = 0;
        boolean re, entrou = false;

        while (i < numVertices) {
            entrou = true;
            itr = (ArrayIterator)iteratorBFS(vertices[i]);

            while (itr.hasNext()) {
                itr.next();
                contador++;
            }
            if (contador < numVertices) {
                return false;
            }
            contador = 0;
        }
        if (entrou == true) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int size() {return numVertices;
    }

    protected void expandCapacityArray() {
        T[] larger = (T[]) (new Object[numVertices * 2]);

        for (int scan = 0; scan < numVertices; scan++) {
            larger[scan] = vertices[scan];
        }

        vertices = larger;
    }

    protected void expandCapacityMatriz() {
        double[][] larger = (new double[numVertices * 2][numVertices * 2]);

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                larger[i][j] = adjMatrix[i][j];
            }
        }
        adjMatrix = larger;
    }

    public String printADJMatrix() {
        int i = 0, j = 0;
        String matriz = "";
        while (i < adjMatrix.length) {
            while (j < adjMatrix.length) {
                matriz = matriz + adjMatrix[i][j] + " |";
                j++;
            }
            j = 0;
            i++;
            matriz = matriz + "\n";
        }
        return matriz;
    }

    public JPanel verMapa() {
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setBackground(Color.BLACK);

        int i = 0, j = 0;
        while (i < adjMatrix.length) {
            while (j < i+1) {
                if(adjMatrix[i][j] != 0){
                    JLabel jl = new JLabel(((Espaco)vertices[i]).getAposento()+"<---->"+((Espaco)vertices[j]).getAposento());
                    System.out.println(((Espaco)vertices[i]).getAposento()+"<---->"+((Espaco)vertices[j]).getAposento());
                    jl.setForeground(Color.RED);
                    jl.setFont(new Font("Serif", Font.PLAIN, 16));
                    panel.add(jl);
                }
                j++;
            }
            j = 0;
            i++;
        }
        return panel;
    }
}
