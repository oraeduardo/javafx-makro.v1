package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class UserService {

	private UserDao dao = DaoFactory.createUserDao();
	
	public List<User> findAll() {
		//MOCK - retorno de dados fake.
		/*List<Department> list = new ArrayList<>();
		list.add(new Department(1, "Books"));
		list.add(new Department(2, "Computers"));
		list.add(new Department(3, "Electronics"));
		return list;*/
		return dao.findAll();
	}

	public User findById(Integer Id) {
		return dao.findById(Id);
	}
	
	public User findByName(String name) {
		return dao.findByName(name);
	}
	
	public void saveOrUpdate(User obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(User obj) {
		dao.deleteById(obj.getId());
	}
}
