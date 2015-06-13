<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="col-sm-12">
	<div class="page-header text-muted divider">
		<h3>Новости</h3>
	</div>
</div>

<c:forEach items="${newsall}" var="newsall">
	<div class="row">
		<div class="col-sm-10">
			<h3>
				<a href="#">${newsall.name}</a>
				<c:if test="${newsall.topOfNews eq 1}">
					<span class="glyphicon glyphicon-bookmark">Top</span>
				</c:if>
			</h3>
			<h4>
				<a class="label label-default">${newsall.newsInfoUA}</a>
			</h4>
			<h4>
				<a class="label label-default">${newsall.newsInfoEn}</a>
			</h4>
			<h4>
				<small class="text-muted">${newsall.publishedDate}</small>
			</h4>
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
	</div>
</c:forEach>

<div class="col-sm-12">
	<div class="page-header text-muted divider">
		<h3>События</h3>
	</div>
</div>

<c:forEach items="${eventsall}" var="eventsall">
	<div class="row">
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
				<small class="text-muted">${eventsall.publishedDate}</small>
			</h4>
			<h4>
				<small class="text-muted">${eventsall.expirationDate}</small>
			</h4>
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
	</div>
</c:forEach>

<div class="col-sm-12">
	<div class="page-header text-muted divider">
		<h3>Партнёры</h3>
	</div>
</div>

<div class="row">
	<c:forEach items="${partnersall}" var="partnersall">
		<div class="col-sm-4 text-center">
			<h4>${partnersall.name}</h4>
			<h5>${partnersall.partnersInfoUA}</h5>
			<h5>${partnersall.partnersInfoEN}</h5>
			<h6>
				<a target="_blank" href="http://${partnersall.link}">${partnersall.link}</a>
			</h6>
			<div class="col-sm-2">
				<form action="displayServlet" method="get">
					<table>
						<tr>
							<td>
								<div
									style="background: img-circle none repeat scroll 0 0 ghostwhite; height: 150px; width: 150px;">
									<img src="displayServlet?schema=partners&id=${partnersall.id}"
										class="img-respsonsive img-circle" height="150px"
										width="150px" alt="ProfilePic">
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</c:forEach>
</div>
