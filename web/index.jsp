<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyItems.am</title>

        <%
    User user = (User) session.getAttribute("user");
%>

<body>

<div style="width: 1000px;margin: 0 auto">

    <div>
        <%
            if (user != null) {%>
        Բարև Պողոս
        <a href="/item/add">Ավելացնել |</a>
        <a href="/item"> Իմ Հայտարարությունները |</a>
        <a href="/logout">Logout</a>

        <%} else {%>
        <div style="margin: 0 auto">

            <a href="/login">Լօգին</a>
            <a href="/user">|Գրանցում</a><br>
        </div>
        <br>

            <div style="margin: auto;">

                <a href="/item">Գլխավոր|</a>
                <a href="/">Ավտոմեքենաներ|</a>
                <a href="/">Տներ| </a>
                <a href="/">Կոմերցիոն|</a>
                <a href="/">Կահույք</a>

            </div>

        <%}%>

    </div>
</div>
</body>
</html>
