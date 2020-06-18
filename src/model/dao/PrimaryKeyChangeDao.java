package model.dao;

import java.sql.Date;
import java.util.List;

import model.entities.Parametro;
import model.entities.PrimaryKeyChange;

public interface PrimaryKeyChangeDao {

	PrimaryKeyChange findById(String cod_empresa,
            				  String cod_estab,
            				  Date data_fiscal,
            				  Date data_emissao,
            				  String movto_e_s,
            				  String norm_dev,
            				  String cod_fis_jur,
            				  String num_docfis,
            				  String serie_docfis);

	List<PrimaryKeyChange> findAll(Parametro pr);
	
	void update(PrimaryKeyChange obj);

	void deleteById(PrimaryKeyChange obj);

}