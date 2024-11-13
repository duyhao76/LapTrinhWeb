package vn.Login_Register.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.Login_Register.models.CategoryModel;
import vn.Login_Register.services.ICategoryService;
import vn.Login_Register.services.impl.CategoryService;

@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/add",
		"/admin/category/insert", "/admin/category/edit", "/admin/category/update",
		"/admin/category/delete", "/admin/category/search"})
public class CategoryController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("categories")) {
			List<CategoryModel> list = cateService.findAll();
			req.setAttribute("listcategory", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}
		else if (url.contains("add")){
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
		else if (url.contains("edit")){
			int id = Integer.parseInt(req.getParameter("id"));
			
			CategoryModel category = cateService.findById(id);
			
			req.setAttribute("cate", category);
			
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
		else if (url.contains("delete")) {
			String id = req.getParameter("id");
			
			cateService.delete(Integer.parseInt(id));
			
			resp.sendRedirect(req.getContextPath() + "/admin/categories");

		}
	}	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("insert")) {
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			String image = "https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/o/p/oppo-reno10-pro-plus-tim.png";
			
			CategoryModel category = new CategoryModel();
			category.setCategoryname(categoryname);
			category.setImages(image);
			category.setStatus(statuss);
			
			cateService.insert(category);
			
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
		else if (url.contains("update")){
			int categoryID = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			String image = "https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/o/p/oppo-reno10-pro-plus-tim.png";
			
			CategoryModel category = new CategoryModel();
			category.setCategoryid(categoryID);
			category.setCategoryname(categoryname);
			category.setImages(image);
			category.setStatus(statuss);
			
			cateService.update(category);
			
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
