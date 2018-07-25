<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册失败</title>
</head>
<script type="text/javascript">
　　　　　　　　var time = 3; //时间,秒
　　　　　　　　function Redirect() {
 　　　　　　　　window.location = "${pageContext.request.contextPath}/register.jsp";
　　　　　　　　}
　　　　　　　　var i = 0;
　　　　　　　　function dis() {
 　　　　　　　　document.all.s.innerHTML = "还剩" + (time - i) + "秒";
 　　　　　　　　i++;
　　　　　　　　}
　　　　　　　　timer = setInterval('dis()', 1000); //显示时间
　　　　　　　　timer = setTimeout('Redirect()', time * 1000); //跳转
　　　　　　</script>
<body>
	<h1 style="align: center">不好意思，注册失败，请重新注册</h1>
</body>
</html>