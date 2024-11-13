package vn.Login_Register.services;

import vn.Login_Register.models.UserModel;

public interface IUserService {
	UserModel login (String username, String password);
	UserModel FindByUserName (String username);
	boolean register (String username, String password, String fullname);
}
