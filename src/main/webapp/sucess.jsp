<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Sucesso!</h2>
<%
    String name=(String)request.getParameter("name");
    String email=(String)request.getParameter("email");
%>
<%=name %>

<%=email %>
</body>
</html>
