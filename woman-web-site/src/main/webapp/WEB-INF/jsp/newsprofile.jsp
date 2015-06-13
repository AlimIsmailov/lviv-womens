<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="row">
	<div class="col-sm-10">
		<h3>
			<a href="#">${news.name}</a>
		</h3>
		<h4>
			<a class="label label-default">${news.newsInfoUA}</a>
		</h4>
		<h4>
			<a class="label label-default">${news.newsInfoEn}</a>
		</h4>
		<h4>
			<small class="text-muted">${news.publishedDate}</small>
		</h4>
	</div>
</div>

<br />

<form:form commandName="editnews"
	cssClass="form-horizontal registrationForm">

	<c:if test="${param.success eq true}">
		<div class="alert alert-info">Новость создана!</div>
	</c:if>

	<div class="form-group">
		<label for="newsInfoUA" class="col-sm-2 control-label">Новость
			УКР:</label>
		<div class="col-sm-10">
			<form:input path="newsInfoUA" cssClass="form-control"
				placeholder="Новость УКР" />
			<form:errors path="newsInfoUA" />
		</div>
	</div>

	<div class="form-group">
		<label for="newsInfoEn" class="col-sm-2 control-label">Новость
			ENG:</label>
		<div class="col-sm-10">
			<form:input path="newsInfoEn" cssClass="form-control"
				placeholder="Новость ENG" />
			<form:errors path="newsInfoEn" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-10">
			<input type="submit" value="Создать">
		</div>
	</div>

</form:form>