package model.dao;

import java.util.List;

import model.entities.Parametro;

public interface ParametroDao {

	List<Parametro> findAll();

	void atualiza(Parametro pr);
	
	void execute(Parametro pr);
}