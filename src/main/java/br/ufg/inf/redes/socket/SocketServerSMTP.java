package br.ufg.inf.redes.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import br.ufg.inf.redes.interpretador.Interpretador;

public class SocketServerSMTP {

	private static final int PORTA = 14440;
	private ServerSocket serverSocket;
	private Interpretador interpretador;
	private boolean parado = false;

	private void iniciar() throws IOException {

		serverSocket = new ServerSocket( PORTA );
		interpretador = new Interpretador();
		Socket socket = serverSocket.accept();

		while(! parado) {

			InputStreamReader input = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(input);
			String comando = br.readLine();

			String retorno = interpretador.receberComando(comando);

			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);

			pw.println(retorno);

		}

		socket.close();


	}

	public static void main( String[] args ) {
		SocketServerSMTP sss = new SocketServerSMTP();

		try {
			sss.iniciar();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
