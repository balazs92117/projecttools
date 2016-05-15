package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kindelmann Balázs (G4XEAO) - kibtaai at inf dot elte dot hu
 */
public class QuizGameServer {

    static Socket clientSocket;
    static PrintWriter out;
    static BufferedReader in;
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
     * @param portNumber Serverportamin hallgat
     * @throws Exception IOException
     */
    private static boolean startSocket(int portNumber) {
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
    private static void answer() {
	String inputLine;
	try {
	    while ((inputLine = in.readLine()) != null) {
		if(inputLine.equals("REQUEST")) {
		    System.out.println("Server: Got REQUEST")
		    out.println("Kerdés 1|Valasz 1|Valasz 2|Valasz 3|Valasz 4|3|");
		}
	    }
	} catch (IOException ex) {
	    System.out.println("Server: Cant read through socket");
	}
    }
}
