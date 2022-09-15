<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login page</title>
</head>
<body>
<%
    String msg = (String) request.getAttribute("msg");

%>
<%
    if (msg != null) {%>

<p style="color: red"><%=msg%></p>
<%}%>
<form action="/login" method="post">
    <input type="email" name="email" placeholder="please input email"><br>
    <input type="password" name="password" placeholder="please input password"><br>
    <input type="submit" name="login">

</form>

</body>
</html>
