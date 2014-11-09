package br.ufg.inf.redes.controle;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import br.ufg.inf.redes.entidades.Email;
import br.ufg.inf.redes.interpretador.TipoOperacao;

/** Implementação não está completa. Vamos implementar melhor na próxima. */
public class Reencaminhador {

	static byte[] buffer = new byte[4096];

	public void encaminharParaOutroSMTP(Email email) throws Exception {

		String mxSender = email.identificarDominio(email.getDestinatario());
		String host;

		switch (mxSender) {
		case "grupo8-r2.inf.ufg.br":
			host = "smtp.grupo8.inf.ufg.br";
			break;
		case "grupo8-mv3.inf.ufg.br":
			host = "smtp2.grupo8.inf.ufg.br";
			break;
		default:
			host = "localhost";
			break;
		}

		Socket socket = new Socket(host, 25);

		OutputStream paraServidor = socket.getOutputStream();
		InputStream doServidor = socket.getInputStream();

		doServidor.read(buffer);

		String resposta = new String(buffer);
		if (!resposta.startsWith("220"))
			throw new Exception("Falha no Helo");

		buffer = ("HELO" + host).getBytes(); // Mandando um Hello para o servidor

		paraServidor.write(buffer);

		doServidor.read(buffer);

		resposta = new String(buffer);
		if (!resposta.startsWith("250") && !resposta.equals("250"))
			throw new Exception("Falha no Helo");

		buffer = ("MAIL FROM: " + email.getRemetente()).getBytes();
		paraServidor.write(buffer);

		doServidor.read(buffer);

		resposta = new String(buffer);
		if (!resposta.startsWith("220") && !resposta.equals("220"))
			throw new Exception("Falha no MAIL FROM");

		while (buffer.length != 0) {
			doServidor.read(buffer);
		}

		buffer = ("RCPT TO: " + email.getDestinatario()).getBytes();
		paraServidor.write(buffer);

		doServidor.read(buffer);

		resposta = new String(buffer);
		if (!resposta.startsWith("220") && !resposta.equals("220"))
			throw new Exception("Falha no RCPT TO");

		while (buffer.length != 0) {
			doServidor.read(buffer);
		}

		buffer = "data".getBytes();
		paraServidor.write(buffer);

		doServidor.read(buffer);

		resposta = new String(buffer);
		if (!resposta.startsWith("354"))
			throw new Exception("Falha no inicio do DATA");

		while (buffer.length != 0) {
			doServidor.read(buffer);
		}

		buffer = ("from: " + email.getRemetente() + "\nto: "
				+ email.getDestinatario() + "\nsubject: " + email.getAssunto()
				+ "\n" + email.getMensagem() + "\n.\n\n").getBytes();

		paraServidor.write(buffer);

		doServidor.read(buffer);
		resposta = new String(buffer);
		if (!resposta.startsWith("250"))
			throw new Exception("Falha no envio da mensagem");

		while (buffer.length != 0) {
			doServidor.read(buffer);
		}

		buffer = "quit".getBytes();
		paraServidor.write(buffer);

		doServidor.read(buffer);
		resposta = new String(buffer);
		if (!resposta.startsWith("250"))
			throw new Exception("Falha ao fechar conexão remota");

		socket.close();
	}
}
