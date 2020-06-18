package model.entities;

import java.io.Serializable;
import java.util.Date;

public final class Parametro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codEmpresa;
	private String codEstab;
	private String razaoSocial;
	private Date dataInicial;
	private Date dataFinal;
	
	public Parametro() {
	}

	public Parametro(String codEmpresa, 
			         String codEstab, 
			         String razaoSocial,
			         Date dataInicial,
			         Date dataFinal) {
		this.codEmpresa = codEmpresa;
		this.codEstab = codEstab;
		this.razaoSocial = razaoSocial;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getCodEstab() {
		return codEstab;
	}

	public void setCodEstab(String codEstab) {
		this.codEstab = codEstab;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}