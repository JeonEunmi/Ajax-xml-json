<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	//절대경로 확인
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>쌍용교육센터</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	//XML to JSON
	//http://www.utilities-online.info/xmltojson

	//JSON 유효성 검사
	//https://jsonlint.com

	//XML 형식
	/*
	 <?xml version="1.0" encoding="UTF-8" ?>
	 <employees>
		 <employee mid="1">
			 <firstName>John</firstName>
			 <lastName>Doe</lastName>
		 </employee>
		 <employee mid="2">
			 <firstName>Anna</firstName>
			 <lastName>Smith</lastName>
		 </employee>
		 <employee mid="3">
			 <firstName>Peter</firstName>
			 <lastName>Jones</lastName>
		 </employee>
	 </employees>
	 */

	//JSON 형식
	/*
	 {"employees": {
	 	"employee": [{"-mid": "1",	"firstName": "John", "lastName": "Doe"}
	 		,{"-mid": "2", "firstName": "Anna", "lastName": "Smith"}
	 		,{"-mid": "3", "firstName": "Peter", "lastName": "Jones"}
	 	]}
	 }
	 */

	$(document).ready(function() {
		var text = '{"employees": {"employee": [{"-mid": "1","firstName": "John","lastName": "Doe"},{"-mid": "2","firstName": "Anna", "lastName": "Smith"}, {"-mid": "3", "firstName": "Peter", "lastName": "Jones"}]}}';

		console.log(text);

		var json = JSON.parse(text);
		var result = "";

		//방법1
		/* 
		for (var a=0; a<json.employees.employee.length; ++a) 
		{
			var employee = json.employees.employee[a];
			result += employee["-mid"] 
					+ " / " + employee.firstName 
					+ " / " + employee.lastName + "<br>";
		}
		 */

		//방법2
		/* 
		for (var a in json.employees.employee) {
			var employee = json.employees.employee[a];
			result += employee["-mid"] 
					+ " / " + employee.firstName 
					+ " / " + employee.lastName + "<br>";
		}
		 */

		//방법3
		/* 
		$.each(json.employees.employee, function (a) {
			result += json.employees.employee[a]["-mid"] 
			+ " / " + json.employees.employee[a].firstName 
			+ " / " + json.employees.employee[a].lastName + "<br>";
		});
		 */

		//방법4
		$.each(json.employees.employee, function(a, emp) {
			result += emp["-mid"] + " / "
					+ emp.firstName + " / "
					+ emp.lastName + "<br>";
		});

		$("#demo").html(result);
	});
</script>


</head>
<body>

	<div class="container">

		<h1>JSON Test</h1>
		<div id="demo"></div>

	</div>

</body>
</html>