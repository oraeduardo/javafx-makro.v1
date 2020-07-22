package model.services;

import model.entities.User;

public class UserService {

	public Boolean findUser(User user) {
		
	    return (user.getUser().equals("root") && user.getPassword().equals("123"));
	}
	
}
