package br.ufg.inf.redes.testes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerClient {

	private static boolean parado = false;


	public static void main(String[] args) throws IOException {

		byte[] buffer = new byte[1024];
		Scanner entrada = new Scanner( System.in );
		String comando;
		String retorno;

		while(!parado) {
			comando = entrada.nextLine();

			DatagramSocket socketCliente = new DatagramSocket(); //Cria um socket cliente
			InetAddress enderecoServer = InetAddress.getByName("localhost");

			buffer = comando.getBytes();

			DatagramPacket pacoteEnviado = new DatagramPacket(buffer, buffer.length, enderecoServer, 14440);
			socketCliente.send( pacoteEnviado );


			DatagramPacket pacoteRecebido = new DatagramPacket(buffer, buffer.length);
			socketCliente.receive( pacoteRecebido );

			retorno = new String( pacoteRecebido.getData() );

			System.out.println(retorno);

			socketCliente.close();

		}
	}

}
