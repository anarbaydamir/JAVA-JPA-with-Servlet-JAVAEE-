<%@ page import="com.company.dao.inter.UserDaoInter" %>
<%@ page import="com.company.main.Context" %>
<%@ page import="com.company.entity.User" %>
<%@ page import="com.company.resume.controller.UserRequestUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: qwant
  Date: 4/5/20
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
    <%
//        User u = null;
//        try {
//            u = UserRequestUtil.processRequest(request,response);
//        }
//        catch (Exception ex) {
//            response.sendRedirect("error.jsp?msg="+ex.getMessage());
//            return;
//        }

        User u = (User) request.getAttribute("user");
    %>
<form method="post" action="userdetail">
    <div class="container">
    <div class="row">
    <div class="col-sm-4">
        <input type="hidden" name="id" value="<%=u.getId()%>"/>
    <input type="hidden" name="action" value="update"/>
    <div class="form-group">
        <label for="name">name:</label>
        <input type="text" class="form-control" name="name" value="<%=u.getName()%>"/>
    </div>
    <div class="form-group">
        <label for="surname">surname:</label>
        <input type="text" class="form-control" name="surname" value="<%=u.getSurname()%>"/>
    </div>
    <div class="form-group">
        <label for="surname">email:</label>
        <input type="text" class="form-control" name="email" value="<%=u.getEmail()%>"/>
    </div>
    <div class="form-group">
        <label for="surname">phone number:</label>
        <input type="text" class="form-control" name="phone" value="<%=u.getPhoneNumber()%>"/>
    </div>
    <input type="submit" class="btn btn-primary" name="save" value="Save"/>
    </div>
    </div>
    </div>
</form>
</body>
</html>
