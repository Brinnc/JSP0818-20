<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], input[type=file], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){ //문서가 로드되면, 버튼들에 이벤트 연결
		$("#bt_list").click(function(){
			//location.href="";
			$(location).attr("href", "/gallery/list.jsp");
		});
	
		$("#bt_regist").click(function(){
			//location.href="";
			$("form").attr({
				action: "/gallery/regist", //서블릿매핑 주소
				method: "post",
				enctype: "multipart/form-data" //js encoding
			});
			
			$("form").submit();
		});
		
	});
</script>
</head>
<body>

	<div class="container">
		<form>
			<input type="text" name="title" placeholder="Title is.."> 
			<input type="text" name="writer" placeholder="Writer is..">
			<textarea id="content" name="content" placeholder="Write something.." style="height: 200px"></textarea>
			<input type="file" name="filename" placeholder="Your last name..">
			
			<input type="button" id="bt_list" value="리스트">
			<input type="button" id="bt_regist" value="등록">
		</form>
	</div>

</body>
</html>
