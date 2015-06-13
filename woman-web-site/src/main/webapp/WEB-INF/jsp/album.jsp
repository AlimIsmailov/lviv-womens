<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="col-sm-12">
	<caption>
		<h2>Наши события</h2>
	</caption>
</div>

<c:forEach items="${alleventsinalbum}" var="alleventsinalbum">
	<div>
		<div class="col-md-3">
			<form action="displayServlet" method="get">
				<table>
					<tr>
						<td>
							<div
								style="background: none repeat scroll 0 0 ghostwhite; height: 150px; width: 150px;">
								<img
									src="displayServlet?schema=events&id=${alleventsinalbum.id}"
									height="150px" width="150px" alt="ProfilePic">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</c:forEach>

<div class="col-sm-12">
	<a class="btn btn-link btn-lg btn-block"
		href='<spring:url value="/album/showmoreevents.html"></spring:url>'
		role="button">Відобразити ще 20 фото</a>
</div>

<div class="col-sm-12">
	<caption>
		<h2>Наши новости</h2>
	</caption>
</div>

<c:forEach items="${allnewsinalbum}" var="allnewsinalbum">
	<div>
		<div class="col-md-3">
			<form action="displayServlet" method="get">
				<table>
					<tr>
						<td>
							<div
								style="background: none repeat scroll 0 0 ghostwhite; height: 150px; width: 150px;">
								<img src="displayServlet?schema=news&id=${allnewsinalbum.id}"
									height="150px" width="150px" alt="ProfilePic">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</c:forEach>

<div class="col-sm-12">
	<a class="btn btn-link btn-lg btn-block"
		href='<spring:url value="/album/showmorenews.html"></spring:url>'
		role="button">Відобразити ще 20 фото</a>
</div>