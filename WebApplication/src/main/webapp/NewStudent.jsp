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
	if (session.getAttribute("user")==null)
		{  response.sendRedirect("index.html");}
	%>



<nav  style="background-color:#3d3d5c; padding: 10px;">
 	
   		<button class="btn btn-outline-success me-2"><a href="<%=request.getContextPath()%>/Home" style="text-decoration:none; color:white;"> Home </a></button>
   		<button class="btn btn-primary"> <a href="<%=request.getContextPath()%>/NewProfessor" style="text-decoration:none; color:white;"> New Professor </a> </button>
   		<button class="btn btn-primary"><a href="<%=request.getContextPath()%>/NewStudent" style="text-decoration:none; color:white;"> New Student </a></button>
   	   	<button class="btn btn-primary"><a href="<%=request.getContextPath()%>/NewCourse" style="text-decoration:none; color:white;"> New Course </a></button>
   	 	<button class="btn btn-danger"><a href="<%=request.getContextPath()%>/Logout" style="text-decoration:none; color:white;"> Logout </a></button>
   	
</nav>

<div class="container"> 
	<div class="row"> 
		<div class="col-md-6"> 
			<div class="card"> 
			<form action="CreateStudent" class="box" method="POST"> 
				<h1>Student Registration</h1> 
				<br>
				<input type="text" name="id" pattern="[0-9]+" placeholder="Id" required>
				<input type="text" name="name" placeholder="Name" required> 
				<input type="text" name="surname" placeholder="Surname" required> 
				<input type="text" name="department" placeholder="Department" required> 
				<input type="text" name="username" placeholder="Username" required> 
				<input type="password" name="password" placeholder="Password" required> 
				<br>
				<input type="submit" value="Register" > 
				
			</form> 
	</div> 
		</div> 
	</div>
</div>

</body>
</html>