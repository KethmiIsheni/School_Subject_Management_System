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
			style="background-color: #3C69E7">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> School Information Management System </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Subjects</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${subject != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${subject == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${subject != null}">
            			Edit Subjects
            		</c:if>
						<c:if test="${subject == null}">
            			Add New subjects
            		</c:if>
					</h2>
				</caption>

				<c:if test="${subject != null}">
					<input type="hidden" name="subID" value="<c:out value='${subject.subID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label> Subject Name</label> <input type="text"
						value="<c:out value='${subject.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>No of Credits</label> <input type="text"
						value="<c:out value='${subject.credit}' />" class="form-control"
						name="credit">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
				
			</div>
		</div>
	</div>

</body>
</html>