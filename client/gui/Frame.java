package client.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.BevelBorder;

//Az ablak megvalósítása, származtatás JFrame osztályból
public class Frame extends JFrame {
    //Változók
    private QuizGameLogic logic;
    private int selected = 0;
    private int correct = 0;
    private int wrong = 0;
    private String correctA = "";

    private Button a = new Button("A", 0);
    private Button b = new Button("B", 1);
    private Button c = new Button("C", 2);
    private Button d = new Button("D", 3);
    
    private ActionListener listenerA;
    private ActionListener listenerB;
    private ActionListener listenerC;
    private ActionListener listenerD;
    private ActionListener startAction;
    private ActionListener closeAction;

    private JLabel question = new JLabel("Kérdés?");

    private final JLabel status = new JLabel();

    private ArrayList<Button> buttons = new ArrayList<>();
    private String[] anwsers;

    public Frame() {
        initFrame();
        hookActionListeners();
        setButtons();
        setMenu();
        setPageStart();
        setPageEnd();
        try {
            logic = new QuizGameLogic(this);
        } catch (Exception ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        correct = 0;
        wrong = 0;
        gameEnd(false);
    }

    //Menü létrehozása, beállítása
    private void setMenu() {
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
    public void setButtons() {
        buttons.add(a);
        buttons.add(b);
        buttons.add(c);
        buttons.add(d);
        for (Button qb : buttons) {
            qb.setFocusable(false);
        }
        buttonColor();

        buttons.get(0).addActionListener(listenerA);
        buttons.get(1).addActionListener(listenerB);
        buttons.get(2).addActionListener(listenerC);
        buttons.get(3).addActionListener(listenerD);

        this.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel quizPanel = new JPanel();
        JPanel scorePanel = new JPanel();

        quizPanel.setLayout(new GridLayout(2, 1));

        northPanel.setLayout(new FlowLayout());
        question.setPreferredSize(new Dimension(250, 250));
        question.setVerticalAlignment(JLabel.CENTER);
        question.setHorizontalAlignment(JLabel.CENTER);
        question.setForeground(Color.GRAY);
        northPanel.add(question);

        southPanel.setLayout(new GridLayout(2, 2));
        southPanel.add(buttons.get(0));
        southPanel.add(buttons.get(1));
        southPanel.add(buttons.get(2));
        southPanel.add(buttons.get(3));

        scorePanel.setLayout(new GridLayout(1, 1));
        scorePanel.setSize(100, 100);
        scorePanel.add(status);
        status.setHorizontalAlignment(JLabel.CENTER);

        quizPanel.add(northPanel);
        quizPanel.add(southPanel);

        this.add(quizPanel, BorderLayout.NORTH);
        this.add(scorePanel, BorderLayout.SOUTH);

        northPanel.setBackground(Color.GRAY);
    }

    //Ablak tulajdonságainak beállítása
    private void initFrame() {
        setTitle("QUIZ");
        setSize(new Dimension(650, 650));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    private void setPageStart() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel frameHeader = new JLabel("Quiz");
        Font font = new Font(Font.SERIF, Font.BOLD, 26);

        frameHeader.setFont(font);
        frameHeader.setForeground(Color.WHITE);
        panel.setBackground(Color.BLUE);
        panel.add(frameHeader);

        add(panel, BorderLayout.PAGE_START);
    }

    private void setPageEnd() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        status.setHorizontalAlignment(SwingConstants.LEFT);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        panel.setPreferredSize(new Dimension(getWidth(), 16));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(status);
        add(panel, BorderLayout.SOUTH);
    }

    //Események lekezelése
    private void hookActionListeners() {
        //Játék indítás esemény
        startAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Új játék!");
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
                    result(logic.iscorrectAnswer(0));
                } catch (IOException ex) {
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
                    result(logic.iscorrectAnswer(0));
                } catch (IOException ex) {
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
                    result(logic.iscorrectAnswer(0));
                } catch (IOException ex) {
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
                    result(logic.iscorrectAnswer(0));
                } catch (IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }
    //Eredmény 
    private void result(boolean b) throws IOException {
        if (b) {
            ++correct;
        } else {
            ++wrong;
        }
        status.setText("<html><font size=\"2\" color=\"green\">Jó: " + correct + "                      "
                + "      </font><font size=\"2\" color=\"red\">Rossz: " + wrong + "</font></html>");

        if (b) {
            JOptionPane.showMessageDialog(null, "Helyes válasz! ");

        } else {
            boolean b2 = false;
            int i = 0;
            while (!b2 && i < 4) {
                if (logic.iscorrectAnswer(i)) {
                    correctA = anwsers[i];
                }
                ++i;
            }
            JOptionPane.showMessageDialog(null, "Rossz válasz! \n A helyes válasz: " + correctA);
        }

        if (correct + wrong == 10) {
            gameEnd(true);
        } else {
            revalidate();
            repaint();
            init();
        }
    }

    //Új játék indítása
    private void newGame() throws IOException {
        for (Button b : buttons) {
            b.setEnabled(true);
        }
        buttonColor();
        correct = 0;
        wrong = 0;
        init();
    }

    //A játék vége, kiírja, mennyi helyes választ adott a játékos
    public void gameEnd(boolean i) {
        for (Button b : buttons) {
            b.setEnabled(false);
            b.setText("");
        }
        question.setText("");
        status.setText("");
        revalidate();
        repaint();

        if (i) {
            JOptionPane.showMessageDialog(null, "Gratulálok. Elért eredményed: " + getCorrect());
        }
    }

    //Jó válaszok lekérdezése
    public int getCorrect() {
        return this.correct;
    }

    //Kérdések, válaszok beállítása
    public void setNewQuestion(String questions, String[] answers) {
        this.anwsers = answers;
        question.setText("<html><div align=\"center\"><font size=\"5\">" + questions + "</font></div></html>");
        buttons.get(0).setText("<html><div align=\"center\">" + answers[0] + "</div></html>");
        buttons.get(1).setText("<html><div align=\"center\">" + answers[1] + "</div></html>");
        buttons.get(2).setText("<html><div align=\"center\">" + answers[2] + "</div></html>");
        buttons.get(3).setText("<html><div align=\"center\">" + answers[3] + "</div></html>");
        revalidate();
        repaint();
    }
    
    //kérdés inicializálása 4 válasszal
    private void init() throws IOException {
        status.setText("<html><font size=\"2\" color=\"green\">Jó: " + correct + "                      "
                + "</font><font size=\"2\" color=\"red\">Rossz: " + wrong + "</font></html>");
        buttonColor();
        revalidate();
        repaint();
        System.out.println("Kérdés: ");
        logic.newQuestionRequest();
    }
    
    //gombok színe
    private void buttonColor(){
        for(Button b : buttons){
            b.setBackground(Color.YELLOW);
            b.setForeground(Color.BLACK);
        }
    }
}
