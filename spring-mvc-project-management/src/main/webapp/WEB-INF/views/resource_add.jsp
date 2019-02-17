<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Manager</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<spring:url value="/css/bootstrap-select.min.css"/>" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="<spring:url value="/js/bootstrap-select.min.js"/>"></script>

<script>var ctx = "${pageContext.request.contextPath}"</script>
<script src="<spring:url value="/js/resource.js"/>"></script>

<link rel="stylesheet" href="<spring:url value="/css/style.css"/>" type="text/css"/>

</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">
	
		<div class="row">
			<h1>Resource</h1>
		</div>
		
		<spring:url value="/resource/review" var="formUrl"/>
		<form:form action="${formUrl}" method="POST" modelAttribute="resource">
			
			<div class="row">
				
				<div class="form-group">
					<label for="resource-name">Name</label>
					<form:input path="name" cssClass="form-control" id="resource-name"/>
					<form:errors path="name"/>
				</div>

				<div class="form-group">
					<label for="resource-type">Type</label>
					<form:select path="type" items="${typeOptions}" cssClass="selectpicker" id="resource-type"/>
				</div>

				<div class="form-group">
					<label for="cost">Cost</label>
					<form:input id="cost" cssClass="form-control" path="cost" />
				</div>

				<div class="form-group">
					<label for="unitOfMeasure">Unit of Measure</label>
					<form:radiobuttons path="unitOfMeasure"  items="${radioOptions}"/>
				</div>
				
				<div class="form-group">
					<label for="indicators">Indicators</label>
					<form:checkboxes path="indicators"  items="${checkOptions}"/>
					<a id="request-link" href="<spring:url value="/resource/request"/>">Send Request</a>
				</div>
				
				<div class="form-group">
					<label for="notes">Notes</label>
					<form:textarea path="notes" cssClass="form-control" rows="3"/>
				</div>
				
				<button type="submit" class="btn btn-default">Submit</button>

			</div>
		
		</form:form>
		
		<br/>
		
		<spring:url value="/resource/upload" var="uploadURL"/>
		<form method="POST" action="${uploadURL}" enctype="multipart/form-data">
			<div class="form-group">
				<label for="file">File to upload:</label>
				<input type="file" name="file" class="form-control" />
				<br/>
				<input type="submit" value="Upload">	
			</div>
		</form>
		
	</div>
</body>
</html>