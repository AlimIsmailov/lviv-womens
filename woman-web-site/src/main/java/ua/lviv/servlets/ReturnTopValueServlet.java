package ua.lviv.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.woman.workwithfiles.DBConnection;

public class ReturnTopValueServlet extends DBConnection {

	private static final long serialVersionUID = 1L;
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String imageId = request.getParameter("id");
		String schemaName = request.getParameter("schema");

		if (imageId == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
			stmt = conn.prepareStatement("select topOfEvents from "
					+ schemaName + " where id='" + imageId + "'");
			rs = stmt.executeQuery();
			if (rs.next()) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
