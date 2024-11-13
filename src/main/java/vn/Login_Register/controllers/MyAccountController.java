package vn.Login_Register.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.Login_Register.models.UserModel;
import vn.Login_Register.utils.Constant;

@WebServlet(urlPatterns = {"/myaccount"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class MyAccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_DIRECTORY  = "uploads";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/myaccount.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String uploadPath = UPLOAD_DIRECTORY;
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();
		
		HttpSession session = req.getSession();
        UserModel user = (UserModel) session.getAttribute("account");
		
		try {
			String fileName = "";
			for (Part part : req.getParts()) {
				if (part.getName().equals("profileImage") && part.getSize() > 0) {
					fileName = getFileName(part);
					part.write(uploadPath + fileName);
					
					// Cập nhật avatar cho người dùng trong session
                    user.setAvatar(fileName);
                    session.setAttribute("account", user); // Cập nhật lại session
				}
				
			}
			req.setAttribute("message", "File " + fileName + " has uploaded successfully!");
		} catch (Exception e) {
		    e.printStackTrace();  // Ghi log lỗi ra console
		    req.setAttribute("message", "Error: " + e.getMessage());
		    req.getRequestDispatcher("/views/result.jsp").forward(req, resp);
		}
		getServletContext().getRequestDispatcher("/views/result.jsp").forward(req, resp);
	}

	private String getFileName(Part part) {
		for (String content : part.getHeader("content disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return Constant.DEFAULT_FILENAME;
	}

}
