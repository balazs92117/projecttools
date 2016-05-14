import java.net.*;
import java.io.*;

public class QuizGameLogic {

	private String question;
	private String[] answers;
	private int correctIndex;
	private Frame gui;
	
	private Socket socket;
	private PrintWriter pw;
	private Scanner sc;
	
	
	public QuizGameLogic(Frame gui) {
		this.socket = new Socket("localhost", 11223);
        this.pw = new PrintWriter(s.getOutputStream(), true);
        this.sc = new Scanner(s.getInputStream());
		
		this.gui = gui;
	}
	
	private void getQuestionFromServer() {
		pw.println("REQUEST");
		this.question = sc.next();
		for(int i=0;i<4;i++) {
			this.answers[i] = sc.next();
		}
		this.correctIndex = sc.nextInt();
	}
	
	private void setQuestionInGui() {
		//...	
	}
	
	private void gameQuestionSetup() {
		//...
	}
	
	public boolean isCorrectAnswer(int i) {
		//...
	}
}