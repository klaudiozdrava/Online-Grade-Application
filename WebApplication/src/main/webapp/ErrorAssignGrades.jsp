<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Professor Registration</title>
<link rel="stylesheet" href="login.css" />
<!-- CSS only -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

</head>
<body>

	<% 
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
		response.setHeader("Pragma","no-cache");//HTTP 1.0
		response.setHeader("Expires", "0");
		if (request.getAttribute("Id")==null)
			{  response.sendRedirect("ProfessorHome");}
		else if(session.getAttribute("user")==null){
			response.sendRedirect("index.html");
		}
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
	
	<div class="container"> 
	<div class="row"> 
		<div class="col-md-6"> 
			<div class="card"> 
			<form action="Successfully" class="box" name="myForm"  method="POST"> 
				<h1>${Id}</h1> 
				<h2 style="color:red;">Student Registration Number was incorrect : No students has this number  </h2>
				<br>
				<input type="text" name="StudentId" pattern="[0-9]+" placeholder="Student Registration Number" required>
				<input type="text" name="ExamGrade" pattern="[0-9]+" placeholder="Exam" required>
				<input type="text" name="TestGrade" pattern="[0-9]+" placeholder="Test" required>
				<input type="text" name="ProjectGrade" pattern="[0-9]+" placeholder="Project" required>
				
				<input type="submit" value="Assign" > 
				
			</form> 
	</div> 
		</div> 
	</div>
</div>
	

</body>
</html>