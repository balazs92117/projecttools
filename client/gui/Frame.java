package client.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
//import client.gui.logic;
        
//Az ablak megvalósítása, származtatás JFrame osztályból
public class Frame extends JFrame{
    //Változók
    
    private QuizGameLogic logic;
    private int selected=0;
    private int correct=0;
    private int wrong=0;
    
    private Button a= new Button("A", 0);
    private Button b= new Button("B", 1);
    private Button c= new Button("C", 2);
    private Button d= new Button("D", 3);
    
    private ActionListener listenerA;
    private ActionListener listenerB;
    private ActionListener listenerC;
    private ActionListener listenerD;
    private ActionListener startAction;
    private ActionListener closeAction;
    
    private JLabel question=new JLabel();
    
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<String> anwsers = new ArrayList<>();
    
    public Frame(){
        initFrame();
        hookActionListeners();
        setButtons();
        setMenu();
        try {
            logic = new QuizGameLogic(this);
        } catch (Exception ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        correct=0;
        wrong=0;
        isGameEnd(false);
    }
    //Menü létrehozása, beállítása
    private void setMenu(){
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem newGame = new JMenuItem("Új játék");
        JMenuItem closeGame = new JMenuItem("Bezárás");
        newGame.addActionListener(startAction);
        closeGame.addActionListener(closeAction);
        
        file.add(newGame);
        file.addSeparator();
        file.add(closeGame);
        
        menu.add(file);
        setJMenuBar(menu);  
    }
    
    //Gombok beállítása
    private void setButtons(){
        buttons.add(a);
        buttons.add(b);
        buttons.add(c);
        buttons.add(d);
        
    }
    //Ablak tulajdonságainak beállítása
    private void initFrame() {
        setTitle("QUIZ");
        setSize(new Dimension(850, 850));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
   //Vége van-e a játéknak
    public boolean isGameEnd(boolean i){
        return false;
    }
    //Események lekezelése
    private void hookActionListeners() {
        //Játék indítás esemény
        startAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 System.out.println("NEW GAME!");
                try {
                    newGame();
                } catch (IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        //Bezárás esemény
        closeAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.exitServer();
                System.exit(0);
            }
        };
        //"A" választás esemény
        listenerA = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click A!");
                selected = 0;
                try {
                    result(logic.correctAnswer(0));
                }catch(IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        //"B választás esemény"
        listenerB = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click B!");
                selected = 1;
                try {
                    result(logic.correctAnswer(0));
                }catch(IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        //"C" választás esemény
        listenerC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click C!");
                selected = 2;
                try {
                    result(logic.correctAnswer(0));
                }catch(IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        //"D" választás esemény
        listenerD = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click D!");
                selected = 3;
                try {
                    result(logic.correctAnswer(0));
                }catch(IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }
    //Eredmény 
    private void result(boolean b) throws IOException{
    }
    
    //Új játék indítása
    private void newGame() throws IOException{
        
        for(Button b : buttons)
        {
            b.setEnabled(true);
        }
        correct = 0;
        wrong = 0;
    }
}
