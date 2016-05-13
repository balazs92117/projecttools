package client.gui;

import javax.swing.JButton;

public class Button extends JButton{
    
    private int azonosito;
    
    public Button(String szoveg , int azonosito){
        super(szoveg);
        this.azonosito=azonosito;
    }
    
    public void setAzonosito(int azon){
        this.azonosito=azon;
    }
    
    public int getAzonosito(){
        return this.azonosito;
    }
    
}
