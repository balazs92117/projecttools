package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Kindelmann Balázs (G4XEAO) - kibtaai at inf dot elte dot hu
 */
public class QuizGameServer {

    static Socket clientSocket;
    static PrintWriter out;
    static BufferedReader in;
    
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";  
    static final String DB_URL = "jdbc:derby://localhost/projecttools";

    //  Database credentials
    static final String USER = "projecttools";
    static final String PASS = "projecttools";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	if(startSocket(11223)) {
	    answer();
	}
    }
    /**
     * Elindítja a szervert
     * @param portNumber Serverport amire bindol
     * @throws Exception IOException
     * @return boolean Sikerult-e letrehozni a szervert
     */
    private static boolean startSocket(int portNumber) {
	getRandomQuestion();
	try {
	    ServerSocket serverSocket = new ServerSocket(portNumber);
	    clientSocket = serverSocket.accept();
	    out = new PrintWriter(clientSocket.getOutputStream(), true);
	    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    return true;
	} catch (IOException ex) {
	    System.out.println("Server: Can not bind to port #"+portNumber);
	    return false;
	}
    }
    /**
     * Vár a REQUESTkérésekre, és átküldi a kliensnek a választ kérésesetén.
     * @throws Exception IOException
     */
    private static void answer() {
	String inputLine;
	try {
	    while ((inputLine = in.readLine()) != null) {
		if(inputLine.equals("REQUEST")) {
		    System.out.println("Server: Got REQUEST");
		    out.println(getRandomQuestion());
		}
	    }
	} catch (IOException ex) {
	    System.out.println("Server: Cant read through socket");
	}
    }
    /**
     * Kapcsolódik az adatbázishoz, és egy random kérdés+választ kiszedbelőle
     * @return String random kérdés azadatbázisból
     */
    private static String getRandomQuestion() {
	String finalQuestion = null;
	Connection conn = null;
	Statement stmt = null;
	try{
	    //STEP 2: Register JDBC driver
	    Class.forName(JDBC_DRIVER);

	    //STEP 3: Open a connection
	    conn = DriverManager.getConnection(DB_URL, USER, PASS);

	    //STEP 4: Execute a query
	    stmt = conn.createStatement();

	    String sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER() AS rownum1, tmp.* FROM (SELECT PROJECTTOOLS.QUESTIONS.* FROM PROJECTTOOLS.QUESTIONS ORDER BY RANDOM()) AS tmp) AS tmp2 WHERE rownum1 <= 1";
	    ResultSet rs = stmt.executeQuery(sql);
	    //STEP 5: Extract data from result set
	    while(rs.next()){
		//Retrieve by column name
		String question  = rs.getString("question");
		String answer1  = rs.getString("answer1");
		String answer2  = rs.getString("answer2");
		String answer3  = rs.getString("answer3");
		String answer4  = rs.getString("answer4");
		int correctanswer = rs.getInt("correctanswer");
		//Display values
		finalQuestion = question+"|"+answer1+"|"+answer2+"|"+answer3+"|"+answer4+"|"+correctanswer+"|";
	    }
	    rs.close();
	} catch(SQLException se){
	    //Handle errors for JDBC
	    se.printStackTrace();
	} catch(Exception e){
	    //Handle errors for Class.forName
	    e.printStackTrace();
	} finally {
	//finally block used to close resources
	    try{
		if(stmt!=null)
		    conn.close();
	    } catch(SQLException se){
		se.printStackTrace();
	    }
	    try {
		if(conn!=null)
		    conn.close();
	    } catch(SQLException se){
		se.printStackTrace();
	    }//end finally try
	}//end try
	return finalQuestion;
    }
}
