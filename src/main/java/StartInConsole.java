import Estruturas.ArrayOrderedList;
import Estruturas.ArrayUnorderedList;
import Estruturas.Network;
import Estruturas.Mapa;
import Estruturas.Espaco;
import Estruturas.FileManager;
import Estruturas.Classificacao;

import java.util.Iterator;
import java.util.Scanner;

public class StartInConsole {
    private static ArrayUnorderedList<Classificacao> classif=new ArrayUnorderedList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        FileManager fm=new FileManager();
        Mapa mapa=fm.readFile("mapa.json");
        //System.out.println(mapa.toString());



        ArrayUnorderedList<Mapa> mapas=new ArrayUnorderedList<>();

        mapas.addToRear(mapa);
        int opcao = 0;
        do {
            Network n=new Network();
            Espaco[] espacos=mapa.getMapa();
        /*
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
                        int n = arrayExterior.length;
                        arrayExterior = Arrays.copyOf(arrayExterior, n + 1);
                        arrayExterior[n] = espacos[i].getAposento();
                    }
                }
                i++;
            }
            Espaco exterior=new Espaco("exterior",0, arrayExterior);
            n.addVertex(exterior);*/
            int i=0,k=0;
            ArrayUnorderedList<String> arrayExt=new ArrayUnorderedList<>(1);
            while (i < espacos.length) {
                n.addVertex(espacos[i]);
                Iterator it=espacos[i].getLigacoes().iterator();
                while(it.hasNext()){
                    String lig=(String)it.next();
                    if(lig.equals("entrada")){
                        ArrayUnorderedList<String> arrayEntrada=new ArrayUnorderedList<>(1);
                        arrayEntrada.addToRear(espacos[i].getAposento());
                        Espaco entrada=new Espaco("entrada",0, arrayEntrada);
                        n.addVertex(entrada);
                    }
                    if(lig.equals("exterior")){
                        arrayExt.addToRear(espacos[i].getAposento());
                    }
                }
                i++;
            }
            Espaco exterior=new Espaco("exterior",0, arrayExt);
            n.addVertex(exterior);


            n.calculateWeigth();
           // System.out.println(n.printADJMatrix());

            System.out.println("\n\n############# Casa Assombrada #############");
            System.out.println("===========================================");
            System.out.println("1 - Inserir novo mapa em formato JSON");
            System.out.println("2 - Escolher mapa para jogar");
            System.out.println("3 - Jogar em Manual");
            System.out.println("4 - Simulação");
            System.out.println("5 - Classificações");
            System.out.println("0 - Sair");
            System.out.println("===========================================\n");
            opcao = sc.nextInt();
            System.out.print("\n");
            switch (opcao) {
                case 1:
                    System.out.println("Insira o path para o ficheiro JSON");
                    String path;
                    path = sc.next();
                    //path="mapa1.json";
                    if (!mapas.contains(fm.readFile(path))) {
                        mapa = fm.readFile(path);
                        mapas.addToRear(mapa);
                    }
                    break;
                case 2:
                    int opcao2=0;
                    do {
                        System.out.println("\n\n############### Mapas ###############");
                        System.out.println("===========================================");
                        Iterator it=mapas.iterator();
                        int id=0;
                        while(it.hasNext()){
                            Mapa mp=(Mapa) it.next();
                            id++;
                            System.out.println(id+" - "+mp.getNome());
                        }
                        System.out.println("0 - Sair");
                        System.out.println("===========================================\n");
                        opcao2=sc.nextInt();
                        if (opcao2>0 && opcao2<mapas.size()+1){
                            Iterator it1=mapas.iterator();
                            int f=0;
                            while (it1.hasNext()) {
                                Mapa mp=(Mapa) it1.next();
                                f++;
                                if (opcao2==f) {
                                    mapa = mp;
                                    System.out.println("************ Escolheste " + mp.getNome() + " ************");
                                }
                            }
                        }
                    }while (opcao2!=0);
                    break;
                case 3:
                    int opcao1=0;
                    do {
                        System.out.println("\n\n############### Jogo Manual ###############");
                        System.out.println("===========================================");
                        System.out.println("1 - Básico");
                        System.out.println("2 - Normal");
                        System.out.println("3 - Dificil");
                        System.out.println("0 - Sair");
                        System.out.println("===========================================\n");
                        opcao1 = sc.nextInt();
                        System.out.print("\n");
                        switch (opcao1){
                            case 1:
                                System.out.println("\n\n############# Jogo Básico #############");
                                System.out.println("========================================");
                                manual(n,mapa, 1);
                                break;
                            case 2:
                                System.out.println("\n\n############# Jogo Normal #############");
                                System.out.println("========================================");
                                manual(n,mapa, 2);
                                break;
                            case 3:
                                System.out.println("\n\n############# Jogo Dificil #############");
                                System.out.println("========================================");
                                manual(n,mapa, 3);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Opcao Invalida!");
                                break;
                        }
                    }while (opcao1!=0);
                    break;
                case 4:
                    simulacao(n,mapa);
                    break;
                case 5:
                    Iterator it=classif.iterator();
                    System.out.println("---"+ mapa.getNome() +"---");
                    while(it.hasNext()){
                        Classificacao cl=(Classificacao) it.next();
                        if (cl.getMapa().equals(mapa)){
                            Iterator it1=cl.getVida().iterator();
                            while (it1.hasNext()){
                                Double vd=(Double) it1.next();
                                System.out.println("---     "+ vd +"     ---");
                            }
                        }
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao Invalida!");
                    break;
            }
        } while (opcao != 0);
    }

