import Estruturas.Network;
import Mapa.Espaco;
import Mapa.Mapa;

import javax.swing.text.html.HTMLDocument;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
/*
public class Demo2 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        FileManager fm=new FileManager();
        Mapa mapa=fm.readFile("C:\\Users\\Pedro Luis\\Documents\\GitHub\\EDProject_8170262\\mapa.json");
        System.out.println(mapa.toString());



        Mapa[] mapas=new Mapa[]{mapa};
        int opcao = 0;
        do {
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
                    String path = sc.next();
                    mapa = fm.readFile(path);
                    break;
                case 2:
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
                                break;
                            case 2:
                                System.out.println("\n\n############# Jogo Normal #############");
                                System.out.println("========================================");
                                break;
                            case 3:
                                System.out.println("\n\n############# Jogo Dificil #############");
                                System.out.println("========================================");
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("OpÃ§Ã£o InvÃ¡lida!");
                                break;
                        }
                    }while (opcao1!=0);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("OpÃ§Ã£o InvÃ¡lida!");
                    break;
            }
        } while (opcao != 0);
    }

    public static void manual(Network n, Mapa mapa, int dif){
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
            System.out.println("*****Estás no/a "+current.getAposento()+"*****");
            numLig=0;
            Iterator it=n.adjVertex(current);
            while (it.hasNext()) {
                Espaco espaco = (Espaco) it.next();
                numLig++;
                System.out.println(numLig+" - "+ espaco.getAposento());
            }*/
          /*  for (String i: current.getLigacoes()){
                numLig++;
                System.out.println(numLig+" - "+i);
            }*/
/*
            System.out.println("0 - Sair");

            opcao=sc.nextInt();

            if (opcao!=0) {
                currentName = current.getLigacoes()[opcao - 1];
                current = n.getVertex(currentName);
                vida = vida - (current.getFantasma() * dif);
                System.out.println("Vida : " + vida);


                for (String liga : current.getLigacoes()) {
                    if (liga.equals("exterior")) {
                        end = 1;
                    }
                }
            }
        }while(end != 1 && vida>0 && opcao!=0);

        if (opcao!=0) {
            if (vida >= 0) {
                System.out.println("Conseguiste sair!!!! Com " + vida + " de vida restante.");
            } else {
                System.out.println("Perdes-te!!! Ficaste sem vida!");
            }
        }
    }

    public static void simulacao(Espaco[] espacos,Network n, Mapa mapa){
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
*/
