package model.dao;

import db.DBOracle;
import model.dao.impl.LoginDaoJDBC;
import model.dao.impl.ParametroDaoJDBC;
import model.dao.impl.PrimaryKeyChangeDaoJDBC;
import model.dao.impl.UserDaoJDBC;

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
	
	public static UserDao createUserDao() {
		return new UserDaoJDBC(DBOracle.getConnection());
	}

	public static LoginDao createLoginDao() {
		return new LoginDaoJDBC(DBOracle.getConnection());
	}
	
}