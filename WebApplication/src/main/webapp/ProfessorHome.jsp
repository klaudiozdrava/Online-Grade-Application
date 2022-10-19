<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="login.css" />
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

</head>
<body>

<% 
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
	response.setHeader("Pragma","no-cache");//HTTP 1.0
	response.setHeader("Expires", "0");
	if (session.getAttribute("user")==null)
		{  response.sendRedirect("index.html");}
	%>


<nav  style="background-color:#3d3d5c; padding: 10px;">
 	
   		<button class="btn btn-outline-success me-2">
   			<a href="<%=request.getContextPath()%>/ProfessorHome" style="text-decoration:none; color:white;"> Home </a>
   		</button>
   	
   		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
   			 Show Grades
 		</button>
		   <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
		  	<c:forEach var="course" items="${courses}">
		  		<li><a style="text-decoration:none; width : 3px;" href="<%=request.getContextPath()%>/Course?id=<c:out value='${course.cid}' />"> ${course.name} </a></li>
		 	</c:forEach>
		  </ul>
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
   			 Assign Grades
 		</button>
		   <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
		  	<c:forEach var="course" items="${courses}">
		  		<li><a style="text-decoration:none; width : 3px;" href="<%=request.getContextPath()%>/AssignGrade?id=<c:out value='${course.cid}' />"> ${course.name} </a></li>
		 	</c:forEach>
		  </ul>
		  
		<button class="btn btn-danger">
   			<a href="<%=request.getContextPath()%>/ProfessorLogout" style="text-decoration:none; color:white;"> Logout </a>
   		</button>
   	
</nav>

<h1 style="text-align: center; color:white;">STUDENTS</h1>

<div class="container" style="margin-top:10px;"> 
	<div class="row"> 
		<div class="col-md-13"> 
			<div class="card"> 
			
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Student ID</th>
							<th>Student Name</th>
							<th>Student Surname</th>
							<th>Grade</th>
							<th>Exam</th>
							<th>Project</th>
							<th>Test</th>
							<th>Course Name</th>
							<th>Department</th>
							<th>Semester</th>
						</tr>
					</thead>
					<tbody>
						<c:set var = "i" scope = "page" value = "0"/>
						<c:forEach var="course" items="${courseData}">
	
							<tr >
								<td ><c:out value="${students[i].registrationNumber}" /></td>
								<td ><c:out value="${students[i].name}" /></td>
								<td ><c:out value="${students[i].surname}" /></td>
								<td ><c:out value="${grades[i].finalgrade}" /></td>
							    <td ><c:out value="${grades[i].examgrade}" /></td>
								<td ><c:out value="${grades[i].projectgrade}" /></td>
								<td ><c:out value="${grades[i].testgrade}" /></td>
								<td ><c:out value="${course.name}" /></td>
								<td ><c:out value="${course.department}" /></td>
								<td ><c:out value="${course.semester}" /></td>
							</tr>
							 <c:set var = "i" scope = "page" value = "${i+1 }"/>
						</c:forEach>
			
					</tbody>
	
				</table>
			</div> 
		</div> 
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>