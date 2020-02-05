package GUI;

import Estruturas.ArrayOrderedList;
import Estruturas.ArrayUnorderedList;
import Estruturas.Network;
import Estruturas.Mapa;
import Estruturas.Espaco;
import Estruturas.Classificacao;

import Estruturas.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

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

    private ArrayUnorderedList<Classificacao> classif=new ArrayUnorderedList<>();
    private Espaco current=new Espaco("", 0, new ArrayUnorderedList<>());
    private ArrayUnorderedList<Mapa> mapas=new ArrayUnorderedList<>();
    private FileManager fm=new FileManager();
    private Network n;
    private Mapa mapa=new Mapa(null,0,null);
    private Espaco[] espacos;

    private CardLayout cLayout;

    /**
     * Construtor que contem todos os listenenrs dos botoes da aplicação
     * Neste metodo é inserido um mapa "default"
     */
    public MenuGUI() {
        inserirMapa("mapa.json");



        add(Panel);
        cardLayout.add(insertCard, "insert");
        cardLayout.add(chooseCard, "choose");
        cardLayout.add(manualCard, "manual");
        cardLayout.add(manualCard1, "manual1");
        cardLayout.add(simulacaoCard, "simul");
        cardLayout.add(classifCard, "classif");
        cardLayout.add(homeCard, "home");

        imageHome.setIcon(new ImageIcon("images/backgroundHome.png"));

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
                cLayout.show(cardLayout,"manual1");
                manualGame(1,current,100.00);

            }
        });
        medioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cLayout.show(cardLayout,"manual1");
                manualGame(2,current,100.00);

            }
        });
        dificilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cLayout.show(cardLayout,"manual1");
                manualGame(3,current,100.00);
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
    }

    /**
     * Metodo para inserir o mapa, se já não existir um mapa com o mesmo nome
     * Ainda chama para calcular o grafo e adiciona o mapa á lista de mapas
     * @param path
     */
    private void inserirMapa(String path){
        if (!mapas.contains(fm.readFile(path))){
            mapa = fm.readFile(path);
            espacos=mapa.getMapa();
            setNetGraph();
            mapas.addToRear(mapa);
        }
    }

    /**
     * Instancia o grafo para este mapa
     */
    private void setNetGraph(){
        n=new Network();
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

        for(Espaco esp:mapa.getMapa()){
            Iterator iterator=esp.getLigacoes().iterator();
            while (iterator.hasNext()){
                String apos=(String)iterator.next();
                if (apos.equals("entrada")){
                    current=esp;
                }
            }
        }
    }

    /**
     * Metodo para escolher o mapa que quer jogar
     */
    private void chooseMap(){
        Iterator it=mapas.iterator();
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
                    setNetGraph();
                }
            });
            mapasPanel.add(b);
        }
        mapasPanel.validate();
        mapasPanel.repaint();
    }

    /**
     * Método do jogo em manual
     * @param dif
     * @param current
     * @param vida
     */
    private void manualGame( int dif, Espaco current,Double vida){
        try {
            if (vida > 0) {
                currentEspLabel.setText("*****Estás no/a " + current.getAposento() + "*****");
                vidaLabel.setText(vida.toString());
                Iterator it = n.adjVertex(current);
                doorPanel.removeAll();
                while (it.hasNext()) {
                    Espaco espaco = (Espaco) it.next();
                    if (espaco.getAposento().equals("exterior")) {
                        ImageIcon img = new ImageIcon("images/doorExt.png");
                        JButton b = new JButton(img);
                        b.setText(espaco.getAposento());
                        b.setBackground(Color.BLACK);
                        b.setForeground(Color.RED);
                        b.setHorizontalAlignment(SwingConstants.LEFT);
                        b.setVerticalAlignment(SwingConstants.TOP);
                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                cLayout.show(cardLayout, "manual");
                                if (vida > 0) {
                                    addToClassif(vida);
                                    statusLabel.setText("Conseguiste sair!!!! Com " + vida + " de vida restante.");
                                } else {
                                    statusLabel.setText("Perdes-te!!! Ficaste sem vida!");
                                }
                            }
                        });
                        doorPanel.add(b);
                    } else {
                        if (espaco.getFantasma() > 0) {
                            ImageIcon img = new ImageIcon("images/doorFant.png");
                            JButton b = new JButton(img);
                            b.setText(espaco.getAposento());
                            b.setForeground(Color.RED);
                            b.setHorizontalAlignment(SwingConstants.LEFT);
                            b.setVerticalAlignment(SwingConstants.TOP);
                            b.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    Espaco current1 = espaco;
                                    Double vida1 = vida - (current1.getFantasma() * dif);
                                    manualGame(dif, current1, vida1);
                                }
                            });
                            doorPanel.add(b);
                        } else {
                            ImageIcon img = new ImageIcon("images/door.png");
                            JButton b = new JButton(img);
                            b.setText(espaco.getAposento());
                            b.setBackground(Color.BLACK);
                            b.setForeground(Color.RED);
                            b.setHorizontalAlignment(SwingConstants.LEFT);
                            b.setVerticalAlignment(SwingConstants.TOP);
                            b.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    Espaco current1 = espaco;
                                    Double vida1 = vida - (current1.getFantasma() * dif);
                                    manualGame(dif, current1, vida1);
                                }
                            });
                            doorPanel.add(b);
                        }
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
     * Metodo para adicionar a pontuação á classificaçao
     * @param vida
     */
    private void addToClassif(Double vida){
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
    }

    /**
     * Metodo que devolve a melhor solução para este mapa
     */
    private void simulacao(){
        int i=0;

        try {
            Iterator it = n.iteratorShortestPath(n.getVertex("entrada"), n.getVertex("exterior"));
            Iterator it2 = n.iteratorShortestPath(n.getVertex("entrada"), n.getVertex("exterior"));
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
                jl.setFont(new Font("Serif", Font.PLAIN, 36));
                panel.add(jl);
                i++;
            }

            JLabel jl = new JLabel(" ");
            jl.setForeground(Color.RED);
            jl.setFont(new Font("Serif", Font.PLAIN, 36));
            panel.add(jl);
            double vida = mapa.getPontos() - n.calculatePathWeigth(it2);
            JLabel jl1 = new JLabel("Vida : " + vida);
            jl1.setForeground(Color.RED);
            jl1.setFont(new Font("Serif", Font.PLAIN, 36));
            panel.add(jl1);
            simulacaoCard.add(panel);
            simulacaoCard.validate();
            simulacaoCard.repaint();
        }catch (NullPointerException e){

        }
    }

    /**
     * Metodo que mostra o TOP 10 das pontuaçoes deste mapa
     */
    private void classificacao(){
        Iterator it=classif.iterator();
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
        jl4.setFont(new Font("Serif", Font.PLAIN, 36));
        panel.add(jl4);
        JLabel jl=new JLabel(mapa.getNome());
        jl.setForeground(Color.RED);
        jl.setFont(new Font("Serif", Font.PLAIN, 48));
        panel.add(jl);
        JLabel jl1=new JLabel(" ");
        jl1.setForeground(Color.RED);
        jl1.setFont(new Font("Serif", Font.PLAIN, 36));
        panel.add(jl1);
        int i=0;
        while(it.hasNext()){
            Classificacao cl=(Classificacao) it.next();
            if (cl.getMapa().equals(mapa)){
                Iterator it1=cl.getVida().iterator();
                while (it1.hasNext()&&i<10){
                    Double vd=(Double) it1.next();
                    JLabel jl2=new JLabel(vd.toString());
                    jl2.setForeground(Color.RED);
                    jl2.setFont(new Font("Serif", Font.PLAIN, 24));
                    panel.add(jl2);
                    i++;
                }
            }
        }
        classifCard.add(panel);
        classifCard.validate();
        classifCard.repaint();
    }
}
