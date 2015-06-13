package ua.lviv.woman.workwithfiles;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/donorUploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class DonorsFileUploadServlet extends DBConnection {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		System.out.println("start!!!!");
		String name = request.getParameter("name");
		String donorsInfoUa = request.getParameter("donorsInfoUA");
		String donorsInfoEN = request.getParameter("donorsInfoEN");
		String link = request.getParameter("link");

		System.out.println("инициализация переменных");

		InputStream inputStream = null; // input stream of the upload file

		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("donorsImage");
		System.out.println("image init");
		if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			System.out.println("initialization of image");

			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}

		Connection conn = null; // connection to the database
		String message = null; // message will be sent back to client

		try {
			// connects to the database

			System.out.println("start connection to db");

			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

			System.out.println("connection to DB");

			// constructs SQL statement
			String sql = "INSERT INTO donors (name, donorsInfoUA, donorsInfoEN, image, link) values (?, ?, ?, ?, ?)";

			System.out.println("sql ok");

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, donorsInfoUa);
			statement.setString(3, donorsInfoEN);
			statement.setString(5, link);

			System.out.println("stmt ok");

			if (inputStream != null) {
				// fetches input stream of the upload file for the blob column
				statement.setBlob(4, inputStream);
				System.out.println("input ok");
			}

			// sends the statement to the database server
			int row = statement.executeUpdate();

			System.out.println("stmt update");

			if (row > 0) {
				message = "File uploaded and saved into database";
			}
		} catch (SQLException ex) {
			message = "ERROR: " + ex.getMessage();
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				// closes the database connection
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

			response.sendRedirect("donors.html?success=true");
		}
	}

}
