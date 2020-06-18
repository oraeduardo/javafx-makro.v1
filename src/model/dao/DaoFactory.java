package model.dao;

import db.DBOracle;
import model.dao.impl.ParametroDaoJDBC;
import model.dao.impl.PrimaryKeyChangeDaoJDBC;

public class DaoFactory {

	//Conex�o com banco de dados MySQL
//	public static PrimaryKeyChangeDao createPrimaryKeyChangeDao() {
//		return new PrimaryKeyChangeDaoJDBC(DB.getConnection());
//	}
	
	//Conex�o com banco de dados Oracle
	public static PrimaryKeyChangeDao createPrimaryKeyChangeDao() {
		return new PrimaryKeyChangeDaoJDBC(DBOracle.getConnection());
	}
	
	//Conex�o com banco de dados Oracle
	public static ParametroDao createLojaDao() {
		return new ParametroDaoJDBC(DBOracle.getConnection());
	}

}