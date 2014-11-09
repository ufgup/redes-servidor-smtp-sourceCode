package br.ufg.inf.redes.controle;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import br.ufg.inf.redes.entidades.Email;

public class Reencaminhador {

	static byte[] buffer = new byte[4096];

	public void encaminharParaOutroSMTP(Email email) throws Exception {

		String mxSender = email.identificarDominio(email.getDestinatario());

		Socket socket = new Socket(
				ListaServersSMTP.valueOf(mxSender).getHost(), 25);

		OutputStream paraServidor = socket.getOutputStream();
		InputStream doServidor = socket.getInputStream();

		buffer = "HELO".getBytes(); // Mandando um Hello para o servidor

		paraServidor.write(buffer);

		doServidor.read(buffer);

		String resposta = new String(buffer);
		if (!resposta.startsWith("250"))
			throw new Exception("Falha no Helo");

		buffer = ("MAIL FROM: " + email.getRemetente()).getBytes();
		paraServidor.write(buffer);

		resposta = new String(buffer);
		if (!resposta.startsWith("250"))
			throw new Exception("Falha no MAIL FROM");

		buffer = ("RCPT TO: " + email.getDestinatario()).getBytes();
		paraServidor.write(buffer);

		resposta = new String(buffer);
		if (!resposta.startsWith("250"))
			throw new Exception("Falha no RCPT TO");

		buffer = "data".getBytes();
		paraServidor.write(buffer);

		resposta = new String(buffer);
		if (!resposta.startsWith("354"))
			throw new Exception("Falha no inicio do DATA");

		buffer = ("from: " + email.getRemetente() +
					"\nto: " + email.getDestinatario() +
					"\nsubject: " + email.getAssunto() +
					"\n" + email.getMensagem() +
					"\n.\n\n").getBytes();

		paraServidor.write(buffer);

		resposta = new String(buffer);
		if(! resposta.startsWith("250") )
			throw new Exception("Falha no envio da mensagem");


		buffer = "quit".getBytes();
		paraServidor.write(buffer);

		resposta = new String(buffer);
		if(! resposta.startsWith("250") )
			throw new Exception("Falha ao fechar conexão remota");

	}

}
