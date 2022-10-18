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
 	
 	
   		<button class="btn btn-outline-success me-2"><a href="<%=request.getContextPath()%>/StudentServlet" style="text-decoration:none; color:white;"> Grades </a></button>   	    
		   	  
		 <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
	   			 Semesters
	 		</button>
		  
		 <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
		  	<c:forEach var="semester" items="${semesters}">
		   		<li><a style="text-decoration:none; width : 3px;" href="<%=request.getContextPath()%>/Semester?id=<c:out value='${semester}' />"> ${semester} </a></li>
			</c:forEach>
		  </ul>
		 <button class="btn btn-danger"><a href="<%=request.getContextPath()%>/LogoutStudent" style="text-decoration:none; color:white;"> Logout </a></button>
		
   	
</nav>

<div class="container" style="margin-top:60px;"> 
	<div class="row"> 
		<div class="col-md-13"> 
			<div class="card"> 
			
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Course</th>
							<th>Exam Grade</th>
							<th>Projects Grade</th>
							<th>Tests Grade</th>
							<th>Course Grade</th>
							<th>Semester</th>
						</tr>
					</thead>
					<tbody>
						 <c:set var = "i" scope = "page" value = "0"/>
						<c:forEach var="grade" items="${grades}">
	
							<tr >
								<td><c:out value="${studC[i].name}" /></td>
								<td ><c:out value="${grade.examgrade}" /></td>
								<td ><c:out value="${grade.projectgrade}" /></td>
								<td><c:out value="${grade.testgrade}" /></td>
								<td><c:out value="${grade.finalgrade}" /></td>
								<td><c:out value="${studC[i].semester}" /></td>
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