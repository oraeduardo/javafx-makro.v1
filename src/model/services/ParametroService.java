package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ParametroDao;
import model.entities.Parametro;

public class ParametroService {

	private ParametroDao dao = DaoFactory.createLojaDao();
	
	public List<Parametro> findAll() {
		//MOCK - retorno de dados fake.
		/*List<Department> list = new ArrayList<>();
		list.add(new Department(1, "Books"));
		list.add(new Department(2, "Computers"));
		list.add(new Department(3, "Electronics"));
		return list;*/
		
		//List<PrimaryKeyChange> list = new ArrayList<>();
		//list.add(new PrimaryKeyChange("1","2",new java.util.Date(),new java.util.Date(),"5","6","7","8","9","10","11","12",new java.util.Date(),new java.util.Date(),"15","16","17","18","19","20","21","22","23","24","25"));
		//return list;
		
		return dao.findAll();
	}

	public void Atualiza(Parametro pr) {
		dao.atualiza(pr);
	}
	
	public void Execute(Parametro pr) {
    	dao.execute(pr);
	}
}
