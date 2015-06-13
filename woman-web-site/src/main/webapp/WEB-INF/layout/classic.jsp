<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<%@ include file="../layout/taglib.jsp"%>

<html>
<head>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>

<script src="http://maps.google.com/maps?file=api"
	type="text/javascript"></script>

<script type="text/javascript">
	//<![CDATA[
	function load() {
		if (GBrowserIsCompatible()) {
			var map = new GMap2(document.getElementById("map"));
			map.setCenter(new GLatLng(49.798435, 24.047175), 15);
			var point = new GLatLng(49.798435, 24.047175);
			marker = new GMarker(point);
			map.addOverlay(marker)
		}
	}
	//]]>
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><tiles:getAsString name="title" /></title>


</head>
<body onload="load()">

	<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
		prefix="tilesx"%>

	<tilesx:useAttribute name="current" />

	<div class="wrapper">
		<div class="box">
			<div class="row">
				<!-- sidebar -->
				<div class="column col-sm-3" id="sidebar"
					style="background-image: url(/images/logo.jpg)">
					<a class="logo"
						href='<spring:url value="home_page.html"></spring:url>'><h3>Женская
							перспектива</h3></a>
					<ul class="nav">
						<br>

						<li class="${current == 'event' ? 'active' : ''}"><a
							href='<spring:url value="/event.html"></spring:url>'>События</a></li>
						<li class="${current == 'news' ? 'active' : ''}"><a
							href='<spring:url value="/news.html"></spring:url>'>Новости</a></li>
						<li class="${current == 'project' ? 'active' : ''}"><a
							href='<spring:url value="/project.html"></spring:url>'>Проекты</a></li>
						<li class="${current == 'donate' ? 'active' : ''}"><a
							href='<spring:url value="/donate.html"></spring:url>'>Пожертвования</a></li>
						<li class="${current == 'donors' ? 'active' : ''}"><a
							href='<spring:url value="/donors.html"></spring:url>'>Доноры</a></li>
						<li class="${current == 'questionnaire' ? 'active' : ''}"><a
							href='<spring:url value="/questionnaire.html"></spring:url>'>Анкета</a></li>
						<li class="${current == 'archive' ? 'active' : ''}"><a
							href='<spring:url value="/archive.html"></spring:url>'>Архив</a></li>
						<li class="${current == 'album' ? 'active' : ''}"><a
							href='<spring:url value="/album.html"></spring:url>'>Фотоальбом</a></li>
						<li><a href="#contacts">Контакты</a></li>
						<security:authorize access="! isAuthenticated()">
							<li class="${current == 'login' ? 'active' : ''}"><a
								href='<spring:url value="/login.html" />'>Войти</a></li>
						</security:authorize>
						<security:authorize access="isAuthenticated()">
							<li class="${current == 'administration' ? 'active' : ''}"><a
								href='<spring:url value="administration.html"></spring:url>'>Администрирование
									сайта</a></li>
							<li><a href='<spring:url value="logout" />'>Выйти</a></li>
						</security:authorize>
					</ul>
				</div>

				<div class="column col-sm-9" id="main">
					<div class="padding">
						<div class="full col-sm-9">

							<!-- main -->
							<tiles:insertAttribute name="body" />

							<div class="col-sm-12">
								<div class="page-header text-muted divider">Мы в
									социальной сети</div>
							</div>

							<div class="row">
								<div class="col-sm-6">
									<a
										href="https://www.facebook.com/ZahidnoukrainskijCentrZinociPerspektivi?pnref=lhc"
										target="_blank">Facebook</a> <small class="text-muted">|</small><a
										href="https://www.youtube.com/" target="_blank"> YouTube </a>
									<small class="text-muted">|</small>
								</div>
							</div>

							<div class="col-sm-12">
								<div class="page-header text-muted divider">Контакты</div>
							</div>

							<div class="row" id="contacts">
								<div class="col-sm-6">
									<a>79070 , Україна , Львів, проспект Червоної Калини 36,
										торговий центр "Шувар", офіс 315 тел (032) 295-50-60 моб (067)
										67-407-70 </a> <a href="mailto:women@women.lviv.ua">
										women@women.lviv.ua </a> <small class="text-muted">|</small>
								</div>

							</div>

							<br />

							<div id="map" style="width: 800px; height: 250px"></div>

						</div>
						<!-- /col-9 -->
					</div>
					<!-- /padding -->
				</div>
				<!-- /main -->
			</div>
		</div>
	</div>

	<div>

		<br> <br>


		<center>
			<tiles:insertAttribute name="footer" />
		</center>

		<br> <br>

	</div>

</body>
</html>