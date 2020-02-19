package GUI;

import Estruturas.*;
import Exceptions.EmptyCollectionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MenuGUI extends JFrame{
    private JButton inserirMapaButton;
    private JButton escolherMapaButton;
    private JButton jogarManualButton;
    private JButton simulacaoButton;
    private JButton classificacoesButton;
    private JPanel cardLayout;
    private JPanel menuButtons;
    private JPanel insertCard;
    private JPanel chooseCard;
    private JPanel manualCard;
    private JPanel Panel;
    private JButton homeButton;
    private JPanel homeCard;
    private JPanel simulacaoCard;
    private JPanel classifCard;
    private JLabel imageHome;
    private JPanel manualCard1;
    private JButton facilButton;
    private JButton medioButton;
    private JButton dificilButton;
    private JLabel currentEspLabel;
    private JPanel doorPanel;
    private JLabel vidaLabel;
    private JLabel statusLabel;
    private JTextField textField1;
    private JButton INSERIRButton;
    private JPanel mapasPanel;
    private JButton verMapaButton;
    private JLabel imagePlaying;
    private JPanel verMapa;
    private JTextField utilizador;

    private ArrayUnorderedList<Classificacao> classif=null;
    private Espaco current=new Espaco("", 0, new ArrayUnorderedList<>());
    private ArrayUnorderedList<Mapa> mapas=new ArrayUnorderedList<>();
    private FileManager fm=new FileManager();
    private Network n;
    private Mapa mapa=new Mapa(null,0.0,null);
    private Espaco[] espacos;
    private int bigFant;
    private int randEscudo=0;
    private int randApos=-1;
    private int escudo=0;

    private CardLayout cLayout;

    /**
     * Construtor que contem todos os listenenrs dos botoes da aplicação
     * Neste metodo é inserido um mapa "default"
     */
    public MenuGUI() {
        inserirMapa("mapas\\mapa_defesa01.json");



        add(Panel);
        cardLayout.add(insertCard, "insert");
        cardLayout.add(chooseCard, "choose");
        cardLayout.add(manualCard, "manual");
        cardLayout.add(manualCard1, "manual1");
        cardLayout.add(simulacaoCard, "simul");
        cardLayout.add(classifCard, "classif");
        cardLayout.add(homeCard, "home");
        cardLayout.add(verMapa, "verM");


        imageHome.setIcon(new ImageIcon("images/backgroundHome.png"));
        imagePlaying.setIcon(new ImageIcon("images/Playing.png"));

        cLayout=(CardLayout) (cardLayout.getLayout());
        cLayout.show(cardLayout,"home");

        inserirMapaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cLayout.show(cardLayout,"insert");

            }
        });
        escolherMapaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cLayout.show(cardLayout,"choose");
                chooseMap();
            }
        });
        jogarManualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cLayout.show(cardLayout,"manual");
                statusLabel.setText("");
            }
        });
        simulacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cLayout.show(cardLayout,"simul");
                simulacao();
            }
        });
        classificacoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cLayout.show(cardLayout,"classif");
                classificacao();
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cLayout.show(cardLayout,"home");
            }
        });
        facilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!utilizador.getText().equals("")){
                    cLayout.show(cardLayout,"manual1");
                    setEscudoManualGame(1,current, mapa.getPontos(),utilizador.getText());
                }
            }
        });
        medioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!utilizador.getText().equals("")){
                    cLayout.show(cardLayout,"manual1");
                    setEscudoManualGame(2,current, mapa.getPontos(),utilizador.getText());
                }
            }
        });
        dificilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!utilizador.getText().equals("")){
                    cLayout.show(cardLayout,"manual1");
                    setEscudoManualGame(3,current, mapa.getPontos(),utilizador.getText());
                }
            }
        });
        INSERIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cLayout.show(cardLayout,"home");
                inserirMapa(textField1.getText());
            }
        });
        textField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                JFileChooser jFileChooser1 = new JFileChooser();
                int returnvalue = jFileChooser1.showOpenDialog(null);
                if (returnvalue == JFileChooser.APPROVE_OPTION) {
                    textField1.setText(jFileChooser1.getSelectedFile().getAbsolutePath());
                }
            }
        });
        verMapaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cLayout.show(cardLayout,"verM");
                verMapa();
            }
        });
    }

    /**
     * Metodo para inserir o mapa, se já não existir um mapa com o mesmo nome
     * Ainda chama para calcular o grafo e adiciona o mapa á lista de mapas
     * @param path
     */
    private void inserirMapa(String path){
        if (!mapas.contains(fm.readFile(path))){
            Mapa mapaAux = fm.readFile(path);
            Network nAux=setNetGraph(mapaAux);


            if (mapaAux.getPontos()-nAux.shortestPathWeight(nAux.getVertex("entrada"),nAux.getVertex("exterior"))>0) {
                mapa = mapaAux;
                n = nAux;
                espacos = mapa.getMapa();
                mapas.addToRear(mapa);

                for (Espaco esp : mapa.getMapa()) {
                    ArrayIterator iterator = (ArrayIterator) esp.getLigacoes().iterator();
                    while (iterator.hasNext()) {
                        String apos = (String) iterator.next();
                        if (apos.equals("entrada")) {
                            current = esp;
                        }
                    }
                }
            }else{
                System.out.println("Mapa Inválido");
            }
        }
    }

    /**
     * Instancia o grafo para este mapa
     */
    private Network setNetGraph(Mapa mapaAux){
        Network net=new Network();
        espacos=mapaAux.getMapa();
        int i=0,k=0;
        ArrayUnorderedList<String> arrayExt=new ArrayUnorderedList<>(1);
        while (i < espacos.length) {
            net.addVertex(espacos[i]);
            if (espacos[i].getFantasma()>bigFant){
                bigFant=espacos[i].getFantasma();
            }
            ArrayIterator it=(ArrayIterator) espacos[i].getLigacoes().iterator();
            while(it.hasNext()){
                String lig=(String)it.next();
                if(lig.equals("entrada")){
                    ArrayUnorderedList<String> arrayEntrada=new ArrayUnorderedList<>(1);
                    arrayEntrada.addToRear(espacos[i].getAposento());
                    Espaco entrada=new Espaco("entrada",0, arrayEntrada);
                    net.addVertex(entrada);
                }
                if(lig.equals("exterior")){
                    arrayExt.addToRear(espacos[i].getAposento());
                }
            }
            i++;
        }
        Espaco exterior=new Espaco("exterior",0, arrayExt);
        net.addVertex(exterior);


        net.calculateWeigth();
        return net;
    }

    /**
     * Metodo para escolher o mapa que quer jogar
     */
    private void chooseMap(){
        ArrayIterator it=(ArrayIterator)mapas.iterator();
        mapasPanel.removeAll();
        while(it.hasNext()){
            Mapa mp=(Mapa) it.next();
            ImageIcon img = new ImageIcon("images/backgroundButton.png");
            JButton b = new JButton(img);
            b.setText(mp.getNome());
            b.setHorizontalAlignment(SwingConstants.LEFT);
            b.setVerticalAlignment(SwingConstants.TOP);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    cLayout.show(cardLayout, "home");
                    mapa=mp;
                    espacos=mapa.getMapa();
                    n=setNetGraph(mapa);
                }
            });
            mapasPanel.add(b);
        }
        mapasPanel.validate();
        mapasPanel.repaint();
    }

    private void setEscudoManualGame(int dif, Espaco current,Double vida,String utilizador){
        Random rand=new Random();
        randEscudo=rand.nextInt(bigFant);
        randApos=rand.nextInt(n.size());

        int ok=0, count=0;
        do {
            if ((n.getVertex(randApos)).getFantasma() == 0) {
                ok++;
            } else {
                if (randApos<n.size()){
                    randApos++;
                }else{
                    randApos=0;
                }
            }
            count++;
        }while (ok==0 || count==n.size());
        System.out.println(n.getVertex(randApos).getAposento());
        manualGame(n, dif, current, vida,utilizador);
    }

    /**
     * Metodo do jogo manual
     * @param nAux
     * @param dif
     * @param current
     * @param vida
     */
    private void manualGame(Network nAux, int dif, Espaco current,Double vida, String utilizador){
        try {
            if (vida > 0) {
                currentEspLabel.setText("*****Estás no/a " + current.getAposento() + "*****");
                vidaLabel.setText(vida.toString());
                ArrayIterator it = (ArrayIterator)nAux.adjVertex(current);
                doorPanel.removeAll();
                while (it.hasNext()) {
                    Espaco espaco = (Espaco) it.next();
                    if (espaco.getAposento().equals("exterior")) {
                        JButton b = new JButton();
                        b.setText(espaco.getAposento());
                        b.setBackground(Color.BLACK);
                        b.setForeground(Color.GREEN);
                        b.setHorizontalAlignment(SwingConstants.LEFT);
                        b.setVerticalAlignment(SwingConstants.TOP);
                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                cLayout.show(cardLayout, "manual");
                                if (vida > 0) {
                                    Classificacao c=new Classificacao(mapa.getNome(), utilizador, vida);
                                    addToClassification(c);
                                    statusLabel.setText("Conseguiste sair!!!! Com " + vida + " de vida restante.");
                                } else {
                                    statusLabel.setText("Perdes-te!!! Ficaste sem vida!");
                                }
                            }
                        });
                        doorPanel.add(b);
                    } else {
                        JButton b = new JButton();
                        b.setText(espaco.getAposento());
                        b.setForeground(Color.RED);
                        b.setHorizontalAlignment(SwingConstants.LEFT);
                        b.setVerticalAlignment(SwingConstants.TOP);
                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                Espaco current1 = espaco;
                                if (current1.equals(n.getVertex(randApos))){
                                    escudo=randEscudo;
                                }
                                int vidaNeg=0;
                                if (current1.getFantasma()>0) {
                                    if (current1.getFantasma() > escudo) {
                                        vidaNeg = (current1.getFantasma() - escudo) * dif;
                                        escudo = 0;
                                    } else {
                                        vidaNeg = 0;
                                        escudo = escudo - current1.getFantasma();
                                    }
                                }
                                System.out.println(current1.getFantasma());
                                System.out.println(escudo);
                                Double vida1 = vida - vidaNeg;
                                manualGame(nAux,dif, current1, vida1,utilizador);
                            }
                        });
                        doorPanel.add(b);
                        }
                    }
                doorPanel.validate();
                doorPanel.repaint();
            } else {
                cLayout.show(cardLayout, "manual");
                statusLabel.setText("Perdes-te!!! Ficaste sem vida!");
            }
        }catch (NullPointerException e){

        }
    }

    /**
     * Método responsável por adicionar um jogador à tabela classificativa
     *
     * @param cl classificação a ser adicionada
     * @return true or false
     */
    private boolean addToClassification(Classificacao cl) {
        readCSVFile();
        File file = new File("files/Classificacoes.csv");

        try {
            classif.addToRear(cl);
            //Escrever no ficheiro
            String str = "Mapa,Utilizador,Vida\n";
            FileWriter fw = new FileWriter(file);

            ArrayIterator it=(ArrayIterator) classif.iterator();
            while (it.hasNext()) {
                Classificacao cla=(Classificacao) it.next();
                str += cla.getMapa() + "," + cla.getUtiliador() + "," + cla.getVida() + "\n";
            }
            fw.write(str);
            fw.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método responsável por ler o ficheiro CSV das tabelas classificativas
     */
    public void readCSVFile() {
        File file = new File("files/Classificacoes.csv");

        try {
            Scanner reader = new Scanner(file);
            reader.nextLine();//passa header a frente
            classif = new ArrayUnorderedList<>();

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] dados = data.split(",");
                classif.addToRear(new Classificacao(dados[0], dados[1], Double.parseDouble(dados[2])));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que devolve a melhor solução para este mapa
     */
    private void simulacao(){
        int i=0;

        try {
            ArrayIterator it =(ArrayIterator) n.iteratorShortestPath(n.getVertex("entrada"), n.getVertex("exterior"));
            //ArrayIterator it2 = (ArrayIterator) n.iteratorShortestPath(n.getVertex("entrada"), n.getVertex("exterior"));
            //ArrayIterator it3 = (ArrayIterator) n.iteratorShortestPath(n.getVertex("entrada"), n.getVertex("exterior"));
            //ArrayIterator it4 = (ArrayIterator) n.iteratorShortestPath(n.getVertex("entrada"), n.getVertex("exterior"));

            i = 1;
            simulacaoCard.removeAll();
            JPanel panel = new JPanel();
            BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(boxlayout);
            panel.setBackground(Color.BLACK);

            while (it.hasNext()) {
                Espaco espaco = (Espaco) it.next();
                JLabel jl = new JLabel(i + "a paragem: " + espaco.getAposento());
                jl.setForeground(Color.RED);
                jl.setFont(new Font("Serif", Font.PLAIN, 16));
                panel.add(jl);
                i++;
            }

            JLabel jl = new JLabel(" ");
            jl.setForeground(Color.RED);
            jl.setFont(new Font("Serif", Font.PLAIN, 36));
            panel.add(jl);
          /*  double vidaF = mapa.getPontos() - n.calculatePathWeigth(it2);
            double vidaM = mapa.getPontos() - (n.calculatePathWeigth(it3)*2);
            double vidaD = mapa.getPontos() - (n.calculatePathWeigth(it4)*3);
            JLabel jl1 = new JLabel("Vida- Fácil:" + vidaF+" | Médio:"+vidaM+" | Dificil:"+vidaD);
            jl1.setForeground(Color.RED);
            jl1.setFont(new Font("Serif", Font.PLAIN, 20));
            panel.add(jl1);*/
            simulacaoCard.add(panel);
            simulacaoCard.validate();
            simulacaoCard.repaint();
        }catch (NullPointerException e){

        }
    }

    private void verMapa(){
        verMapa.removeAll();
        verMapa.add(n.verMapa());
        verMapa.validate();
        verMapa.repaint();
    }

    /**
     * Metodo que mostra o TOP 10 das pontuaçoes deste mapa
     */
    private void classificacao(){
        readCSVFile();
        classif=orderClassif();
        ArrayIterator it=(ArrayIterator)classif.iterator();
        classifCard.removeAll();
        JPanel panel= new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setBackground(Color.BLACK);
        JLabel jl3=new JLabel("TOP 10");
        jl3.setForeground(Color.RED);
        jl3.setFont(new Font("Serif", Font.PLAIN, 72));
        panel.add(jl3);
        JLabel jl4=new JLabel(" ");
        jl4.setForeground(Color.RED);
        jl4.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(jl4);
        JLabel jl=new JLabel(mapa.getNome());
        jl.setForeground(Color.RED);
        jl.setFont(new Font("Serif", Font.PLAIN, 48));
        panel.add(jl);
        JLabel jl21=new JLabel(" ");
        jl21.setForeground(Color.RED);
        jl21.setFont(new Font("Serif", Font.PLAIN, 10));
        panel.add(jl21);
        JLabel jl1=new JLabel("Utilizador - Vida");
        jl1.setForeground(Color.RED);
        jl1.setFont(new Font("Serif", Font.PLAIN, 36));
        panel.add(jl1);
        JLabel jl11=new JLabel(" ");
        jl11.setForeground(Color.RED);
        jl1.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(jl11);
        int i=0;
        while(it.hasNext() && i<10){
            Classificacao cl=(Classificacao) it.next();
            if (cl.getMapa().equals(mapa.getNome())){
                Double vd=cl.getVida();
                String util=cl.getUtiliador();
                String str=util+" - "+vd;
                JLabel jl2=new JLabel(str);
                jl2.setForeground(Color.RED);
                jl2.setFont(new Font("Serif", Font.PLAIN, 20));
                panel.add(jl2);
                i++;
            }
        }
        classifCard.add(panel);
        classifCard.validate();
        classifCard.repaint();
    }

    /**
     * Método responsável por ordenar a tabela de classificações
     *
     * @return um ArrayUnorderList com os users ordenados
     * @throws EmptyCollectionException se não existirem users nas classificações
     */
    public ArrayUnorderedList<Classificacao> orderClassif() throws EmptyCollectionException {
        ArrayUnorderedList<Classificacao> tabela = new ArrayUnorderedList<>();
        ArrayOrderedList<Double> vida = new ArrayOrderedList<>();

        readCSVFile();

        ArrayIterator it = (ArrayIterator) classif.iterator();
        while (it.hasNext()) {
            Classificacao cl = (Classificacao) it.next();
            vida.add(cl.getVida());
        }
        ArrayUnorderedList<Integer> contemNaTabela = new ArrayUnorderedList<>();

        Classificacao[] classificacaos;

        ArrayIterator it2 = (ArrayIterator) classif.iterator();
        int j = 0;
        Classificacao[] temp = new Classificacao[classif.size()];
        while (it2.hasNext()) {
            Classificacao cla = (Classificacao) it2.next();
            temp[j] = cla;
            j++;
        }
        classificacaos = temp;

        for (int i = 0; i < classificacaos.length; ) {
            if (!contemNaTabela.contains(i) && vida.last() == classificacaos[i].getVida()) {
                contemNaTabela.addToRear(i);
                vida.removeLast();

                tabela.addToRear(classificacaos[i]);
                i = 0;
            } else {
                i++;
            }
        }
        return tabela;
    }

}
