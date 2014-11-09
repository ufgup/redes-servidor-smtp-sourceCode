package br.ufg.inf.redes.persistencia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import br.ufg.inf.redes.entidades.Email;
import br.ufg.inf.redes.persistencia.utilitarios.PropertiesManipulador;

public class GeradorProperties {

	private static final String CAMINHO_PADRAO = "~/emails/";
	private Properties props;

	private void gerarProperties(Email mail) {
		this.props = PropertiesManipulador.criarProperties(mail);
	}

	public void gravarLocalSender(Email mail) throws IOException {

		gerarProperties(mail);

		StringBuilder caminho = new StringBuilder();

		caminho.append( CAMINHO_PADRAO );
		caminho.append( identificarNome("mail.remetente") );

		criaDiretorios( caminho.toString() );

		caminho.append("/enviados");

		criaDiretorios( caminho.toString() );

		caminho.append( String.valueOf(mail.hashCode()) );
		caminho.append(".properties");

		salvarProperties( caminho.toString() );



	}

	public void gravarLocalReceiver(Email mail) throws IOException {

		gerarProperties(mail);

		StringBuilder caminho = new StringBuilder();

		caminho = new StringBuilder();

		caminho.append( CAMINHO_PADRAO );
		caminho.append( identificarNome("mail.destinatario") );

		caminho.append("/entrada");

		criaDiretorios( caminho.toString() );

		caminho.append( String.valueOf(mail.hashCode()) );
		caminho.append(".properties");

		salvarProperties( caminho.toString() );
	}

	private String identificarNome(String keyPropertie) {

		String nome = this.props.getProperty(keyPropertie);
		String[] values = nome.split("@", 2);
		return values[0];

	}

	private void criaDiretorios(String path) {

		File diretorio = new File(path);
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
	}

	private void salvarProperties(String path) throws IOException {

		File arquivo = new File( path );
		FileOutputStream fos = new FileOutputStream(arquivo);

		props.store(fos, "#Arquivo de email");

		fos.flush();
		fos.close();

	}
}
