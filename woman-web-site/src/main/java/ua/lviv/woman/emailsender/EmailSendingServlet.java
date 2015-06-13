package ua.lviv.woman.emailsender;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmailSendingServlet")
@MultipartConfig(maxFileSize = 16177215)
public class EmailSendingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String USER_NAME = "tester.lviv.woman"; /*
																 * GMail user
																 * name just the
																 * part before
																 * "@gmail.com")
																 */
	private static final String PASSWORD = "testtest1234"; // GMail password
	private static final String RECIPIENT = "iar1101@mail.ru";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String subjest = request.getParameter("subject");
		String text = request.getParameter("content");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");

		sendFromGMail(subjest, text, name, email, phoneNumber);

		response.sendRedirect("questionnaire.html?success=true");
	}

	private static void sendFromGMail(String subject, String text, String name,
			String email, String phoneNumber) {
		final String username = USER_NAME;
		final String password = PASSWORD;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USER_NAME + "@gmail.com"));
			System.out.println(message.getFrom());
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(RECIPIENT));
			message.setSubject(subject);
			message.setText("Лист від " + name + ".\n\n" + text
					+ "\n\n Мої контактні данні. \n Email: " + email
					+ "\n Контактний номер телефону: " + phoneNumber);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
