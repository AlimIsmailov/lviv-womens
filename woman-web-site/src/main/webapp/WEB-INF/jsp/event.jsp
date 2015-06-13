<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<%@ include file="../layout/scripts/entoua.jsp"%>

<%@ include file="../layout/deletetriger.jsp"%>

<caption>
	<h2>Наши события</h2>
</caption>

<security:authorize access="hasRole('ROLE_ADMIN')">
	<form:form method="post" action="eventUploadServlet"
		enctype="multipart/form-data"
		cssClass="form-horizontal registrationForm">

		<c:if test="${param.success eq true}">
			<div class="alert alert-info">Событие созданно!</div>
		</c:if>

		<table>
			<tr>
				<td><label>Имя:</label></td>
				<td><input type="text" class="form-control" name="name"
					size="50" /></td>
			</tr>
			<tr>
				<td><label>Инфо УКР:</label></td>
				<td><textarea rows="6" class="form-control" cols="40"
						name="eventInformationUA" size="50"></textarea></td>
			</tr>
			<tr>
				<td><label>Инфо АНГЛ:</label></td>
				<td><textarea rows="6" class="form-control" cols="40"
						name="eventInformationEN" size="50"></textarea></td>
			</tr>
			<tr>
				<td><label>Дата окончания:</label></td>
				<td><input type="text" class="form-control"
					name="expirationDate" size="10" placeholder="YYYY-MM-DD" /></td>
			</tr>
			<tr>
				<td><label>Дата Начала:</label></td>
				<td><input type="text" class="form-control" name="startDate"
					size="10" placeholder="YYYY-MM-DD" /></td>
			</tr>
			<tr>
				<td><label>Фото:</label></td>
				<td><input type="file" name="eventImage" size="50" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save"></td>
			</tr>
		</table>
	</form:form>
</security:authorize>

<br />

<c:forEach items="${eventsall}" var="eventsall">
	<div class="row">
		<c:if test="${eventsall.toArchive eq 0}">
			<div class="col-sm-10">
				<h3>
					<a href="#">${eventsall.name}</a>
					<c:if test="${eventsall.topOfEvents eq 1}">
						<span class="glyphicon glyphicon-bookmark">Top</span>
					</c:if>
				</h3>
				<h4>
					<a class="label label-default">${eventsall.eventInformationEN}</a>
				</h4>
				<h4>
					<a class="label label-default">${eventsall.eventInformationUA}</a>
				</h4>
				<h4>
					<small class="text-muted">Дата початку:
						${eventsall.publishedDate}</small>
				</h4>
				<h4>
					<small class="text-muted">Дата закінчення:
						${eventsall.expirationDate}</small>
				</h4>
				<h4>
					<small class="text-muted">Дата початку:
						${eventsall.startDate}</small>
				</h4>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<a class="btn-xs btn-danger triggerRemove"
						href='<spring:url value="/event/remove/${eventsall.id}.html"></spring:url>'><span
						class="glyphicon glyphicon-trash"></span></a>
					<a
						href='<spring:url value="/event/edit/${eventsall.id}.html"></spring:url>'
						class="btn-xs btn-success">edit</a>
					<a
						href='<spring:url value="/event/totop/${eventsall.id}.html"></spring:url>'
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
									<img src="displayServlet?schema=events&id=${eventsall.id}"
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
	<li><a href='<spring:url value="/event/prev.html"></spring:url>'
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
	</a></li>
	<c:forEach begin="1" end="${allPageCount}" varStatus="paging">
		<li><a
			href='<spring:url value="/event/${paging.current - 1}.html"></spring:url>'>${paging.current}</a></li>
	</c:forEach>
	<li><a href='<spring:url value="/event/next.html"></spring:url>'
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
					href='<spring:url value="/event/pagesize/${pageSize.current}.html"></spring:url>'>${pageSize.current}</a></li>
			</c:forEach>
		</ul></li>
	<li class="dropdown"><button
			class="btn btn-default dropdown-toggle" type="button"
			data-toggle="dropdown" aria-expanded="true">
			Сортувати по: <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu">
			<li><a
				href='<spring:url value="/event/sort/publishedDate.html"></spring:url>'>Даті
					початку</a></li>
			<li><a
				href='<spring:url value="/event/sort/expirationDate.html"></spring:url>'>Даті
					закінчення</a></li>
			<li><a
				href='<spring:url value="/event/sort/topOfEvents.html"></spring:url>'>Топ
					новин</a></li>

		</ul></li>
</ul>