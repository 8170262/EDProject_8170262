import GUI.MenuGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MenuGUI menuGUI=new MenuGUI();
        menuGUI.setTitle("GAME");
        menuGUI.setVisible(true);
        menuGUI.setSize(1150,750);
        menuGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
