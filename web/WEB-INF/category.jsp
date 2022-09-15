<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>category page</title>
</head>
<body>


<%
    List<Category> categories = (List<Category>) request.getAttribute("authors");

%>

<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>action</th>
    </tr>
    <%
        for (Category category : categories) { %>
        </td>
        <td><%=category.getId()%>
        </td>
        <td><%=category.getName()%>
        <td>
            <a href="/authors/remove?authorId=<%=category.getId()%>">Remove</a> |
            <a href="/authors/edit?authorId=<%=category.getId()%>">Edit</a>

    <% }%>


</table>

</body>
</html>
