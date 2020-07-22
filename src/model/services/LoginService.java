package model.services;

import model.entities.Login;

public class LoginService {

	public Boolean findUser(Login user) {
		
	    return (user.getUser().equals("root") && user.getPassword().equals("123"));
	}
	
}
