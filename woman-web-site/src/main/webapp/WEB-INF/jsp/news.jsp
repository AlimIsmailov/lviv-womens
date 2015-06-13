<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<br />

<caption>
	<h2>Наши новини</h2>
</caption>

<security:authorize access="hasRole('ROLE_ADMIN')">
	<form:form method="post" action="newsUploadServlet"
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
						name="newsInfoUA" size="50"></textarea></td>
			</tr>
			<tr>
				<td><label>Инфо АНГЛ:</label></td>
				<td><textarea class="form-control" rows="6" cols="40"
						name="newsInfoEn" size="50"></textarea></td>
			</tr>
			<tr>
				<td><label>Дата:</label></td>
				<td><input class="form-control" type="text"
					name="exparationDate" size="10" placeholder="YYYY-MM-DD" /></td>
			</tr>
			<tr>
				<td><label>Фото:</label></td>
				<td><input type="file" name="newImage" size="50" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save"></td>
			</tr>
		</table>
	</form:form>
</security:authorize>

<br />

<c:forEach items="${newsall}" var="newsall">
	<div class="row">
		<c:if test="${newsall.toArchive eq 0}">
			<div class="col-sm-10">
				<h3>
					<a href="#">${newsall.name}</a>
				</h3>
				<div class="row">
					<div class="col-sm-6">
						<h5>
							<a class="label label-default">${newsall.newsInfoUA}</a>
						</h5>
					</div>
					<div class="col-sm-6">
						<h5>
							<a class="label label-default">${newsall.newsInfoEn}</a>
						</h5>
					</div>
				</div>
				<h4>
					<small class="text-muted">Дата початку:
						${newsall.publishedDate}</small>
				</h4>
				<h4>
					<small class="text-muted">Дата закінчення:
						${newsall.exparationDate}</small>
				</h4>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<a class="btn-xs btn-danger triggerRemove"
						href='<spring:url value="/news/remove/${newsall.id}.html"></spring:url>'><span
						class="glyphicon glyphicon-trash"></span></a>
					<a
						href='<spring:url value="/news/edit/${newsall.id}.html"></spring:url>'
						class="btn-xs btn-success">edit</a>
					<a
						href='<spring:url value="/news/totop/${newsall.id}.html"></spring:url>'
						class="btn-xs btn-success">to top</a>
				</security:authorize>
			</div>
			<div class="col-sm-2">
				<form action="displayServlet" method="get">
					<table>
						<tr>
							<td>
								<div
									style="background: none repeat scroll 0 0 ghostwhite; height: 150px; width: 150px;">
									<img src="displayServlet?schema=news&id=${newsall.id}"
										height="150px" width="150px" alt="ProfilePic">
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</c:if>
	</div>
</c:forEach>

<ul class="pagination">
	<li><a href='<spring:url value="/news/prev.html"></spring:url>'
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
	</a></li>
	<c:forEach begin="1" end="${allPageCount}" varStatus="paging">
		<li><a
			href='<spring:url value="/news/${paging.current - 1}.html"></spring:url>'>${paging.current}</a></li>
	</c:forEach>
	<li><a href='<spring:url value="/news/next.html"></spring:url>'
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
					href='<spring:url value="/news/pagesize/${pageSize.current}.html"></spring:url>'>${pageSize.current}</a></li>
			</c:forEach>
		</ul></li>
	<li class="dropdown"><button
			class="btn btn-default dropdown-toggle" type="button"
			data-toggle="dropdown" aria-expanded="true">
			Сортувати по: <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu">
			<li><a
				href='<spring:url value="/news/sort/publishedDate.html"></spring:url>'>Даті
					початку</a></li>
			<li><a
				href='<spring:url value="/news/sort/exparationDate.html"></spring:url>'>Даті
					закінчення</a></li>
			<li><a
				href='<spring:url value="/news/sort/topOfNews.html"></spring:url>'>Топ
					новин</a></li>

		</ul></li>
</ul>