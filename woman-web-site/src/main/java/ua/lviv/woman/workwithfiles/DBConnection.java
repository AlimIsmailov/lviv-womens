package ua.lviv.woman.workwithfiles;

import javax.servlet.http.HttpServlet;

public class DBConnection extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected String dbURL = "jdbc:mysql://localhost:3306/WomanDB";
	protected String dbUser = "root";
	protected String dbPass = "admin";
	protected String dbDriver = "com.mysql.jdbc.Driver";
	protected String dbEncoding = "UTF-8";

}
