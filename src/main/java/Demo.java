import Estruturas.ArrayList;
import Estruturas.Network;
import Mapa.Mapa;
import Mapa.Espaco;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

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

        manual(n,mapa, 1);
    }

    public static void manual(Network n,Mapa mapa, int dif){
        Espaco current=new Espaco("",0,new String[]{});
        String currentName;
        Scanner sc = new Scanner(System.in);
        int end=0, opcao=0, count=1,numLig=0;
        double vida=mapa.getPontos();
        for(Espaco esp:mapa.getMapa()){
            for (String i:esp.getLigacoes()){
                if (i.equals("entrada")){
                    current=esp;
                }
            }
        }


        do{
            numLig=0;
            for (String i: current.getLigacoes()){
                numLig++;
                System.out.println(numLig+" - "+i);
            }

            opcao=sc.nextInt();

            currentName=current.getLigacoes()[opcao-1];
            current=n.getVertex(currentName);
            vida=vida-(current.getFantasma()*dif);


            for (String liga:current.getLigacoes()){
                if(liga.equals("exterior")){
                    end=1;
                }
            }

        }while(end != 1 && vida>0);
        if (vida>=0){
            System.out.println("Conseguiste sair!!!! Com "+vida+" de vida restante.");
        }else{
            System.out.println("Perdes-te!!! Ficaste sem vida!");
        }

    }
}