    /**
     * Método do jogo em manual
     * @param n
     * @param mapa
     * @param dif
     */
    private static void manual(Network n, Mapa mapa, int dif){
        Espaco current=new Espaco("",0,new ArrayUnorderedList<>());
        String currentName="";
        Scanner sc = new Scanner(System.in);
        int end=0, opcao=0, numLig=0;
        double vida=mapa.getPontos();
        for(Espaco esp:mapa.getMapa()){
            Iterator iterator=esp.getLigacoes().iterator();
            while (iterator.hasNext()){
                String apos=(String)iterator.next();
                if (apos.equals("entrada")){
                    current=esp;
                }
            }
        }
        do{
            System.out.println("*****Estás no/a "+current.getAposento()+"*****");
            numLig=0;
            Iterator it=n.adjVertex(current);
            Iterator it1=n.adjVertex(current);
            while (it.hasNext()) {
                Espaco espaco = (Espaco) it.next();
                numLig++;
                if (espaco.getFantasma()>0){
                    System.out.println(numLig+" - "+ espaco.getAposento() + " - F(" + espaco.getFantasma()*dif +")");
                }else{
                    System.out.println(numLig+" - "+ espaco.getAposento());
                }
            }
            System.out.println("0 - Sair");

            opcao=sc.nextInt();

            if (opcao!=0) {
                int i=0;
                while(it1.hasNext()){
                    Espaco espaco = (Espaco) it1.next();
                    i++;
                    if (opcao==i){
                        currentName = espaco.getAposento();
                    }
                }
                current = n.getVertex(currentName);
                vida = vida - (current.getFantasma() * dif);
                System.out.println("Vida : " + vida);

                Iterator it2=n.adjVertex(current);
                while (it2.hasNext()) {
                    Espaco espaco = (Espaco) it2.next();
                    if ((espaco.getAposento()).equals("exterior")) {
                        end = 1;
                    }
                }
            }
        }while(end != 1 && vida>0 && opcao!=0);


        if (opcao!=0) {
            if (vida > 0) {
                if (classif.isEmpty()){
                    ArrayOrderedList<Double> db=new ArrayOrderedList<>();
                    db.add(vida);
                    Classificacao cl= new Classificacao(mapa, db);
                    classif.addToRear(cl);
                }else{
                    Iterator it=classif.iterator();
                    int i=0;
                    while (it.hasNext()){
                        Classificacao c=(Classificacao) it.next();
                        if (c.getMapa().equals(mapa)){
                            c.getVida().add(vida);
                            i++;
                        }
                    }
                    if(i==0){
                        ArrayOrderedList<Double> db=new ArrayOrderedList<>();
                        db.add(vida);
                        Classificacao cl= new Classificacao(mapa, db);
                        classif.addToRear(cl);
                    }
                }
                System.out.println("Conseguiste sair!!!! Com " + vida + " de vida restante.");
            } else {
                System.out.println("Perdes-te!!! Ficaste sem vida!");
            }
        }
    }

    /**
     * Metodo que devolve a melhor solução para este mapa
     * @param n
     * @param mapa
     */
    private static void simulacao(Network n, Mapa mapa){
        int i=0;

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
