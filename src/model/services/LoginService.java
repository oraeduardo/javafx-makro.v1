package model.services;

import model.dao.DaoFactory;
import model.dao.LoginDao;
import model.entities.Login;

public class LoginService {

	private LoginDao dao = DaoFactory.createLoginDao();
	
	public Boolean findLogin(Login login) {
		
	    //return (user.getUser().equals("root") && user.getPassword().equals("123"));
		return dao.findLogin(login);
	}
	
	
}
