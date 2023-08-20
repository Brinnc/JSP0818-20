<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{margin:3px;}
</style>
<script type="text/javascript">
	//일반적인 폼이 아닌 바이너리 파일이 포함된 폼을 서버로 전송함 -> 업로드
	//업로드는 절대 GET방식으로는 불가함. 오직 POST방식만 가능.
	function upload(){
		form1.encoding="multipart/form-data"; //html에서는 enctype
		
		//폼을 서버로 전송
		form1.submit();
	}
</script>
</head>
<body>
<pre>
	<form name="form1" method="post" action="/board/upload.jsp">
	<input type="text" name="title" placeholder="제목 입력">
	<input type="file" name="photo">
	<button onClick="upload()">업로드</button>
	</form>
</pre>
</body>
</html>