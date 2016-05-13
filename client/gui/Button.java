package client.gui;

import javax.swing.JButton;
//Gomb megvalósítása, származtatás JButton osztályból
public class Button extends JButton{
    
    private int azonosito;
    
    public Button(String szoveg , int azonosito){
        super(szoveg);
        this.azonosito=azonosito;
    }
    
    //gomb azonosítójának beállítása
    public void setAzonosito(int azon){
        this.azonosito=azon;
    }
    
    //gomb azonosítójának lekérdezése
    public int getAzonosito(){
        return this.azonosito;
    }
    
}
