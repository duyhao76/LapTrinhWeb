package vn.Login_Register.controllers;

import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.Login_Register.dao.impl.UserDaoImpl;

public class ImageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
    UserDaoImpl userDao = new UserDaoImpl();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("image/jpeg");
		
		String username = req.getParameter("username");
		
		byte[] avatar = userDao.getUserAvatar(username);
		
		if (avatar != null) {
			resp.setContentType("image/jpeg");
			resp.setContentLengthLong(avatar.length);
			
			try (OutputStream out = resp.getOutputStream()){
				out.write(avatar);
				out.flush();
			}
		}
		else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
