package client.logic;

import java.net.*;
import java.io.*;
import java.util.*;

public class QuizGameLogic {

	private String question;
	private String[] answers;
	private int correctIndex;
	private Frame gui;
	
	private Socket socket;
	private PrintWriter pw;
	private Scanner sc;
	
	 /**
     * A QuizGameLogic osztály konstruktora egy gui-t vár paraméterül
     * <p>
     * Létrehozza a kapcsolatot a szerverrel.
     * @param gui Frame példány
     * @throws Exception IOException
     */
	
	public QuizGameLogic(Frame gui) throws IOException {
		this.socket = new Socket("localhost", 11223);
        this.pw = new PrintWriter(socket.getOutputStream(), true);
        this.sc = new Scanner(socket.getInputStream());
		
		this.gui = gui;
	}
	
	/**
     * Lekér a szervertől egy kérdést és a hozzá tartozó válaszlehetőségeket, valamint a helyes válasz indexét.
     */
	private void getQuestionFromServer() {
		System.out.println("Client: Requesting question and answers from server");
		pw.println("REQUEST");
		this.question = sc.next();
		for(int i=0;i<4;i++) {
			this.answers[i] = sc.next();
		}
		this.correctIndex = sc.nextInt();
	}
	
	/**
     * Elküldi a kérdést és a hozzá tartozó válaszlehetőségeket a guinak.
     */
	private void setQuestionInGui() {
		gui.setNewQuestion(question, answers);
	}
	/**
	 * Új kérdést indít.
	 * Meghívja a getQuestionFromServer-t és a setQuestionInGui-t 
	 */
	public void gameQuestionSetup() {
		getQuestionFromServer();
		setQuestionInGui();
	}
	
	/**
     * Leellenőrzi, hogy helyes-e a válasz az adott kérdésre.
     * @param i a megadott válasz indexe
     * @return igaz vagy hamis, a válasz helyességétől függően
     */
	public boolean isCorrectAnswer(int i) {
		return (i == this.correctIndex);
	}
	
	/**
	 * Bontja a kapcsolatot a szerverrel.
	 */
	public void exitServer() throws IOException {
        socket.close();
    }
}