<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assign</title>
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

<h1 style="text-align: center; color:white;">PROFESSORS</h1>

<div class="container" style="margin-top:10px;"> 
	<div class="row"> 
		<div class="col-md-13"> 
			<div class="card"> 
			
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Surname</th>
							<th>Department</th>
							<th>About</th>
							<th>Achievements</th>
							<th>Actions</th>
							</tr>
					</thead>
					<tbody>
						
						<c:forEach var="professor" items="${all_professors}">

						<tr>
							<td><c:out value="${professor.id}" /></td>
							<td><c:out value="${professor.name}" /></td>
							<td><c:out value="${professor.surname}" /></td>
							<td><c:out value="${professor.department}" /></td>
							<td><c:out value="${professor.about}" /></td>
							<td><c:out value="${professor.achievements}" /></td>
							<td> 
							<a href="<%=request.getContextPath()%>/Assigned?id=<c:out value='${professor.id}' />">Assign It</a>
							
							<!--  <form action="Secretary" method="get">
									<input type="hidden"  name="prof" value="${professor.id}">
									<input type="submit" class="btn btn-primary" value="Assign" > 
								 </form> 
								-->
							</td>
						</tr>
					</c:forEach>
			
					</tbody>
	
				</table>
				
			
			
			</div> 
		</div> 
	</div>
</div>



</body>
</html>