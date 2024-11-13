package vn.Login_Register.services.impl;

import vn.Login_Register.models.UserModel;
import vn.Login_Register.services.IUserService;
import vn.Login_Register.dao.IUserDao;
import vn.Login_Register.dao.impl.UserDaoImpl;

public class UserService implements IUserService {

	IUserDao userDao = new UserDaoImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel FindByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public boolean register(String username, String password, String fullname) {
		UserModel existingUser = userDao.findByUserName(username);
		if (existingUser != null) {
			return false; 
		}
		UserModel newUser = new UserModel();
		newUser.setUserName(username);
		newUser.setPassWord(password); 
		newUser.setFullName(fullname);
		try {
	        userDao.insert(newUser);
	        return true;  
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;  
	    }
	}
}
