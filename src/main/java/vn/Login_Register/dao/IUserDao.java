package vn.Login_Register.dao;

import java.util.List;

import vn.Login_Register.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void insert(UserModel user);
	
	UserModel findByUserName(String username);
	
	byte[] getUserAvatar(String username);
}
