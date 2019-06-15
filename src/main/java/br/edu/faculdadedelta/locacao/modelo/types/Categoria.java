package br.edu.faculdadedelta.locacao.modelo.types;

public enum Categoria {

	HATCH("Hatch"), SEDAN("Sedan"), UTILITARIO("Utilitario"), ESPORTIVO("Esportivo");

	private String descricao;

	private Categoria(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
