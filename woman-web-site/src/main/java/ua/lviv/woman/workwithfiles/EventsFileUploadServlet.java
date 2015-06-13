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

@WebServlet("/eventUploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class EventsFileUploadServlet extends DBConnection {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		System.out.println("start!!!!");
		String name = request.getParameter("name");
		String eventInformationUa = request.getParameter("eventInformationUA");
		String eventInformationEN = request.getParameter("eventInformationEN");
		String exparationDate = request.getParameter("expirationDate");
		String startDate = request.getParameter("startDate");

		System.out.println("инициализация переменных");

		Date exDate = null;
		try {
			exDate = new SimpleDateFormat("yyyy-MM-dd").parse(exparationDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date stDate = null;
		try {
			stDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date pubDate = new Date();

		InputStream inputStream = null; // input stream of the upload file

		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("eventImage");
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
			String sql = "INSERT INTO events (name, eventInformationUA, eventInformationEN, image, expirationDate, publishedDate, startDate, toArchive, topOfEvents) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			System.out.println("sql ok");

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, eventInformationUa);
			statement.setString(3, eventInformationEN);
			statement.setDate(5, new java.sql.Date(exDate.getTime()));
			statement.setDate(6, new java.sql.Date(pubDate.getTime()));
			statement.setDate(7, new java.sql.Date(stDate.getTime()));
			statement.setString(8, "0");
			statement.setString(9, "0");

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

			System.out.println("redirect ok");

			response.sendRedirect("event.html?success=true");
		}
	}

}
