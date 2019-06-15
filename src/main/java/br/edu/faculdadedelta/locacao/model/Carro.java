package br.edu.faculdadedelta.locacao.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo placa é obrigatório!")
	private String placa;

	@NotBlank(message = "O campo chassi é obrigatório!")
	private String chassi;

	@NotNull(message = "O campo valor da diária é obrigatório!")
	@Column(name = "valor_diaria", precision = 10, scale = 2)
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorDaDiaria;

	@ManyToOne
	@JoinColumn(name = "id_modelo")
	@NotNull(message = ("O campo modelo é obrigatório!"))
	private Modelo modelo;

	public Carro() {
		super();
	}

	public Carro(Long id, String placa, String chassi, BigDecimal valorDaDiaria, Modelo modelo) {
		super();
		this.id = id;
		this.placa = placa;
		this.chassi = chassi;
		this.valorDaDiaria = valorDaDiaria;
		this.modelo = modelo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public BigDecimal getValorDaDiaria() {
		return valorDaDiaria;
	}

	public void setValorDaDiaria(BigDecimal valorDaDiaria) {
		this.valorDaDiaria = valorDaDiaria;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Carro other = (Carro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
