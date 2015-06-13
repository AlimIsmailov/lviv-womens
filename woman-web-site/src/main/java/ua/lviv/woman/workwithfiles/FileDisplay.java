package ua.lviv.woman.workwithfiles;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayServlet")
public class FileDisplay extends DBConnection {

	private static final long serialVersionUID = 1L;
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public void init() throws ServletException {
	}

	public FileDisplay() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String imageId = request.getParameter("id");
		String schemaName = request.getParameter("schema");
		System.out.println(imageId);
		InputStream sImage;

		if (imageId == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
			stmt = conn.prepareStatement("select image from " + schemaName
					+ " where id='" + imageId + "'");
			rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Inside RS");
				byte[] bytearray = new byte[1048576];
				int size = 0;
				sImage = rs.getBinaryStream(1);
				response.reset();
				response.setContentType("image/jpeg");
				while ((size = sImage.read(bytearray)) != -1) {
					response.getOutputStream().write(bytearray, 0, size);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
