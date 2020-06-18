package model.entities;

import java.io.Serializable;
import java.util.Date;

public class PrimaryKeyChange implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cod_empresa;
	private String cod_estab;
	private Date data_fiscal;
	private Date data_emissao;
	private String movto_e_s;
	private String norm_dev;
	private String cod_fis_jur;
	private String num_docfis;
	private String serie_docfis;
	private String sub_serie_docfis;
	private String cod_empresa_aj;
	private String cod_estab_aj;
	private Date data_fiscal_aj;
	private Date data_emissao_aj;
	private String movto_e_s_aj;
	private String norm_dev_aj;
	private String cod_fis_jur_aj;
	private String num_docfis_aj;
	private String serie_docfis_aj;
	private String sub_serie_docfis_aj;
	private String cod_class_docfis_aj;
	private String cod_docto_aj;
	private String cod_modelo_aj;
	private String cod_modelo_cotepe_aj;
	private String num_autentic_nfe_aj;

	public PrimaryKeyChange() {
	}

	public PrimaryKeyChange(String cod_empresa, 
			                String cod_estab, 
			                Date data_fiscal, 
			                Date data_emissao, 
			                String movto_e_s,
			                String norm_dev, 
			                String cod_fis_jur, 
			                String num_docfis, 
			                String serie_docfis, 
			                String sub_serie_docfis,
			                String cod_empresa_aj, 
			                String cod_estab_aj, 
			                Date data_fiscal_aj, 
			                Date data_emissao_aj, 
			                String movto_e_s_aj,
			                String norm_dev_aj, 
			                String cod_fis_jur_aj, 
			                String num_docfis_aj, 
			                String serie_docfis_aj,
			                String sub_serie_docfis_aj, 
			                String cod_class_docfis_aj, 
			                String cod_docto_aj, 
			                String cod_modelo_aj,
			                String cod_modelo_cotepe_aj, 
			                String num_autentic_nfe_aj) {
		this.cod_empresa = cod_empresa;
		this.cod_estab = cod_estab;
		this.data_fiscal = data_fiscal;
		this.data_emissao = data_emissao;
		this.movto_e_s = movto_e_s;
		this.norm_dev = norm_dev;
		this.cod_fis_jur = cod_fis_jur;
		this.num_docfis = num_docfis;
		this.serie_docfis = serie_docfis;
		this.sub_serie_docfis = sub_serie_docfis;
		this.cod_empresa_aj = cod_empresa_aj;
		this.cod_estab_aj = cod_estab_aj;
		this.data_fiscal_aj = data_fiscal_aj;
		this.data_emissao_aj = data_emissao_aj;
		this.movto_e_s_aj = movto_e_s_aj;
		this.norm_dev_aj = norm_dev_aj;
		this.cod_fis_jur_aj = cod_fis_jur_aj;
		this.num_docfis_aj = num_docfis_aj;
		this.serie_docfis_aj = serie_docfis_aj;
		this.sub_serie_docfis_aj = sub_serie_docfis_aj;
		this.cod_class_docfis_aj = cod_class_docfis_aj;
		this.cod_docto_aj = cod_docto_aj;
		this.cod_modelo_aj = cod_modelo_aj;
		this.cod_modelo_cotepe_aj = cod_modelo_cotepe_aj;
		this.num_autentic_nfe_aj = num_autentic_nfe_aj;
	}

	public String getCod_empresa() {
		return cod_empresa;
	}

	public void setCod_empresa(String cod_empresa) {
		this.cod_empresa = cod_empresa;
	}

	public String getCod_estab() {
		return cod_estab;
	}

	public void setCod_estab(String cod_estab) {
		this.cod_estab = cod_estab;
	}

	public Date getData_fiscal() {
		return data_fiscal;
	}

	public void setData_fiscal(Date data_fiscal) {
		this.data_fiscal = data_fiscal;
	}

	public Date getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(Date data_emissao) {
		this.data_emissao = data_emissao;
	}

	public String getMovto_e_s() {
		return movto_e_s;
	}

	public void setMovto_e_s(String movto_e_s) {
		this.movto_e_s = movto_e_s;
	}

	public String getNorm_dev() {
		return norm_dev;
	}

	public void setNorm_dev(String norm_dev) {
		this.norm_dev = norm_dev;
	}

	public String getCod_fis_jur() {
		return cod_fis_jur;
	}

	public void setCod_fis_jur(String cod_fis_jur) {
		this.cod_fis_jur = cod_fis_jur;
	}

	public String getNum_docfis() {
		return num_docfis;
	}

	public void setNum_docfis(String num_docfis) {
		this.num_docfis = num_docfis;
	}

	public String getSerie_docfis() {
		return serie_docfis;
	}

	public void setSerie_docfis(String serie_docfis) {
		this.serie_docfis = serie_docfis;
	}

	public String getSub_serie_docfis() {
		return sub_serie_docfis;
	}

	public void setSub_serie_docfis(String sub_serie_docfis) {
		this.sub_serie_docfis = sub_serie_docfis;
	}

	public String getCod_empresa_aj() {
		return cod_empresa_aj;
	}

	public void setCod_empresa_aj(String cod_empresa_aj) {
		this.cod_empresa_aj = cod_empresa_aj;
	}

	public String getCod_estab_aj() {
		return cod_estab_aj;
	}

	public void setCod_estab_aj(String cod_estab_aj) {
		this.cod_estab_aj = cod_estab_aj;
	}

	public Date getData_fiscal_aj() {
		return data_fiscal_aj;
	}

	public void setData_fiscal_aj(Date data_fiscal_aj) {
		this.data_fiscal_aj = data_fiscal_aj;
	}

	public Date getData_emissao_aj() {
		return data_emissao_aj;
	}

	public void setData_emissao_aj(Date data_emissao_aj) {
		this.data_emissao_aj = data_emissao_aj;
	}

	public String getMovto_e_s_aj() {
		return movto_e_s_aj;
	}

	public void setMovto_e_s_aj(String movto_e_s_aj) {
		this.movto_e_s_aj = movto_e_s_aj;
	}

	public String getNorm_dev_aj() {
		return norm_dev_aj;
	}

	public void setNorm_dev_aj(String norm_dev_aj) {
		this.norm_dev_aj = norm_dev_aj;
	}

	public String getCod_fis_jur_aj() {
		return cod_fis_jur_aj;
	}

	public void setCod_fis_jur_aj(String cod_fis_jur_aj) {
		this.cod_fis_jur_aj = cod_fis_jur_aj;
	}

	public String getNum_docfis_aj() {
		return num_docfis_aj;
	}

	public void setNum_docfis_aj(String num_docfis_aj) {
		this.num_docfis_aj = num_docfis_aj;
	}

	public String getSerie_docfis_aj() {
		return serie_docfis_aj;
	}

	public void setSerie_docfis_aj(String serie_docfis_aj) {
		this.serie_docfis_aj = serie_docfis_aj;
	}

	public String getSub_serie_docfis_aj() {
		return sub_serie_docfis_aj;
	}

	public void setSub_serie_docfis_aj(String sub_serie_docfis_aj) {
		this.sub_serie_docfis_aj = sub_serie_docfis_aj;
	}

	public String getCod_class_docfis_aj() {
		return cod_class_docfis_aj;
	}

	public void setCod_class_docfis_aj(String cod_class_docfis_aj) {
		this.cod_class_docfis_aj = cod_class_docfis_aj;
	}

	public String getCod_docto_aj() {
		return cod_docto_aj;
	}

	public void setCod_docto_aj(String cod_docto_aj) {
		this.cod_docto_aj = cod_docto_aj;
	}

	public String getCod_modelo_aj() {
		return cod_modelo_aj;
	}

	public void setCod_modelo_aj(String cod_modelo_aj) {
		this.cod_modelo_aj = cod_modelo_aj;
	}

	public String getCod_modelo_cotepe_aj() {
		return cod_modelo_cotepe_aj;
	}

	public void setCod_modelo_cotepe_aj(String cod_modelo_cotepe_aj) {
		this.cod_modelo_cotepe_aj = cod_modelo_cotepe_aj;
	}

	public String getNum_autentic_nfe_aj() {
		return num_autentic_nfe_aj;
	}

	public void setNum_autentic_nfe_aj(String num_autentic_nfe_aj) {
		this.num_autentic_nfe_aj = num_autentic_nfe_aj;
	}

	@Override
	public String toString() {
		return "PrimaryKeyChange [cod_empresa=" + cod_empresa
				+ ", cod_estab=" + cod_estab
				+ ", data_fiscal=" + data_fiscal
				+ ", data_emissao=" + data_emissao
				+ ", movto_e_s=" + movto_e_s
				+ ", norm_dev=" + norm_dev
				+ ", cod_fis_jur=" + cod_fis_jur
				+ ", num_docfis=" + num_docfis
				+ ", serie_docfis=" + serie_docfis
				+ ", sub_serie_docfis=" + sub_serie_docfis + "]";
	}

	
}
