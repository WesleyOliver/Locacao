package br.edu.faculdadedelta.locacao.modelo.types;

public enum Sexo {

	MASCULINO("Masculino"), FEMININO("Feminino");

	private String descricao;

	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
