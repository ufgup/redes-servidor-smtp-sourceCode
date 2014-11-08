package br.ufg.inf.redes.persistencia.utilitarios;

import java.io.FileInputStream;
import java.util.Properties;

import br.ufg.inf.redes.entidades.Email;

public class PropertiesManipulador {

	public static Properties getProp( String file_properties ) {

		Properties props;

			try {

				props = new Properties(); //Inst&acirc;ncia um objeto Properties
				FileInputStream file = new FileInputStream( file_properties ); //Cria um objeto FileInputStream, passando como par&acirc;metro do construtor o endere&ccedil;o do arquivo de configura&ccedil;&atilde;o na m&aacute;quina
				props.load( file ); //Diz para o objeto de propriedade carregar o arquivo de FileInputStream

				return props;

			} catch(Exception e) {
				e.printStackTrace();
			}


		return null; //retorna a propriedade
	}

	public static Properties criarProperties() {

		Properties props = new Properties();

		props.setProperty("mail.remetente", null);
		props.setProperty("mail.destinatario", null);
		props.setProperty("mail.assunto", null);
		props.setProperty("mail.mensagem", null);

		return props;
	}

	public static Properties criarProperties(Email mail) {

		Properties props = new Properties();

		props.setProperty("mail.remetente", mail.getRemetente());
		props.setProperty("mail.destinatario", mail.getDestinatario());
		props.setProperty("mail.assunto", mail.getAssunto());
		props.setProperty("mail.mensagem", mail.getMensagem());

		return props;
	}

}