package client.gui;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
//import client.gui.logic;
        
//Az ablak megvalósítása, származtatás JFrame osztályból
public class Frame extends JFrame{
    //Változók
    
    //private QuizGameLogic logic;
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
    
    private JLabel question=new JLabel();
    
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<String> anwsers = new ArrayList<>();
    
    public Frame(){
        
    }
    
}
