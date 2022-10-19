<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Course</title>
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
			<form action="CreateCourse" name="myForm" onsubmit="return validateForm()" class="box" method="POST"> 
				<h1>New Course</h1> 
				<input type="text" name="id"  placeholder="Id" maxlength="5" size="5" minlength="5" required>
				<input type="text" name="name" placeholder="Name" required> 
				<input type="text" id="project" name="project_factor" placeholder="Project Factor" oninput="validateNumber(this);" required> 
				<input type="text" id="test" name="test_factor" placeholder="Test Factor" oninput="validateNumber(this);" required> 
				<input type="text" id="exam" name="exam_factor" placeholder="Exam Factor" oninput="validateNumber(this);" required> 
				<input type="text" name="direction" placeholder="Direction" required> 
				<input type="text" name="department" placeholder="Department" required> 
	            <input type="text" name="semester" placeholder="Semester (A-Z)" maxlength="1" size="1" minlength="1" required>
				<textarea  name="about" rows="4" cols="40" placeholder="Course information"></textarea>
				<textarea  name="books" rows="4" cols="40" placeholder="Books"></textarea>
				
				<br>
					<input type="submit" value="Create" > 
				</form> 
			</div> 
		</div> 
	</div>
</div>

	<script>
		var validNumber = new RegExp(/^\d*\.?\d*$/);
		var lastValid = document.getElementById("project").value;
		var lastValid2 = document.getElementById("test").value;
		var lastValid3 = document.getElementById("exam").value;
		function validateNumber(elem) {
		  if (validNumber.test(elem.value)) {
		    lastValid = elem.value;
		    lastValid2=elem.value;
		    lastValid3=elem.val
		  } else {
		    elem.value = lastValid;
		  }
		}
		
	</script>
	
	<script>
	function validateForm() {
		  let project = Number(document.forms["myForm"]["project_factor"].value);
		  let test = Number(document.forms["myForm"]["test_factor"].value);
		  let exam = Number(document.forms["myForm"]["exam_factor"].value);
		  let results=project+test+exam;
		  if (results!=100) {
		    alert("The sum of factors of the course must be equals to 100");
		    return false;
		  }
		}
	</script>


</body>
</html>