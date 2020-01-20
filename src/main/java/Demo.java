import Estruturas.ArrayList;
import Estruturas.Network;
import Mapa.Mapa;
import Mapa.Espaco;

import java.util.Arrays;
import java.util.Iterator;

public class Demo {

    public static void main(String[] args){
        Network n=new Network();
        FileManager fm=new FileManager();
        Mapa mapa=fm.readFile("C:\\Users\\Pedro Luis\\Documents\\GitHub\\EDProject_8170262\\mapa.json");
        System.out.println(mapa.toString());
        Espaco[] espacos=mapa.getMapa();


        int i=0;
        String[] arrayExterior=new String[]{};
        while (i < espacos.length) {
            n.addVertex(espacos[i]);
            for(String lig : espacos[i].getLigacoes()){
                if(lig.equals("entrada")){
                    String[] arrayEntrada=new String[]{espacos[i].getAposento()};
                    Espaco entrada=new Espaco("entrada",0, arrayEntrada);
                    n.addVertex(entrada);
                }
                if(lig.equals("exterior")){
                    final int N = arrayExterior.length;
                    arrayExterior = Arrays.copyOf(arrayExterior, N + 1);
                    arrayExterior[N] = espacos[i].getAposento();
                }
            }
            i++;
        }
        Espaco exterior=new Espaco("exterior",0, arrayExterior);
        n.addVertex(exterior);


        n.calculateWeigth();
        System.out.println(n.printADJMatrix());


        Iterator it = n.iteratorShortestPath(n.getVertex("entrada"), n.getVertex("exterior"));
        Iterator it2 = n.iteratorShortestPath(n.getVertex("entrada"), n.getVertex("exterior"));
        i = 1;
        while (it.hasNext()) {
            Espaco espaco = (Espaco) it.next();
            System.out.println(i + "a paragem: " + espaco.getAposento());
            i++;
        }
        double vida=mapa.getPontos()-n.calculatePathWeigth(it2);
        System.out.println("Vida : " + vida);
    }
}
