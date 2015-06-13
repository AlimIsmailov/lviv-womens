<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="col-sm-12">
	<div class="page-header text-muted divider">
		<h3>Новости</h3>
	</div>
</div>

<c:forEach items="${newstoarchive}" var="newstoarchive">
	<div class="row">
		<c:if test="${newstoarchive.toArchive eq 1}">
			<div class="col-sm-10">
				<h3>
					<a href="#">${newstoarchive.name}</a>
				</h3>
				<h4>
					<a class="label label-default">${newstoarchive.newsInfoUA}</a>
				</h4>
				<h4>
					<a class="label label-default">${newstoarchive.newsInfoEn}</a>
				</h4>
				<h4>
					<small class="text-muted">${newstoarchive.publishedDate}</small>
				</h4>
			</div>
			<div class="col-sm-2">
				<form action="displayServlet" method="get">
					<table>
						<tr>
							<td>
								<div
									style="background: none repeat scroll 0 0 ghostwhite; height: 150px; width: 150px;">
									<img src="displayServlet?schema=news&id=${newstoarchive.id}"
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

<div class="col-sm-12">
	<a class="btn btn-link btn-lg btn-block"
		href='<spring:url value="/archive/morenews.html"></spring:url>'
		role="button">Відобразити ще...</a>
</div>

<div class="col-sm-12">
	<div class="page-header text-muted divider">
		<h3>События</h3>
	</div>
</div>

<c:forEach items="${eventstoarchive}" var="eventstoarchive">
	<div class="row">
		<c:if test="${eventstoarchive.toArchive eq 1}">
			<div class="col-sm-10">
				<h3>
					<a href="#">${eventstoarchive.name}</a>
				</h3>
				<h4>
					<a class="label label-default">${eventstoarchive.eventInformationEN}</a>
				</h4>
				<h4>
					<a class="label label-default">${eventstoarchive.eventInformationUA}</a>
				</h4>
				<h4>
					<small class="text-muted">${eventstoarchive.publishedDate}</small>
				</h4>
			</div>
			<div class="col-sm-2">
				<form action="displayServlet" method="get">
					<table>
						<tr>
							<td>
								<div
									style="background: none repeat scroll 0 0 ghostwhite; height: 150px; width: 150px;">
									<img
										src="displayServlet?schema=events&id=${eventstoarchive.id}"
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

<div class="col-sm-12">
	<a class="btn btn-link btn-lg btn-block"
		href='<spring:url value="/archive/moreevents.html"></spring:url>'
		role="button">Відобразити ще...</a>
</div>