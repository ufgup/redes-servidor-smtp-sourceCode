package br.ufg.inf.redes.entidades;

/**
 * Classe POJO do objeto Email cont&ecirc;m os atr&iacute;butos
 * necess&aacute;rios pra qualquer mensagem de email
 */
public class Email {

	private String remetente;
	private String destinatario;
	private String mensagem;
	private String assunto;

	/* Construtores de classe */
	public Email() {
		remetente = null;
		destinatario = null;
		mensagem = null;
		assunto = null;
	}

	public Email(String remetente, String destinatario, String mensagem,
			String assunto) {
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.mensagem = mensagem;
		this.assunto = assunto;
	}

	/* Metodos Getter e Setter */
	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	/**
	 * Valida se o email tem, minimamente o remetente, destinatario e um
	 * conte&uacute;do de texto
	 */
	public boolean estaProntoParaEnviar() {

		if (this.remetente != null && this.destinatario != null
				&& this.mensagem != null)
			return true;

		return false;
	}

	public String identificarDominio(String email) {

		String[] values = email.split("@", 2);
		return values[2];

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result
				+ ((destinatario == null) ? 0 : destinatario.hashCode());
		result = prime * result
				+ ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result
				+ ((remetente == null) ? 0 : remetente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (assunto == null) {
			if (other.assunto != null)
				return false;
		} else if (!assunto.equals(other.assunto))
			return false;
		if (destinatario == null) {
			if (other.destinatario != null)
				return false;
		} else if (!destinatario.equals(other.destinatario))
			return false;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (remetente == null) {
			if (other.remetente != null)
				return false;
		} else if (!remetente.equals(other.remetente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Email [remetente=" + remetente + "\n destinatario="
				+ destinatario + "\n assunto=" + assunto + "\n mensagem="
				+ mensagem + "]";
	}

}
