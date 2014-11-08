package br.ufg.inf.redes.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import br.ufg.inf.redes.interpretador.Interpretador;

public class SocketServerSMTP {

	private static final int PORTA = 25;
	private byte[] buffer = new byte[1024];
	private static Interpretador interpretador = new Interpretador();

	public void iniciar() throws IOException {

		DatagramSocket socketServidor = new DatagramSocket( PORTA );

		while(true) {
			DatagramPacket pacoteRecebido = new DatagramPacket(buffer, buffer.length);
			socketServidor.receive( pacoteRecebido );

			String comando = pacoteRecebido.getData().toString();

			String retorno = interpretador.receberComando(comando);

			InetAddress endereco = pacoteRecebido.getAddress();
			int porta = pacoteRecebido.getPort();

			buffer = retorno.getBytes();

			DatagramPacket pacoteEnviado = new DatagramPacket(buffer, buffer.length, endereco, porta);

		}
	}
}
