package model.dao;

import db.DBOracle;
import model.dao.impl.ParametroDaoJDBC;
import model.dao.impl.PrimaryKeyChangeDaoJDBC;

public class DaoFactory {

	//Conexão com banco de dados MySQL
//	public static PrimaryKeyChangeDao createPrimaryKeyChangeDao() {
//		return new PrimaryKeyChangeDaoJDBC(DB.getConnection());
//	}
	
	//Conexão com banco de dados Oracle
	public static PrimaryKeyChangeDao createPrimaryKeyChangeDao() {
		return new PrimaryKeyChangeDaoJDBC(DBOracle.getConnection());
	}
	
	//Conexão com banco de dados Oracle
	public static ParametroDao createLojaDao() {
		return new ParametroDaoJDBC(DBOracle.getConnection());
	}

}