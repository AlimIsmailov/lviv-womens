<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<%-- 

	<div class="form-group">
		<h3>Выберите тему анкеты</h3>
		<c:forEach items="${directions}" var="directions">
			<p>
				<input type="radio" name="directionintegration"
					value="${directions.id}" />${directions.name}
			</p>
		</c:forEach>
	</div>

 --%>

<form:form action="EmailSendingServlet" method="post">

	<c:if test="${param.success eq true}">
		<div class="alert alert-info">Ваш вопрос отправлен. С вами
			свяжутся в ближайшее время. Спасибо!</div>
	</c:if>

	<table>
		<caption>
			<h2>Задайте ваше питання</h2>
		</caption>
		<tr>
			<td><label>Ваше Ім'я:</label></td>
			<td><input type="text" class="form-control" name="name"
				size="50" /></td>
		</tr>
		<tr>
			<td><label>Тема питання:</label></td>
			<td><input type="text" class="form-control" name="subject"
				size="50" /></td>
		</tr>
		<tr>
			<td><label>Ваш имейл:</label></td>
			<td><input type="email" class="form-control" name="email"
				size="50" /></td>
		</tr>
		<tr>
			<td><label>Номер телефону:</label></td>
			<td><input type="text" class="form-control" name="phoneNumber"
				size="50" /></td>
		</tr>
		<tr>
			<td><label>Питання:</label></td>
			<td><textarea rows="6" class="form-control" cols="40"
					name="content" size="1000"></textarea></td>
		</tr>
		<!-- <tr>
			<td><label>Файл:</label></td>
			<td><input type="file" name="fileToSend" size="50" /></td>
		</tr> -->
		<tr>
			<td><label> </label></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Відправити" /></td>
		</tr>
	</table>

</form:form>