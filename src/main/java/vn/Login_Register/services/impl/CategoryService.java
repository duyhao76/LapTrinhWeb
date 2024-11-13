package vn.Login_Register.services.impl;

import java.util.List;

import vn.Login_Register.dao.ICategoryDao;
import vn.Login_Register.dao.impl.CategoryDaoImpl;
import vn.Login_Register.models.CategoryModel;
import vn.Login_Register.services.ICategoryService;

public class CategoryService implements ICategoryService{

	public ICategoryDao cateDao = new CategoryDaoImpl();
	
	@Override
	public List<CategoryModel> findAll() {
		return cateDao.findAll();
	}

	@Override
	public CategoryModel findById(int id) {
		return cateDao.findById(id);
	}

	@Override
	public void insert(CategoryModel category) {
		cateDao.insert(category);		
	}

	@Override
	public void update(CategoryModel category) {
		CategoryModel cate = new CategoryModel();
		cate = cateDao.findById(category.getCategoryid());
		if(cate != null) {
			cateDao.update(category);
		}
		
	}

	@Override
	public void delete(int id) {
		CategoryModel cate = new CategoryModel();
		cate = cateDao.findById(id);
		if(cate != null) {
			cateDao.delete(id);
		}
		
	}

	@Override
	public List<CategoryModel> findName(String keyword) {
		return cateDao.findName(keyword);
	}

}
