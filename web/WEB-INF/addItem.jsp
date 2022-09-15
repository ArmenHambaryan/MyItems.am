<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%
        HttpSession session1 = request.getSession();
        User user = (User) session1.getAttribute("user");
        List<Category> categories = (List<Category>) request.getAttribute("category");
    %>
</head>
<body>
<br>

<form action="/item/add" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="input only title"><br>
    <input type="number" name="price" placeholder="input only price"><br>

    <select name="categoryId">
        <%for (Category category : categories) {%>
        <option value="<%=category.getId()%>"><%=category.getName()%>
        </option>
        <%}%>
    </select>

    <br>
    <input type="file" name="picUrl"><br>
    <input type="submit" value="ok">
</form>

</body>
</html>
