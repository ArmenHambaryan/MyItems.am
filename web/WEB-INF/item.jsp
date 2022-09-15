<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Item</title>
</head>
<body>

<%
    List<Item> items = (List<Item>) request.getAttribute("item");
%>

<table>
    <tr>

        <th>action</th>
    </tr>
        <%for (Item item : items) { %>

    <tr>
        <td>
                <%if (item.getPicUrl() == null || item.getPicUrl().length() == 0) {%>
            <img src="../defold/img.png" width="100">

                <%} else { %>
            <img src="/getImage?picUrl=<%=item.getPrice()%>" width="100">
                <% }%><br>

                <%=item.getTitle()%><br>
                <%=item.getPrice()%><br>

                <% if (item.getCategory()!=null) {%>
                <%=item.getCategory().getName()%>
                <%}%>
                <%}%>
</body>
</html>
