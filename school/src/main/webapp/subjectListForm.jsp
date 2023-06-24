<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SkyLine High School</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: rgb(0, 39, 54)">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> School Information Management System</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Subjects</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Subjects</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New subject</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Subject ID</th>
						<th>Name</th>
						<th>Credit</th>
						
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="subject" items="${listsubject}">

						<tr>
							<td><c:out value="${subject.subID}" /></td>
							<td><c:out value="${subject.name}" /></td>
							<td><c:out value="${subject.credit}" /></td>
							
							<td><a href="edit?subID=<c:out value='${subject.subID}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?subID=<c:out value='${subject.subID}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>

</body>
</html>