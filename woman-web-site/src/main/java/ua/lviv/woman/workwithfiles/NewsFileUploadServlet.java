package ua.lviv.woman.workwithfiles;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/newsUploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class NewsFileUploadServlet extends DBConnection {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		String name = request.getParameter("name");
		String newsInfoUa = request.getParameter("newsInfoUA");
		String newsInfoEN = request.getParameter("newsInfoEn");
		String exparationDate = request.getParameter("exparationDate");

		Date exDate = null;
		try {
			exDate = new SimpleDateFormat("yyyy-MM-dd").parse(exparationDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date pubDate = new Date();

		InputStream inputStream = null; // input stream of the upload file

		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("newImage");
		if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}

		Connection conn = null; // connection to the database
		String message = null; // message will be sent back to client

		try {
			// connects to the database
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

			// constructs SQL statement
			String sql = "INSERT INTO news (name, image, newsInfoUA, newsInfoEn, exparationDate, publishedDate, toArchive, topOfNews) values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(3, newsInfoUa);
			statement.setString(4, newsInfoEN);
			statement.setDate(5, new java.sql.Date(exDate.getTime()));
			statement.setDate(6, new java.sql.Date(pubDate.getTime()));
			statement.setString(7, "0");
			statement.setString(8, "0");

			if (inputStream != null) {
				// fetches input stream of the upload file for the blob column
				System.out.println("inputStream OK");
				statement.setBlob(2, inputStream);
			}

			// sends the statement to the database server
			int row = statement.executeUpdate();
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
			response.sendRedirect("news.html?success=true");
		}
	}

}
