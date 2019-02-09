<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Manager</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"
	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>"
	type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script
	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<h2>Please review the resource for accuracy</h2>

			<div class="form-group">
				<label for="project-name">Name</label> <span>${resource.name}</span>
			</div>

			<div class="form-group">
				<label for="project_type">Type</label> <span>${resource.type }</span>
			</div>

			<div class="form-group">
				<label>Cost</label> <span>${resource.cost}</span>
			</div>

			<div class="form-group">
				<label>Unit Of Measure</label> <span>${resource.unitOfMeasure}</span>
			</div>

			<div class="form-group">
				<label>Indicators</label>
				<c:forEach var="indicator" items="${resource.indicators}">
					<span>${indicator}</span>
				</c:forEach>
			</div>

			<a href="<spring:url value="/resource/add"/>" class="btn btn-default">Edit</a>
			<a href="<spring:url value="/resource/save"/>" class="btn btn-default">Save</a>
		</div>
	</div>
</body>
</html>
