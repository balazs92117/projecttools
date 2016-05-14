import java.net.*;

public class QuizGameLogic {

	private String question;
	private String[] answers;
	private int correctIndex;
	private Frame gui;
	private Socket socket;
	
	public QuizGameLogic(Frame gui) {
		this.gui = gui;
	}
	
	private String getQuestionFromServer() {
		//...
		return question;
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