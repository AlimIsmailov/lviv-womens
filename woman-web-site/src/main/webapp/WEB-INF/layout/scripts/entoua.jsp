<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../taglib.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		$("#buttonua").click(function() {
			$("#enlang").hide();
			$("#ualang").show();
		});
		$("#buttonen").click(function() {
			$("#ualang").hide();
			$("#enlang").show();
		});
		$("#buttonua2").click(function() {
			$("#enlang2").hide();
			$("#ualang2").show();
		});
		$("#buttonen2").click(function() {
			$("#ualang2").hide();
			$("#enlang2").show();
		});
	});
</script>
