<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<br />

<security:authorize access="hasRole('ROLE_ADMIN')">
	<form:form method="post" action="projectsUploadServlet"
		enctype="multipart/form-data"
		cssClass="form-horizontal registrationForm">

		<c:if test="${param.success eq true}">
			<div class="alert alert-info">Проект создана!</div>
		</c:if>

		<table>
			<tr>
				<td><label>Имя:</label></td>
				<td><input class="form-control" type="text" name="name"
					size="50" /></td>
			</tr>
			<tr>
				<td><label>Инфо УКР:</label></td>
				<td><textarea class="form-control" rows="6" cols="40"
						name="projectInfoUA" size="50"></textarea></td>
			</tr>
			<tr>
				<td><label>Инфо АНГЛ:</label></td>
				<td><textarea class="form-control" rows="6" cols="40"
						name="projectInfoEN" size="50"></textarea></td>
			</tr>
			<tr>
				<td><label>Дата закінченя:</label></td>
				<td><input class="form-control" type="text"
					name="exparationDate" size="10" placeholder="YYYY-MM-DD" /></td>
			</tr>
			<tr>
				<td><label>Фото:</label></td>
				<td><input type="file" name="image" size="50" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save"></td>
			</tr>
		</table>
	</form:form>
</security:authorize>

<form method="POST" enctype="multipart/form-data" action="fup.cgi">
	File to upload: <input type="file" name="upfile"><br /> Notes
	about the file: <input type="text" name="note"><br /> <br /> <input
		type="submit" value="Press"> to upload the file!
</form>

<br />

<c:forEach items="${projectsall}" var="projectsall">
	<div class="row">
		<div class="col-sm-10">
			<h3>
				<a href="#">${projectsall.name}</a>
			</h3>
			<h4>
				<span class="label label-default">${projectsall.projectInfo}</span>
			</h4>
			<h4>
				<small class="text-muted">Дата опублікування :
					${projectsall.publishedDate}</small>
			</h4>
			<h4>
				<small class="text-muted">Дата закінчення:
					${projectsall.exparationDate}</small>
			</h4>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<a class="btn-lg btn-danger triggerRemove"
					href='<spring:url value="/projects/remove/${projectsall.id}.html"></spring:url>'><span
					class="glyphicon glyphicon-trash"></span></a>
			</security:authorize>
		</div>
		<div class="col-sm-2">
			<form action="displayServlet" method="get">
				<table>
					<tr>
						<td>
							<div
								style="background: none repeat scroll 0 0 ghostwhite; height: 150px; width: 150px;">
								<img src="displayServlet?schema=projects&id=${projectsall.id}"
									height="150px" width="150px" alt="ProfilePic">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</c:forEach>

<ul class="pagination">
	<li><a href='<spring:url value="/project/prev.html"></spring:url>'
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
	</a></li>
	<c:forEach begin="1" end="${allPageCount}" varStatus="paging">
		<li><a
			href='<spring:url value="/project/${paging.current - 1}.html"></spring:url>'>${paging.current}</a></li>
	</c:forEach>
	<li><a href='<spring:url value="/project/next.html"></spring:url>'
		aria-label="Next"> <span aria-hidden="true">&raquo;</span>
	</a></li>
	<li class="dropdown"><button
			class="btn btn-default dropdown-toggle" type="button"
			data-toggle="dropdown" aria-expanded="true">
			${currentPageSize}<span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu">
			<c:forEach begin="1" end="10" varStatus="pageSize">
				<li><a
					href='<spring:url value="/project/pagesize/${pageSize.current}.html"></spring:url>'>${pageSize.current}</a></li>
			</c:forEach>
		</ul></li>
</ul>

